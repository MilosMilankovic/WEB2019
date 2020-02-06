package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.KartaDAO;
import model.Karta;

/**
 * Servlet implementation class KupiKartuServlet
 */
public class KupiKartuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KupiKartuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sedistaInput = request.getParameter("sedistaInput");
		String idProjekcija = request.getParameter("idProjekcija");
		String idKorisnik = request.getParameter("idKorisnik");
		
		String[] sedista = sedistaInput.split(Pattern.quote(","));
		String status = "success";
		if(sedista.length>1) {
			for(int i=0; i<sedista.length-1; i++) {
				
				int prvo = Integer.parseInt(sedista[i]);
				int drugo = Integer.parseInt(sedista[i+1]);
				if(Math.abs(drugo-prvo)>1) {
					status = "failure";
					break;
				}
				
			}
		}
		System.out.println("sedista " + Arrays.deepToString(sedista));
		System.out.println("sedista input" + sedistaInput);
		System.out.println("id projekcija " + idProjekcija);
		
		if(status.equals("success")) {
			for(String s:sedista) {
				Karta karta = new Karta(0, Integer.parseInt(idProjekcija), Integer.parseInt(s), new Date(), Integer.valueOf(idKorisnik), false);
				KartaDAO.add(karta);
			}
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
