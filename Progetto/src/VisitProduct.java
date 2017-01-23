

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProdottoDAO;
import data.Prodotto;
import persistence.DAOFactory;

/**
 * Servlet implementation class VisitProduct
 */
@WebServlet("/VisitProduct")
public class VisitProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nomeProdotto =Integer.parseInt(request.getParameter("idProdotto"));
		ProdottoDAO d = DAOFactory.getDAOFactory().getProdottoDao();
		Prodotto p= d.visitProdotto(nomeProdotto);
		System.out.println(p.getNome());

		request.setAttribute("prodotto", p);
		forwardOnJsp(request, response, "/jsp/VisitProduct.jsp");
	}

	
	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}

}
