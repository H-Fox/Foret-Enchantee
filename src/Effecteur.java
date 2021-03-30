import java.util.List;

public class Effecteur {
	
	protected List<Integer> position;
	protected int priorite = -1;
	protected boolean tirer = false;
	
	public Effecteur(List<Integer> _position, int _priorite, boolean _tirer) {
		position = _position;
		priorite = _priorite;
		tirer = _tirer;
	}
	
	public int getPriorite() {
		return priorite;
	}

	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}


	public List<Integer> getPosition() {
		return position;
	}

	public void setPosition(List<Integer> position) {
		this.position = position;
	}

	public boolean isTirer() {
		return tirer;
	}

	public void setTirer(boolean tirer) {
		this.tirer = tirer;
	}
	

}
