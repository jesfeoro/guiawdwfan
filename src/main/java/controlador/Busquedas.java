package controlador;

import interfaces.DAOBusqueda;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Busqueda;
import dao.DAOBusquedaImpl;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public enum Opciones {
		atraccion, parques, restaurante
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String campo = request.getParameter("spotlight");
		// System.out.println("campo -->"+campo);
		Busqueda b = new Busqueda();
		b.setNombre(campo);
		DAOBusqueda dao = new DAOBusquedaImpl();
		int num = 0;
		try {
			num = dao.getCuantos(b);
			// out.println("tipo -->"+tipo);
			if (num == 0 || num > 1) {
				// out.println("Hay "+num+" elementos en la base de datos");
				request.setAttribute("resultados", num);
				if (num > 1) {
					b = dao.obtenerCampos(b);
					request.setAttribute("busqueda", b);
				}
				request.getRequestDispatcher("resultadosB.jsp").forward(
						request, response);
			} else {
				b = dao.getUnNombre(b);
				if (campo.equals(b.getNombre())) {
					Opciones tipoOp = Opciones.valueOf(b.getTipos());
					switch (tipoOp) {
					case atraccion:
						campo = URLEncoder.encode(campo, "UTF-8");
						response.sendRedirect("Atrac?atraccion=" + campo);
						break;
					case parques:
						campo = URLEncoder.encode(campo, "UTF-8");
						response.sendRedirect("Parques?parque=" + campo);
						break;
					case restaurante:
						campo = URLEncoder.encode(campo, "UTF-8");
						response.sendRedirect("Resta?restaurante=" + campo);
						break;
					}
				} else {
					request.setAttribute("resultados", num);
					b = dao.obtenerCampos(b);
					request.setAttribute("busqueda", b);
					request.getRequestDispatcher("resultadosB.jsp").forward(
							request, response);
				}
				// out.println("Solo Hay "+num+" elemento en la base de datos");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
