package servlet;

import java.io.IOException;
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
 * Servlet implementation class DodajFilmServlet
 */
public class DodajFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DodajFilmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String status = "success";
		int id = 0;
		String naziv = request.getParameter("naziv");
		String reziser = request.getParameter("reziser");
		String glumci = request.getParameter("glumci");
		String zanrovi = request.getParameter("zanrovi");
		String trajanje = request.getParameter("trajanje");
		int tr = Integer.parseInt(trajanje);
		String distributer = request.getParameter("distributer");
		String zemljaPorekla = request.getParameter("zemljaPorekla");
		String godinaProizvodnje = request.getParameter("godina");
		int godina = Integer.parseInt(godinaProizvodnje);
		String opis = request.getParameter("opis");

		Korisnik korisnikUlogovani = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
		if (korisnikUlogovani.getUloga().equals(Uloga.ADMIN.toString())) {
			Film f = new Film(id, naziv, reziser, glumci, zanrovi, tr, distributer, zemljaPorekla, godina, opis, false);
			FilmDAO.add(f);
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
	}

}
