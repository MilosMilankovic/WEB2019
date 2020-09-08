package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		System.out.println("Ime " + userName + "sifra " + password);
		//Korisnik korisnik = null;
		try {
			
			Korisnik korisnik = KorisnikDAO.getOne(userName, password);
			if (korisnik == null) {
				throw new Exception("Nije pronadjen korisnik sa prosledjenim imenom");
			}
			
			/*
			if (!korisnik.getLozinka().equals(password)) {
				throw new Exception("Pogresna lozinka");
			}
			*/
			else { 
				request.getSession().setAttribute("ulogovanKorisnik", korisnik);
				request.getSession().setAttribute("ulogovaniKorisnik", korisnik.getKorisnickoIme());
				request.getSession().setAttribute("uloga", korisnik.getUloga());
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			}
		/*
		}catch (Exception ex) {
			String message = ex.getMessage();
			if (message == null) {
				message = "Nepredvidjena greska!";
				ex.printStackTrace();
			}
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("message", message);

			request.setAttribute("data", data);
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
		}	*/
			
		} catch (Exception ex) {
			ex.printStackTrace();
				status = "failure";
		}
		
	
		Map<String, Object> data = new HashMap<>();
		data.put("status", status);
		data.put("uloga", request.getSession().getAttribute("uloga"));
		data.put("ulogovaniKorisnik", request.getSession().getAttribute("ulogovaniKorisnik"));
		/*if(korisnik!=null) {
			System.out.println("ulogovaniKorisnik" + korisnik.getId());
			data.put("ulogovaniKorisnik", korisnik.getId());
			data.put("uloga", korisnik.getUloga());
		} */
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
	}
  
}
