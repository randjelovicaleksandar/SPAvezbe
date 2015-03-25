package osnovnikoncepti;

/**
 * Implementacija metode koja racuna proizvod 2 cela broja preko operacije
 * sabiranje
 * 
 * @author Dejan Stojimirovic, Strukture podataka i algoritmi, FON, 2014
 * 
 */
public class Mnozenje {

	// enum (enumeracija) kao tip podatka
	// u memoriji se cuva kao int
	// Pozitivan = 0;
	// Negativan = 1;
	public enum Znak {
		Pozitivan, Negativan
	};

	/**
	 * Metoda koja mnozi 2 cela broja. Primer sa enumeracijom
	 * 
	 * @param a
	 *            mnozilac 1
	 * @param b
	 *            mnozilac 2
	 * @return rezultat mnozenja dva cela broj
	 */
	public static int vratiProizvod(int a, int b) {
		// inicijalizacija promenljivih
		int rezultat = 0;
		Znak znak = Znak.Pozitivan;

		// proveravamo da li je jedan od ulaznih parametara 0
		if (a == 0 || b == 0)
			// ako jeste onda vracamo rezultat koji je jednak 0 zbog pocetne
			// inicijalizacije
			return rezultat; // return 0;

		// proveravamo znakove ulaznih parametara
		if ((a < 0 && b > 0) || (a > 0 && b < 0)) { // if(a*b < 0){
			// ako su znakovi razliciti, proizvod treba da bude negativan
			znak = Znak.Negativan;
		}

		// nalazimo apsolutne vrednosti promenljivih zbog kasnijeg koriscenja u
		// petlji
		a = Math.abs(a); // if(a < 0)
		b = Math.abs(b); // a = a * -1;

		// vrsimo sabiranje
		// ********************* I nacin *********************
		for (int i = 0; i < b; i++) {
			rezultat += a;
		}
		// ********************* kraj I nacin *********************

		// ********************* II nacin *********************
		// int i = 0;
		// while (i < b) {
		// rezultat += a;
		// i++;
		// }
		// ********************* kraj II nacin *********************

		// proveravamo znak rezultata i ako je negativan vracamo proizvod
		// rezultata i broja -1

		// ********************* I nacin *********************
		if (znak == Znak.Negativan) {
			return -1 * rezultat;
		}
		// vracamo rezultat mnozenja
		return rezultat;
		// ********************* kraj I nacin *********************

		// ********************* II nacin *********************
		// switch (znak) {
		// case Pozitivan:
		// return rezultat;ndem
		// default:
		// return rezultat * (-1);
		// }
		// ********************* kraj II nacin *********************
	}

	/**
	 * Metoda koja mnozi 2 cela broja. Primer bez enumeracije
	 * 
	 * @param a
	 *            mnozilac 1
	 * @param b
	 *            mnozilac 2
	 * @return rezultat mnozenja dva cela broj
	 */
	public static int vratiProizvod2(int a, int b) {
		// inicijalizacija promenljivih
		int rezultat = 0;
		int znak = 1; // ako je pozitivan 1, ako je negativan -1

		if (a == 0 || b == 0)
			return rezultat;

		// proveravamo znakove ulaznih parametara
		if ((a < 0 && b > 0) || (a > 0 && b < 0)) { // if(a*b < 0){
			// ako su znakovi razliciti, proizvod treba da bude negativan
			znak = -1;
		}

		a = Math.abs(a); // if(a < 0)
		b = Math.abs(b); // a = a * -1;

		for (int i = 0; i < b; i++) {
			rezultat += a;
		}

		// vracamo rezultat * znak
		// Posto smo uzeli apsolutne vrednosti oba parametra, rezultat je uvek
		// pozitivan. Za znak smo rekli da ima vrednost ili 1 ili -1. Zbog toga
		// samo pomnozimo rezultat i znak. Ako proizvod treba da bude pozitivan,
		// znak ce ima vrednost 1, samim tim vrednost koja se vraca se nece
		// promeniti. Ako proizvod treba da bude negativan, vrednost koja se
		// vraca je negativna negativna jer promenljiva znak ima vrednost -1.

		return rezultat * znak;
	}
	
	public static void main(String[] args) {
			System.out.println(Mnozenje.vratiProizvod(-5, 20));
	}

}
