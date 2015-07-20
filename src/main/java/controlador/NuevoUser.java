package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;

/**
 * Servlet implementation class NewUser
 */
@WebServlet("/NuevoUser")
public class NuevoUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuevoUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String user =request.getParameter("usuario");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		Usuario usu = new Usuario();
		//Usuario usu1= usu.NuevoUsu(user, email, pass);
		Boolean res = usu.NuevoUsu(user,email,pass);
		// Enviar email al usuario nuevo
		HttpSession sesion = request.getSession();
		usu.setUsuario(user);
		usu.setPassword(pass);
		sesion.setAttribute("Usuario", usu);
		out.print(true);
	}

}
