package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import com.sun.org.apache.regexp.internal.recompile;

import model.Korisnik;
import enums.Uloga;

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

				int index = 1;
				int id = rset.getInt(index);
				index += 2;
				String lozinka = rset.getString(index++);
				String time = rset.getString(index++);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Date datumRegistracije = sdf.parse(time);
				String uloga = Uloga.valueOf(rset.getString(index++).toUpperCase()).toString();
				boolean obrisan = rset.getBoolean(index++);
				System.out.println(id + " " + korisnickoIme + " " + lozinka + " " + datumRegistracije + " " + uloga
						+ " " + obrisan);
				return new Korisnik(id, korisnickoIme, lozinka, datumRegistracije, uloga, obrisan);
			}
		} catch (ParseException ex) {
			System.out.println("Greska u parsiranju datuma!");
			ex.printStackTrace();
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
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String korisnickoIme = rset.getString(index++);
				String lozinka = rset.getString(index++);
				String time = rset.getString(index++);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Date datumRegistracije = sdf.parse(time);
				//Date datumRegistracije = rset.getDate(index++);
				String uloga = Uloga.valueOf(rset.getString(index++).toUpperCase()).toString();
				
				boolean obrisan = rset.getBoolean(index++);

				korisnici.add(new Korisnik(id, korisnickoIme, lozinka, datumRegistracije, uloga, obrisan));
			}
			return korisnici;
			
		}  catch (ParseException ex) {
			System.out.println("Greska u parsiranju datuma!");
			ex.printStackTrace();
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
			String query = "INSERT INTO korisnik (korisnickoIme, lozinka, uloga, obrisan)"
					+ "VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);

			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			
			//pstmt.setDate(index++,  new java.sql.Date(new Date().getTime()));
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

	public static boolean update(Korisnik korisnik) {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE korisnik SET korisnickoIme = ?, lozinka = ?, uloga = ?, obrisan = ? WHERE id = ?";
			pstmt = conn.prepareStatement(query);

			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setString(index++, korisnik.getUloga().toString());
			pstmt.setBoolean(index++, korisnik.isObrisan());
			pstmt.setInt(index++, korisnik.getId());

			return pstmt.executeUpdate() == 1;

		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			
		}

		return false;
	}

	public static boolean delete(int id) {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE korisnik SET obrisan = ? WHERE id = ?";
			pstmt = conn.prepareStatement(query);

			int index = 1;
			pstmt.setBoolean(index++, true);
			pstmt.setInt(index++, id);
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

	
	public static Korisnik getId(int id) {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM korisnik WHERE id = ? AND obrisan = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setBoolean(2, false);

			rset = pstmt.executeQuery();

			if (rset.next()) {

				int index = 2;
				
				String korisnickoIme = rset.getString(index++);
				String lozinka = rset.getString(index++);
				String time = rset.getString(index++);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Date datumRegistracije = sdf.parse(time);
				String uloga = Uloga.valueOf(rset.getString(index++).toUpperCase()).toString();
				boolean obrisan = rset.getBoolean(index++);
				System.out.println(id + " " + korisnickoIme + " " + lozinka + " " + datumRegistracije + " " + uloga
						+ " " + obrisan);
				return new Korisnik(id, korisnickoIme, lozinka, datumRegistracije, uloga, obrisan);
			}
		} catch (ParseException ex) {
			System.out.println("Greska u parsiranju datuma!");
			ex.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
	}
}
