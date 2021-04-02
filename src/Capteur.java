import java.util.List;

public class Capteur {
	
	protected boolean odeur;
	protected boolean vent;
	protected boolean brillant;
	protected boolean crevasse;
	List<Case> casesNonVisitees;
	
	public void capter(Case caseCourrente, Foret foret) {
		odeur = false;
		vent = false;
		brillant = false;
		crevasse = false;
		
	}
	
	public boolean isCrevasse() {
		return crevasse;
	}
	public void setCrevasse(boolean crevasse) {
		this.crevasse = crevasse;
	}
	public boolean isOdeur() {
		return odeur;
	}
	public void setOdeur(boolean odeur) {
		this.odeur = odeur;
	}
	public boolean isVent() {
		return vent;
	}
	public void setVent(boolean vent) {
		this.vent = vent;
	}
	public boolean isBrillant() {
		return brillant;
	}
	public void setBrillant(boolean brillant) {
		this.brillant = brillant;
	}
	
	

}
