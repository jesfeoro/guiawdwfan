package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Restaurante;

/**
 * Servlet implementation class Resta
 */
@WebServlet("/Resta")
public class Resta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Resta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String resta= request.getParameter("restaurante");
		PrintWriter out = response.getWriter();
		//out.println("Restaurante es -->"+resta);
		//System.out.println("EN resta");
		//System.out.println(resta);
		Restaurante res = new Restaurante();
		res = res.getRestaurante(resta);
		if (res.getNombre() == null || res.getNombre() == "" ) {
			    	
					String motivo ="No existe ninguna restaurante con ese nombre en nuestra Base de Datos";
			    	request.setAttribute("motivo", motivo);		
					request.getRequestDispatcher("error.jsp").forward(request, response);
			    }else {
			    	request.setAttribute("restaura", res);		
					request.getRequestDispatcher("restaurante.jsp").forward(request, response);
			    }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
