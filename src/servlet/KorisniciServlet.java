package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.FilmDAO;
import dao.KorisnikDAO;
import model.Korisnik;

/**
 * Servlet implementation class KorisniciServlet
 */
public class KorisniciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KorisniciServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Korisnik> korisnici = new ArrayList<>();
		try {
			korisnici = KorisnikDAO.getAll();
			System.out.println(korisnici);
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			data.put("dataList", korisnici);

			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		} catch (Exception ex) {
			System.out.println("greskaa");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String status = "success";

		String idStr = request.getParameter("id");

		int id = 0;

		if (idStr != null && idStr.length() > 0) {
			id = Integer.parseInt(idStr);
		}
		String ime = request.getParameter("ime");
		String lozinka = request.getParameter("lozinka");
		String uloga = request.getParameter("uloga");
		
		if(ime!=null && lozinka!=null && uloga!=null && !ime.isEmpty() && !lozinka.isEmpty() && !uloga.isEmpty()) {
			Korisnik k = new Korisnik(id, ime, lozinka, null, uloga, false);
			KorisnikDAO.update(k);
		}else {
			status = "failure";
		}

		Map<String, Object> data = new HashMap<>();
		data.put("status", status);

		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

}
