package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProjekcijaDAO;
import model.Projekcija;

/**
 * Servlet implementation class DodajProjekcijuServlet
 */
public class DodajProjekcijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajProjekcijuServlet() {
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
		
		String status = "success";
		
		String film = request.getParameter("film");
		int filmint = Integer.parseInt(film);
		String tipProjekcije = request.getParameter("tipProjekcije");
		int tipProjekcijeint = Integer.parseInt(tipProjekcije);
		String datumIvreme = request.getParameter("datumIvreme");
		datumIvreme = datumIvreme+":00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		
		Date datumIvremed = null;
		try {
			datumIvremed = sdf.parse(datumIvreme);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		String cena = request.getParameter("cenaKarte");
		int cenaint = Integer.parseInt(tipProjekcije);
		String sala = request.getParameter("sala");
		int salaint = Integer.parseInt(sala);
		
		Projekcija p = new Projekcija(0, filmint, tipProjekcijeint, salaint, datumIvremed, cenaint, 4, false);
		ProjekcijaDAO.add(p);
		
		
	}

}
