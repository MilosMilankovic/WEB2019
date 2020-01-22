package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Film;

public class FilmDAO {

	
	public static Film get(int id) {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
				String query = "SELECT * FROM film WHERE id = ? AND obrisan = ?";
				
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, id);
				pstmt.setBoolean(2, false);
				rset = pstmt.executeQuery();
				
				if (rset.next()) {
					int index = 2;
					String naziv = rset.getString(index++);
					String reziser = rset.getString(index++);
					String glumci = rset.getString(index++);
					String zanrovi = rset.getString(index++);
					Integer trajanje = rset.getInt(index++);
					String distributer = rset.getString(index++);
					String zemljaPorekla = rset.getString(index++);
					Integer godinaProizvodnje = rset.getInt(index++);
					String opis = rset.getString(index++);
					boolean obrisan = rset.getBoolean(index++);
					
					return new Film(id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan);
				}
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return null;
	}
	
	
	
	public static ArrayList<Film> getAll() {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Film> filmovi = new ArrayList<Film>();
		try {
				String query = "SELECT * FROM film WHERE obrisan = ?";
				
				pstmt = conn.prepareStatement(query);
				pstmt.setBoolean(1, false);
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					int index = 1;
					Integer id = rset.getInt(index++);
					String naziv = rset.getString(index++);
					String reziser = rset.getString(index++);
					String glumci = rset.getString(index++);
					String zanrovi = rset.getString(index++);
					Integer trajanje = rset.getInt(index++);
					String distributer = rset.getString(index++);
					String zemljaPorekla = rset.getString(index++);
					Integer godinaProizvodnje = rset.getInt(index++);
					String opis = rset.getString(index++);
					boolean obrisan = rset.getBoolean(index++);
					
					filmovi.add(new Film(id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan));
				}
				return filmovi;
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	
	public static boolean add(Film film) {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
				String query = "INSERT INTO film (naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis)"
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query);
				
				int index = 1;
				
				pstmt.setString(index++, film.getNaziv());
				pstmt.setString(index++, film.getReziser());
				pstmt.setString(index++, film.getGlumci());
				pstmt.setString(index++, film.getZanrovi());
				pstmt.setInt(index++, film.getTrajanje());
				pstmt.setString(index++, film.getDistributer());
				pstmt.setString(index++, film.getZemljaPorekla());
				pstmt.setInt(index++, film.getGodinaProizvodnje());
				pstmt.setString(index++, film.getOpis());
				//pstmt.setBoolean(index++, film.isObrisan());
				
				return pstmt.executeUpdate() == 1;
	
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu ovde?");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return false;
	}
	
	
	
	public static boolean update(Film film) {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
				String query = "UPDATE film SET naziv = ?, reziser = ?, glumci = ?, zanrovi = ?, trajanje = ?, distributer = ?, zemljaPorekla = ?, godinaProizvodnje = ?, opis = ?, obrisan = ? WHERE id = ?";
				pstmt = conn.prepareStatement(query);
				
				int index = 1;
				pstmt.setString(index++, film.getNaziv());
				pstmt.setString(index++, film.getReziser());
				pstmt.setString(index++, film.getGlumci());
				pstmt.setString(index++, film.getZanrovi());
				pstmt.setInt(index++, film.getTrajanje());
				pstmt.setString(index++, film.getDistributer());
				pstmt.setString(index++, film.getZemljaPorekla());
				pstmt.setInt(index++, film.getGodinaProizvodnje());
				pstmt.setString(index++, film.getOpis());
				pstmt.setBoolean(index++, film.isObrisan());
				pstmt.setInt(index++, film.getId());
				
				return pstmt.executeUpdate() == 1;
		
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	
	

	public static boolean delete(Film film) {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
				String query = "UPDATE film SET obrisan = ? WHERE id = ?";
				pstmt = conn.prepareStatement(query);
				
				int index = 1;
				pstmt.setBoolean(index++, true);
				pstmt.setInt(index++, film.getId());
				
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
