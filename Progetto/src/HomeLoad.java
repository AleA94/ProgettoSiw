
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AstaProdottoDAO;
import DAO.CategoriaDAO;
import persistence.MySQLDaoFactory;

/**
 * Servlet implementation class HomeLoad
 */
@WebServlet("")
public class HomeLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoriaDAO d = MySQLDaoFactory.getDAOFactory().getCategoriaDao();
	AstaProdottoDAO a = MySQLDaoFactory.getDAOFactory().getAstaProdottoDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("asteProdotto", a.getAste(0, 1));
		request.setAttribute("categorie", d.getMacroCategorie());
		forwardOnJsp(request, response, "/jsp/index.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}

}
