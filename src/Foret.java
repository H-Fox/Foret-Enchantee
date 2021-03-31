import java.util.ArrayList;
import java.util.List;

import constantes.Directions;
import constantes.EtatsCases;

public class Foret {

	protected Case[][] grille;
	protected static int dimension = 2;
	protected Agent joueur;
	protected ForetGraphique affichage;

	public Foret() {
		dimension++;
		affichage = new ForetGraphique(dimension);
		System.out.println(dimension);
		joueur = new Agent(this);
		grille = new Case[dimension][dimension];
		for(int i = 0; i < dimension; i ++) {
			for(int j = 0; j < dimension; j ++) {
				grille[i][j] = new Case();
				grille[i][j].getPosition().add(i);
				grille[i][j].getPosition().add(j);
				grille[i][j].setForet(this);
			}
		}
		initialiserGrille();
	}
	


	/**
	 * Initialise la carte en placant le joueur et le portail
	 * 
	 * @result Grille avec joueur et portail places
	 */
	private void initialiserGrille() {
		//Placer joueur
		int x = (int) (Math.random()*Foret.dimension);
		int y = (int) (Math.random()*Foret.dimension);
		
		System.out.println("Placer joueur : "+x+", "+y);

		grille[x][y].setJoueur(true);
		grille[x][y].setVisitee(true);
		joueur.setX(x);
		joueur.setY(y);
		joueur.getCasesConnues().add(grille[x][y]);

		//Placer portail
		x = (int) (Math.random()*Foret.dimension);
		y = (int) (Math.random()*Foret.dimension);
		System.out.println("Placer portail : "+x+", "+y);

		grille[x][y].setBrillante(true);

		//Placer monstres et/ou crevasse
		int nombreCasesARemplir = (int) (Math.random()*(Foret.dimension*Foret.dimension)); //Nombre de cases à remplir entre 0 et dimension²
		//System.out.println("Nombre de cases a remplir : "+nombreCasesARemplir);
		for (int i = 0; i < nombreCasesARemplir; i++) {

			do {
				x = (int) (Math.random()*Foret.dimension);
				y = (int) (Math.random()*Foret.dimension);
				//System.out.println("Placer element : "+x+", "+y);
			}
			while(grille[x][y].isJoueur()
					|| grille[x][y].isBrillante());

			int choix = (int) (Math.random()*3);

			switch(choix) {
			case 0:
				//Placer un monstre	
				//System.out.println("Placer monstre : "+x+", "+y);
				grille[x][y].getContenu().add(new Monstre());
				grille[x][y].setMonstre(1);
				break;
			case 1:
				//Placer une crevasse	
				//System.out.println("Placer crevasse : "+x+", "+y);
				grille[x][y].getContenu().add(new Crevasse());
				grille[x][y].setCrevasse(1);
				break;
			}

		}

		//Placer vent et odeur
		MAJForet();
	}
	
	public void MAJForet() {
		
		//Placer vent et odeur
		for (int i = 0; i < Foret.dimension; i++) {
			for (int j = 0; j < Foret.dimension; j++) {
				for(Element element : grille[i][j].getContenu()) {
					if(element instanceof Monstre) {
						//Ajout odeur UP
						if(j-1 >= 0) {
							switch(grille[i][j-1].getEtat()) {
							case EtatsCases.VIDE:
								grille[i][j-1].setEtat(EtatsCases.ODEUR);
								break;
							case EtatsCases.VENT:
								grille[i][j-1].setEtat(EtatsCases.VENT_ODEUR);
								break;							
							}
						}						
						//Ajout odeur DOWN
						if(j+1 <= Foret.dimension-1) {
							switch(grille[i][j+1].getEtat()) {
							case EtatsCases.VIDE:
								grille[i][j+1].setEtat(EtatsCases.ODEUR);
								break;
							case EtatsCases.VENT:
								grille[i][j+1].setEtat(EtatsCases.VENT_ODEUR);
								break;	
							}
						}
						//Ajout odeur RIGHT
						if(i+1 <= Foret.dimension-1) {
							switch(grille[i+1][j].getEtat()) {
							case EtatsCases.VIDE:
								grille[i+1][j].setEtat(EtatsCases.ODEUR);
								break;
							case EtatsCases.VENT:
								grille[i+1][j].setEtat(EtatsCases.VENT_ODEUR);
								break;	
							}
						}
						//Ajout odeur LEFT
						if(i-1 >= 0) {
							switch(grille[i-1][j].getEtat()) {
							case EtatsCases.VIDE:
								grille[i-1][j].setEtat(EtatsCases.ODEUR);
								break;
							case EtatsCases.VENT:
								grille[i-1][j].setEtat(EtatsCases.VENT_ODEUR);
								break;
							}
						}
					}
					if(element instanceof Crevasse) {
						//Ajout vent UP
						if(j-1 >= 0) {
							switch(grille[i][j-1].getEtat()) {
							case EtatsCases.VIDE:
								grille[i][j-1].setEtat(EtatsCases.VENT);
								break;
							case EtatsCases.ODEUR:
								grille[i][j-1].setEtat(EtatsCases.VENT_ODEUR);
								break;
							}
						}						
						//Ajout vent DOWN
						if(j+1 <= Foret.dimension-1) {
							switch(grille[i][j+1].getEtat()) {
							case EtatsCases.VIDE:
								grille[i][j+1].setEtat(EtatsCases.VENT);
								break;
							case EtatsCases.ODEUR:
								grille[i][j+1].setEtat(EtatsCases.VENT_ODEUR);
								break;
							}
						}
						//Ajout vent RIGHT
						if(i+1 <= Foret.dimension-1) {
							switch(grille[i+1][j].getEtat()) {
							case EtatsCases.VIDE:
								grille[i+1][j].setEtat(EtatsCases.VENT);
								break;
							case EtatsCases.ODEUR:
								grille[i+1][j].setEtat(EtatsCases.VENT_ODEUR);
								break;
							}
						}
						//Ajout vent LEFT
						if(i-1 >= 0) {
							switch(grille[i-1][j].getEtat()) {
							case EtatsCases.VIDE:
								grille[i-1][j].setEtat(EtatsCases.VENT);
								break;
							case EtatsCases.ODEUR:
								grille[i-1][j].setEtat(EtatsCases.VENT_ODEUR);
								break;
							}
						}
					}
				}
			}
		}
	}

	public void afficher() {
		System.out.println("Dimension dans afficher :"+ dimension);
		System.out.println("Dimensions grille dans afficher : "+grille.length+", "+grille[0].length);
		for (int i = 0; i < Foret.dimension; i++) {
			for (int j = 0; j < Foret.dimension; j++) {
				boolean monstre = false;
				boolean crevasse = false;
				for(Element element : grille[i][j].getContenu()) {
					if(element instanceof Monstre) {
						monstre = true;
					}
					if(element instanceof Crevasse) {
						crevasse = true;
					}
				}
				System.out.print(monstre+", "+crevasse+", "+grille[i][j].getEtat()+"   ");
			}
			System.out.println();
		}

	}

}
