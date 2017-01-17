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

		String categoria = request.getParameter("categoria");
		String nomeProdotto = request.getParameter("nomeProdotto");
		ProdottoDAO d = DAOFactory.getDAOFactory().getProdottoDao();
		//System.out.println(categoria);
		List<Prodotto> l;
		if(categoria=="" || categoria=="Tutte Le categorie")
		{
			
			 l = d.findProdotto(nomeProdotto);
			
		}
		else
		{
			 l = d.findProdottoByCategoria(nomeProdotto,categoria);			
		}
		request.setAttribute("prodotti", l);
		forwardOnJsp(request, response, "/jsp/SearchProduct.jsp");

	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}
}