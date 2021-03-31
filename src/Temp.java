import java.util.ArrayList;
import java.util.List;

import constantes.Directions;

public class Temp {
	
	protected void mettreAJour() {
		if(effecteur == null) {
			casesAdjacentes = initialiserCasesAdjacentes();
		}
		else {
			List<Case> nouvellesCasesAdjacentes = new ArrayList<>();
			nouvellesCasesAdjacentes = initialiserCasesAdjacentes();

			// l'agent s'est déplacé vers le haut
			if(this.effecteur.getDirection() == Directions.HAUT) {

				// Modification des parametres de la case voisine Haute
				if (nouvellesCasesAdjacentes.get(Directions.HAUT).isValable()) {

					if(capteur.isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setMonstre(2);
					}
					if(capteur.isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setCrevasse(2);
					}
				}

				// Modification des parametres de la case voisine gauche
				if (nouvellesCasesAdjacentes.get(Directions.GAUCHE).isValable()) {
					if(capteur.isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setMonstre(2);
						if(this.casesAdjacentes.get(Directions.GAUCHE).isVisitee() == true && this.casesAdjacentes.get(Directions.GAUCHE).isOdeur() == false) {
							nouvellesCasesAdjacentes.get(Directions.GAUCHE).setMonstre(0);
						}
					}
					if(capteur.isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setCrevasse(2);
						if(this.casesAdjacentes.get(Directions.GAUCHE).isVisitee() == true && this.casesAdjacentes.get(Directions.GAUCHE).isVent() == false) {
							nouvellesCasesAdjacentes.get(Directions.GAUCHE).setCrevasse(0);
						}
					}

					if (capteur.isOdeur() == true && this.casesAdjacentes.get(Directions.GAUCHE).isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setMonstre(1);
						if (nouvellesCasesAdjacentes.get(Directions.DROITE).isValable()) nouvellesCasesAdjacentes.get(Directions.DROITE).setVide();
					}
					if (capteur.isVent() == true && this.casesAdjacentes.get(Directions.GAUCHE).isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setCrevasse(1);
						if (nouvellesCasesAdjacentes.get(Directions.DROITE).isValable()) nouvellesCasesAdjacentes.get(Directions.DROITE).setVide();

					}
					if (capteur.isVent()==true && this.casesAdjacentes.get(Directions.GAUCHE).isOdeur()==true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setVide();
					}
					if (capteur.isOdeur()==true && this.casesAdjacentes.get(Directions.GAUCHE).isVent()==true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setVide();
					}	
				} 

				// Modification des parametres de la case voisine Droite
				if (nouvellesCasesAdjacentes.get(Directions.DROITE).isValable()) {
					if(capteur.isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setMonstre(2);
						if(this.casesAdjacentes.get(Directions.DROITE).isVisitee() == true && this.casesAdjacentes.get(Directions.DROITE).isOdeur() == false) {
							nouvellesCasesAdjacentes.get(Directions.DROITE).setMonstre(0);
						}
					}
					if(capteur.isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setCrevasse(2);
						if(this.casesAdjacentes.get(Directions.DROITE).isVisitee() == true && this.casesAdjacentes.get(Directions.DROITE).isVent() == false) {
							nouvellesCasesAdjacentes.get(Directions.DROITE).setCrevasse(0);
						}
					}

					if (capteur.isOdeur() == true && this.casesAdjacentes.get(Directions.DROITE).isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setMonstre(1);
						if (nouvellesCasesAdjacentes.get(Directions.GAUCHE).isValable()) nouvellesCasesAdjacentes.get(Directions.GAUCHE).setVide();
					}
					if (capteur.isVent() == true && this.casesAdjacentes.get(Directions.GAUCHE).isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setCrevasse(1);
						if (nouvellesCasesAdjacentes.get(Directions.GAUCHE).isValable()) nouvellesCasesAdjacentes.get(Directions.GAUCHE).setVide();

					}
					if (capteur.isVent()==true && this.casesAdjacentes.get(Directions.DROITE).isOdeur()==true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setVide();
					}
					if (capteur.isOdeur()==true && this.casesAdjacentes.get(Directions.DROITE).isVent()==true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setVide();
					}	
				} 

				//Modification des parametres de la case voisine basse
				if (nouvellesCasesAdjacentes.get(Directions.BAS).isValable()) {
					nouvellesCasesAdjacentes.get(Directions.BAS).setVisitee(true);
					if(!this.casePrecedente.isOdeur() && !this.casePrecedente.isVent()) nouvellesCasesAdjacentes.get(Directions.BAS).setVide();
					if(this.casePrecedente.isOdeur()) nouvellesCasesAdjacentes.get(Directions.BAS).setOdeur(true);
					if(this.casePrecedente.isVent()) nouvellesCasesAdjacentes.get(Directions.BAS).setVent(true);
				}

			}
			// l'agent s'est déplacé vers le bas
			if(this.effecteur.getDirection() == Directions.BAS) {
				
				// Modification des parametres de la case voisine Basse
				if (nouvellesCasesAdjacentes.get(Directions.BAS).isValable()) {
					
					if(capteur.isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setMonstre(2);
					}
					if(capteur.isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setCrevasse(2);
					}
				}
				
				// Modification des parametres de la case voisine gauche
				if (nouvellesCasesAdjacentes.get(Directions.GAUCHE).isValable()) {
					if(capteur.isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setMonstre(2);
						if(this.casesAdjacentes.get(Directions.GAUCHE).isVisitee() == true && this.casesAdjacentes.get(Directions.GAUCHE).isOdeur() == false) {
							nouvellesCasesAdjacentes.get(Directions.GAUCHE).setMonstre(0);
						}
					}
					if(capteur.isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setCrevasse(2);
						if(this.casesAdjacentes.get(Directions.GAUCHE).isVisitee() == true && this.casesAdjacentes.get(Directions.GAUCHE).isVent() == false) {
							nouvellesCasesAdjacentes.get(Directions.GAUCHE).setCrevasse(0);
						}
					}
					
					if (capteur.isOdeur() == true && this.casesAdjacentes.get(Directions.GAUCHE).isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setMonstre(1);
						if (nouvellesCasesAdjacentes.get(Directions.DROITE).isValable()) nouvellesCasesAdjacentes.get(Directions.DROITE).setVide();
					}
					if (capteur.isVent() == true && this.casesAdjacentes.get(Directions.GAUCHE).isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setCrevasse(1);
						if (nouvellesCasesAdjacentes.get(Directions.DROITE).isValable()) nouvellesCasesAdjacentes.get(Directions.DROITE).setVide();
						
					}
					if (capteur.isVent()==true && this.casesAdjacentes.get(Directions.GAUCHE).isOdeur()==true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setVide();
					}
					if (capteur.isOdeur()==true && this.casesAdjacentes.get(Directions.GAUCHE).isVent()==true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setVide();
					}	
				} 
				
				// Modification des parametres de la case voisine Droite
				if (nouvellesCasesAdjacentes.get(Directions.DROITE).isValable()) {
					if(capteur.isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setMonstre(2);
						if(this.casesAdjacentes.get(Directions.DROITE).isVisitee() == true && this.casesAdjacentes.get(Directions.DROITE).isOdeur() == false) {
							nouvellesCasesAdjacentes.get(Directions.DROITE).setMonstre(0);
						}
					}
					if(capteur.isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setCrevasse(2);
						if(this.casesAdjacentes.get(Directions.DROITE).isVisitee() == true && this.casesAdjacentes.get(Directions.DROITE).isVent() == false) {
							nouvellesCasesAdjacentes.get(Directions.DROITE).setCrevasse(0);
						}
					}
					
					if (capteur.isOdeur() == true && this.casesAdjacentes.get(Directions.DROITE).isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setMonstre(1);
						if (nouvellesCasesAdjacentes.get(Directions.GAUCHE).isValable()) nouvellesCasesAdjacentes.get(Directions.GAUCHE).setVide();
					}
					if (capteur.isVent() == true && this.casesAdjacentes.get(Directions.DROITE).isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setCrevasse(1);
						if (nouvellesCasesAdjacentes.get(Directions.GAUCHE).isValable()) nouvellesCasesAdjacentes.get(Directions.GAUCHE).setVide();
						
					}
					if (capteur.isVent()==true && this.casesAdjacentes.get(Directions.DROITE).isOdeur()==true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setVide();
					}
					if (capteur.isOdeur()==true && this.casesAdjacentes.get(Directions.DROITE).isVent()==true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setVide();
					}	
				} 
				
				//Modification des parametres de la case voisine haute
				if (nouvellesCasesAdjacentes.get(Directions.HAUT).isValable()) {
					nouvellesCasesAdjacentes.get(Directions.HAUT).setVisitee(true);
					if(!this.casePrecedente.isOdeur() && !this.casePrecedente.isVent()) nouvellesCasesAdjacentes.get(Directions.HAUT).setVide();
					if(this.casePrecedente.isOdeur()) nouvellesCasesAdjacentes.get(Directions.HAUT).setOdeur(true);
					if(this.casePrecedente.isVent()) nouvellesCasesAdjacentes.get(Directions.HAUT).setVent(true);
				}
				
			}
			// l'agent s'est déplacé vers la droite
			if(this.effecteur.getDirection() == Directions.DROITE) {
				
				// Modification des parametres de la case voisine droite
				if (nouvellesCasesAdjacentes.get(Directions.DROITE).isValable()) {
					
					if(capteur.isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setMonstre(2);
					}
					if(capteur.isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.DROITE).setCrevasse(2);
					}
				}
				
				// Modification des parametres de la case voisine basse
				if (nouvellesCasesAdjacentes.get(Directions.BAS).isValable()) {
					if(capteur.isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setMonstre(2);
						if(this.casesAdjacentes.get(Directions.BAS).isVisitee() == true && this.casesAdjacentes.get(Directions.BAS).isOdeur() == false) {
							nouvellesCasesAdjacentes.get(Directions.BAS).setMonstre(0);
						}
					}
					if(capteur.isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setCrevasse(2);
						if(this.casesAdjacentes.get(Directions.BAS).isVisitee() == true && this.casesAdjacentes.get(Directions.BAS).isVent() == false) {
							nouvellesCasesAdjacentes.get(Directions.BAS).setCrevasse(0);
						}
					}
					
					if (capteur.isOdeur() == true && this.casesAdjacentes.get(Directions.BAS).isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setMonstre(1);
						if (nouvellesCasesAdjacentes.get(Directions.HAUT).isValable()) nouvellesCasesAdjacentes.get(Directions.HAUT).setVide();
					}
					if (capteur.isVent() == true && this.casesAdjacentes.get(Directions.BAS).isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setCrevasse(1);
						if (nouvellesCasesAdjacentes.get(Directions.HAUT).isValable()) nouvellesCasesAdjacentes.get(Directions.HAUT).setVide();
						
					}
					if (capteur.isVent()==true && this.casesAdjacentes.get(Directions.BAS).isOdeur()==true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setVide();
					}
					if (capteur.isOdeur()==true && this.casesAdjacentes.get(Directions.BAS).isVent()==true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setVide();
					}	
				} 
				
				// Modification des parametres de la case voisine haute
				if (nouvellesCasesAdjacentes.get(Directions.HAUT).isValable()) {
					if(capteur.isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setMonstre(2);
						if(this.casesAdjacentes.get(Directions.HAUT).isVisitee() == true && this.casesAdjacentes.get(Directions.HAUT).isOdeur() == false) {
							nouvellesCasesAdjacentes.get(Directions.HAUT).setMonstre(0);
						}
					}
					if(capteur.isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setCrevasse(2);
						if(this.casesAdjacentes.get(Directions.HAUT).isVisitee() == true && this.casesAdjacentes.get(Directions.HAUT).isVent() == false) {
							nouvellesCasesAdjacentes.get(Directions.HAUT).setCrevasse(0);
						}
					}
					
					if (capteur.isOdeur() == true && this.casesAdjacentes.get(Directions.HAUT).isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setMonstre(1);
						if (nouvellesCasesAdjacentes.get(Directions.BAS).isValable()) nouvellesCasesAdjacentes.get(Directions.BAS).setVide();
					}
					if (capteur.isVent() == true && this.casesAdjacentes.get(Directions.HAUT).isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setCrevasse(1);
						if (nouvellesCasesAdjacentes.get(Directions.BAS).isValable()) nouvellesCasesAdjacentes.get(Directions.BAS).setVide();
						
					}
					if (capteur.isVent()==true && this.casesAdjacentes.get(Directions.HAUT).isOdeur()==true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setVide();
					}
					if (capteur.isOdeur()==true && this.casesAdjacentes.get(Directions.HAUT).isVent()==true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setVide();
					}	
				} 
				
				//Modification des parametres de la case voisine basse
				if (nouvellesCasesAdjacentes.get(Directions.GAUCHE).isValable()) {
					nouvellesCasesAdjacentes.get(Directions.GAUCHE).setVisitee(true);
					if(!this.casePrecedente.isOdeur() && !this.casePrecedente.isVent()) nouvellesCasesAdjacentes.get(Directions.GAUCHE).setVide();
					if(this.casePrecedente.isOdeur()) nouvellesCasesAdjacentes.get(Directions.GAUCHE).setOdeur(true);
					if(this.casePrecedente.isVent()) nouvellesCasesAdjacentes.get(Directions.GAUCHE).setVent(true);
				}
				
			}
			// l'agent s'est déplacé vers la gauche
			if(this.effecteur.getDirection() == Directions.GAUCHE) {
				
				// Modification des parametres de la case voisine gauche
				if (nouvellesCasesAdjacentes.get(Directions.GAUCHE).isValable()) {
					
					if(capteur.isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setMonstre(2);
					}
					if(capteur.isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.GAUCHE).setCrevasse(2);
					}
				}
				
				// Modification des parametres de la case voisine basse
				if (nouvellesCasesAdjacentes.get(Directions.BAS).isValable()) {
					if(capteur.isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setMonstre(2);
						if(this.casesAdjacentes.get(Directions.BAS).isVisitee() == true && this.casesAdjacentes.get(Directions.BAS).isOdeur() == false) {
							nouvellesCasesAdjacentes.get(Directions.BAS).setMonstre(0);
						}
					}
					if(capteur.isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setCrevasse(2);
						if(this.casesAdjacentes.get(Directions.BAS).isVisitee() == true && this.casesAdjacentes.get(Directions.BAS).isVent() == false) {
							nouvellesCasesAdjacentes.get(Directions.BAS).setCrevasse(0);
						}
					}
					
					if (capteur.isOdeur() == true && this.casesAdjacentes.get(Directions.BAS).isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setMonstre(1);
						if (nouvellesCasesAdjacentes.get(Directions.HAUT).isValable()) nouvellesCasesAdjacentes.get(Directions.HAUT).setVide();
					}
					if (capteur.isVent() == true && this.casesAdjacentes.get(Directions.BAS).isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setCrevasse(1);
						if (nouvellesCasesAdjacentes.get(Directions.HAUT).isValable()) nouvellesCasesAdjacentes.get(Directions.HAUT).setVide();
						
					}
					if (capteur.isVent()==true && this.casesAdjacentes.get(Directions.BAS).isOdeur()==true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setVide();
					}
					if (capteur.isOdeur()==true && this.casesAdjacentes.get(Directions.BAS).isVent()==true) {
						nouvellesCasesAdjacentes.get(Directions.BAS).setVide();
					}	
				} 
				
				// Modification des parametres de la case voisine haute
				if (nouvellesCasesAdjacentes.get(Directions.HAUT).isValable()) {
					if(capteur.isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setMonstre(2);
						if(this.casesAdjacentes.get(Directions.HAUT).isVisitee() == true && this.casesAdjacentes.get(Directions.HAUT).isOdeur() == false) {
							nouvellesCasesAdjacentes.get(Directions.HAUT).setMonstre(0);
						}
					}
					if(capteur.isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setCrevasse(2);
						if(this.casesAdjacentes.get(Directions.HAUT).isVisitee() == true && this.casesAdjacentes.get(Directions.HAUT).isVent() == false) {
							nouvellesCasesAdjacentes.get(Directions.HAUT).setCrevasse(0);
						}
					}
					
					if (capteur.isOdeur() == true && this.casesAdjacentes.get(Directions.HAUT).isOdeur() == true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setMonstre(1);
						if (nouvellesCasesAdjacentes.get(Directions.BAS).isValable()) nouvellesCasesAdjacentes.get(Directions.BAS).setVide();
					}
					if (capteur.isVent() == true && this.casesAdjacentes.get(Directions.HAUT).isVent() == true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setCrevasse(1);
						if (nouvellesCasesAdjacentes.get(Directions.BAS).isValable()) nouvellesCasesAdjacentes.get(Directions.BAS).setVide();
						
					}
					if (capteur.isVent()==true && this.casesAdjacentes.get(Directions.HAUT).isOdeur()==true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setVide();
					}
					if (capteur.isOdeur()==true && this.casesAdjacentes.get(Directions.HAUT).isVent()==true) {
						nouvellesCasesAdjacentes.get(Directions.HAUT).setVide();
					}	
				} 
				
				//Modification des parametres de la case voisine droite
				if (nouvellesCasesAdjacentes.get(Directions.DROITE).isValable()) {
					nouvellesCasesAdjacentes.get(Directions.DROITE).setVisitee(true);
					if(!this.casePrecedente.isOdeur() && !this.casePrecedente.isVent()) nouvellesCasesAdjacentes.get(Directions.DROITE).setVide();
					if(this.casePrecedente.isOdeur()) nouvellesCasesAdjacentes.get(Directions.DROITE).setOdeur(true);
					if(this.casePrecedente.isVent()) nouvellesCasesAdjacentes.get(Directions.DROITE).setVent(true);
				}
				
			}
			this.casesAdjacentes = nouvellesCasesAdjacentes;
		}
	}
	
	protected List<Case> initialiserCasesAdjacentes() {
		//Creation de la liste des cases adjacentes
		List<Case> nouvellesCasesAdjacentes = new ArrayList<>();		
		for(int i=0; i<4; i++) {
			nouvellesCasesAdjacentes.add(new Case());
		}
		//Met a jour l'etat valable de chaque case adjacente
		//en fonction de leur position par rapport aux bordures
		if (this.x == 0) {			
			nouvellesCasesAdjacentes.get(Directions.GAUCHE).setValable(false);					
		}
		if (this.x == Foret.dimension - 1) {
			nouvellesCasesAdjacentes.get(Directions.DROITE).setValable(false);			
		} 
		if (this.y == 0) {
			nouvellesCasesAdjacentes.get(Directions.HAUT).setValable(false);				
		}
		if (this.y == Foret.dimension - 1) {
			nouvellesCasesAdjacentes.get(Directions.BAS).setValable(false);				
		}
		//Pour chaque case adjacente :
		for(int index = 0; index < nouvellesCasesAdjacentes.size(); index++) {
			//Si la case est valable, on lui donne sa position
			if(nouvellesCasesAdjacentes.get(index).isValable()) {
				switch(index) {
				case Directions.HAUT:
					nouvellesCasesAdjacentes.get(Directions.HAUT).getPosition().add(x);		
					nouvellesCasesAdjacentes.get(Directions.HAUT).getPosition().add(y-1);
					break;
				case Directions.BAS:
					nouvellesCasesAdjacentes.get(Directions.BAS).getPosition().add(x);		
					nouvellesCasesAdjacentes.get(Directions.BAS).getPosition().add(y+1);
					break;
				case Directions.DROITE:
					nouvellesCasesAdjacentes.get(Directions.DROITE).getPosition().add(x+1);		
					nouvellesCasesAdjacentes.get(Directions.DROITE).getPosition().add(y);
					break;
				case Directions.GAUCHE:
					nouvellesCasesAdjacentes.get(Directions.GAUCHE).getPosition().add(x-1);		
					nouvellesCasesAdjacentes.get(Directions.GAUCHE).getPosition().add(y);
					break;
				}
				//Si la case est valable ET visitee, on met a jour ses differents etats
				if(nouvellesCasesAdjacentes.get(index).isVisitee()) {
					nouvellesCasesAdjacentes.set(index, foret.grille[nouvellesCasesAdjacentes.get(index).getPosition().get(0)]
							[nouvellesCasesAdjacentes.get(index).getPosition().get(1)]);
				}		
			}
		}
		return nouvellesCasesAdjacentes;
	}

}
