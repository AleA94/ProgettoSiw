
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import DAO.AstaProdottoDAO;
import data.AstaProdotto;
import persistence.MySQLDaoFactory;

/**
 * Servlet implementation class HomeLoad
 */
@WebServlet("/Ast")
public class Ast extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AstaProdottoDAO a = MySQLDaoFactory.getDAOFactory().getAstaDao();

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
		// TODO Auto-generated method stub
		if (request.getParameter("day") != null) {
			System.out.println(
					"ciao" + request.getParameter("day").substring(1, request.getParameter("day").length() - 1));

			request.setAttribute("date",
					a.getAste(
							Integer.parseInt(
									request.getParameter("day").substring(1, request.getParameter("day").length() - 1)),
							1));
			ArrayList<AstaProdotto> asteInScadenza = (ArrayList<AstaProdotto>) a.getAste(Integer
					.parseInt(request.getParameter("day").substring(1, request.getParameter("day").length() - 1)), 1);

			JSONArray a = new JSONArray();
			for (int i = 0; i < asteInScadenza.size(); i++) {
				JSONObject j = new JSONObject();
				try {
					j.put("asta", asteInScadenza.get(i).getNomeProdotto());
					j.put("descrizione", asteInScadenza.get(i).getDescrizioneProdotto());
					j.put("prezzo", asteInScadenza.get(i).getPrezzoCorrente());
					a.put(j);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			response.setContentType("application/json");
			response.getWriter().print(a.toString());
		} else {
		}
	}

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
