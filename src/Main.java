
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		int coup = 0;
		int niveau = 0;
		while(true) {			
			Foret foret = new Foret();
			niveau = Foret.dimension - 2;
			
			System.out.println("\n\nLancement du niveau "+niveau+"\n");
			
			Capteur capteur = new Capteur();
			Agent agent = new Agent(foret.getPositionJoueur());
			capteur.capter(foret.getCaseCourrante(), foret);
			agent.setCapteur(capteur);
			
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
				foret.MAJForet();
				coup++;
				System.out.println("Tour : "+coup);
				capteur.capter(foret.getCaseCourrante(), foret);
				agent.setCapteur(capteur);
			}
			System.out.println("\nLe score de l'agent est de : "+agent.getPerformance()+"\n");
			if(!agent.vivant) {
				System.out.println("____________________________MORT____________________________");
				System.out.println("Le score de l'agent est de : "+agent.getPerformance());
				Foret.dimension = 2;
				System.out.println("\n\n");
			}
		}		
	}
}
