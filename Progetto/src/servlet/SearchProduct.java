package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CategoriaDAO;
import DAO.VendeProdottoAstaDAO;
import data.VendeProdottoAsta;
import persistence.DAOFactory;
import persistence.MySQLDaoFactory;

public class SearchProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoriaDAO c = MySQLDaoFactory.getDAOFactory().getCategoriaDao();
	VendeProdottoAstaDAO d = DAOFactory.getDAOFactory().getVendeProdottoAstaDAO();

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
		List<VendeProdottoAsta> l;
		if (!request.getParameter("categoria").equals("all")) {
			request.setAttribute("cat", c.getSubbyMacro(Integer.parseInt(request.getParameter("categoria"))));
			l = d.findProdottoByCategoria(nomeProdotto, Integer.parseInt(request.getParameter("categoria")));
		} else {
			request.setAttribute("cat", c.getSottoCategorie());
			l = d.findProdotto(nomeProdotto);
		}

		request.setAttribute("prodotti", l);
		request.setAttribute("nomeProdotto", nomeProdotto);
		forwardOnJsp(request, response, "/jsp/SearchProduct.jsp");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("action") != null) {
			if (request.getParameter("action").equals("filter")) {
				String nomeProdotto = request.getParameter("nomeProdotto");
				String orderBy = request.getParameter("order");
				List<VendeProdottoAsta> ricerca = null;
				if (request.getParameterValues("categoria") != null) {
					String[] categorie = request.getParameterValues("categoria");
					ricerca = d.getProdottiByCategoriaAndOrder(nomeProdotto, new ArrayList<>(Arrays.asList(categorie)),
							orderBy);
				} else {
					ricerca = d.getProdottiOrdered(nomeProdotto, orderBy);
				}
				request.setAttribute("cat", c.getSottoCategorie());
				request.setAttribute("prodotti", ricerca);
			}
			forwardOnJsp(request, response, "/jsp/SearchProduct.jsp");
		}
	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}

	private void include(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.include(request, response);
	}
}
