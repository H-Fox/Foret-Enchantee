import java.util.ArrayList;
import java.util.List;

import constantes.Directions;

public class Foret {

	protected Case[][] grille;
	protected static int dimension = 2;
	protected Agent joueur;
	protected ForetGraphique affichage;

	public Foret() {
		dimension++;

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
		affichage = new ForetGraphique(dimension);
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
		grille[x][y].setDanger(0);
		joueur.setX(x);
		joueur.setY(y);

		//Placer portail
		x = (int) (Math.random()*Foret.dimension);
		y = (int) (Math.random()*Foret.dimension);
		System.out.println("Placer portail : "+x+", "+y);

		grille[x][y].setBrillante(true);
		grille[x][y].getContenu().add(new Portail());

		//Placer monstres et/ou crevasse
		int nombreCasesARemplir = (int) (Math.random()*(Foret.dimension*Foret.dimension)); //Nombre de cases à remplir entre 0 et dimension²
		System.out.println("Nombre de cases a remplir : "+nombreCasesARemplir);
		for (int i = 0; i < nombreCasesARemplir; i++) {

			do {
				x = (int) (Math.random()*Foret.dimension);
				y = (int) (Math.random()*Foret.dimension);
				System.out.println("Placer element : "+x+", "+y);
			}
			while(grille[x][y].isJoueur()
					|| grille[x][y].isBrillante());

			int choix = (int) (Math.random()*3);

			switch(choix) {
			case 0:
				//Placer un monstre	
				System.out.println("Placer monstre : "+x+", "+y);
				grille[x][y].getContenu().add(new Monstre());
				grille[x][y].setMonstre(1);
				break;
			case 1:
				//Placer une crevasse	
				System.out.println("Placer crevasse : "+x+", "+y);
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
						if(j-1 >= 0 && !grille[i][j-1].isOdeur()) {							
								grille[i][j-1].setOdeur(true);
						}						
						//Ajout odeur DOWN
						if(j+1 <= Foret.dimension-1 && !grille[i][j+1].isOdeur()) {
							grille[i][j+1].setOdeur(true);
						}
						//Ajout odeur RIGHT
						if(i+1 <= Foret.dimension-1 && !grille[i+1][j].isOdeur()) {
							grille[i+1][j].setOdeur(true);
						}
						//Ajout odeur LEFT
						if(i-1 >= 0 && !grille[i-1][j].isOdeur()) {
							grille[i-1][j].setOdeur(true);
						}
					}
					if(element instanceof Crevasse) {
						//Ajout vent UP
						if(j-1 >= 0 && !grille[i][j-1].isVent()) {
							grille[i][j-1].setVent(true);
						}						
						//Ajout vent DOWN
						if(j+1 <= Foret.dimension-1 && !grille[i][j+1].isVent()) {
							grille[i][j+1].setVent(true);
						}
						//Ajout vent RIGHT
						if(i+1 <= Foret.dimension-1 && !grille[i+1][j].isVent()) {
							grille[i+1][j].setVent(true);
						}
						//Ajout vent LEFT
						if(i-1 >= 0 && !grille[i-1][j].isVent()) {
							grille[i-1][j].setVent(true);
						}
					}
				}
			}
		}
	}

	public void afficher() {
		affichage.afficherGraphiquement(this);
		System.out.println("Dimension dans afficher :"+ dimension);
		System.out.println("Dimensions grille dans afficher : "+grille.length+", "+grille[0].length);
		for (int i = 0; i < Foret.dimension; i++) {
			for (int j = 0; j < Foret.dimension; j++) {
				boolean monstre = false;
				boolean crevasse = false;
				boolean portail = false;
				for(Element element : grille[i][j].getContenu()) {
					if(element instanceof Monstre) {
						monstre = true;
					}
					if(element instanceof Crevasse) {
						crevasse = true;
					}
					if(element instanceof Portail) {
						portail = true;
					}
				}
				String cell = "  0 ";
				String visitee = ", PV ";
				String odeurVent = ", -- ";
				if(crevasse) {
					cell = "  C ";
				}
				if(monstre) {
					cell = "  M ";
				}
				if(crevasse && monstre) {
					cell = " M/C";
				}
				if(portail) {
					cell = "  P ";
				}
				if(grille[i][j].isJoueur()) {
					cell = "  J ";
				}
				if(grille[i][j].isVisitee()) {
					visitee = ", V  ";
				}
				if(grille[i][j].isOdeur()) {
					odeurVent = ", Od ";
				}
				if(grille[i][j].isVent()) {
					odeurVent = ", Ve ";
				}
				if(grille[i][j].isOdeur() && grille[i][j].isVent()) {
					odeurVent = ", O/V";
				}
//				System.out.print("  "+cell+visitee+odeurVent);
				System.out.print(" "+grille[i][j].getDanger());
				//System.out.print("J = "+grille[i][j].isJoueur()+", M = "+monstre+", C = "+crevasse+", P = "+portail+"   ");
			}
			System.out.println();
		}

	}

	public Case[][] getGrille() {
		return grille;
	}



	public void setGrille(Case[][] grille) {
		this.grille = grille;
	}



	public static int getDimension() {
		return dimension;
	}



	public static void setDimension(int dimension) {
		Foret.dimension = dimension;
	}



	public Agent getJoueur() {
		return joueur;
	}



	public void setJoueur(Agent joueur) {
		this.joueur = joueur;
	}



	public ForetGraphique getAffichage() {
		return affichage;
	}



	public void setAffichage(ForetGraphique affichage) {
		this.affichage = affichage;
	}

}
