
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Foret foret = new Foret();
		
		Agent agent = new Agent(foret);
		
		while (agent.vivant) {
			agent.jouer();
		}
		
	}

}
