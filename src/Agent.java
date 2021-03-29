import java.util.ArrayList;
import java.util.List;

import constantes.Directions;

public class Agent extends Element{
	
	protected int x = -1;
	protected int y = -1;
	protected Case casePrecedente;
	
	protected int performance = 0;
	protected int nombreCasesParcourues = 0;
	
	protected List<Case> casesAdjacentes;
	
	public Agent() {
		casePrecedente = new Case();
		casesAdjacentes = new ArrayList<>();
	}
	
	protected void deplacer(int direction, Foret foret) {
		switch(direction) {
		case Directions.HAUT:
			foret.grille[x][y].setJoueur(false);
			y--;
			foret.grille[x][y].setJoueur(true);
			break;
		case Directions.BAS:
			foret.grille[x][y].setJoueur(false);
			y++;
			foret.grille[x][y].setJoueur(true);
			break;
		case Directions.DROITE:
			foret.grille[x][y].setJoueur(false);
			x++;
			foret.grille[x][y].setJoueur(true);
			break;
		case Directions.GAUCHE:
			foret.grille[x][y].setJoueur(false);
			x--;
			foret.grille[x][y].setJoueur(true);
			break;
			
		}
	}
	
	protected void trouverDirection(Foret foret) {
		
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
