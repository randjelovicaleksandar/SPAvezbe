package osnovnikoncepti;

/**
 * @author Dejan Stojimirovic, Strukture podataka i algoritmi, FON, 2014
 */
public class Predmet {

	private String naziv;
	private int ocena;

	public Predmet(String naziv, int ocena) {
		this.naziv = naziv;
		this.ocena = ocena;
	}

	public String getNaziv() {
		return naziv;
	}

	public int getOcena() {
		return ocena;
	}

	@Override
	public String toString() {
		return "Predmet " + naziv + " je položen sa ocenom " + ocena;
	}

	@Override
	// ****************** prvi nacin *****************************
	// public boolean equals(Object o) {
	// if (o != null && o instanceof Predmet) {
	// Predmet p = (Predmet) o;
	// if (naziv.equals(p.getNaziv())) {
	// return true;
	// }
	// }
	// return false;
	// }
	// ****************** drugi nacin *****************************
	public boolean equals(Object o) {
		if (o != null && o instanceof Predmet) {
			Predmet p = (Predmet) o;
			// naziv.equals(p.getNaziv()) vec samo po sebi vraca true ili false
			// vrednost, tako da ne moramo da proveravamo da li je vrednost true
			// da bi vratili true samo vratimo ono sto je rezultat poredjenja
			return naziv.equals(p.getNaziv());
		}
		return false;
	}

}
