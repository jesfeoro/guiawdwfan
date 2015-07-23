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
 * Servlet implementation class TratarUser
 */
@WebServlet("/TratarUser")
public class TratarUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TratarUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public enum Opciones
	{
	    NUEVOUSER, ACCESOUSER, LOSTPASS, LOGOUT, USERCORRECTO, EMAILCORRECTO   
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcion");
		Opciones tipoOp = Opciones.valueOf(opcion.toUpperCase());
		switch (tipoOp)
		{
		    case NUEVOUSER:
		    	NuevoUser(request, response);
		    	break;
		    case ACCESOUSER: 
		    	AccesoUser(request, response);
		    	break;
		    case LOSTPASS:
		    	LostPass(request, response);
		    	break;
		    case LOGOUT:
		    	LogOut(request, response);
		    	break;
		    case USERCORRECTO:
		    	UserCorrecto(request, response);
		    	break;
		    case EMAILCORRECTO:
		    	EmailCorrecto(request, response);
		    	break;
		        

		}
	}
	public void AccesoUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
			Usuario usu1 = new Usuario(); 
			usu1= usu1.busquedaUser(email, pass);
			if (usu1.isRegistrado()) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("Usuario", usu1);
				response.sendRedirect("index3.jsp");
			}else {				
				response.sendRedirect("JSP/errores.jsp");
			}
	}
	public void LogOut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			request.getSession().invalidate();
			response.sendRedirect("index3.jsp");
	}
	public void NuevoUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
			out.print(res);
	}
	public void UserCorrecto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();			
			String user= request.getParameter("usuario");
			user = user.toLowerCase();
			Usuario usu = new Usuario();
			Boolean res = usu.usuCorrecto(user);
			out.println(res);
			out.close();
	}
	public void EmailCorrecto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();		
		String tipos= request.getParameter("tipos");	
		boolean res =false;
		Usuario usu = new Usuario();
		if (tipos.equals("registro")) {		
			String email= request.getParameter("email");
			res = usu.emaCorrecto(email, tipos);
		}else {
			String email= request.getParameter("email2");
			res = usu.emaCorrecto(email, tipos);
		}
		
		out.println(res);
		out.close();
		
	}
	public void LostPass(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email2");

		out.print(email);
	}
}
