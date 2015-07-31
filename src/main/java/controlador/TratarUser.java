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

import org.apache.commons.codec.digest.DigestUtils;

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
	    NUEVOUSER, ACCESOUSER, LOSTPASS, LOGOUT, USERCORRECTO,
	    EMAILCORRECTO, PASSPERDIDA, RESTAURAPASS   
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
		    case PASSPERDIDA:
		    	passperdida(request, response);
		    	break;
		    case RESTAURAPASS:
		    	RestauraPass(request, response);
		    	break;

		}
	}
	public void AccesoUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
			PrintWriter out = response.getWriter();
			Usuario usu1 = new Usuario(); 
			usu1= usu1.busquedaUser(email, pass);
			if (usu1.isRegistrado()) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("Usuario", usu1);
				//response.sendRedirect("index3.jsp");
				out.print(true);
			}else {				
				out.print(false);
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
	public void passperdida(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();			
		String clave= request.getParameter("clave");
		String ema= request.getParameter("email");
		System.out.println("mandado email -->"+ema);
		Usuario usu = new Usuario();
		Boolean res = usu.BusquedaClave(clave,ema);
		out.println(res);
		out.close();
}
	public void LostPass(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email2");
		Usuario usu = new Usuario();
		
		// comprobaremos primero si existe ese email en la tabla 'lostpassuser' para decir al usuario
		// que no puede hacerlo por que se le envio una 'contraseña segura' hace menos de 24 horas
		// y no puede hacerla hasta que no pase ese tiempo. 
		
		 if (usu.buscaemalost(email)== true){
		   String respuesta = "El usuario ya tiene una recuperacion de contraseña activa. No puede realizar esta operación hasta que no pasen 24 horas";
			request.setAttribute("motivo", respuesta);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else{
		
		
		
		// añadimos a la base de datos una tabla mas  con un registro con el password generado la clave y el token
		// lo mandamos a un servlet con  un token(un numero codificado) y el email( y en el servlet hacemos la comprobacion a la base
		// de datos si existe ese registro con el token y el email
		// Mostramos Si existe, campos clave segura, captcha, pass, repetir pass, Boton de Aceptar y Cancelar
		// Boton Aceptar --> Update de la tabla usuarios el campo contraseña y delete del registro de
		// la tabla ConPerdido. y redirigimos a la pagina principal
		// Boton Cancelar --> Pantalla de aviso texto ="Seguro que deseas salir?(Si sales tendras que perdir de nuevo
		// el envio de "contraseña perdida"
		

		// Crearemos una contraseña segura con PasswordGenerator
		String clave =PasswordGenerator.getPassword(
				PasswordGenerator.MINUSCULAS+
				PasswordGenerator.MAYUSCULAS,10);
		
		System.out.println("clave= "+clave);
		// y la encriptamos para obtener un token, q le enviaremos al usuario
		String token=DigestUtils.sha256Hex(clave);
		System.out.println("clave encriptada= "+token);
		//Obtenemos el Id del usuario		
		int id=usu.ObtenerIDUsuario(email);
		
		System.out.println("id usuario -->"+id);
		// Agregar registro a la tabla lostPassUser
		usu.InsertarLostPass(id,email,clave,token);
		System.out.println("Insertada fila en tabla lostpass");
		
		//enviar mensaje al usuario

		// obetnemos la url y modificamos el destino asi para mandarsela al usuario
		String url = request.getRequestURL().toString();
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
		
		//response.sendRedirect("LostPass?token="+token+"&email="+email);
		

		out.println("baseURL + envio = "+baseURL+"LostPass?token="+token+"&email="+email);
		}
	}
	public void RestauraPass(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("passR");
		String token = request.getParameter("token");
		System.out.println("Restaura pass email -->"+email);
		System.out.println("Restaura pass pass -->"+pass);
		System.out.println("Restaura pass token -->"+token);
		
		Usuario usu = new Usuario();
		usu.CambiarPass(pass, email, token);
		System.out.println("contraseña cambiada con exito");
		response.sendRedirect("index3.jsp");
	}
}
