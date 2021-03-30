import java.util.ArrayList;
import java.util.List;

import constantes.Directions;

public class Agent extends Element{

	protected int x = -1;
	protected int y = -1;

	protected Case casePrecedente;
	protected List<Case> casesConnues;

	protected int performance = 0;

	protected Effecteur effecteur;
	protected List<Effecteur> listeActionsPossibles;
	protected Capteur capteur;

	protected Foret foret;



	public Agent(Foret foret) {
		listeActionsPossibles = new ArrayList<>();
		casePrecedente = new Case();
		casesConnues = new ArrayList<>();
		this.foret = foret;
	}

	protected void deplacer(int direction) {
		switch(direction) {
		case Directions.HAUT:
			foret.grille[x][y].setJoueur(false);
			y--;
			foret.grille[x][y].setJoueur(true);
			foret.grille[x][y].setVisitee(true);
			break;
		case Directions.BAS:
			foret.grille[x][y].setJoueur(false);
			y++;
			foret.grille[x][y].setJoueur(true);
			foret.grille[x][y].setVisitee(true);
			break;
		case Directions.DROITE:
			foret.grille[x][y].setJoueur(false);
			x++;
			foret.grille[x][y].setJoueur(true);
			foret.grille[x][y].setVisitee(true);
			break;
		case Directions.GAUCHE:
			foret.grille[x][y].setJoueur(false);
			x--;
			foret.grille[x][y].setJoueur(true);
			foret.grille[x][y].setVisitee(true);
			break;
		}
	}

	protected void listerActionsPossibles() {

		List<Effecteur> actionsPossibles = new ArrayList<>();
		List<Case> casesCiblesPourTirer = new ArrayList<>();

		for(int i = 0; i < Foret.dimension; i++) {
			for(int j = 0; j < Foret.dimension; j++) {
				//Prio numero 1 : Les cases non visitees et vides
				if(!foret.grille[i][j].isVisitee()
						&& foret.grille[i][j].getDanger() == 0) {
					List<Integer> pos = new ArrayList<>();
					pos.add(i);
					pos.add(j);
					Effecteur effecteur = new Effecteur(pos, 1, false);
					actionsPossibles.add(effecteur);
				}
				//Prio numero 2 : Tirer sur les monstres là ou pas de crevasse pour ensuite s'y rendre
				if(!foret.grille[i][j].isVisitee()
						&& foret.grille[i][j].getMonstre() == 2
						&& foret.grille[i][j].getCrevasse() == 0) {
					casesCiblesPourTirer.add(foret.grille[i][j]);
				}
			}
		} 
		Case temp = null;  
		for(int i=0; i < casesCiblesPourTirer.size(); i++) {
			for(int j=1; j < (casesCiblesPourTirer.size()-i); j++){  
				if(casesCiblesPourTirer.get(j-1).getDanger() > casesCiblesPourTirer.get(j).getDanger()){
					//echanges des elements
					temp = casesCiblesPourTirer.get(j-1);  
					casesCiblesPourTirer.set(j-1, casesCiblesPourTirer.get(j));  
					casesCiblesPourTirer.set(j, temp);  
				} 
			}
		}
	}

	static void tri_bulle(int[] tab)
	{  
		int taille = tab.length;  
		int tmp = 0;  
		for(int i=0; i < taille; i++) 
		{
			for(int j=1; j < (taille-i); j++)
			{  
				if(tab[j-1] > tab[j])
				{
					//echanges des elements
					tmp = tab[j-1];  
					tab[j-1] = tab[j];  
					tab[j] = tmp;  
				}

			}
		}
	}

	protected void interpreterConnaissances() {

		for(int x = 0; x < Foret.dimension; x++) {
			for(int y = 0; y < Foret.dimension; y++) {
				if(!foret.grille[x][y].isVisitee()) {
					foret.grille[x][y].etudierAdjacence();
				}
			}
		}
	}

	public Case getCasePrecedente() {
		return casePrecedente;
	}

	public void setCasePrecedente(Case casePrecedente) {
		this.casePrecedente = casePrecedente;
	}

	public List<Case> getCasesConnues() {
		return casesConnues;
	}

	public void setCasesConnues(List<Case> casesConnues) {
		this.casesConnues = casesConnues;
	}

	public int getPerformance() {
		return performance;
	}

	public void setPerformance(int performance) {
		this.performance = performance;
	}

	public Effecteur getEffecteur() {
		return effecteur;
	}

	public void setEffecteur(Effecteur effecteur) {
		this.effecteur = effecteur;
	}

	public Capteur getCapteur() {
		return capteur;
	}

	public void setCapteur(Capteur capteur) {
		this.capteur = capteur;
	}

	public Foret getForet() {
		return foret;
	}

	public void setForet(Foret foret) {
		this.foret = foret;
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
