package linearnestrukture.staticke;

import java.util.EmptyStackException;

/**
 * Implementacija Steka na staticki nacin koriscenjem niza. Stek je linearna
 * struktura koja funkcionise po LIFO (Last In First Out) principu, odnosno
 * poslednji element koji je ubacen na stek se prvi izbacuje. Elementi se
 * ubacuju i izbacuju sa istog kraja strukture.
 * 
 * @author Dejan Stojimirovic, Strukture podataka i algoritmi, FON, 2014
 * 
 */
public class StatickiStek {
	// niz u kome se cuvaju elementi steka
	private int[] niz;

	// indeks poslednjeg ubacenog elementa, tj pokazuje na poslednji uneti
	// element
	private int vrh;

	/**
	 * Konstruktor. Inicijalizuje sve promenljive koje se koriste u
	 * implementaciji
	 * 
	 * @param kapacitet
	 *            maksimalni broj elemenata u steku
	 */
	public StatickiStek(int kapacitet) {
		// kapacitet predstavlja najveci broj elemenata koji se moze ubaciti u
		// stek
		niz = new int[kapacitet];

		// Posto prilikom inicijalizacije niza nemamo unetih elemenata, a
		// indeksi u nizu krecu od 0, mi postavljamo vrh na -1 zato sto taj
		// indeks ne postoji u nizu
		vrh = -1;
	}

	/**
	 * Vraca broj elemenata na steku
	 * 
	 * @return broj elemenata
	 */
	protected int brojElemenata() {
		// Vrh pokazuje indeks u nizu poslednjeg unetog elementa. To znaci da
		// ako imamo jedan element u nizu, vrh ce biti 0. Ako imamo 3 elementa u
		// nizu, vrh ce biti 2. Iz toga zakljucujemo da je broj elemenata u nizu
		// uvek za jedan veci od vrednosti promenjljive vrh, pa zato vracamo
		// vrednost vrh uvecanu za jedan
		return vrh + 1;
	}

	/**
	 * Proverava da li je stek pun
	 * 
	 * @return true ako je stek pun. U suprotnom false
	 */
	public boolean punStek() {
		// Stek je put ako su sva mesta popunjena, a s obzirom na ponasanje
		// steka, pun je ako se nalazi element na poslednjem mestu u nizu. Ako
		// niz ima 5 elemenata, indeks poslednjeg elementa u nizu je 4, sto
		// dovodi do zakljucka da je indeks poslednjeg elementa u nizu uvek za
		// jedan manji od kapaciteta niza, te zato proveravamo da li postoji
		// element na poziciji kapacitet niza umanjen za jedan. Posto smo vec
		// rekli da promenljiva vrh "pokazuje" na mesto gde je ubacen poslednji
		// element u nizu, da li je niz pun proveravamo tako sto uporedimo da li
		// promenljiva vrh ima vrednost poslednjeg indeksa u nizu

		// ****************** prvi nacin *****************************
		// if(vrh == niz.length - 1)
		// return true;
		// else
		// return false;

		// ****************** drugi nacin *****************************
		return (vrh == niz.length - 1);
	}

	/**
	 * Proverava da li je stek prazan.
	 * 
	 * @return true ako je stek prazan. U suprotnom false.
	 */
	public boolean prazanStek() {
		// Indeksi u nizu se krecu od 0 do N i mogu biti samo pozitivan broj.
		// Ako je vrednost promenjive vrh negativan broj (u nasem slucaju -1),
		// posto ne postoji indeks u nizu sa tim brojem, to znaci da je stek
		// prazan.
		return vrh == -1;
	}

	/**
	 * Stavlja novi element na stek (ubacuje novi element)
	 * 
	 * @param element
	 *            vrednost koja se ubacuje na stek
	 * @return true ako je element ubacen, false ako nema mesta (stek je pun)
	 */
	public boolean push(int element) {
		// Kada ubacujemo novi element u stek koji je implementiran pomocu niza
		// (staticka struktura, sto znaci da se unapred zna najveci broj
		// elemenata), moramo da proverimo da li je stek pun. Ako je pun, ne
		// moze se ubaciti novi element
		if (punStek())
			return false;

		// Ako stek nije pun, onda prvo pomeramo vrh za jedno mesto unapred (Vrh
		// pokazuje na mesto gde je ubacen poslednji element. Prvo prazno mesto
		// je odmah nakon tog elementa) i na tu poziciju ubacujemo novi element.

		// ****************** prvi nacin *****************************
		vrh++; // ili vrh = vrh + 1; ili vrh += 1; ili ++vrh;
		niz[vrh] = element;
		return true;

		// ****************** drugi nacin *****************************
		// niz[++vrh] = element;
		// return true;
	}

	/**
	 * Izbacuje poslednje ubaceni element (element na vrhu steka)
	 * 
	 * @return izbaceni element
	 * @throws EmptyStackException
	 *             Ako je stek prazan, baca se izuzetak.
	 */
	public int pop() throws EmptyStackException {
		// Kada hocemo da izbacimo element iz steka, moramo prvo proveriti da li
		// postoje element u steku, tj. moramo da proverimo da stek nije prazan
		if (prazanStek())
			throw new EmptyStackException();

		// Element se izbacuje tako sto se vrednost promenljive vrh smanjuje za
		// jedan, a time kazemo da je posledni ubaceni element onaj element koji
		// je ubacen pre elementa koji se izbacuje

		// ****************** prvi nacin *****************************
		int element = niz[vrh];
		vrh--;
		return element;
		// ****************** drugi nacin *****************************
		// return niz[vrh--];
	}

	/**
	 * Uzima element sa vrha steka bez izbacivanja
	 * 
	 * @return element sa vrha steka
	 * @throws EmptyStackException
	 *             Ako je stek prazan, baca se izuzetak.
	 */
	public int peek() throws EmptyStackException {
		// Da bi smo dobili poslednji ubaceni element, potrebno je da prvo
		// proverimo da li stek nije prazan, tj da proverimo da li u steku
		// postoje elementi
		if (prazanStek())
			throw new EmptyStackException();

		return niz[vrh];
	}

	public static void main(String[] args) {
		StatickiStek ss = new StatickiStek(4);
		ss.push(234);
		ss.pop();
	}

}
