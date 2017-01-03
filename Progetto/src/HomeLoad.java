
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Categoria;
import util.DbConnector;

/**
 * Servlet implementation class HomeLoad
 */
@WebServlet("/HomeLoad")
public class HomeLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DbConnector d;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeLoad() {
		super();
		d = DbConnector.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s = request.getParameter("query");
		for (Categoria st : d.getCategorie()) {
			response.getWriter().print(st.getNome() + ";");

		}

	}

}
