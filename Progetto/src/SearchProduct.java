import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import DAO.ProdottoDAO;
import data.Prodotto;
import persistence.DAOFactory;


@WebServlet("/SearchProduct")
public class SearchProduct extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeProdotto = request.getParameter("nomeProdotto");
		String categoria = request.getParameter("categoria");
		System.out.println(categoria);

		ProdottoDAO d = DAOFactory.getDAOFactory().getProdottoDao();
		List<Prodotto> l = d.findProdotto(nomeProdotto);

		request.setAttribute("prodotti", l);
		forwardOnJsp(request, response, "/jsp/SearchProduct.jsp");

	}
	
	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}
}
