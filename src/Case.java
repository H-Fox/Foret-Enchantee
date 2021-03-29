import java.util.ArrayList;
import java.util.List;

import constantes.EtatsCases;

public class Case {
	
	private int etat = EtatsCases.VIDE;
	private List<Element> contenu;
	private boolean joueur;
	private boolean brillante;
	private boolean visitee;
	private List<Integer> position;

	public Case() {
		contenu = new ArrayList<>();
		joueur = false;
		brillante = false;
		visitee = false;
		position = new ArrayList<>();
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
