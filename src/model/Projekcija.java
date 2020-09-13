package model;

import java.util.Date;

public class Projekcija {

	
	private int id;
	private int film;
	private int tipProjekcije;
	private int sala;
	private Date datumIvreme;
	private int cenaKarte;
	private int administrator;
	private boolean obrisan;
	
	
	
	
	
	public Projekcija(int id, int film, int tipProjekcije, int sala, Date datumIvreme, int cenaKarte,
			int administrator, boolean obrisan) {
		super();
		this.id = id;
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datumIvreme = datumIvreme;
		this.cenaKarte = cenaKarte;
		this.administrator = administrator;
		this.obrisan = obrisan;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFilm() {
		return film;
	}
	public void setFilm(int film) {
		this.film = film;
	}
	public int getTipProjekcije() {
		return tipProjekcije;
	}
	public void setTipProjekcije(int tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}
	public int getSala() {
		return sala;
	}
	public void setSala(int sala) {
		this.sala = sala;
	}
	public Date getDatumIvreme() {
		return datumIvreme;
	}
	public void setDatumIvreme(Date datumIvreme) {
		this.datumIvreme = datumIvreme;
	}
	public int getCenaKarte() {
		return cenaKarte;
	}
	public void setCenaKarte(int cenaKarte) {
		this.cenaKarte = cenaKarte;
	}
	public int getAdministrator() {
		return administrator;
	}
	public void setAdministrator(int administrator) {
		this.administrator = administrator;
	}



	public boolean isObrisan() {
		return obrisan;
	}



	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}



	@Override
	public String toString() {
		return "Projekcija [id=" + id + ", film=" + film + ", tipProjekcije=" + tipProjekcije + ", sala=" + sala
				+ ", datumIvreme=" + datumIvreme + ", cenaKarte=" + cenaKarte + ", administrator=" + administrator
				+ ", obrisan=" + obrisan + "]";
	}
	
	
	
	
}
