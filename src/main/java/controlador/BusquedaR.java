package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Restaurante;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class BusquedaR
 */
@WebServlet("/BusquedaR")
public class BusquedaR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusquedaR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Dentro del servlet BusquedaR");
		
		String tipos= request.getParameter("opcion");
		System.out.println("La opcion son -->"+tipos);
		String parque= request.getParameter("codigo");
		System.out.println("El parque son -->"+parque);
		 try {
			String resp= "Respondiendo de tipos-->"+tipos;		
			//response.setContentType("text/plain");		
			//response.getWriter().write(resp); 
			//response.setContentType("application/json");		
			ArrayList<Restaurante> list =new ArrayList<Restaurante>();
			Restaurante res= new Restaurante();
			list=res.obtenerRest(tipos,parque);
			 Gson gson = new Gson();
			 JsonElement element = gson.toJsonTree(list, new TypeToken<List<Restaurante>>() {}.getType());
			 JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
		    response.getWriter().print(jsonArray); 
		    
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
