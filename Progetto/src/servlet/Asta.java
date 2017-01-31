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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			response.setContentType("application/json");
			response.getWriter().print(a.toString());
		}

		else {
			if (request.getParameter("prezzoCor") != null) {
				OffertaVeloceDao offerta = MySQLDaoFactory.getDAOFactory().getOffertaVeloceDao();

				// recupero offerta vincitore attuale select * from offerta
				// where offerta_max=(select max(offerta_max) from offerta)
				OffertaDao offerta_m = MySQLDaoFactory.getDAOFactory().getOfferta();
				Offerta offertaMax = offerta_m.getOffertaMax(7);
				System.out.println(offertaMax.getAsta() + " " + offertaMax.getEmail_utente() + " "
						+ offertaMax.getImporto() + " " + offertaMax.getOfferta_max());

				// importo >offeta max?
				System.out.println(Double.parseDouble(request.getParameter("off")));

				if (Float.parseFloat(request.getParameter("off")) > offertaMax.getOfferta_max()) {
					asteProd.updateAsta(Integer.parseInt(request.getParameter("asta")),
							Float.parseFloat(request.getParameter("off")));
					offerta.insertOfferta(((Utente) request.getSession().getAttribute("account")).getEmail(),
							Float.parseFloat(request.getParameter("off")),
							Float.parseFloat(request.getParameter("offMax")), request.getParameter("asta"));
				}
				// si settare asta con prezzo corrente= importo update asta set
				// prezzoCorrente=? where id=?

				// no la mia offerta max è maggiore della vecchia offerta max?
				else if (Float.parseFloat((request.getParameter("offMax"))) > offertaMax.getOfferta_max()) {

					asteProd.updateAsta(Integer.parseInt(request.getParameter("asta")),
							(offertaMax.getOfferta_max() + 1));
					offerta.insertOfferta(((Utente) request.getSession().getAttribute("account")).getEmail(),
							(offertaMax.getOfferta_max() + 1), Float.parseFloat(request.getParameter("offMax")),
							request.getParameter("asta"));

				}

				// si 1) inserire nuova offerta non mia email offerta= sua
				// offerta max+1 offerta max= mia offerta max
				// 2)settare l asta prezzo corrente= sua offerta max +1
				// no 1) inserire nuova offerta con sua email importo= mia+
				else if (Float.parseFloat((request.getParameter("offMax"))) > offertaMax.getImporto()) {

					asteProd.updateAsta(Integer.parseInt(request.getParameter("asta")),
							Float.parseFloat(request.getParameter("offMax")) + 1);
					offerta.insertOfferta(offertaMax.getEmail_utente(),
							Float.parseFloat(request.getParameter("offMax")) + 1, offertaMax.getOfferta_max(),
							request.getParameter("asta"));

				}

				// offerta max +1 offerta max= sua offerta max
				// 2)settare l'asta prezzo= mia offerta massima +1
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
