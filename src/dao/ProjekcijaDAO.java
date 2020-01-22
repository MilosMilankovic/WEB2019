package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Projekcija;
import model.TipProjekcije;

public class ProjekcijaDAO {

	public static Projekcija get(int id) {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
				String query = "SELECT * FROM projekcija WHERE id = ? AND obrisan = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, id);
				pstmt.setBoolean(2, false);
				rset = pstmt.executeQuery();
				
				if (rset.next()) {
					
					int index = 1; //iz baze
					int film = rset.getInt(index);
					Integer tipProjekcije = rset.getInt(index++);
					Integer sala = rset.getInt(index++);
					Date datumIvreme = rset.getDate(index++);
					Integer cenaKarte = rset.getInt(index++);
					Integer administrator = rset.getInt(index++);
					Boolean obrisan = rset.getBoolean(index++);
					
					return new Projekcija(id, film, tipProjekcije, sala, datumIvreme, cenaKarte, administrator, obrisan);
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
	
	
	public static ArrayList<Projekcija> getAll() {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Projekcija> projekcije = new ArrayList<Projekcija>();
		try {
				String query = "SELECT * FROM projekcija WHERE obrisan = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setBoolean(1, false);
				rset = pstmt.executeQuery();
				
				while (rset.next()) {
					int index = 1;
					Integer id = rset.getInt(index++);
					int film = rset.getInt(index++);
					Integer tipProjekcije = rset.getInt(index++);
					Integer sala = rset.getInt(index++);
					String time = rset.getString(index++);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					Date datumIvreme = sdf.parse(time);
					
					//Date datumIvreme = rset.getDate(index++);
					Integer cenaKarte = rset.getInt(index++);
					Integer administrator = rset.getInt(index++);
					Boolean obrisan = rset.getBoolean(index++);
					
					projekcije.add(new Projekcija(id, film, tipProjekcije, sala, datumIvreme, cenaKarte, administrator, obrisan));
				}
				return projekcije;
		} catch (ParseException ex) {
			System.out.println("Greska u parsiranju datuma!");
			ex.printStackTrace();		
		
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
	} 
	
	
	
	public static boolean add(Projekcija projekcija) {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
				String query = "INSERT INTO projekcija (film, tipProjekcije, sala, datumIvreme, cenaKarte, administrator, obrisan)"
								+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query);
				
				int index = 1;
				
				pstmt.setInt(index++, projekcija.getFilm());
				pstmt.setInt(index++, projekcija.getTipProjekcije());
				pstmt.setInt(index++, projekcija.getSala());
				//java.sql.Date date = new java.sql.Date(new Date().getTime());
				SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String formattedTime = output.format(projekcija.getDatumIvreme());
				
				pstmt.setString(index++, formattedTime);
				pstmt.setInt(index++, projekcija.getCenaKarte());
				pstmt.setInt(index++, projekcija.getAdministrator());
				pstmt.setBoolean(index++, projekcija.isObrisan());
				
				return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	
	public static boolean update(Projekcija projekcija) {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
				String query = "UPDATE projekcija SET  film = ?, tipProjekcije = ?, sala = ?, cenaKarte = ?, administrator = ?, obrisan = ? WHERE id = ?";
				pstmt = conn.prepareStatement(query);
				
				int index = 1;
				pstmt.setInt(index++, projekcija.getFilm());
				pstmt.setInt(index++, projekcija.getId());
				pstmt.setInt(index++, projekcija.getSala());
				pstmt.setInt(index++ , projekcija.getCenaKarte());
				pstmt.setInt(index++, projekcija.getAdministrator());
				pstmt.setBoolean(index++, projekcija.isObrisan());
				pstmt.setInt(index++, projekcija.getId());
				
				return pstmt.executeUpdate() == 1;
		
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return false;
	}
	
	
	public static boolean delete(Projekcija projekcija) {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE projekcija SET obrisan = ? WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setBoolean(index++, true);
			pstmt.setInt(index++, projekcija.getId());
			
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
	
	

