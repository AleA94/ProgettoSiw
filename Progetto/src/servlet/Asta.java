package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import DAO.AstaProdottoDAO;
import DAO.OffertaDao;
import DAO.OffertaVeloceDao;
import data.AstaProdotto;
import data.Offerta;
import data.Utente;
import persistence.MySQLDaoFactory;

/**
 * Servlet implementation class HomeLoad
 */
public class Asta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AstaProdottoDAO asteProd = MySQLDaoFactory.getDAOFactory().getAstaProdottoDao();

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

			ArrayList<AstaProdotto> asteInScadenza = (ArrayList<AstaProdotto>) asteProd.getAste(
					Integer.parseInt(request.getParameter("day")), Integer.parseInt(request.getParameter("ore")));

			JSONArray a = new JSONArray();
			for (int i = 0; i < asteInScadenza.size(); i++) {
				JSONObject j = new JSONObject();
				try {
					j.put("idAsta", asteInScadenza.get(i).getIdAsta());
					j.put("asta", asteInScadenza.get(i).getNomeProdotto());
					j.put("descrizione", asteInScadenza.get(i).getDescrizioneProdotto());
					j.put("prezzo", asteInScadenza.get(i).getPrezzoCorrente());
					j.put("urlImage", asteInScadenza.get(i).getUrlImg());
					a.put(j);

				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
			response.setContentType("application/json");
			response.getWriter().print(a.toString());
		}

		else {
			if (request.getParameter("prezzoCor") != null) {
				OffertaVeloceDao offerta = MySQLDaoFactory.getDAOFactory().getOffertaVeloceDao();

				OffertaDao offerta_m = MySQLDaoFactory.getDAOFactory().getOfferta();
				Offerta offertaMax = offerta_m.getOffertaMax(7);
				float offMax;
				if (!(request.getParameter("offMax")).toString().equals("null"))
					offMax = Float.parseFloat((request.getParameter("offMax")));
				else {
					offMax = Float.parseFloat(request.getParameter("off"));
				}

				if (Float.parseFloat(request.getParameter("off")) > offertaMax.getOfferta_max()) {
					asteProd.updateAsta(Integer.parseInt(request.getParameter("asta")),
							Float.parseFloat(request.getParameter("off")));
					offerta.insertOfferta(((Utente) request.getSession().getAttribute("account")).getEmail(),
							Float.parseFloat(request.getParameter("off")), offMax, request.getParameter("asta"));
					response.getWriter().print("attualeVincitore");

				} else if (offMax > offertaMax.getOfferta_max()) {

					asteProd.updateAsta(Integer.parseInt(request.getParameter("asta")),
							(offertaMax.getOfferta_max() + 1));
					offerta.insertOfferta(((Utente) request.getSession().getAttribute("account")).getEmail(),
							(offertaMax.getOfferta_max() + 1), offMax, request.getParameter("asta"));
					response.getWriter().print("attualeVincitore");

				}

				else if (offMax > offertaMax.getImporto()) {

					asteProd.updateAsta(Integer.parseInt(request.getParameter("asta")), offMax + 1);
					offerta.insertOfferta(offertaMax.getEmail_utente(), offMax + 1, offertaMax.getOfferta_max(),
							request.getParameter("asta"));
					response.getWriter().print("OffertaMaxBassa");

				} else {
					response.getWriter().print("OffertaMaxMinoreImporto");

				}
			}
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
