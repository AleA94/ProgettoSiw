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

@WebServlet("/SearchProduct")
public class SearchProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nomeProdotto = request.getParameter("nomeProdotto");
		ProdottoDAO d = DAOFactory.getDAOFactory().getProdottoDao();
		String k= "Non esistono prodotti per la tua ricerca!";
		List<Prodotto> l;
		if (!request.getParameter("categoria").equals("all")) {

			l = d.findProdottoByCategoria(nomeProdotto, Integer.parseInt(request.getParameter("categoria")));

		} else {
			l = d.findProdotto(nomeProdotto);

		}
		if(l.size()!=0)
			request.setAttribute("prodotti", l);
		else
			request.setAttribute("emptyP", k);
		forwardOnJsp(request, response, "/jsp/SearchProduct.jsp");

	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}
}
