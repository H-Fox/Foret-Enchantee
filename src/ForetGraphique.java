

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
	
	private ImageIcon AGENT = new ImageIcon(getClass().getResource("images/Agent.png"));
	private ImageIcon MONSTRE = new ImageIcon(getClass().getResource("images/Monstre.png"));
	private ImageIcon CREVASSE = new ImageIcon(getClass().getResource("images/Crevasse.png"));
	private ImageIcon ODEUR = new ImageIcon(getClass().getResource("images/Odeur.png"));
	private ImageIcon VENT = new ImageIcon(getClass().getResource("images/Vent.png"));
	private ImageIcon PORTAIL = new ImageIcon(getClass().getResource("images/Portail.png"));
	private ImageIcon AGENTVENT = new ImageIcon(getClass().getResource("images/AgentVent.png"));
	private ImageIcon AGENTODEUR = new ImageIcon(getClass().getResource("images/AgentOdeur.png"));
	private ImageIcon AGENTPORTAIL = new ImageIcon(getClass().getResource("images/AgentPortail.png"));
	private ImageIcon CASENOIRE = new ImageIcon(getClass().getResource("images/CaseNoire.png"));
	private ImageIcon AGENTVENTODEUR = new ImageIcon(getClass().getResource("images/AgentOdeurVent.png"));
	private ImageIcon OdeurVent = new ImageIcon(getClass().getResource("images/OdeurVent.png"));


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
				grille[i][j].setIcon(CASENOIRE);
				if(foret.grille[i][j].isVisitee()) {
					if(foret.grille[i][j].isOdeur() && !foret.grille[i][j].isVent()) {
						//Afficher odeur et pas vent
						if(foret.grille[i][j].isJoueur()) {
							grille[i][j].setIcon(AGENTODEUR);
						}
						else {
							grille[i][j].setIcon(ODEUR);
						}
					}
					if(foret.grille[i][j].isOdeur() && foret.grille[i][j].isVent()) {
						//Afficher monstre ET crevasse
						if(foret.grille[i][j].isJoueur()) {
							grille[i][j].setIcon(AGENTVENTODEUR);
						}
						else {
							grille[i][j].setIcon(OdeurVent);
						}
					}
					if(!foret.grille[i][j].isOdeur() && !foret.grille[i][j].isVent()) {
						//checker si odeur/vent
						if(foret.grille[i][j].isJoueur()) {
							grille[i][j].setIcon(AGENT);
						}
						else {
							grille[i][j].setIcon(null);
						}
					}
					if (!foret.grille[i][j].isOdeur() && foret.grille[i][j].isVent()) {
						//Afficher crevasse
						if(foret.grille[i][j].isJoueur()) {
							grille[i][j].setIcon(AGENTVENT);
						}
						else {
							grille[i][j].setIcon(VENT);
						}
					}

				}
				else {
					
					if(foret.grille[i][j].isBrillante()) {
						//Afficher portail
						if(foret.grille[i][j].isJoueur()) {
							grille[i][j].setIcon(AGENTPORTAIL);
						}
						else {
							grille[i][j].setIcon(PORTAIL);
						}

					}

					if(monstre && !crevasse) {
						//Afficher monstre
						grille[i][j].setIcon(MONSTRE);
					}
					if(monstre && crevasse) {
						//Afficher monstre ET crevasse
						grille[i][j].setIcon(MONSTRE);
					}
					
					if (!monstre && crevasse) {
						//Afficher crevasse
						grille[i][j].setIcon(CREVASSE);
					}
				}


				this.repaint();
			}
		}

	}
}
