package linearnestrukture.dinamicke.jslista;

/**
 * Jednostruko spregnuta lista. Dinamicka opsta lista - elementi mogu da se
 * dodaju bilo gde u strukturu i bilo koji element moze da se izbaci. Redosled
 * ubacivanja i izbacivanja nije definisan. Elementi su uvezani preko jedne veze
 * "unapred", tako da je moguce direktno kretanje kroz strukturu samo u jednom
 * smeru od prvog do poslednjeg. Na svaki cvor pokazuje tacno jedan pokazivac,
 * te je pri ubacivanju novog cvora potrebno povezati jedan pokazivac na njega,
 * dok je pri izbacivanju potrebno razvezati jedan pokazivac.
 * 
 * @author Dejan Stojimirovic, Strukture podataka i algoritmi, FON, 2014
 * 
 */
public class JSLista {
	/**
	 * Pokazivav na prvi cvor u listi. Posto su cvorovi medjusobno uvezani,
	 * dovoljno je pamtiti gde se nalazi prvi cvor, pa preko njega pristupiti
	 * ostalim cvorovima.
	 */
	public CvorJSListe prvi;

	public JSLista() {
		prvi = null;
	}

	/**
	 * Proverava da li je lista prazna. Lista je prazna kada ne postoji
	 * pokazivac na prvi cvor.
	 * 
	 * @return true ako je lista prazna. u suprotnom false
	 */
	public boolean praznaLista() {
		return prvi == null;
	}

	/**
	 * Ubacivanje novog elementa na pocetak liste.
	 * 
	 * @param podatak
	 *            Podatak koji ce se cuvati u novom cvoru
	 */
	public void ubaciNaPocetak(int podatak) {
		// Posto kreiramo dinamicku strukturu, nemamo ogranicenje na broj
		// elemenata koji moze da se smesti u nju, pa samim tim ne moramo da
		// proveravamo da li je puta

		// ****************** prvi nacin *****************************
		// Kreiranje novog cvora. Posto se novi cvor kreira na pocetku
		// strukture, njegov sledeci element ce biti trenutno prvi element. Ako
		// je lista prazna, sledeci element od novog ce biti null. Posto je
		// lista prazna, to znaci da je i prvi jednak null, pa samim tip i tu
		// situaciju obuhvatamo na ovaj nacin
		CvorJSListe novi = new CvorJSListe(podatak, prvi);
		// nakon kreiranja cvora postavlja se da je novokreirani cvor prvi cvor.
		prvi = novi;

		// ****************** drugi nacin *****************************
		// prvi = new CvorJSListe(podatak, prvi);

	}

	/**
	 * Ubacivanje novog elementa na kraj liste. Potrebno je naci poslednji cvor
	 * i ubaciti novi nakon njega.
	 * 
	 * @param podatak
	 *            Podatak koji ce se cuvati u novom cvoru
	 */
	public void ubaciNaKraj(int podatak) {
		if (praznaLista()) {
			// ako je lista prazna, algoritam za ubacivanje novog cvora na kraj
			// liste je isti kao i za ubacivanje na pocetak, jer se ubacuje
			// prvi koji je istovremeno i poslednji cvor
			ubaciNaPocetak(podatak);
		} else {
			// pomocni pokazivac preko koga se krece kroz listu
			CvorJSListe pom = prvi;
			// pronalazak poslednjeg cvora u listi (onaj cvor koji nema
			// sledbenika)
			while (pom.sledeci != null) {
				pom = pom.sledeci;
			}
			// kreiranje novog cvora. Posto se ubacuje na kraj liste,
			// onda on nema sledbenika.
			CvorJSListe novi = new CvorJSListe(podatak, null);
			// povezivanje da je novokreirani cvor sledbenik do tada poslednjem
			// cvoru
			pom.sledeci = novi;
		}
	}

	/**
	 * Izbacivanje elementa sa pocetka liste. Element se izbacuje tako sto se
	 * pokazivac na prvi cvor preveze da pokazuje na drugi cvor. Time niko vise
	 * ne pokazuje na taj cvor cime se on brise iz memorije.
	 * 
	 * @return Vrednost iz cvora koji je izbacen.
	 * @throws Exception
	 *             Ako je lista prazna baca se izuzetak.
	 */
	public int izbaciSaPocetka() throws Exception {
		// Prvo moramo proveriti da li u listi ima elemenata. Ako nema
		// elemenata, ne mozemo nista izbaciti.
		// Ako je lista prazna baca se izuzetak.
		if (praznaLista())
			throw new Exception("lista je prazna");

		// sacuvamo podatak iz prvog cvora u pomocnu promenljivu
		int podatak = prvi.podatak;
		// prebacujemo prvi na drugi cvor
		prvi = prvi.sledeci;
		// vracamo sacuvani podatak
		return podatak;
	}

	/**
	 * Izbacivanje elementa sa kraja liste. Da bismo izbacili poslednji cvor
	 * potrebno je da nadjemo pretposlednji cvor i da razvezemo vezu izmedju
	 * pretposlednjeg i poslednjeg cvora.
	 * 
	 * @return Vrednost iz cvora koji je izbacen.
	 * @throws Exception
	 *             Ako je lista prazna baca se izuzetak
	 */
	public int izbaciSaKraja() throws Exception {
		// Prvo moramo proveriti da li u listi ima elemenata. Ako nema
		// elemenata, ne mozemo nista izbaciti.
		// Ako je lista prazna baca se izuzetak.
		if (praznaLista())
			throw new Exception("lista je prazna");

		// ako lista ima samo jedan element, onda se izbacuje prvi
		if (prvi.sledeci == null)
			return izbaciSaPocetka();

		// pronalazimo pretposlednji cvor
		CvorJSListe pom = prvi;
		// pretposlednji cvor ima samo jedan element ispred sebe
		while (pom.sledeci.sledeci != null) {
			pom = pom.sledeci;
		}
		
		int podatak = pom.sledeci.podatak;
		// razvezujemo vezu izmedju pretposlednjeg i poslednjeg
		pom.sledeci = null;
		return podatak;
	}
}
