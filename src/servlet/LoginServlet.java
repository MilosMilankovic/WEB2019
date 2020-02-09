package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.KorisnikDAO;
import model.Korisnik;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String status = "success";
		String korisnickoIme = request.getParameter("username");
		String lozinka = request.getParameter("password");
		Korisnik korisnik = null;
		try {
			
			korisnik = KorisnikDAO.get(korisnickoIme);
			if (korisnik == null) {
				
				throw new Exception("Nije pronadjen korisnik sa prosledjenim imenom");
			}
			
			
			if (!korisnik.getLozinka().equals(lozinka)) {
				throw new Exception("Pogresna lozinka");
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("ulogovanKorisnik", korisnik);
			
		
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
				status = "failure";
		}
		
	
		Map<String, Object> data = new HashMap<>();
		data.put("status", status);
		if(korisnik!=null) {
			System.out.println("ulogovaniKorisnik" + korisnik.getId());
			data.put("ulogovaniKorisnik", korisnik.getId());
			data.put("uloga", korisnik.getUloga());
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
	}

}
