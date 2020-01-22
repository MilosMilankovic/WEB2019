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
import dao.KorisnikDAO;

import enums.Uloga;
import model.Korisnik;





/**
 * Servlet implementation class KorisnikServlet
 */
public class KorisnikServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KorisnikServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String status = request.getParameter("status");
		
		if(status.equals("delete")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			Korisnik k = KorisnikDAO.getId(id);
			k.setObrisan(true);
			
			KorisnikDAO.update(k);
			
			Map<String, Object> data = new HashMap<>();		
			data.put("status", "success");
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		
		} /*else if(status.equals("edit")) {
			
			String korisnickoIme = request.getParameter("username");
			String lozinka = request.getParameter("password");
			String role = request.getParameter("role");
			int id = Integer.parseInt(request.getParameter("id"));
			
			Uloga novaUloga = Uloga.valueOf(role);
			
			Korisnik korisnik = KorisnikDAO.getOneId(id);
			
			korisnik.setUsername(username);
			korisnik.setPassword(password);
			korisnik.setUloga(uloga);
			
			KorisnikDAO.update(korisnik);
			Map<String, Object> data = new HashMap<>();
			
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}*/
	}
}
