package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.KorisnikDAO;

import model.Korisnik;

import enums.Uloga;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("OKIDA SE REGISTER SERVLET");
		String status = "success";
		String korisnickoImen = request.getParameter("username");
		String lozinkan = request.getParameter("password");
		System.out.println(korisnickoImen + lozinkan);

		try {
			Korisnik existingUser = KorisnikDAO.get(korisnickoImen);
			if (existingUser != null) {
				throw new Exception("User already exist!");
			} else {
				Date d = new Date();
				Date date = new Date();
				// LocalDateTime date = LocalDateTime.now();
				int id = 0;

				Korisnik korisnik = new Korisnik(id, korisnickoImen, lozinkan, date, Uloga.KORISNIK.toString(), false);
				boolean registracija = KorisnikDAO.add(korisnik);
				if(!registracija) {
					status="failure";
				}
				System.out.println("Da li je registracija uspesna? " + registracija);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			status="failure";
		}

		Map<String, Object> data = new HashMap<>();
		data.put("status", status);

		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);

	}
}
