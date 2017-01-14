
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProdottoDAO;
import data.Prodotto;
import data.Utente;
import persistence.MySQLDaoFactory;

/**
 * Servlet implementation class NewItem
 */
@WebServlet("/NewItem")
public class NewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdottoDAO d = MySQLDaoFactory.getDAOFactory().getProdottoDao();

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		int categoria = Integer.parseInt(request.getParameter("categoria"));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date inizio = null;
		Date fine = null;
		if (request.getParameter("dataInizio") != null && request.getParameter("dataInizio") != "")
			try {
				inizio = format.parse(request.getParameter("dataInizio"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (request.getParameter("dataFine") != null && request.getParameter("dataFine") != "")
			try {
				fine = format.parse(request.getParameter("dataFine"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		int inAsta = 0;
		if (request.getParameter("inAsta") != null)
			inAsta = 1;

		float prezzo = Float.parseFloat(request.getParameter("prezzo"));

		Prodotto p = new Prodotto(inAsta, categoria, inizio, fine, prezzo, nome, descrizione,
				((Utente) request.getSession().getAttribute("account")).getEmail());

		d.save(p);
		forwardOnJsp(request, response, "/jsp/index.jsp");
	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}
}
