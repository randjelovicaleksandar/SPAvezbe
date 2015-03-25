package linearnestrukture.staticke;

/**
 * Implementacija Reda na staticki nacin koriscenjem niza. Red je linearna
 * struktura koja funkcionise po FIFO (First In First Out) principu, odnosno
 * prvi element koji je ubacen u red se prvi izbacuje. Elementi se ubacuju na
 * jednom, a izbacuju sa drugog kraja strukture.
 * 
 * @author Dejan Stojimirovic, Strukture podataka i algoritmi, FON, 2014
 * 
 */
public class StatickiRed {
	// niz u kome se cuvaju elementi reda
	private int[] niz;
	// indeks prvog elementa u redu (pocetak reda)
	private int p;
	// indeks poslednjeg elementa u redu (kraj reda)
	private int k;
	// broj elemenata u redu
	private int brElemenata;

	/**
	 * Konstruktor. Inicijalizuje sve promenljive koje se koriste u
	 * implementaciji
	 * 
	 * @param kapacitet
	 *            Maksimalni broj elemenata u redu
	 */
	public StatickiRed(int kapacitet) {
		// kapacitet predstavlja najveci broj elemenata koji se moze ubaciti u
		// stek
		niz = new int[kapacitet];
		brElemenata = 0;
		// Prilikom inicijalizacije niza nemamo unetih elemenata, a p je indeks
		// elementa na pocetku reda, tj. elementa koji se prvi izbacuje iz reda.
		// Posto ce se prvi element ubaciti na mesto sa indeksom 0, zato
		// vrednost promenljive p postavljano na 0.
		p = 0;
		// Prilikom inicijalizacije niza nemamo unetih elemenata, a
		// indeksi u nizu krecu od 0, mi postavljamo k na -1 zato sto taj
		// indeks ne postoji u nizu
		k = -1;
	}

	/**
	 * Proverava da li je red pun
	 * 
	 * @return true ako je red pun. U suprotnom false.
	 */
	public boolean punRed() {
		// Red je pun kada je broj elemenata u redu jednak najvecem broju
		// elemenata koji se mogu ubaciti
		return brElemenata == niz.length;
	}

	/**
	 * Proverava da li je red prazan.
	 * 
	 * @return true ako je red prazan. U suprotnom false.
	 */
	public boolean prazanRed() {
		// Red je prazan kada je broj elemenata u redu jednak 0.
		return brElemenata == 0;
	}

	/**
	 * Ubacuje novi element u red
	 * 
	 * @param element
	 *            vrednost koja se ubacuje u red
	 * @return true ako je element ubacen, false ako nema mesta (red je pun)
	 */
	public boolean enqueue(int element) {
		// Kada ubacujemo novi element u red koji je implementiran pomocu niza
		// (staticka struktura, sto znaci da se unapred zna najveci broj
		// elemenata), moramo da proverimo da li je red pun. Ako je pun, ne
		// moze se ubaciti novi element
		if (punRed())
			return false;

		// Ako red nije pun, onda prvo pomeramo k za jedno mesto unapred (k
		// pokazuje na mesto gde je ubacen poslednji element. Prvo prazno mesto
		// je odmah nakon tog elementa) i na tu poziciju ubacujemo novi element.
		// Posto je red "ciklicna" struktura, u slucaju da smo dosli do kraja
		// niza, tj. kada k dobije vrednost jednaku kapacitetu niza, k vracamo
		// na 0. Nakon toga povecamo broja elemenata u redu za jedan.

		// ****************** prvi nacin *****************************
		k++;
		if (k == niz.length)
			k = 0;
		niz[k] = element;
		brElemenata++;
		return true;

		// ****************** drugi nacin *****************************
		// niz[k = ++k % niz.length] = element;
		// brElemenata++;
		// return true;
	}

	/**
	 * Izbacuje prvi element iz reda (element na pocetku)
	 * 
	 * @return izbaceni element sa pocetka reda.
	 * @throws Exception
	 *             Ako je red prazan, baca se izuzetak.
	 */
	public int dequeue() throws Exception {
		// Kada hocemo da izbacimo element iz reda, moramo prvo proveriti da li
		// postoje element u redu, tj. moramo da proverimo da red nije prazan
		if (prazanRed())
			throw new Exception("Red je prazan");

		// Element iz reda izbacujemo tako sto p (promenljiva koja pokazuje na
		// prvi element u redu) pomerimo za jedno mesto napred. Posto je red
		// "ciklicna" struktura, u slucaju da smo dosli do kraja
		// niza, tj. kada p dobije vrednost jednaku kapacitetu niza, p vracamo
		// na 0, a nakon toga smanjimo brojac elemenata u redu za 1.
		int podatak = niz[p];
		p++;
		if (p == niz.length)
			p = 0;
		brElemenata--;
		return podatak;

		// ****************** drugi nacin *****************************
		// p = ++p % niz.length;
		// brElemenata--;
		// return podatak;
	}

	/**
	 * Vraca element koji se nalazi na pocetku reda
	 * 
	 * @return prvi element u redu.
	 * @throws Exception
	 *             Ako je red prazan, baca se izuzetak.
	 */
	public int pocetak() throws Exception {
		// Da bi smo dobili prvi ubaceni element, potrebno je da prvo
		// proverimo da li red nije prazan, tj da proverimo da li u redu
		// postoje elementi
		if (prazanRed())
			throw new Exception("Red je prazan");

		return niz[p];
	}

	/**
	 * Vraca element koji se nalazi na kraju reda
	 * 
	 * @return poslednji element u redu.
	 * @throws Exception
	 *             Ako je red prazan, baca se izuzetak.
	 */
	public int kraj() throws Exception {
		// Da bi smo dobili poslednji ubaceni element, potrebno je da prvo
		// proverimo da li red nije prazan, tj da proverimo da li u redu
		// postoje elementi
		if (prazanRed())
			throw new Exception("Red je prazan");

		return niz[k];
	}

	/**
	 * Ispisuje sve elemente reda od pocetka do kraja (for petlja)
	 */
	public void ispisi() {
		if (prazanRed())
			return;
		for (int i = p; i != k;) {
			System.out.println(niz[i]);
			// prvi nacin
			// i = ++i % niz.length;

			// drugi nacin
			i++;
			if (i == niz.length)
				i = 0;
		}
		
		System.out.println(niz[k]);
	}

	/**
	 * Ispisuje sve elemente reda od pocetka do kraja (while petlja)
	 */
	public void ispisi2() {
		int i = p;
		int br = 0;
		while (br < brElemenata) {
			System.out.println(niz[i]);
			br++;

			// prvi nacin
			// i = ++i % niz.length;

			// drugi nacin
			i++;
			if (i == niz.length)
				i = 0;
		}
	}

}
