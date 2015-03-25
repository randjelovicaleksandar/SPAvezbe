package linearnestrukture.dinamicke.jslista;

/**
 * Cvor jednostruko spregnute liste. Svaki cvor liste u sebi cuva podatak (kod
 * ovog cvora to je ceo broj) i pokazivac na sledeci cvor.
 * 
 * @author Dejan Stojimirovic, Strukture podataka i algoritmi, FON, 2014
 * 
 */
public class CvorJSListe {
	// podatak koji se cuva u cvoru.
	// Ako cvor treba da cuva neki drugi tip elementa, ovde to treba promeniti
	public int podatak;

	// "pokazivac" na sledeci cvor u listi
	public CvorJSListe sledeci;

	/**
	 * Konstruktor. Prihvata i postavlja sve elemente cvora.
	 * 
	 * @param p Podatak koji se cuva u cvoru.
	 * @param sled Pokazivac na sledeci element za cvor koji se kreira
	 */
	public CvorJSListe(int p, CvorJSListe sled) {
		podatak = p;
		sledeci = sled;
	}
}