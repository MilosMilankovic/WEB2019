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
import dao.KorisnikDAO;
import enums.Uloga;
import model.Film;
import model.Korisnik;

/**
 * Servlet implementation class ObrisiKorisnikaServlet
 */
public class ObrisiKorisnikaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObrisiKorisnikaServlet() {
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
		String idKorisnik = request.getParameter("idKorisnik");
		System.out.println("id korisnika u servletu " + idKorisnik);
		Korisnik korisnikUlogovani = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
		System.out.println("ULOGA KORISNIKA JEEE " + korisnikUlogovani.getUloga());
		if (korisnikUlogovani.getUloga().equals(Uloga.ADMIN.toString()) && (Integer.parseInt(idKorisnik)!=korisnikUlogovani.getId())) {
			try {
				Korisnik korisnik = KorisnikDAO
						.getId(Integer.parseInt(idKorisnik));/* FilmDAO.get(Integer.parseInt(idFilm)); */
				KorisnikDAO.delete(korisnik);
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
