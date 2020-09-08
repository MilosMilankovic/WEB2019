package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ProjekcijaDAO;
import model.Projekcija;

/**
 * Servlet implementation class ProjekcijeZaFilm
 */
public class ProjekcijeZaFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjekcijeZaFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idFilm = Integer.parseInt(request.getParameter("idFilm"));
		List<Projekcija> projekcije = new ArrayList<>();
		try {
			projekcije = ProjekcijaDAO.projekcijeZaFilm(idFilm);
			System.out.println(projekcije);
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			data.put("dataList", projekcije);
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
