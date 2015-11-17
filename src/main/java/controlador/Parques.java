package controlador;

import interfaces.DAOParque;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Parque;
import dao.DAOParqueImpl;

/**
 * Servlet implementation class Parques
 */
@WebServlet("/Parques")
public class Parques extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Parques() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String parque = request.getParameter("parque");
		Parque par = new Parque();
		DAOParque dao = new DAOParqueImpl();
		try {
			par.setNombre(parque);
			par=dao.obtenerParque(par);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		par.obtenerParque(parque);
		System.out.println("He llegado al final");
		request.setAttribute("parque", par);		
		request.getRequestDispatcher("parque.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
