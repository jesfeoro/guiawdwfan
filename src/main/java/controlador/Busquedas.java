package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Busqueda;
/**
 * Servlet implementation class Busqueda
 */
@WebServlet("/Busquedas")
public class Busquedas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Busquedas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public enum Opciones
	{
	    atraccion, parques   
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String campo= request.getParameter("spotlight");
		PrintWriter out = response.getWriter();
		out.println("campo -->"+campo);
		Busqueda b = new Busqueda();
		int num = b.getCuantos(campo);
		//out.println("tipo -->"+tipo);
		if(num==0 || num >1) {
			//out.println("Hay "+num+" elementos en la base de datos");
			request.setAttribute("resultados", num);
			if(num>1) {
				b=b.obtenerCampos(campo);
				request.setAttribute("busqueda", b);
				
			}
			request.getRequestDispatcher("resultadosB.jsp").forward(request, response);
		}else {
			b=b.getUnNombre(campo);
			if(campo.equals(b.getNombre())) {
				Opciones tipoOp = Opciones.valueOf(b.getTipos());
				switch (tipoOp)
				{
				    case atraccion:
				    	response.sendRedirect("Atrac?atraccion="+campo);
				    	break;
				    case parques: 
				    	response.sendRedirect("Parques?parque="+campo);
				    	break;
				}			
			}else {
				request.setAttribute("resultados", num);
				b=b.obtenerCampos(campo);
				request.setAttribute("busqueda", b);
				request.getRequestDispatcher("resultadosB.jsp").forward(request, response);
			}
			//out.println("Solo Hay "+num+" elemento en la base de datos");
		}

	}

}
