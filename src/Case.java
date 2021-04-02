import java.util.ArrayList;
import java.util.List;

import constantes.Directions;

public class Case {

	//Environnement dont fait partie la case
	private Foret foret;
	//Liste contenant les elements (monstre/crevasse) de la case
	private List<Element> contenu;
	//Niveau de danger estime par l'agent de la case
	private int danger;
	//Variables booleennes d'etats de la case (visibles par l'agent une fois la case visitee)
	private boolean joueur;
	private boolean brillante;
	private boolean visitee;
	private boolean valable;
	private boolean odeur;
	private boolean vent;
	private boolean vide = false;
	//Variables d'etats mises a jour par ce que capte l'agent
	private int monstre; // 0 - pas de monstre / 1 - monstre / 2 - monstre potentiel
	private int crevasse;// 0 - pas de crevasse / 1 - crevasse / 2 - crevasse potentielle
	//Liste contenant les cases adjacentes à cette case
	private List<Case> casesAdjacentes;
	//Position de la case
	private List<Integer> position;

	public Case() {		
		odeur = false;
		vent = false;
		monstre = -1;
		crevasse = -1;
		danger = 1;
		valable = true;
		contenu = new ArrayList<>();
		joueur = false;
		brillante = false;
		visitee = false;
		position = new ArrayList<>();
		casesAdjacentes = new ArrayList<>();
	}
	


	protected void initialiserCasesAdjacentes() {
//		System.out.println("initAdja");
		List<Case> casesAdjacentes = new ArrayList<>();
		
		for(int i = 0; i < 4; i++) {
			
			casesAdjacentes.add(new Case());
			
			if(i == Directions.BAS) {
				if (this.getPosition().get(1) == Foret.dimension - 1) {	
					casesAdjacentes.get(Directions.BAS).setValable(false);	
				}
				else {
					casesAdjacentes.set(Directions.BAS, foret.grille[this.getPosition().get(0)][this.getPosition().get(1)+1]);
					System.out.println();
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
		
		this.casesAdjacentes = casesAdjacentes;
	}

	//Getters / Setters
	public Foret getForet() {
		return foret;
	}

	public void setForet(Foret foret) {
		this.foret = foret;
	}
	public boolean isVide() {
		return vide;
	}

	public void setVide(boolean vide) {
		this.vide = vide;
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
