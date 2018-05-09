package control;

import java.io.IOException;

import javax.activation.ActivationDataFlavor;

import javax.servlet.ServletException;

import javax.servlet.annotation.MultipartConfig;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import model.Action;

import model.ActionData;

/**
 * 
 * Servlet implementation class F_Controller
 * 
 */

@WebServlet("/ttt/*")

@MultipartConfig(

		
		location = "C:\\tomcat\\temp",

		maxFileSize = 1024 * 5000,

		maxRequestSize = 1024 * 1024 * 100,

		fileSizeThreshold = 1024 * 1024 * 10
		
		
)

public class F_Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see HttpServlet#HttpServlet()
	 * 
	 */

	public F_Controller() {

		super();

		// TODO Auto-generated constructor stub

	}

	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		
		System.out.println("getRequestURI() : "+request.getRequestURI());
		System.out.println("getContextPath() : "+request.getContextPath());
		
		String service = request.getRequestURI().substring(

				(request.getContextPath() + "/ttt/").length());

		System.out.println("F_Controller->"+service);
		System.out.println(request.getRemoteAddr());
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		try {

			Action action = (Action) Class.forName("ttt." + service).newInstance();

			ActionData data = action.execute(request, response);

			if (data != null) {

				if (data.isRedirect())

				{

					response.sendRedirect(data.getPath());

				} else {

					request.getRequestDispatcher("../view/template.jsp").

							forward(request, response);

				}

			}
		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method st=ub

		doGet(request, response);

	}

}
