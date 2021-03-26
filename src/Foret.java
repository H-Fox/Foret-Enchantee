import constantes.EtatsCases;

public class Foret {

	protected Case[][] grille;
	protected static int dimension = 3;

	public Foret() {
		grille = new Case[dimension][dimension];
		initialiserGrille();
		dimension++;
	}
	
	/**
	 * Initialise la carte en placant le joueur et le portail
	 * 
	 * @result Grille avec joueur et portail places
	 */
	private void initialiserGrille() {
		//Placer joueur
		int rand1 = (int) Math.random()*Foret.dimension;
		int rand2 = (int) Math.random()*Foret.dimension;
		
		grille[rand1][rand2].setEtat(EtatsCases.JOUEUR);
		
		//Placer portail
		rand1 = (int) Math.random()*Foret.dimension;
		rand2 = (int) Math.random()*Foret.dimension;
		
		grille[rand1][rand2].setEtat(EtatsCases.PORTAIL);
	}

}
