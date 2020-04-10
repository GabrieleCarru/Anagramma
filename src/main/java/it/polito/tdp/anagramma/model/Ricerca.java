package it.polito.tdp.anagramma.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Ricerca {
	
	private List<String> soluzione;
	
	/**
	 * Genera tutti gli anagrammi della parola specificata in ingresso
	 * @param parola: parola da anagrammare
	 * @return elenco di tutte gli anagrammi della parola specificata
	 */
	public List<String> anagrammi(String parola) {
		 this.soluzione = new ArrayList<String>();
		
		 //caso iniziale
		 parola = parola.toUpperCase();
		 List<Character> lettereDisponibili = new ArrayList<Character>();
		 for(int i=0; i<parola.length(); i++) {
			 lettereDisponibili.add(parola.charAt(i));
		 }
		 
		 //avvio la ricorsione
		 cerca("", 0, lettereDisponibili);
		 
		return this.soluzione;
	}
	
	/**
	 * Procedura ricorsiva per il calcolo dell'anagramma
	 * @param parziale : parte iniziale dell'anagramma costruito finora
	 * @param livello : livello della ricorsione, sempre uguale a parziale.lenght()
	 * @param lettereDisponibili : insieme delle lettere non ancora utilizzate
	 */
	private void cerca(String parziale, int livello, List<Character> lettereDisponibili) {
		if(lettereDisponibili.size() == 0) { 
			//caso terminale
			this.soluzione.add(parziale);
			
		}
		
		//caso normale
		// provare ad aggiugnere alla soluzione parziale tutti i caratteri presenti tra 'lettereDisponibili'
		for(Character ch : lettereDisponibili) {
			// il fatto che sia una stringa implica che sia immutabile, dunque creo 'tentativo'
			// e, non avendo modificato la stringa 'parziale', non devo fare backtracking!
			String tentativo = parziale + ch;
			
			// non posso rimuovere da una lista mentre sto iterando
			List<Character> rimanenti = new ArrayList<>(lettereDisponibili);
			rimanenti.remove(ch);
			
			cerca(tentativo, livello+1, rimanenti);
		}
	}
	
	
	
}

/*
Dato di partenza: parola da anagrammare, di lunghezza N
Soluzione parziale: una parte dell'anagramma già costruito (i primi caratteri).
Livello: numero di lettere di cui è composta la soluzione parziale.
Soluzione finale: soluzione di lunghezza N -> caso terminale
Caso terminale: salvare la soluzione trovate
Generazione delle nuove soluzioni: provare ad aggiungere una lettera, scegliendola
tra quelle che non sono ancora state utilizzate (nella soluzione parziale).
*/