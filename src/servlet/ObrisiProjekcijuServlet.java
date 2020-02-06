package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ProjekcijaDAO;
import enums.Uloga;
import model.Korisnik;
import model.Projekcija;

/**
 * Servlet implementation class ObrisiProjekcijuServlet
 */
public class ObrisiProjekcijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObrisiProjekcijuServlet() {
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
		String idProjekcija = request.getParameter("idProjekcija");
		System.out.println("id projekcije u servletu " + idProjekcija);
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
		System.out.println("ULOGA KORISNIKA JEEE " + korisnik.getUloga());
		if (korisnik.getUloga().equals(Uloga.ADMIN.toString())) {
			try {
				Projekcija projekcija = ProjekcijaDAO.get(Integer.parseInt(idProjekcija));
				ProjekcijaDAO.delete(projekcija);

				Map<String, Object> data = new HashMap<>();
				data.put("status", "obrisan");

				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(data);
				System.out.println(jsonData);
				//
				response.setContentType("application/json");
				response.getWriter().write(jsonData);
			} catch (Exception ex) {
				System.out.println("greskaa");
				ex.printStackTrace();
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
