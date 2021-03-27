
public class Monstre extends Element{
	
	protected boolean vivant;
	
	public Monstre() {
		vivant = true;
	}
	
	protected void tuer() {
		vivant = false;
	}

}
