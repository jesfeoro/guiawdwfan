package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;

/**
 * Servlet implementation class EnvioCaptcha
 */
@WebServlet("/EnvioCaptcha")
public class EnvioCaptcha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnvioCaptcha() {
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
		// We're doing this in a JSP here, but in your own app you'll want to put
	    // this logic in your MVC framework of choice
		 HttpSession session = request.getSession(true);
		 PrintWriter out =response.getWriter();
	    Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
	    // Or, for an AudioCaptcha:
	    // AudioCaptcha captcha = (AudioCaptcha) session.getAttribute(Captcha.NAME);
	    request.setCharacterEncoding("UTF-8"); // Do this so we can capture non-Latin chars
	    String answer = request.getParameter("answer");
	   System.out.println("dentro del servlet del captcha");
	    if (captcha.isCorrect(answer)) {     	
	    	out.print(true);
	    	System.out.println("correcto");
	    }else {	    	
	    	out.print(false);
	    	System.out.println("INcorrecto");
	    } 
	}

}
