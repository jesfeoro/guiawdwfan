package controlador;

import interfaces.DAORestaurante;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Restaurante;
import dao.DAORestauranteImpl;

/**
 * Servlet implementation class TiposRestaura
 */
@WebServlet("/TiposRestaura")
public class TiposRestaura extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TiposRestaura() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String parque = request.getParameter("parque");
		try {
			Restaurante res = new Restaurante();
			DAORestaurante dao = new DAORestauranteImpl();
			res.setListaR(dao.obtenerRest("todos", parque));
			ArrayList<Restaurante> list = res.getListaR();
			request.setAttribute("miparque", parque);
			request.setAttribute("parquenom", list);
			request.getRequestDispatcher("tiporest.jsp").forward(request,
					response);
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
