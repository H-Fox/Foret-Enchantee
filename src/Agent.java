import java.util.ArrayList;
import java.util.List;

import constantes.Directions;

public class Agent extends Element{

	protected int x = -1;
	protected int y = -1;
	
	protected boolean vivant;

	protected Case casePrecedente;
	protected List<Case> casesConnues;

	protected int performance = 0;

	protected Effecteur effecteur;
	protected List<Effecteur> listeActionsPossiblesPrio1;
	protected List<Effecteur> listeActionsPossiblesPrio2;
	protected List<Effecteur> listeActionsPossiblesPrio3;
	protected Capteur capteur;

	protected Foret foret;

	public Agent(Foret foret) {
		vivant = true;
		listeActionsPossiblesPrio1 = new ArrayList<>();
		listeActionsPossiblesPrio2 = new ArrayList<>();
		listeActionsPossiblesPrio3 = new ArrayList<>();
		casePrecedente = new Case();
		capteur = new Capteur();
		casesConnues = new ArrayList<>();
		this.foret = foret;
	}
	
	protected void jouer() {
		if(this.capteur.isBrillant()) {
			vivant = false;
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

	protected void deplacer(Effecteur eff) {
		foret.grille[x][y].setJoueur(false);
		x = eff.getCaseCiblee().getPosition().get(0);
		y = eff.getCaseCiblee().getPosition().get(1);
		foret.grille[x][y].setJoueur(true);
		capteur.setOdeur(foret.grille[x][y].isOdeur());
		capteur.setVent(foret.grille[x][y].isVent());
		capteur.setBrillant(foret.grille[x][y].isBrillante());

		eff.getCaseCiblee().setVisitee(true);
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

	static void tri_bulle(int[] tab)
	{  
		int taille = tab.length;  
		int tmp = 0;  
		for(int i=0; i < taille; i++) 
		{
			for(int j=1; j < (taille-i); j++)
			{  
				if(tab[j-1] > tab[j])
				{
					//echanges des elements
					tmp = tab[j-1];  
					tab[j-1] = tab[j];  
					tab[j] = tmp;  
				}

			}
		}
	}

	protected void interpreterConnaissances() {

		for(int x = 0; x < Foret.dimension; x++) {
			for(int y = 0; y < Foret.dimension; y++) {
				if(!foret.grille[x][y].isVisitee()) {
					//foret.grille[x][y].etudierAdjacence();
					etudierAdjacence(foret.grille[x][y]);
				}
			}
		}
	}

	public void etudierAdjacence(Case caseEtudiee) {

		caseEtudiee.initialiserCasesAdjacentes();
		int compteurOdeur = 0;
		int compteurVent = 0;
		int compteurCaseVisitee = 0;
		for(Case caseTraitee : caseEtudiee.getCasesAdjacentes()) {			
			if(caseTraitee.isValable()) {
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
		//Regle 1 : Si les cases connues de la périphérie d'une case non visitee
		//ne contiennent pas toutes des odeurs, alors il n'y a pas de monstre dans 
		//cette case non visitee
		//Sinon il y a potentiellement un monstre
		if(compteurOdeur == compteurCaseVisitee) {
			caseEtudiee.setMonstre(2); //Monstre Potentiel
			caseEtudiee.setDanger(Math.max(compteurVent, compteurOdeur));
		}
		else {
			caseEtudiee.setMonstre(0);
		}
		//Regle 2 : Si les cases connues de la périphérie d'une case non visitee
		//ne contiennent pas toutes du vent, alors il n'y a pas de crevasse dans 
		//cette case non visitee
		//Sinon il y a potentiellement un crevasse
		if(compteurVent == compteurCaseVisitee) {
			caseEtudiee.setCrevasse(2); //Crevasse Potentiel
			caseEtudiee.setDanger(Math.max(compteurVent, compteurOdeur));
		}
		else {
			caseEtudiee.setCrevasse(0);
		}
		//Regle 3 : Si la case non visitee n'a ni de monstre potentiel, ni de crevasse 
		//potentielle, alors elle est vide
		if(caseEtudiee.getCrevasse() == 0 && caseEtudiee.getMonstre() == 0) {
			caseEtudiee.setVide(true);
			caseEtudiee.setDanger(0);
		}

	}

	public Case getCasePrecedente() {
		return casePrecedente;
	}

	public void setCasePrecedente(Case casePrecedente) {
		this.casePrecedente = casePrecedente;
	}

	public List<Case> getCasesConnues() {
		return casesConnues;
	}

	public void setCasesConnues(List<Case> casesConnues) {
		this.casesConnues = casesConnues;
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
