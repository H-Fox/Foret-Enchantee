import java.util.List;

public class Effecteur {
	
	protected Case caseCiblee;
	protected int priorite;
	protected boolean tirer = false;
	
	public Effecteur(Case _caseCiblee,int _priorite, boolean _tirer) {
		caseCiblee = _caseCiblee;
		priorite = _priorite;
		tirer = _tirer;
	}


	
	public int getPriorite() {
		return priorite;
	}



	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}



	public Case getCaseCiblee() {
		return caseCiblee;
	}


	public void setCaseCiblee(Case caseCiblee) {
		this.caseCiblee = caseCiblee;
	}


	public boolean isTirer() {
		return tirer;
	}

	public void setTirer(boolean tirer) {
		this.tirer = tirer;
	}
	

}
