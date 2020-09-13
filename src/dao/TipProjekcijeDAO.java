package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Film;
import model.TipProjekcije;

public class TipProjekcijeDAO {
public static ArrayList<TipProjekcije> getAll() {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<TipProjekcije> tipoviProjekcije = new ArrayList<TipProjekcije>();
		try {
				String query = "SELECT * FROM tip_projekcije";
				
				pstmt = conn.prepareStatement(query);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					int index = 1;
					Integer id = rset.getInt(index++);
					String naziv = rset.getString(index++);
					
					
					tipoviProjekcije.add(new TipProjekcije(id, naziv));
				}
				return tipoviProjekcije;
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
	}
}
