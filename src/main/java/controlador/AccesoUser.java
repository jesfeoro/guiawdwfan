package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;

/**
 * Servlet implementation class AccesoUser
 */
@WebServlet("/AccesoUser")
public class AccesoUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccesoUser() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		Usuario usu1 = new Usuario();
		String usuario=usu1.busquedaUser(email, pass);

		if(usuario == null) {
			response.sendRedirect("JSP/errores.jsp");
		}else {		
			HttpSession sesion = request.getSession();
			usu1.setUsuario(usuario);
			usu1.setPassword(pass);
			sesion.setAttribute("Usuario", usu1);
			response.sendRedirect("index3.jsp");	
		}
	
		
	}

}
