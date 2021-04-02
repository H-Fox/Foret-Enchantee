
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
		while(true) {
			Foret foret = new Foret();
			
			Agent agent = foret.getJoueur();
			
			foret.setJoueur(agent);
			
			while (agent.vivant && !agent.gagne) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				foret.afficher();
				System.out.println();
				
				agent.jouer();
//				foret.afficherDanger();
			}
			if(!agent.vivant) {
				System.out.println("MORT____________________________");
				Foret.dimension = 2;
			}
		}
		
		
	}
	
	public static void jouer() {
		
		
	}

}
