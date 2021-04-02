import java.util.ArrayList;
import java.util.List;

public class Capteur {
	
	protected boolean odeur;
	protected boolean vent;
	protected boolean brillant;
	protected boolean crevasse;
	List<Case> casesForet = new ArrayList<>();
	
	public void capter(Case caseCourrante, Foret foret) {
		casesForet = new ArrayList<>();
		for(int x = 0; x < Foret.dimension; x++) {
			for(int y = 0; y < Foret.dimension; y++) {
				casesForet.add(foret.grille[x][y]);				
			}
		}
		odeur = caseCourrante.isOdeur();
		vent = caseCourrante.isVent();
		brillant = caseCourrante.isBrillante();
		crevasse = false;
		for(Element elt : caseCourrante.getContenu()) {
			if(elt instanceof Crevasse) {
				crevasse = true;
			}
		}		
	}
	
	public List<Case> observerForet() {
		return casesForet;
	}

	public void setCasesNonVisitees(List<Case> casesNonVisitees) {
		this.casesForet = casesNonVisitees;
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
