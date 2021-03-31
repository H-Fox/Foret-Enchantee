

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ForetGraphique extends JFrame {

	public int taille;

	private static final long serialVersionUID = 1L;
	private JButton[][] mesCases;
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


	public ForetGraphique(int taille){
		this.taille = taille;
		mesCases = new JButton[taille][taille];
		setTitle("Foret");
		setSize(700,500);
		setLayout(new GridLayout(taille,taille));
		Container c = getContentPane();
		System.out.println(taille);
		for(int i =0;i<taille;i++) {
			for(int j = 0;j<taille;j++) {				
				mesCases[i][j] = new JButton();
				System.out.println(taille);
				c.add(mesCases[i][j]);
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
				if(foret.grille[i][j].isBrillante()) {
					//Afficher portail
					if(foret.grille[i][j].isJoueur()) {
						mesCases[i][j].setIcon(AGENTPORTAIL);
					}
					else {
						mesCases[i][j].setIcon(PORTAIL);
					}

				}

				if(monstre && !crevasse) {
					//Afficher monstre
					mesCases[i][j].setIcon(MONSTRE);
				}
				if(monstre && crevasse) {
					//Afficher monstre ET crevasse
					mesCases[i][j].setIcon(MONSTRE);
				}
				if(!monstre && !crevasse) {
					//checker si odeur/vent
					mesCases[i][j].setIcon(null);
				}
				if (!monstre && crevasse) {
					//Afficher crevasse
					mesCases[i][j].setIcon(CREVASSE);
				}

				if(foret.grille[i][j].isOdeur() && !foret.grille[i][j].isVent()) {
					//Afficher odeur et pas vent
					if(foret.grille[i][j].isJoueur()) {
						mesCases[i][j].setIcon(AGENTODEUR);
					}
					else {
						mesCases[i][j].setIcon(ODEUR);
					}
				}
				if(foret.grille[i][j].isOdeur() && foret.grille[i][j].isVent()) {
					//Afficher monstre ET crevasse
					if(foret.grille[i][j].isJoueur()) {
						mesCases[i][j].setIcon(AGENTVENTODEUR);
					}
					else {
						mesCases[i][j].setIcon(OdeurVent);
					}
				}
				if(!foret.grille[i][j].isOdeur() && !foret.grille[i][j].isVent()) {
					//checker si odeur/vent
					if(foret.grille[i][j].isJoueur()) {
						mesCases[i][j].setIcon(AGENT);
					}
					else {
						mesCases[i][j].setIcon(null);
					}
				}
				if (!foret.grille[i][j].isOdeur() && foret.grille[i][j].isVent()) {
					//Afficher crevasse
					if(foret.grille[i][j].isJoueur()) {
						mesCases[i][j].setIcon(AGENTVENT);
					}
					else {
						mesCases[i][j].setIcon(VENT);
					}
				}
				this.repaint();
			}
		}

	}




	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
}
