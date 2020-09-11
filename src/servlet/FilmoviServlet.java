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
import enums.Uloga;
import model.Film;
import model.Korisnik;

/**
 * Servlet implementation class FilmoviServlet
 */
public class FilmoviServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FilmoviServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Film> filmovi = new ArrayList<>();
		try {
			filmovi = FilmDAO.getAll();
			System.out.println(filmovi);
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			data.put("dataList", filmovi);
			data.put("uloga", request.getSession().getAttribute("uloga"));
			data.put("ulogovaniKorisnik", request.getSession().getAttribute("ulogovaniKorisnik"));

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

		String status = "success";
		try {

			String idStr = request.getParameter("id");

			int id = 0;

			if (idStr != null && idStr.length() > 0) {
				id = Integer.parseInt(idStr);
			}
			String naziv = request.getParameter("naziv");
			if (naziv == null || naziv.isEmpty() || naziv.trim().equals("")) {
				throw new Exception("Naziv je obavezan");
			}
			String reziser = request.getParameter("reziser");
			String glumci = request.getParameter("glumci");
			String zanrovi = request.getParameter("zanrovi");
			String trajanje = request.getParameter("trajanje");
			int tr = Integer.parseInt(trajanje);
			String distributer = request.getParameter("distributer");
			String zemljaPorekla = request.getParameter("zemljaPorekla");
			String godinaProizvodnje = request.getParameter("godinaProizvodnje");
			int godina = Integer.parseInt(godinaProizvodnje);
			String opis = request.getParameter("opis");

			Korisnik korisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			System.out.println("ULOGA KORISNIKA JEEE " + korisnik.getUloga());
			if (korisnik.getUloga().equals(Uloga.ADMIN.toString())) {
				Film f = new Film(id, naziv, reziser, glumci, zanrovi, tr, distributer, zemljaPorekla, godina, opis,
						false);
				if (id == 0) {
					System.out.println("Rec je o kreiranju novog filma");
					FilmDAO.add(f);
				} else if (id > 0) {
					System.out.println("Rec je o menjanju postojeceg filma");
					FilmDAO.update(f);
				}

			} else {
				status = "failure";
			}

			Map<String, Object> data = new HashMap<>();
			data.put("status", status);
			data.put("uloga", request.getSession().getAttribute("uloga"));
			data.put("ulogovaniKorisnik", request.getSession().getAttribute("ulogovaniKorisnik"));

			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		} catch (Exception e) {
			status = "failure";
			Map<String, Object> data = new HashMap<>();
			data.put("status", status);
			data.put("uloga", request.getSession().getAttribute("uloga"));
			data.put("ulogovaniKorisnik", request.getSession().getAttribute("ulogovaniKorisnik"));

			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
	}

}
