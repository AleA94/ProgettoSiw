
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CategoriaDAO;
import DAO.ProdottoDAO;
import data.Prodotto;
import data.Utente;
import persistence.MySQLDaoFactory;

/**
 * Servlet implementation class ShopManager
 */
@WebServlet("/ShopManager")
public class ShopManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProdottoDAO d = MySQLDaoFactory.getDAOFactory().getProdottoDao();
	CategoriaDAO c = MySQLDaoFactory.getDAOFactory().getCategoriaDao();

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
		Utente utente = (Utente) request.getSession().getAttribute("account");
		if (utente != null) {
			if (request.getParameter("action") != null && request.getParameter("action").equals("edit")) {
				request.setAttribute("p", d.findById(Integer.parseInt(request.getParameter("id"))));
				request.setAttribute("subCategorie", c.getSottoCategorie());
				forwardOnJsp(request, response, "/jsp/EditItem.jsp");
			} else if (utente.geteVenditore() == 1) {
				request.setAttribute("prodotti", d.findByNegozio(utente.getEmail()));
				forwardOnJsp(request, response, "/jsp/ShopManager.jsp");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("idRemove") != null) {
			String idS = request.getParameter("idRemove");
			int id = Integer.parseInt(idS.substring(1, idS.length() - 1));
			d.removeById(id);
		} else if (request.getParameter("par") != null) {
			Prodotto p = new Prodotto();
			p.setIdProdotto(Integer.parseInt(request.getParameter("id")));
			p.setNome(request.getParameter("nome"));
			p.setDescrizione(request.getParameter("descrizione"));
			p.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
			p.setInAsta(Integer.parseInt(request.getParameter("inAsta")));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if (request.getParameter("dataInizio") != null)
					p.setDataInizio(format.parse(request.getParameter("dataInizio")));
				if (request.getParameter("dataFine") != null)
					p.setDataFine(format.parse(request.getParameter("dataFine")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			p.setNegozio(((Utente) request.getSession().getAttribute("account")).getEmail());
			p.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
			d.edit(p);
			response.sendRedirect(request.getContextPath() + "/ShopManager");

		} else {
			request.setAttribute("subCategorie", c.getSottoCategorie());
			forwardOnJsp(request, response, "/jsp/newItem.jsp");
		}
	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}
}
