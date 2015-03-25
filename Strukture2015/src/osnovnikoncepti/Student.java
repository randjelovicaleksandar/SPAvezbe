package osnovnikoncepti;

import java.util.Arrays;

/**
 * @author Dejan Stojimirovic, Strukture podataka i algoritmi, FON, 2014
 * 
 *         Klasa student predstavlja nekog studenta. Student moze da polaze
 *         ispite, da proverava da li je neki ispit polozio, da racuna prosek
 *         ocena polozenih ispita i da pregleda sve polozene ispite.
 * 
 *         Potrebno je implementirati metode koje studentu omogucavaju navedene
 *         funkcionalnosti.
 */
public class Student {

	private String ime;
	private String brojIndeksa;
	private Predmet[] polozeniPredmeti;
	private int brojPolozenihIspita;

	public Student(String ime, String brojIndeksa, int brojIspita) {
		this.ime = ime;
		this.brojIndeksa = brojIndeksa;
		polozeniPredmeti = new Predmet[brojIspita];
		brojPolozenihIspita = 0;
	}

	@Override
	public String toString() {
		String s = "Student " + ime + " sa brojem indeksa " + brojIndeksa
				+ " je polozio " + brojPolozenihIspita + " predmeta. \n";

		for (int i = 0; i < brojPolozenihIspita; i++) {
			s += "\t" + polozeniPredmeti[i].toString() + '\n';
		}
		return s;
	}

	public void dodajIspit(Predmet p) {
		// u slucaju da je brojPolozenihIspita manji od duzine niza, to znaci da
		// je student nije polozio sve ispite
		if (brojPolozenihIspita < polozeniPredmeti.length) {

			// proveravamo da li je ispit koji ubacujemo vec polozen i ako jeste
			// polozen i ako jeste, prikazujemo poruku na ekranu i iskacemo iz
			// metode
			if (daLiJeIspitPolozen(p)) {
				System.err.println("Predmet je vec unet");
				return;
			}

			// ****************** drugi nacin *****************************
			// mozemo reci da metoda daLiJeIspitPolozen vraca int (umesto
			// boolean) koji ce predstavljati
			// index ispita u nizu za koji nas zanima da li je polozen.

			// U slucaju da ispit nije polozen, recicemo da metoda vraca -1. U
			// slucaju da je vracena
			// vrednost -1 onda, posto ispiti nije polozen, samo dodamo ispit u
			// niz kao sto je to uradjeno i u ovoj metodi.

			// U slucaju da metoda vrati neki drugi broj (koji predstavlja index
			// trazenog predmeta u nizu),
			// onda mozemo zameniti staru ocenu novom ocenom. Pokusajte sami da
			// uradite II nacin. Na ispitu
			// ce biti tacno receno kako ce se implementirati neka metoda. Npr:
			// ako je ispit vec polozen, samo
			// ispisati poruku da je ispit polozen, ili, ako je ispit polozen,
			// zameniti staru ocenu novom

			// NAPOMENA: potrebno je prepraviti i metodu dodajIspit i metodu
			// daLiJeIspitPolozen
			// ****************** kraj drugog nacin
			// *****************************

			// a ako nije polozen, dodajemo ispit u niz. Takodje povecavamo i
			// brojPlozenihIspita za 1
			polozeniPredmeti[brojPolozenihIspita] = p;
			brojPolozenihIspita++;

		} else { // brojPolozenihIspita je jednak duzini niza
			System.err.println("Student je položio sve ispite");
		}
	}

	public void dodajIspit(String naziv, int ocena) {
		dodajIspit(new Predmet(naziv, ocena));

		// ****************** drugi nacin *****************************F
		// Predmet p = new Predmet(naziv, ocena);
		// dodajIspit(p);
	}

	public boolean daLiJeIspitPolozen(Predmet p) {

		// prolazimo kroz niz polozenih predmeta i proveravamo da li se zeljeni
		// predmet nalazi u nizu.
		// Ako se nalazi vracamo true, sto znaci da je polozen u suprotnom
		// vracamo false
		// sto znaci da nije polozen
		for (int i = 0; i < brojPolozenihIspita; i++) {
			if (p.equals(polozeniPredmeti[i])) // pogledajte override metode
				// equals u klasi predmet
				return true;

			// nacin provere bez override-a metode equals u klasi predmet
			// if(p.getNaziv().equals(predmeti[i].getNaziv()))
			// return true;
		}
		return false;
	}

	// metoda rekurzivno proverava da li se trazeni ispit nalazi u nizu,
	// tj. da li je polozen. Ako je polozen, vraca true, a ako nije vraca false
	public boolean daLiJeIspitPolozenRekurzivno(int index, Predmet p) {

		// uslovi iskakanja: ako je index manji od 0 ili >= od
		// brojaPolozenihIspita ili ako nije prosledjen predmet
		if (index < 0 || index > brojPolozenihIspita - 1 || p == null) {
			return false;
		}
		// ako je tekuci predmet jednak predmetu koji se unosi, metoda vraca
		// true
		if (polozeniPredmeti[index].getNaziv().equals(p.getNaziv())) {
			return true;
		} else {
			// ako tekuci ispit nije jednak predmetu koji se unosi, metoda vraca
			// ono sta vrati metoda daLiJeIspitPolozenRekurzivno nad ostatkom
			// niza
			return daLiJeIspitPolozenRekurzivno(index + 1, p);
		}
	}

	// metoda racuna prosek ocena
	public double prosek() {
		if (brojPolozenihIspita == 0)
			return 0;

		// NAPOMENA: TIP REZULTATA DELjENJA 2 PODATKA TIPA INT JE INT, pa je
		// zato
		// ovde stavljeno da je zbir ocena tipa double
		double suma = zbirOcenaRekurzivno(0);
		return suma / brojPolozenihIspita;
	}

	// metoda racuna prosek ocena, s tim sto metoda baca izuzetak ako nije
	// polozeni nijedan ispit
	public double prosekSaIzuzetkom() throws Exception {
		if (brojPolozenihIspita == 0)
			throw new Exception("Nije položen nijedan predmet!!!");
		double suma = zbirOcenaRekurzivno(0);
		return suma / brojPolozenihIspita;
	}

	public int zbirOcena() {
		int zbir = 0;
		// ako je brojPolozenihIspita jednak 0, petlja se nece ni izvrsiti pa ce
		// zbir ostati 0
		for (int i = 0; i < brojPolozenihIspita; i++) {
			zbir += polozeniPredmeti[i].getOcena();
		}
		// vracamo zbir ocena
		return zbir;
	}

	// metoda rekurzivno racuna zbir ocena
	private int zbirOcenaRekurzivno(int i) {
		// uslov iskakanja: ako je indeks veci >= broju polozenih ispita ili
		// manji od 0, metoda vraca 0
		if (i < 0 || i >= brojPolozenihIspita)
			return 0;
		// u suprotnom vraca zbir ocena tekuceg predmeta i ostatka niza
		return polozeniPredmeti[i].getOcena() + zbirOcenaRekurzivno(i + 1);
	}
	
	
	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Student other = (Student) obj;
//		if (brojIndeksa == null) {
//			if (other.brojIndeksa != null)
//				return false;
//		} else if (!brojIndeksa.equals(other.brojIndeksa))
//			return false;
//		if (brojPolozenihIspita != other.brojPolozenihIspita)
//			return false;
//		if (ime == null) {
//			if (other.ime != null)
//				return false;
//		} else if (!ime.equals(other.ime))
//			return false;
//		if (!Arrays.equals(polozeniPredmeti, other.polozeniPredmeti))
//			return false;
//		return true;
//	}

	public static void main(String[] args) {
		Student s = new Student("Dejan", "431/06", 3);
		s.dodajIspit("modelovanje", 10);
		s.dodajIspit(new Predmet("Strukture", 10));
		s.dodajIspit(new Predmet("Strukture", 10));
		s.dodajIspit("Matematika", 6);
		s.dodajIspit("Matematika", 6);
		s.dodajIspit("Matematika 1", 6);
		System.out.println(s.toString());
		System.out.println("Prosek ocena je: " + s.prosek());
	}
}
