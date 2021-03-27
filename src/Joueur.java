import constantes.Directions;

public class Joueur extends Element{
	
	protected int x;
	protected int y;
	
	public Joueur() {
		
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
