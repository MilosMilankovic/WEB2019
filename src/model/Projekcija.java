package model;

public class Projekcija {

	
	private int id;
	private String film;
	private TipProjekcije tipProjekcije;
	private Sala sala;
	private String datumIvreme;
	private int cena;
	private Korisnik admin;
	
	
	
	
	
	public Projekcija(int id, String film, TipProjekcije tipProjekcije, Sala sala, String datumIvreme, int cena,
			Korisnik admin) {
		super();
		this.id = id;
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datumIvreme = datumIvreme;
		this.cena = cena;
		this.admin = admin;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilm() {
		return film;
	}
	public void setFilm(String film) {
		this.film = film;
	}
	public TipProjekcije getTipProjekcije() {
		return tipProjekcije;
	}
	public void setTipProjekcije(TipProjekcije tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public String getDatumIvreme() {
		return datumIvreme;
	}
	public void setDatumIvreme(String datumIvreme) {
		this.datumIvreme = datumIvreme;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public Korisnik getAdmin() {
		return admin;
	}
	public void setAdmin(Korisnik admin) {
		this.admin = admin;
	}
	
	
	
}
