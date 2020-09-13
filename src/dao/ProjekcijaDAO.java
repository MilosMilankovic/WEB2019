package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Projekcija;
import model.TipProjekcije;

public class ProjekcijaDAO {

	public static List<Projekcija> projekcijeZaFilm(int idFilm) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Projekcija> dostupneProjekcije = new ArrayList<>();
		List<Projekcija> projekcijeUBuducnosti = new ArrayList<Projekcija>();
		try {
			String query = "SELECT id, film, tipProjekcije, sala, datumIvreme, cenaKarte, administrator, obrisan FROM projekcija WHERE film = ? and datumIvreme > DATETIME('now','localtime') and obrisan = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idFilm);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				Integer id = rset.getInt(index++);
				int film = rset.getInt(index++);
				Integer tipProjekcije = rset.getInt(index++);
				Integer sala = rset.getInt(index++);
				String time = rset.getString(index++);
				System.out.println("time->" + time);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Date datumIvreme = sdf.parse(time);
				System.out.println("date->" + datumIvreme);
				// Date datumIvreme = rset.getDate(index++);
				Integer cenaKarte = rset.getInt(index++);
				Integer administrator = rset.getInt(index++);
				Boolean obrisan = rset.getBoolean(index++);

				projekcijeUBuducnosti.add(
						new Projekcija(id, film, tipProjekcije, sala, datumIvreme, cenaKarte, administrator, obrisan));
			}
			// imamo sve projekcije u buducnosti koje se jos uvek nisu odrzale
			System.out.println("projekcijeUBuducnosti" + projekcijeUBuducnosti);
			// provera koliko ima mesta
			for (Projekcija p : projekcijeUBuducnosti) {
				pstmt = null;
				rset = null;
				String query2 = "SELECT count(s.redni_broj) FROM sediste as s where s.sala = ?";
				pstmt = conn.prepareStatement(query2);
				pstmt.setInt(1, p.getSala());
				rset = pstmt.executeQuery();
				Integer brojMesta = null;
				while (rset.next()) {
					int index = 1;
					brojMesta = rset.getInt(index++);
				}
				pstmt = null;
				rset = null;
				String query3 = "SELECT count(k.id) FROM karta as k where k.projekcijaId = ?";
				pstmt = conn.prepareStatement(query3);
				pstmt.setInt(1, p.getId());

				rset = pstmt.executeQuery();
				Integer brojProdatihKarata = null;
				while (rset.next()) {
					int index = 1;
					brojProdatihKarata = rset.getInt(index++);
				}
				System.out.println("id projekcija " + p.getId());
				System.out.println("broj prodatih karata " + brojProdatihKarata);
				System.out.println("broj mesta " + brojMesta);
				if (brojProdatihKarata < brojMesta) {
					dostupneProjekcije.add(p);
				}
			}

			return dostupneProjekcije;
		} catch (Exception e) {
			e.printStackTrace();
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
				int index = 1;
				Integer idProjekcije = rset.getInt(index++);
				int film = rset.getInt(index++);
				Integer tipProjekcije = rset.getInt(index++);
				Integer sala = rset.getInt(index++);
				String time = rset.getString(index++);
				System.out.println("time->" + time);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Date datumIvreme = sdf.parse(time);
				System.out.println("date->" + datumIvreme);
				// Date datumIvreme = rset.getDate(index++);
				Integer cenaKarte = rset.getInt(index++);
				Integer administrator = rset.getInt(index++);
				Boolean obrisan = rset.getBoolean(index++);

				return new Projekcija(idProjekcije, film, tipProjekcije, sala, datumIvreme, cenaKarte, administrator, obrisan);
				
			}

		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu");
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				System.out.println("time->" + time);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Date datumIvreme = sdf.parse(time);
				System.out.println("date->" + datumIvreme);
				// Date datumIvreme = rset.getDate(index++);
				Integer cenaKarte = rset.getInt(index++);
				Integer administrator = rset.getInt(index++);
				Boolean obrisan = rset.getBoolean(index++);

				projekcije.add(
						new Projekcija(id, film, tipProjekcije, sala, datumIvreme, cenaKarte, administrator, obrisan));
			}
			return projekcije;
		} catch (ParseException ex) {
			System.out.println("Greska u parsiranju datuma!");
			ex.printStackTrace();

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
			// java.sql.Date date = new java.sql.Date(new Date().getTime());
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
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
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
			pstmt.setInt(index++, projekcija.getCenaKarte());
			pstmt.setInt(index++, projekcija.getAdministrator());
			pstmt.setBoolean(index++, projekcija.isObrisan());
			pstmt.setInt(index++, projekcija.getId());

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

	public static boolean delete(Projekcija projekcija) {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean prodateKarteNadjene = false;
		try {
			String queryCount = "select count(*) from karta as k where projekcijaId = ?";
			pstmt = conn.prepareStatement(queryCount);
			int index = 1;
			pstmt.setInt(index++, projekcija.getId());
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int indexGet = 1;
				Integer count = rset.getInt(indexGet++);
				if (count > 0) {
					prodateKarteNadjene = true;
				}
			}

			pstmt = null;

			if (prodateKarteNadjene) {
				String query = "UPDATE projekcija SET obrisan = ? WHERE id = ?";
				pstmt = conn.prepareStatement(query);
				index = 1;
				pstmt.setBoolean(index++, true);
				pstmt.setInt(index++, projekcija.getId());
				return pstmt.executeUpdate() == 1;
			} else {
				String query = "DELETE FROM projekcija WHERE id = ?";
				pstmt = conn.prepareStatement(query);
				index = 1;
				
				pstmt.setInt(index++, projekcija.getId());
				return pstmt.executeUpdate() == 1;
			}

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
