import java.util.ArrayList;
import java.util.List;

import constantes.Directions;

public class Agent extends Element{

	//Position de l'agent dans la foret
	protected int x = -1;
	protected int y = -1;
	//Etats de l'agent
	protected boolean vivant;
	protected boolean gagne = false;
	//Score de performance de l'agent
	protected int performance = 0;
	//Composants de l'agent
	protected Effecteur effecteur;
	protected Capteur capteur;
	//Effecteurs potentiels par ordre de priorite
	protected List<Effecteur> listeActionsPossiblesPrio1;
	protected List<Effecteur> listeActionsPossiblesPrio2;
	protected List<Effecteur> listeActionsPossiblesPrio3;
	//Environnement de l'agent
	protected Foret foret;
	//Singleton
	protected static Agent instance;
	protected static Agent getInstance() {
		return instance;
	}
	

	public Agent(Foret foret) {
		
		vivant = true;
		listeActionsPossiblesPrio1 = new ArrayList<>();
		listeActionsPossiblesPrio2 = new ArrayList<>();
		listeActionsPossiblesPrio3 = new ArrayList<>();
		capteur = new Capteur();
		this.foret = foret;
		instance = this;
	}
	
	protected void jouer() {
		if(this.capteur.isBrillant()) {			
			gagne = true;
		}
		if(this.capteur.isCrevasse()) {
			vivant = false;
			gagne = true;
		}
		else {
			interpreterConnaissances();
			listerActionsPossibles();
			choisirAction();
			agir();
		}
	}

	protected void agir() {
		if(effecteur != null) {
			switch(effecteur.getPriorite()) {
			case 1:
//			System.out.println("Prio 1 : "+effecteur.getCaseCiblee().getPosition().toString());
				deplacer(effecteur);
				break;
			case 2:
//				System.out.println("Prio 2 : "+effecteur.getCaseCiblee().getPosition().toString());
				tirer(effecteur);
				deplacer(effecteur);
				break;
			case 3:
//				System.out.println("Prio 3 : "+effecteur.getCaseCiblee().getPosition().toString());
				deplacer(effecteur);
				break;
			}
		}
		else {
//			System.out.println("Aucune action trouvée");
		}
	}

	protected void deplacer(Effecteur eff) {
//		System.out.println("Dep : "+eff.getCaseCiblee().getPosition().toString());
		foret.grille[x][y].setJoueur(false);
		x = eff.getCaseCiblee().getPosition().get(0);
		y = eff.getCaseCiblee().getPosition().get(1);
		foret.grille[x][y].setJoueur(true);
		capteur.setOdeur(foret.grille[x][y].isOdeur());
		capteur.setVent(foret.grille[x][y].isVent());
		capteur.setBrillant(foret.grille[x][y].isBrillante());
		for(Element elt : foret.grille[x][y].getContenu()) {
			if(elt instanceof Crevasse) {
				capteur.setCrevasse(true);
			}
		}
		eff.getCaseCiblee().setVisitee(true);
		if(!capteur.isCrevasse()) {
			eff.getCaseCiblee().setDanger(0);
		}
	}

	protected void tirer(Effecteur eff) {

		List<Element> nouveauContenu = new ArrayList<>();

		for(Element elt : foret.grille[eff.getCaseCiblee().getPosition().get(0)][eff.getCaseCiblee().getPosition().get(1)].getContenu()) {
			if(elt instanceof Crevasse) {
				nouveauContenu.add(elt);
			}
		}
		foret.grille[eff.getCaseCiblee().getPosition().get(0)][eff.getCaseCiblee().getPosition().get(1)].setContenu(nouveauContenu);	
		foret.MAJForet();
	}

	protected void choisirAction() {

		Effecteur effecteurChoisi = new Effecteur(null, -1, false);

		if(listeActionsPossiblesPrio1.size() > 0) {
			//Choisir aleatoirement une case non visitee et sans danger
			int rand = (int) (Math.random()*listeActionsPossiblesPrio1.size());
			effecteurChoisi = listeActionsPossiblesPrio1.get(rand);
		}
		else if(listeActionsPossiblesPrio2.size() > 0) {
			//Choisir la case non visitee avec la plus grande chance de contenir un monstre
			Case caseForteChanceMonstre = new Case();
			caseForteChanceMonstre.setDanger(0);
			for(int i = 0; i < listeActionsPossiblesPrio2.size(); i++) {
				if(listeActionsPossiblesPrio2.get(i).getCaseCiblee().getDanger() > caseForteChanceMonstre.getDanger()) {
					caseForteChanceMonstre = listeActionsPossiblesPrio2.get(i).getCaseCiblee();
					effecteurChoisi = listeActionsPossiblesPrio2.get(i);
				}
			}
		}
		else if(listeActionsPossiblesPrio3.size() > 0) {
			//Choisir la case non visitee la moins dangeureuse possible
			Case caseMoinsRisquee = new Case();
			caseMoinsRisquee.setDanger(99);
			for(int i = 0; i < listeActionsPossiblesPrio3.size(); i++) {
				if(listeActionsPossiblesPrio3.get(i).getCaseCiblee().getDanger() < caseMoinsRisquee.getDanger()) {
					caseMoinsRisquee = listeActionsPossiblesPrio3.get(i).getCaseCiblee();
					effecteurChoisi = listeActionsPossiblesPrio3.get(i);
				}
			}
		}
		effecteur = effecteurChoisi;
	}

	protected void listerActionsPossibles() {

		List<Effecteur> actionsPossiblesPrio1 = new ArrayList<>();
		List<Effecteur> actionsPossiblesPrio2 = new ArrayList<>();
		List<Effecteur> actionsPossiblesPrio3 = new ArrayList<>();

		for(int i = 0; i < Foret.dimension; i++) {
			for(int j = 0; j < Foret.dimension; j++) {
				//Prio numero 1 : Les cases non visitees et vides
				if(!foret.grille[i][j].isVisitee()
						&& foret.grille[i][j].getDanger() == 0) {
					Effecteur effecteur = new Effecteur(foret.grille[i][j], 1, false);
					actionsPossiblesPrio1.add(effecteur);
				}
				//Prio numero 2 : Tirer sur les monstres là ou pas de crevasse pour ensuite s'y rendre
				if(!foret.grille[i][j].isVisitee()
						&& foret.grille[i][j].getMonstre() == 2
						&& foret.grille[i][j].getCrevasse() == 0) {
					Effecteur effecteur = new Effecteur(foret.grille[i][j], 2, true);
					actionsPossiblesPrio2.add(effecteur);
				}
				//Prio numero 3 : Choisir la case non visitee la moins dangereuse
				if(!foret.grille[i][j].isVisitee()) {
					Effecteur effecteur = new Effecteur(foret.grille[i][j], 3, false);
					actionsPossiblesPrio3.add(effecteur);
				}
			}
		} 
		listeActionsPossiblesPrio1 = actionsPossiblesPrio1;
		listeActionsPossiblesPrio2 = actionsPossiblesPrio2;
		listeActionsPossiblesPrio3 = actionsPossiblesPrio3;
	}

	protected void interpreterConnaissances() {

		for(int x = 0; x < Foret.dimension; x++) {
			for(int y = 0; y < Foret.dimension; y++) {
				if(!foret.grille[x][y].isVisitee()) {
					etudierAdjacence(foret.grille[x][y]);
				}
			}
		}
	}
	/**
	 * Met a jour l'etat de la case etudiee en fonction
	 * de ce que l'agent sait sur les cases adjacentes 
	 * de cette case etudiee.
	 * 
	 * (Mise a jour des faits)
	 * 
	 * @param:
	 * 		caseEtudiee
	 * @result Danger de la case etudiee mis a jour
	 */
	public void etudierAdjacence(Case caseEtudiee) {
		
		caseEtudiee.initialiserCasesAdjacentes();
//		System.out.println("EtudierAdja : "+caseEtudiee.getPosition().toString()+", "+caseEtudiee.getCasesAdjacentes().size());
		int compteurOdeur = 0;
		int compteurVent = 0;
		int compteurCaseVisitee = 0;
		int compteur =1;
		for(Case caseTraitee : caseEtudiee.getCasesAdjacentes()) {		
			//System.out.println("Case adjacente "+compteur+" : "+caseTraitee.getPosition().toString());
			compteur++;
			if(caseTraitee.isValable()) {
				//System.out.println("valable");
				//Valable
				if(caseTraitee.isVisitee()) {
					
					//Valable ET visitee
					compteurCaseVisitee++;
					if(caseTraitee.isOdeur()) {
						compteurOdeur++;
					}
					if(caseTraitee.isVent()) {
						compteurVent++;
					}
				}
			}
		}
		//Regles d'interpretation de l'environnement :
		
		//Regle 1 : 
		//Si une case n'est pas visitee, 
		//Alors elle est par conséquent potentiellement dangereuse.
		if(compteurCaseVisitee == 0) {
			caseEtudiee.setDanger(1);
		}
		//Regle 2 : 
		//Si les cases connues de la périphérie d'une case non visitee
		//		ne contiennent pas toutes des odeurs,
		//Alors il n'y a pas de monstre dans cette case non visitee
		//Sinon il y a potentiellement un monstre
		if(compteurOdeur == compteurCaseVisitee && compteurOdeur != 0) {
			//Presence potentiel d'un monstre
			caseEtudiee.setMonstre(2);
			//Degre de danger en focntion nu nombre de cases odeur/vent trouvees
			caseEtudiee.setDanger(Math.max(compteurVent, compteurOdeur));
		}
		//Regle 3 :
		//Si les cases connues de la peripheries ne sont pas toutes "Odeur"
		//Alors il n'y a pas de monstre
		if(compteurOdeur != compteurCaseVisitee) {
			caseEtudiee.setMonstre(0);
		}
		//Regle 4 : 
		//Si les cases connues de la périphérie d'une case non visitee
		//		ne contiennent pas toutes du vent, 
		//Alors il n'y a pas de crevasse dans cette case non visitee
		//Sinon il y a potentiellement un crevasse
		if(compteurVent == compteurCaseVisitee && compteurVent != 0) {
			caseEtudiee.setCrevasse(2); //Crevasse Potentiel
			caseEtudiee.setDanger(Math.max(compteurVent, compteurOdeur));			
		}
		if(compteurVent != compteurCaseVisitee) {
			caseEtudiee.setCrevasse(0);
		}
		//Regle 5 : 
		//Si la case non visitee n'a ni de monstre potentiel, ni de crevasse 
		//potentielle, 
		//Alors elle est vide
		if(caseEtudiee.getCrevasse() == 0 && caseEtudiee.getMonstre() == 0) {
			caseEtudiee.setVide(true);
			caseEtudiee.setDanger(0);
//			System.out.println("setDanger (!monstre && !crevasse)= 0, "+caseEtudiee.getPosition().toString()+", Danger : "+caseEtudiee.getDanger());
		}

	}

	public int getPerformance() {
		return performance;
	}

	public void setPerformance(int performance) {
		this.performance = performance;
	}

	public Effecteur getEffecteur() {
		return effecteur;
	}

	public void setEffecteur(Effecteur effecteur) {
		this.effecteur = effecteur;
	}

	public Capteur getCapteur() {
		return capteur;
	}

	public void setCapteur(Capteur capteur) {
		this.capteur = capteur;
	}

	public Foret getForet() {
		return foret;
	}

	public void setForet(Foret foret) {
		this.foret = foret;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
