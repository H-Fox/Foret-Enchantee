

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ForetGraphique extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton[][] grille;
	
	private ImageIcon Agent = new ImageIcon(getClass().getResource("icones/Agent.png"));
	private ImageIcon Portail = new ImageIcon(getClass().getResource("icones/Portail.png"));
	private ImageIcon Monstre = new ImageIcon(getClass().getResource("icones/Monstre.png"));
	private ImageIcon Crevasse = new ImageIcon(getClass().getResource("icones/Crevasse.png"));
	private ImageIcon Odeur = new ImageIcon(getClass().getResource("icones/Odeur.png"));
	private ImageIcon Vent = new ImageIcon(getClass().getResource("icones/Vent.png"));
	private ImageIcon OdeurVent = new ImageIcon(getClass().getResource("icones/OdeurVent.png"));	
	private ImageIcon AgentVent = new ImageIcon(getClass().getResource("icones/AgentVent.png"));
	private ImageIcon AgentOdeur = new ImageIcon(getClass().getResource("icones/AgentOdeur.png"));
	private ImageIcon AgentPortail = new ImageIcon(getClass().getResource("icones/AgentPortail.png"));
	private ImageIcon AgentVentOdeur= new ImageIcon(getClass().getResource("icones/AgentOdeurVent.png"));
	private ImageIcon CaseNoire = new ImageIcon(getClass().getResource("icones/CaseNoire.png"));

	public ForetGraphique(){
		grille = new JButton[Foret.dimension][Foret.dimension];
		setTitle("Foret");
		setSize(800,450);
		setLayout(new GridLayout(Foret.dimension,Foret.dimension));
		Container container = getContentPane();
		for(int i = 0; i < Foret.dimension; i++) {
			for(int j = 0; j < Foret.dimension; j++) {				
				grille[i][j] = new JButton();
				container.add(grille[i][j]);
			}
		}

		this.repaint();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	public void afficherGraphiquement(Foret foret) {
		for(int i = 0; i < Foret.dimension; i++) {
			for(int j = 0; j < Foret.dimension; j++) {
				boolean monstre = false;
				boolean crevasse = false;

				for(Element elt : foret.grille[i][j].getContenu()) {
					if(elt instanceof Monstre) {
						monstre = true;
					}
					if(elt instanceof Crevasse) {
						crevasse = true;
					}
				}
				grille[i][j].setIcon(CaseNoire);
				if(foret.grille[i][j].isVisitee()) {
					if(foret.grille[i][j].isOdeur() && !foret.grille[i][j].isVent()) {
						//Afficher odeur et pas vent
						if(foret.grille[i][j].isJoueur()) {
							grille[i][j].setIcon(AgentOdeur);
						}
						else {
							grille[i][j].setIcon(Odeur);
						}
					}
					if(foret.grille[i][j].isOdeur() && foret.grille[i][j].isVent()) {
						//Afficher monstre ET crevasse
						if(foret.grille[i][j].isJoueur()) {
							grille[i][j].setIcon(AgentVentOdeur);
						}
						else {
							grille[i][j].setIcon(OdeurVent);
						}
					}
					if(!foret.grille[i][j].isOdeur() && !foret.grille[i][j].isVent()) {
						//checker si odeur/vent
						if(foret.grille[i][j].isJoueur()) {
							grille[i][j].setIcon(Agent);
						}
						else {
							grille[i][j].setIcon(null);
						}
					}
					if (!foret.grille[i][j].isOdeur() && foret.grille[i][j].isVent()) {
						//Afficher crevasse
						if(foret.grille[i][j].isJoueur()) {
							grille[i][j].setIcon(AgentVent);
						}
						else {
							grille[i][j].setIcon(Vent);
						}
					}

				}
				else {
					
					if(foret.grille[i][j].isBrillante()) {
						//Afficher portail
						if(foret.grille[i][j].isJoueur()) {
							grille[i][j].setIcon(AgentPortail);
						}
						else {
							grille[i][j].setIcon(Portail);
						}

					}

					if(monstre && !crevasse) {
						//Afficher monstre
						grille[i][j].setIcon(Monstre);
					}
					if(monstre && crevasse) {
						//Afficher monstre ET crevasse
						grille[i][j].setIcon(Monstre);
					}
					
					if (!monstre && crevasse) {
						//Afficher crevasse
						grille[i][j].setIcon(Crevasse);
					}
				}


				this.repaint();
			}
		}

	}
}
