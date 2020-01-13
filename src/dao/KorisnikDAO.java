package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.sun.org.apache.regexp.internal.recompile;

import model.Korisnik;
import model.Korisnik.Uloga;

public class KorisnikDAO {

	public static Korisnik get(String korisnickoIme) {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
				String query = "SELECT * FROM korisnik WHERE korisnickoIme = ? AND obrisan = ?";
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, korisnickoIme);
				pstmt.setBoolean(2, false);
				rset = pstmt.executeQuery();
				
				if (rset.next()) {
						
						int index = 2;
						
						String lozinka = rset.getString(index++);
						Date datumRegistracije = rset.getDate(index++);
						Uloga uloga = Uloga.valueOf(rset.getString(index++));
						boolean obrisan = rset.getBoolean(index++);
						
						return new Korisnik(korisnickoIme, lozinka, datumRegistracije, uloga, obrisan);
				}
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	
	
	public static ArrayList<Korisnik> getAll() {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		
		try {
				String query = "SELECT * FROM korisnik WHERE obrisan = ?";
				
				pstmt = conn.prepareStatement(query);
				pstmt.setBoolean(1, false);
				
				while(rset.next()) {
					int index = 1;
					String korisnickoIme = rset.getString(index++);
					String lozinka = rset.getString(index++);
					Date datumRegistracije = rset.getDate(index++);
					Uloga uloga = Uloga.valueOf(rset.getString(index++));
					boolean obrisan = rset.getBoolean(index++);
					
					korisnici.add(new Korisnik(korisnickoIme, lozinka, datumRegistracije, uloga, obrisan));
				}
				return korisnici;
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
		
		
	}
	
	
	public static boolean add(Korisnik korisnik) {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
				String query = "INSERT INTO korisnici (korisnickoIme, lozinka, datumRegistracije, uloga, obrisan)"
								+ "VALUES (?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query);
				
				int index = 1;
				pstmt.setString(index++, korisnik.getKorisnickoIme());
				pstmt.setString(index++, korisnik.getLozinka());
				java.sql.Date date = new java.sql.Date(korisnik.getDatumRegistracije().getTime());
				pstmt.setDate(index++, date);
				pstmt.setString(index++, korisnik.getUloga().toString());
				pstmt.setBoolean(index++, korisnik.isObrisan());
				
				return pstmt.executeUpdate() == 1;
		
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	
	public static boolean update(Korisnik korisnik, String korisnickoIme) {
		
		Connection conn = ConnectionManager.getConnection();
		String korisnickoImeU = (String) korisnickoIme;
		PreparedStatement pstmt = null;
		try {
				String query = "UPDATE korisnici SET korisnickoIme = ?, lozinka = ?, uloga = ?, obrisan = ? WHERE korisnickoIme = ?";
				pstmt = conn.prepareStatement(query);
				
				int index = 1;
				pstmt.setString(index++, korisnik.getKorisnickoIme());
				pstmt.setString(index++, korisnik.getLozinka());
				pstmt.setString(index++, korisnik.getUloga().toString());
				pstmt.setBoolean(index++, korisnik.isObrisan());
				pstmt.setString(index++, korisnickoImeU);
				
				return pstmt.executeUpdate() == 1;
		
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return false;
	}
	
	
	
	public static boolean delete(String korisnickoIme) {
		
		Connection conn = ConnectionManager.getConnection();
		String korisnickoImeU = (String) korisnickoIme;
		
		PreparedStatement pstmt = null;
		try {
				String query = "UPDATE korisnici SET obrisan = 1 WHERE korisnickoIme = ?";
				pstmt = conn.prepareStatement(query);
				
				int index = 1;
				pstmt.setString(index++, korisnickoImeU);
				System.out.println(pstmt);
				
				return pstmt.executeUpdate() == 1;
		
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
				
		return false;
	}
	
	
}
