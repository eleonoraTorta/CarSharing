package it.polito.tdp.carsharing.model;

public class Event implements Comparable<Event> {
	
	//posso creare una classe a parte o un metodo public qui (visto che ci si riferisce agli eventi)
	// e` un metodo enumerativo che consiste in un elenco di costanti
	// in questo modo java controlla automaticamente che i valori assegnati all'attributo tipo facciano parte di questo elenco
	// enum = e` una sorta di classe che assume variabili statiche
	public enum EventType{ NUOVO_CLIENTE, AUTO_RESTITUITA};
	
	private int time;  //minuti dalla mezzanotte
	private EventType type;
	
	

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}



	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}



	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(EventType type) {
		this.type = type;
	}



	/**
	 * @param time
	 * @param type
	 */
	public Event(int time, EventType type) {
		super();
		this.time = time;
		this.type = type;
	}



	// RELAZIONE TR EQUALS E COMPARETO : Se equals =0 —> compareTo =0  (non viceversa!!)
	
	// <0 se this < other (this viene prima di other)
	// =0 se this = other
	// >0 se this > other (this viene dopo ther)
	@Override
	public int compareTo(Event other) {
		return this.time - other.time;
	}

}
