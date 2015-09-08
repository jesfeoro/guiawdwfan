package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Busqueda;

import com.google.gson.Gson;

/**
 * Servlet implementation class ConBusqueda
 */
@WebServlet("/ConBusqueda")
public class ConBusqueda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConBusqueda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
        try {
		    String term = request.getParameter("term");
		    //System.out.println("Datos por llamada AJAX " + term); //muestra en consola lo escrito en el textbox  podemos quitarlo
		   
		    Busqueda m= new Busqueda();
		    
		    ArrayList<String> list = m.getNombres(term);
		    String searchList = new Gson().toJson(list);
		    
		    response.getWriter().write(searchList); 
		    
		} catch (Exception e) {
		    System.err.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
