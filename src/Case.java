import java.util.ArrayList;
import java.util.List;

import constantes.EtatsCases;

public class Case {
	
	private int etat = EtatsCases.VIDE;
	private List<Element> contenu;
	private boolean joueur;
	private boolean portail;

	public Case() {
		contenu = new ArrayList<>();
		joueur = false;
		portail = false;
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

	public boolean isPortail() {
		return portail;
	}

	public void setPortail(boolean portail) {
		this.portail = portail;
	}	
	
	
}
