package model;

public class Karta {
	
	private int id;
	private Projekcija projekcija;
	private Sediste sediste;
	private String datumIvreme;
	private Korisnik korisnik;
	
	
	
	
	public Karta(int id, Projekcija projekcija, Sediste sediste, String datumIvreme, Korisnik korisnik) {
		super();
		this.id = id;
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.datumIvreme = datumIvreme;
		this.korisnik = korisnik;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Projekcija getProjekcija() {
		return projekcija;
	}
	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}
	public Sediste getSediste() {
		return sediste;
	}
	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}
	public String getDatumIvreme() {
		return datumIvreme;
	}
	public void setDatumIvreme(String datumIvreme) {
		this.datumIvreme = datumIvreme;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	
	

}
