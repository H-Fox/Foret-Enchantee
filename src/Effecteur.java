
public class Effecteur {
	
	protected int direction;
	protected boolean tirer;
	
	public Effecteur() {
		direction = -1;
		tirer = false;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isTirer() {
		return tirer;
	}

	public void setTirer(boolean tirer) {
		this.tirer = tirer;
	}
	

}
