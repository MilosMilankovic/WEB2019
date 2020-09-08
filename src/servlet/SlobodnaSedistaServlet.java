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
import dao.SedistaDAO;
import model.Projekcija;
import model.Sediste;

/**
 * Servlet implementation class SlobodnaSedistaServlet
 */
public class SlobodnaSedistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SlobodnaSedistaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idProjekcija = Integer.parseInt(request.getParameter("idProjekcija"));
		List<Sediste> sedista = new ArrayList<>();
		try {
			sedista = SedistaDAO.slobodnaSedista(idProjekcija);
			System.out.println(sedista);
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			data.put("dataList", sedista);
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
