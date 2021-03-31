import java.util.ArrayList;
import java.util.List;

import constantes.Directions;
import constantes.EtatsCases;

public class Case {

	private Foret foret;

	private int etat = EtatsCases.VIDE;
	private List<Element> contenu;
	private int danger;
	private boolean joueur;
	private boolean brillante;
	private boolean visitee;
	private boolean valable;
	private boolean odeur;
	private boolean vent;
	private boolean vide = false;
	private int monstre; // 0 - pas de monstre / 1 - monstre / 2 - monstre potentiel
	private int crevasse;// 0 - pas de crevasse / 1 - crevasse / 2 - crevasse potentielle

	private List<Case> casesAdjacentes;

	private List<Integer> position;

	public Case() {
		
		odeur = false;
		vent = false;
		monstre = 0;
		crevasse = 0;
		danger = 0;
		valable = true;
		contenu = new ArrayList<>();
		joueur = false;
		brillante = false;
		visitee = false;
		position = new ArrayList<>();
		casesAdjacentes = new ArrayList<>();
	}

	public void etudierAdjacence() {
//		
//		initialiserCasesAdjacentes();
//		int compteurOdeur = 0;
//		int compteurVent = 0;
//		int compteurCaseVisitee = 0;
//		for(Case caseTraitee : this.casesAdjacentes) {			
//			if(caseTraitee.isValable()) {
//				//Valable
//				if(caseTraitee.isVisitee()) {
//					//Valable ET visitee
//					compteurCaseVisitee++;
//					if(caseTraitee.isOdeur()) {
//						compteurOdeur++;
//					}
//					if(caseTraitee.isVent()) {
//						compteurVent++;
//					}
//				}
//			}
//		}
//		//Regle 1 : Si les cases connues de la périphérie d'une case non visitee
//		//ne contiennent pas toutes des odeurs, alors il n'y a pas de monstre dans 
//		//cette case non visitee
//		//Sinon il y a potentiellement un monstre
//		if(compteurOdeur == compteurCaseVisitee) {
//			this.monstre = 2; //Monstre Potentiel
//			this.danger = Math.max(compteurVent, compteurOdeur);
//		}
//		else {
//			this.monstre = 0;
//		}
//		//Regle 2 : Si les cases connues de la périphérie d'une case non visitee
//		//ne contiennent pas toutes du vent, alors il n'y a pas de crevasse dans 
//		//cette case non visitee
//		//Sinon il y a potentiellement un crevasse
//		if(compteurVent == compteurCaseVisitee) {
//			this.crevasse = 2; //Crevasse Potentiel
//			this.danger = Math.max(compteurVent, compteurOdeur);
//		}
//		else {
//			this.crevasse = 0;
//		}
//		//Regle 3 : Si la case non visitee n'a ni de monstre potentiel, ni de crevasse 
//		//potentielle, alors elle est vide
//		if(this.crevasse == 0 && this.monstre == 0) {
//			this.vide = true;
//			this.danger = 0;
//		}
//		
	}
	
	public boolean isVide() {
		return vide;
	}

	public void setVide(boolean vide) {
		this.vide = vide;
	}

	protected void initialiserCasesAdjacentes() {
		
		List<Case> casesAdjacentes = new ArrayList<>();
		
		for(int i = 0; i < 4; i++) {
			
			casesAdjacentes.add(new Case());
			
			if(i == Directions.BAS) {
				if (this.getPosition().get(1) == Foret.dimension - 1) {			
					casesAdjacentes.get(Directions.BAS).setValable(false);	
				}
				else {
					casesAdjacentes.set(Directions.BAS, foret.grille[this.getPosition().get(0)][this.getPosition().get(1)+1]);
				}
			}
			if(i == Directions.HAUT) {
				if (this.getPosition().get(1) == 0) {			
					casesAdjacentes.get(Directions.HAUT).setValable(false);	
				}
				else {
					casesAdjacentes.set(Directions.HAUT, foret.grille[this.getPosition().get(0)][this.getPosition().get(1)-1]);
				}
			}
			if(i == Directions.GAUCHE) {
				if (this.getPosition().get(0) == 0) {			
					casesAdjacentes.get(Directions.GAUCHE).setValable(false);	
				}
				else {
					casesAdjacentes.set(Directions.GAUCHE, foret.grille[this.getPosition().get(0)-1][this.getPosition().get(1)]);
				}
			}
			if(i == Directions.DROITE) {
				if (this.getPosition().get(0) == Foret.dimension - 1) {			
					casesAdjacentes.get(Directions.DROITE).setValable(false);	
				}
				else {
					casesAdjacentes.set(Directions.DROITE, foret.grille[this.getPosition().get(0)+1][this.getPosition().get(1)]);
				}
			}
		}
	}

	public Foret getForet() {
		return foret;
	}

	public void setForet(Foret foret) {
		this.foret = foret;
	}

	public List<Case> getCasesAdjacentes() {
		return casesAdjacentes;
	}

	public void setCasesAdjacentes(List<Case> casesAdjacentes) {
		this.casesAdjacentes = casesAdjacentes;
	}



	public int getDanger() {
		return danger;
	}

	public void setDanger(int danger) {
		this.danger = danger;
	}

	public boolean isOdeur() {
		return odeur;
	}

	public void setOdeur(boolean odeur) {
		this.odeur = odeur;
	}

	public boolean isVent() {
		return vent;
	}

	public void setVent(boolean vent) {
		this.vent = vent;
	}

	public int getMonstre() {
		return monstre;
	}

	public void setMonstre(int monstre) {
		this.monstre = monstre;
	}

	public int getCrevasse() {
		return crevasse;
	}

	public void setCrevasse(int crevasse) {
		this.crevasse = crevasse;
	}

	public boolean isValable() {
		return valable;
	}

	public void setValable(boolean valable) {
		this.valable = valable;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public List<Element> getContenu() {
		return contenu;
	}

	public void setContenu(List<Element> contenu) {
		this.contenu = contenu;
	}

	public boolean isJoueur() {
		return joueur;
	}

	public void setJoueur(boolean joueur) {
		this.joueur = joueur;
	}

	public boolean isBrillante() {
		return brillante;
	}

	public void setBrillante(boolean portail) {
		this.brillante = portail;
	}

	public boolean isVisitee() {
		return visitee;
	}

	public void setVisitee(boolean visitee) {
		this.visitee = visitee;
	}

	public List<Integer> getPosition() {
		return position;
	}

	public void setPosition(List<Integer> position) {
		this.position = position;
	}	

}
