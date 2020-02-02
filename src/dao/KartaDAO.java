package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Karta;

public class KartaDAO {

	public static Karta get(int id) {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM karta WHERE id = ? AND obrisan = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();

			if (rset.next()) {

				int index = 1;
				Integer projekcijaId = rset.getInt(index++);
				Integer sediste = rset.getInt(index++);
				Date datumIvreme = rset.getDate(index++);
				Integer korisnik = rset.getInt(index++);
				Boolean obrisan = rset.getBoolean(index++);

				return new Karta(id, projekcijaId, sediste, datumIvreme, korisnik, obrisan);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return null;
	}

	public static ArrayList<Karta> getAll() {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Karta> karte = new ArrayList<Karta>();
		try {
			String query = "SELECT * FROM karta WHERE obrisan = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				Integer id = rset.getInt(index++);
				Integer projekcijaId = rset.getInt(index++);
				Integer sediste = rset.getInt(index++);
				Date datumIvreme = rset.getDate(index++);
				Integer korisnik = rset.getInt(index++);
				Boolean obrisan = rset.getBoolean(index++);

				karte.add(new Karta(id, projekcijaId, sediste, datumIvreme, korisnik, obrisan));
			}
			return karte;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return null;
	}

	public static boolean add(Karta karta) {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO karta (projekcijaId, sediste, obican_korisnik, obrisan)" + "VALUES (?, ?, ? ,?)";
			pstmt = conn.prepareStatement(query);

			int index = 1;
			pstmt.setInt(index++, karta.getProjekcijaId());
			pstmt.setInt(index++, karta.getSediste());

			pstmt.setInt(index++, karta.getKorisnik());
			pstmt.setBoolean(index++, karta.isObrisan());

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return false;
	}

	public static boolean update(Karta karta) {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE karta SET = projekcijaId = ?, sediste = ?, obican_korisnik = ?, obrisan = ? WHERE id = ?";
			pstmt = conn.prepareStatement(query);

			int index = 1;
			pstmt.setInt(index++, karta.getProjekcijaId());
			pstmt.setInt(index++, karta.getSediste());
			pstmt.setInt(index++, karta.getKorisnik());
			pstmt.setBoolean(index++, karta.isObrisan());
			pstmt.setInt(index++, karta.getId());

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return false;
	}

	public static boolean delete(Karta karta) {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE karta SET obrisan = ? WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setBoolean(index++, true);
			pstmt.setInt(index++, karta.getId());

			return pstmt.executeUpdate() == 1;

		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return false;
	}

}
