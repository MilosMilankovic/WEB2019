package model;

public class Sediste {

	private int redniBroj;
	private int sala;
	
	
	
	
	
	public Sediste(int redniBroj, int sala) {
		super();
		this.redniBroj = redniBroj;
		this.sala = sala;
	}
	
	
	public int getRedniBroj() {
		return redniBroj;
	}
	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}
	public int getSala() {
		return sala;
	}
	public void setSala(int sala) {
		this.sala = sala;
	}
	
	
	
	@Override
	public String toString() {
		return "Sediste [redniBroj=" + redniBroj + ", sala=" + sala + "]";
	}
	
	
	
}
