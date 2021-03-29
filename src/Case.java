import java.util.ArrayList;
import java.util.List;

import constantes.EtatsCases;

public class Case {
	
	private int etat = EtatsCases.VIDE;
	private List<Element> contenu;
	
	private boolean joueur;
	private boolean brillante;
	private boolean visitee;
	private boolean valable;
	private boolean odeur;
	private boolean vent;
	private int monstre; // 0 - pas de monstre / 1 - monstre / 2 - monstre potentiel
	private int crevasse;// 0 - pas de crevasse / 1 - crevasse / 2 - crevasse potentielle
	
	private List<Integer> position;

	public Case() {
		odeur = false;
		vent = false;
		monstre = 0;
		crevasse = 0;
		valable = true;
		contenu = new ArrayList<>();
		joueur = false;
		brillante = false;
		visitee = false;
		position = new ArrayList<>();
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
	
	public void setVide() {
		monstre = 0;
		crevasse = 0;
	}
	
}
