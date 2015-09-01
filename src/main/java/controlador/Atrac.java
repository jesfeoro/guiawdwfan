package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atraccion;
/**
 * Servlet implementation class Atrac
 */
@WebServlet("/Atrac")
public class Atrac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Atrac() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String atrac = request.getParameter("atraccion");
		
		Atraccion at = new Atraccion();
		long startTime = System.currentTimeMillis();
		at.obtenerAtraccion(atrac);
		long stopTime = System.currentTimeMillis();
	    long res = stopTime - startTime;
	    System.out.println("TIEMPO --> "+res+" MiliSegundos");
	    if (at.getNombre() == null || at.getNombre() == "" ) {
	    	
			String motivo ="No existe ninguna atracción con ese nombre en nuestra Base de Datos";
	    	request.setAttribute("motivo", motivo);		
			request.getRequestDispatcher("error.jsp").forward(request, response);
	    }else {
	    	request.setAttribute("atrac", at);		
			request.getRequestDispatcher("atraccion.jsp").forward(request, response);
	    }
	 
	    /* Quitar drive y poner photobucket a las fotos y cambiarlo en la base de datos
	     * Rellenar con el objeto atraccion las distintas partes del apartado de redes sociales
	     * Rellenar atracciones indiana jones y the hounted mansion.
	     * Antes del viernes 4
	     * */
	    
		
	/*	String []caracteristica  = at.getTipos();
		Map<Integer,Atraccion>Micaracter =at.getCaracter();
			for (int j = 0; j < caracteristica.length; j++) {
		System.out.println(caracteristica[j].toUpperCase());
		Iterator it = Micaracter.keySet().iterator();
		while(it.hasNext()){
				  Integer key = (Integer) it.next();
				  Atraccion m= Micaracter.get(key);
				  if(caracteristica[j].equals(m.getCaracteristica())) {
				  System.out.println(m.getTipoC()+" -- "+m.getValorC());
				  }
		}
		System.out.println();
	}
	Atraccion m= Micaracter.get(19);
	System.out.println(m.getTipoC()+" -- "+m.getValorC());
		PrintWriter out = response.getWriter();
		out.println(atrac);
		out.println("Datos cargados");*/
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
