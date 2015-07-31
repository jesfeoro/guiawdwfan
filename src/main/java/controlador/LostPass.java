package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;

/**
 * Servlet implementation class LostPass
 */
@WebServlet("/LostPass")
public class LostPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LostPass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email= request.getParameter("email");
		String token= request.getParameter("token");
		System.out.println("el email="+email+" el token ="+token);	
		PrintWriter out = response.getWriter();
		out.print("estoy en lostpass");
		//comprobar si existe este usuario con este token en la base de datos
		// y si existe lo mandamos a contraPerdida.jsp
		Usuario usu = new Usuario();
		if (usu.buscaematoken(email, token)== true) {
			request.getRequestDispatcher("ContraPerdida.jsp").forward(request, response);
		}else {
			//si no existe lo mandamos a la pagina de error
			String respuesta = "La petición de recuperción de contraseña no existe.";
			request.setAttribute("motivo", respuesta);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
