package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.FilmDAO;
import dao.ProjekcijaDAO;
import dao.TipProjekcijeDAO;
import model.Projekcija;
import model.TipProjekcije;
import model.Film;

/**
 * Servlet implementation class ProjekcijaServlet
 */
public class ProjekcijaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Projekcija> projekcije = new ArrayList<>();
		try {
			projekcije = ProjekcijaDAO.getAll();
			List<Film> filmoviSaProjekcija = new ArrayList<>();
			for(Projekcija p:projekcije) {
				int idFilm = p.getFilm();
				Film film = FilmDAO.get(idFilm);
				filmoviSaProjekcija.add(film);
			}
			List<TipProjekcije> tipoviProjekcija = TipProjekcijeDAO.getAll();
			
			
			System.out.println(projekcije);
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			data.put("dataList", projekcije);
			data.put("filmoviSaProjekcijaList", filmoviSaProjekcija);
			data.put("tipoviProjekcija", tipoviProjekcija);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
