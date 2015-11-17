package controlador;

import interfaces.DAOBusqueda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Busqueda;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import dao.DAOBusquedaImpl;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String term = request.getParameter("term");
			// System.out.println("Datos por llamada AJAX " + term); //muestra
			// en consola lo escrito en el textbox podemos quitarlo

			/*
			 * ArrayList<String> list = m.getNombres(term); String searchList =
			 * new Gson().toJson(list);
			 * 
			 * response.getWriter().write(searchList);
			 */
			

			ArrayList<Busqueda> list = new ArrayList<Busqueda>();
			// Hacemos un objeto busqueda y le pasamos el valor del campo term
			// al nombre
			Busqueda m = new Busqueda();
			m.setNombre(term);
			// Usamos la interfaz DAObusqueda e implementamos el objeto
			// DAOBusquedaImpl q tiene implementado los metodos de acceso a la base de datos.
			DAOBusqueda dao = new DAOBusquedaImpl();
			// y con el objeto dao, los valores resultantes se lo pasamos al
			// objeto list que hemos hecho anteriormente
			list = dao.getNombres(m);
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(list,
					new TypeToken<List<Busqueda>>() {
					}.getType());
			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
