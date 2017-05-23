package it.polito.tdp.carsharing.model;

import java.util.PriorityQueue;

import it.polito.tdp.carsharing.model.Event.EventType;

public class Simulator {

	// PARAMETRI DI SIMULAZIONE
	// posso fare sia get che set di queste variabili
	private int NC;  //numero di auto totali
	private int TRAVEL_MIN_TIME = 60;  //1 ora
	private int TRAVEL_MAX_NUM = 3;  //numero massimo di TRAVEL_MIN_TIME per cui l'auto puo` essere affittata
	
	// STATO DEL MODELLO
	//non e` necessario nemmeno il get di queste variabili
	private int autoPresenti ;  //ancora presenti in deposito
	
	// VARIABILI DI INTERESSE  ( che alla fine della simulazione mi interessera` conoscere)
	// di queste variabili mi serve solo il get (non il set, non le posso impostare!)
	private int clientiTot =0;					// numero di clienti totali arrivati al deposito (serviti o meno)
	private int clientiInsoddisfatti = 0; 		// numero di clienti arrivati ma non serviti
	
	// LISTA DI EVENTI
	PriorityQueue <Event> queue;   //non serve che sia visibile all'esterno (non faccio get)

	/**
	 * Costruttore: costruisce un nuovo simulatore
	 * @param nC
	 */
	public Simulator(int nC) {
		super();
		NC = nC;
		this.autoPresenti = this.NC;
		this.queue = new PriorityQueue<>();
	}
	
	/**
	 * Permette di caricare i clienti previsti in arrivo, all'inizio della simulazione
	 * @param time
	 */
	public void addCliente(int time){
		queue.add(new Event (time, EventType.NUOVO_CLIENTE));
	}
	
	public void run(){
		
		while(!queue.isEmpty()){
			Event e = queue.poll();  //prendo l'evento in testa alla coda 
									//(nella coda prioritaria sono gia in ordine di priorita (cioe di tempo)
			//process event
			switch(e.getType()){	// lo switch e` una forma breve di una serie di if (si sposa bene con le enumerazioni)
			
			case NUOVO_CLIENTE:
				
				if(autoPresenti==0){
					//non ho piu` auto
					this.clientiInsoddisfatti++;
					System.out.format("Time %d - cliente insoddisfatto\n", e.getTime());
				
				} else{
					
					//affitta un'auto;
					this.clientiTot++;
					this.autoPresenti--;
					
					int durata = this.TRAVEL_MIN_TIME * (int) (1 + Math.random() * TRAVEL_MAX_NUM);   
					// Math.random() restituisce un numero tra 0 (incluso) e 1 (escluso)
					// se lo moltiplico per TRAVEL_MAX_NUM ho un numero tra 0 (incluso) e 3 (escluso)
					// aggiungo + 1 cosi avro` un numero tra 1 (incluso) e 4 (escluso)
					// faccio cast ad intero perche e` un double
					// in questo modo ho creato un numero casuale compreso tra 1 e il TRAVEL_MAX_NUM 
					
					
					// quando affitto un auto devo anche prevendere un nuovo evento ( quando l'auto verra` restituita)
					// quindi si crea un nuovo evento (di tipo AUTO_RESTITUITA) che si verifichera` nel futuro, al tempo (presente + durata)
					queue.add(new Event (e.getTime() + durata , EventType.AUTO_RESTITUITA));  
					System.out.format("Time %d - auto prestata (rientra alle %d) - auto rimamanenti %d\n", e.getTime(), e.getTime() + durata, this.autoPresenti);
				}
				
				break;	 // se non inserisco il break, quando entro ( ed eseguo) un case poi eseguirei anche gli altri
			
			case AUTO_RESTITUITA:
				this.autoPresenti++;
				System.out.format("Time %d - auto restituita - auto rimamanenti %d\n", e.getTime(), this.autoPresenti);
				break;
			}
		}
	}

	/**
	 * @return the nC
	 */
	public int getNC() {
		return NC;
	}

	/**
	 * @param nC the nC to set
	 */
	public void setNC(int nC) {
		NC = nC;
		this.autoPresenti = nC;
	}

	/**
	 * @return the tRAVEL_MIN_TIME
	 */
	public int getTRAVEL_MIN_TIME() {
		return TRAVEL_MIN_TIME;
	}

	/**
	 * @param tRAVEL_MIN_TIME the tRAVEL_MIN_TIME to set
	 */
	public void setTRAVEL_MIN_TIME(int tRAVEL_MIN_TIME) {
		TRAVEL_MIN_TIME = tRAVEL_MIN_TIME;
	}

	/**
	 * @return the tRAVEL_MAX_NUM
	 */
	public int getTRAVEL_MAX_NUM() {
		return TRAVEL_MAX_NUM;
	}

	/**
	 * @param tRAVEL_MAX_NUM the tRAVEL_MAX_NUM to set
	 */
	public void setTRAVEL_MAX_NUM(int tRAVEL_MAX_NUM) {
		TRAVEL_MAX_NUM = tRAVEL_MAX_NUM;
	}

	/**
	 * @return the clientiTot
	 */
	public int getClientiTot() {
		return clientiTot;
	}

	/**
	 * @return the clientiInsoddisfatti
	 */
	public int getClientiInsoddisfatti() {
		return clientiInsoddisfatti;
	}
	
	
	
	
	
	
	
	
}
