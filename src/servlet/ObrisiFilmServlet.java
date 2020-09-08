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
 * Servlet implementation class ObrisiFilmServlet
 */
public class ObrisiFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObrisiFilmServlet() {
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
		String idFilm = request.getParameter("idFilm");
		System.out.println("id filma u servletu " + idFilm);
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
		System.out.println("ULOGA KORISNIKA JEEE " + korisnik.getUloga());
		if (korisnik.getUloga().equals(Uloga.ADMIN.toString())) {

			try {
				Film film = FilmDAO.get(Integer.parseInt(idFilm));
				FilmDAO.delete(film);
				
				Map<String, Object> data = new HashMap<>();
				data.put("status", "obrisan");
				data.put("uloga", request.getSession().getAttribute("uloga"));
				data.put("ulogovaniKorisnik", request.getSession().getAttribute("ulogovaniKorisnik"));
				
				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(data);
				System.out.println(jsonData);
				//
				response.setContentType("application/json");
				response.getWriter().write(jsonData);
			} catch (Exception ex) {
				System.out.println("greskaa");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
