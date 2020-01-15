package model;

import java.util.Date;

public class Karta {
	
	private int id;
	private int projekcijaId;
	private int sediste;
	private Date datumIvreme;
	private int korisnik;
	private boolean obrisan;
	
	
	
	
	public Karta(int id, int projekcijaId, int sediste, Date datumIvreme, int korisnik, boolean obrisan) {
		super();
		this.id = id;
		this.projekcijaId = projekcijaId;
		this.sediste = sediste;
		this.datumIvreme = datumIvreme;
		this.korisnik = korisnik;
		this.obrisan = obrisan;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public int getProjekcijaId() {
		return projekcijaId;
	}


	public void setProjekcijaId(int projekcijaId) {
		this.projekcijaId = projekcijaId;
	}


	public int getSediste() {
		return sediste;
	}
	public void setSediste(int sediste) {
		this.sediste = sediste;
	}
	public Date getDatumIvreme() {
		return datumIvreme;
	}
	public void setDatumIvreme(Date datumIvreme) {
		this.datumIvreme = datumIvreme;
	}
	public int getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(int korisnik) {
		this.korisnik = korisnik;
	}


	public boolean isObrisan() {
		return obrisan;
	}


	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
	

	
}
