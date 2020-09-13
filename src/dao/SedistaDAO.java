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
import model.Sediste;

public class SedistaDAO {

	public static List<Sediste> slobodnaSedista(int idProjekcija) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Sediste> sedista = new ArrayList<Sediste>();
		try {
			String query = "SELECT redni_broj, sala FROM sediste as s where s.sala = ? AND s.redni_broj not in (SELECT sediste FROM karta as k where k.projekcijaId = ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ProjekcijaDAO.get(idProjekcija).getSala());
			pstmt.setInt(2, idProjekcija);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				Integer redniBroj = rset.getInt(index++);
				int sala = rset.getInt(index++);
				sedista.add(new Sediste(redniBroj, sala));
			}
			return sedista;
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

	

}
