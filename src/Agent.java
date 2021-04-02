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
	

	public Agent(List<Integer> position) {	
		x = position.get(0);
		y = position.get(1);
		vivant = true;
		listeActionsPossiblesPrio1 = new ArrayList<>();
		listeActionsPossiblesPrio2 = new ArrayList<>();
		listeActionsPossiblesPrio3 = new ArrayList<>();
		capteur = new Capteur();
	}
	
	/**
	 * Sequence des methodes permettant le fonctionnement de l'agent
	 * 
	 */
	protected void jouer() {
		if(this.capteur.isBrillant()) {		
			performance += 10*Foret.dimension*Foret.dimension;
			gagne = true;
		}
		if(this.capteur.isCrevasse()) {
			performance -= 10*Foret.dimension*Foret.dimension;
			vivant = false;
		}
		else {
			interpreterConnaissances();
			listerActionsPossibles();
			choisirAction();
			agir();
		}
	}

	/**
	 * Choisi l'action a realiser en fonction du contenu de l'effecteur
	 * issu de la prise de decision
	 * 
	 * (Declenche action(s))
	 * 
	 * @result Action(s) lancee(s)
	 */
	protected void agir() {
		if(effecteur != null) {
			switch(effecteur.getPriorite()) {
			case 1:
				deplacer(effecteur);
				break;
			case 2:
				tirer(effecteur);
				deplacer(effecteur);
				break;
			case 3:
				deplacer(effecteur);
				break;
			}
		}
		else {
			System.out.println("Aucune action trouvée");
		}
	}

	/**
	 * Deplace le joueur sur la case contenue dans l'effecteur
	 * 
	 * (Action deplacer)
	 * 
	 * @param:
	 * 		effecteur
	 * @result Joueur deplace
	 */
	protected void deplacer(Effecteur eff) {
		
		System.out.println("Se deplace");
		
		performance -= 1;
		
		for(Case caseCourrante : capteur.observerForet()) {
			if(caseCourrante.getPosition().get(0) == x
					&& caseCourrante.getPosition().get(1) == y) {
				caseCourrante.setJoueur(false);
			}
		}
		x = eff.getCaseCiblee().getPosition().get(0);
		y = eff.getCaseCiblee().getPosition().get(1);
		for(Case nouvelleCase: capteur.observerForet()) {
			if(nouvelleCase.getPosition().get(0) == x
					&& nouvelleCase.getPosition().get(1) == y) {
				nouvelleCase.setJoueur(true);
				capteur.setOdeur(nouvelleCase.isOdeur());
				capteur.setVent(nouvelleCase.isVent());
				capteur.setBrillant(nouvelleCase.isBrillante());
				for(Element elt : nouvelleCase.getContenu()) {
					if(elt instanceof Crevasse) {
						capteur.setCrevasse(true);
					}
				}
			}
		}
		eff.getCaseCiblee().setVisitee(true);
		if(!capteur.isCrevasse()) {
			List<Integer> temp = new ArrayList<>();
			temp.add(0);
			temp.add(0);
			eff.getCaseCiblee().setDanger(temp);
		}
	}

	/**
	 * Tire sur la case ciblee contenue dans l'effecteur
	 * 
	 * (Action tirer)
	 * 
	 * @param:
	 * 		effecteur
	 * @result Case videe de ses monstres
	 */
	protected void tirer(Effecteur eff) {
		
		System.out.println("Tire");

		performance -= 10;
		
		List<Element> nouveauContenu = new ArrayList<>();

		for(Case caseCible : capteur.observerForet()) {
			if(caseCible.getPosition().get(0) == eff.getCaseCiblee().getPosition().get(0)
					&& caseCible.getPosition().get(1) == eff.getCaseCiblee().getPosition().get(1)) {
				for(Element elt : caseCible.getContenu()) {
					if(elt instanceof Crevasse) {
						nouveauContenu.add(elt);
					}
				}
				caseCible.setContenu(nouveauContenu);
			}
		}
	}

	/**
	 * Determine l'action a realiser parmi celles possibles
	 * 
	 * (Prise de decision)
	 *
	 * @result Effecteur mis a jour
	 */
	protected void choisirAction() {

		Effecteur effecteurChoisi = new Effecteur(null, -1, false);
		//Parcours liste action de priorite 1
		if(listeActionsPossiblesPrio1.size() > 0) {
			//Choisir aleatoirement une case non visitee et sans danger
			int rand = (int) (Math.random()*listeActionsPossiblesPrio1.size());
			effecteurChoisi = listeActionsPossiblesPrio1.get(rand);
		}
		//Parcours liste action de priorite 2
		else if(listeActionsPossiblesPrio2.size() > 0) {
			//Choisir la case non visitee avec la plus grande chance de contenir un monstre
			Case caseForteChanceMonstre = new Case();
			List<Integer> temp = new ArrayList<>();
			temp.add(0);
			temp.add(0);
			caseForteChanceMonstre.setDanger(temp);
			for(int i = 0; i < listeActionsPossiblesPrio2.size(); i++) {
				if(listeActionsPossiblesPrio2.get(i).getCaseCiblee().getDanger().get(0) > caseForteChanceMonstre.getDanger().get(0)) {
					caseForteChanceMonstre = listeActionsPossiblesPrio2.get(i).getCaseCiblee();
					effecteurChoisi = listeActionsPossiblesPrio2.get(i);
				}
			}
		}
		//Parcours liste action de priorite 3
		else if(listeActionsPossiblesPrio3.size() > 0) {
			//Choisir la case non visitee la moins dangeureuse possible
			Case caseMoinsRisquee = new Case();
			List<Integer> temp = new ArrayList<>();
			temp.add(99);
			temp.add(99);
			caseMoinsRisquee.setDanger(temp);
			for(int i = 0; i < listeActionsPossiblesPrio3.size(); i++) {
				if(listeActionsPossiblesPrio3.get(i).getCaseCiblee().getDangerTotal() < caseMoinsRisquee.getDangerTotal()) {
					caseMoinsRisquee = listeActionsPossiblesPrio3.get(i).getCaseCiblee();
					effecteurChoisi = listeActionsPossiblesPrio3.get(i);
				}
			}			
		}
		effecteur = effecteurChoisi;
	}

	/**
	 * Determine tout ce qu'il est possible, en classant les actions possibles 
	 * par ordre de priorite.
	 *  
	 * (Interpretations des faits)
	 * 
	 * @result Actions possibles mises a jour
	 */
	protected void listerActionsPossibles() {

		List<Effecteur> actionsPossiblesPrio1 = new ArrayList<>();
		List<Effecteur> actionsPossiblesPrio2 = new ArrayList<>();
		List<Effecteur> actionsPossiblesPrio3 = new ArrayList<>();
		
		for(Case caseNonVisitee : capteur.observerForet()) {
			if(!caseNonVisitee.isVisitee()
					&& caseNonVisitee.getDangerTotal() == 0) {
				Effecteur effecteur = new Effecteur(caseNonVisitee, 1, false);
				actionsPossiblesPrio1.add(effecteur);
			}
			//Prio numero 2 : Tirer sur les monstres là ou pas de crevasse pour ensuite s'y rendre
			if(!caseNonVisitee.isVisitee()
					&& caseNonVisitee.getMonstre() == 2
					&& caseNonVisitee.getCrevasse() == 0) {
				Effecteur effecteur = new Effecteur(caseNonVisitee, 2, true);
				actionsPossiblesPrio2.add(effecteur);
			}
			//Prio numero 3 : Choisir la case non visitee la moins dangereuse
			if(!caseNonVisitee.isVisitee()) {
				Effecteur effecteur = new Effecteur(caseNonVisitee, 3, false);
				actionsPossiblesPrio3.add(effecteur);
			}
		}
		
		listeActionsPossiblesPrio1 = actionsPossiblesPrio1;
		listeActionsPossiblesPrio2 = actionsPossiblesPrio2;
		listeActionsPossiblesPrio3 = actionsPossiblesPrio3;
	}

	/**
	 * Itere l'etude de case sur l'ensemble des cases non visitees
	 * de l'enrionnement.
	 * 
	 * (Mise a jour des faits)
	 * 
	 * @result Dangers des cases mis a jour
	 */
	protected void interpreterConnaissances() {
		for(Case caseNonVisitee : capteur.observerForet()) {
			if(!caseNonVisitee.isVisitee()) {
				etudierAdjacence(caseNonVisitee);
			}			
		}
	}
	
	/**
	 * Met a jour l'etat de la case etudiee en fonction
	 * de ce que l'agent sait sur les cases adjacentes 
	 * de cette case etudiee.
	 * 
	 * (Mise a jour des faits unitairement)
	 * 
	 * @param:
	 * 		caseEtudiee
	 * @result Danger de la case etudiee mis a jour
	 */
	public void etudierAdjacence(Case caseEtudiee) {
		
		caseEtudiee.initialiserCasesAdjacentes();
		int compteurOdeur = 0;
		int compteurVent = 0;
		int compteurCaseVisitee = 0;
		int compteur =1;
		//Incremente compteur odeur/vent/caseVisitees
		for(Case caseTraitee : caseEtudiee.getCasesAdjacentes()) {
			compteur++;
			if(caseTraitee.isValable()) {
				//Valable
				if(caseTraitee.isVisitee()) {					
					//Valable ET visitee
					compteurCaseVisitee++;
					if(caseTraitee.isOdeur()) {
						//Valable ET visitee ET odeur
						compteurOdeur++;
					}
					if(caseTraitee.isVent()) {
						//Valable ET visitee ET vent
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
			List<Integer> temp = new ArrayList<>();
			temp.add(1);
			temp.add(1);
			caseEtudiee.setDanger(temp);
		}
		//Regle 2 : 
		//Si les cases connues de la périphérie d'une case non visitee
		//		contiennent  toutes des odeurs,
		//Alors il y a potentiellement un ou des monstres dans cette case non visitee
		if(compteurOdeur == compteurCaseVisitee && compteurOdeur != 0) {
			//Presence potentiel d'un monstre
			caseEtudiee.setMonstre(2);
			//Degre de danger en focntion nu nombre de cases odeur/vent trouvees
			List<Integer> temp = new ArrayList<>();
			temp.add(compteurOdeur);
			temp.add(compteurVent);
//			caseEtudiee.setDanger(Math.max(compteurVent, compteurOdeur));
			caseEtudiee.setDanger(temp);
		}
		//Regle 3 :
		//Si les cases connues de la peripheries ne sont pas toutes "Odeur"
		//Alors il n'y a pas de monstre
		if(compteurOdeur != compteurCaseVisitee) {
			caseEtudiee.setMonstre(0);
		}
		//Regle 4 : 
		//Si les cases connues de la périphérie d'une case non visitee
		//		contiennent toutes du vent, 
		//Alors il y a potentiellement une ou des crevasses dans cette case non visite
		if(compteurVent == compteurCaseVisitee && compteurVent != 0) {
			caseEtudiee.setCrevasse(2); //Crevasse Potentiel
			List<Integer> temp = new ArrayList<>();
			temp.add(compteurOdeur);
			temp.add(compteurVent);
			caseEtudiee.setDanger(temp);			
//			caseEtudiee.setDanger(Math.max(compteurVent, compteurOdeur));			
		}
		//Regle 5 :
		//Si les cases connues de la peripheries ne sont pas toutes "Vent"
		//Alors il n'y a pas de crevasse
		if(compteurVent != compteurCaseVisitee) {
			caseEtudiee.setCrevasse(0);
		}
		//Regle 6 : 
		//Si la case non visitee n'a ni de monstre potentiel, ni de crevasse 
		//potentielle, 
		//Alors elle est vide
		if(caseEtudiee.getCrevasse() == 0 && caseEtudiee.getMonstre() == 0) {
			caseEtudiee.setVide(true);
			List<Integer> temp = new ArrayList<>();
			temp.add(0);
			temp.add(0);
			caseEtudiee.setDanger(temp);
		}

	}

	//Getters / Setters
	
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
