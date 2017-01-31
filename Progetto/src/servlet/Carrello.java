package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import DAO.AcquistaDao;
import DAO.CarrelloProdottoDAO;
import data.Acquisto;
import data.CarrelloItem;
import data.Utente;
import persistence.MySQLDaoFactory;

/**
 * Servlet implementation class Carrello
 */
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarrelloProdottoDAO d = MySQLDaoFactory.getDAOFactory().getCarrelloProdottoDAO();
	private AcquistaDao acquisti = MySQLDaoFactory.getDAOFactory().getAcquistaDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("account") != null) {
			String mail = ((Utente) request.getSession().getAttribute("account")).getEmail();
			if (request.getParameter("action") != null) {
				if (request.getParameter("action").equals("acquista")) {
					int id = Integer.parseInt(request.getParameter("id"));
					int qt = Integer.parseInt(request.getParameter("qt"));
					HashMap<Integer, CarrelloItem> carrello = (HashMap<Integer, CarrelloItem>) request.getSession()
							.getAttribute("cart");
					carrello.get(id).setQuantita(qt);
					List<CarrelloItem> c = new ArrayList<>();
					c.add(carrello.get(id));
					request.setAttribute("daAcquistare", c);
					forwardOnJsp(request, response, "/jsp/ConfermaAcquisto.jsp");
				}
			} else {
				HashMap<Integer, CarrelloItem> carrelloByUtente = d.getCarrelloByUtente(mail);
				request.getSession().setAttribute("cart", carrelloByUtente);
				request.setAttribute("carrello", carrelloByUtente.values());
				forwardOnJsp(request, response, "/jsp/Carrello.jsp");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("rimuovi") != null) {
			int id = Integer.parseInt(request.getParameter("rimuovi"));
			HashMap<Integer, CarrelloItem> carrello = (HashMap<Integer, CarrelloItem>) request.getSession()
					.getAttribute("cart");
			carrello.remove(id);
			d.removeItem(id, ((Utente) request.getSession().getAttribute("account")).getEmail());
		} else if (request.getParameter("addCart") != null) {
			String mail = ((Utente) request.getSession().getAttribute("account")).getEmail();
			try {
				JSONObject o = new JSONObject(request.getParameter("addCart"));
				d.addToCart(mail, o.getInt("id"), o.getInt("qt"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (request.getParameter("buyNow") != null) {
			try {
				String mail = ((Utente) request.getSession().getAttribute("account")).getEmail();
				JSONObject o = new JSONObject(request.getParameter("buyNow"));
				Acquisto a = new Acquisto(o.getInt("id"), o.getInt("qt"), mail, new Date());
				acquisti.save(a);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (request.getParameter("action") != null) {
			if (request.getParameter("action").equals("confirmAll")) {
				String mail = ((Utente) request.getSession().getAttribute("account")).getEmail();
				String[] ids = request.getParameterValues("id");
				String[] qts = request.getParameterValues("quantita");
				List<Acquisto> l = new ArrayList<>();
				for (int i = 0; i < ids.length; i++) {
					int id = Integer.parseInt(ids[i]);
					l.add(new Acquisto(id, Integer.parseInt(qts[i]), mail, new Date()));
					d.setQuantityAfterBuy(Integer.parseInt(qts[i]), id, mail);
				}
				d.cleanCart(mail);
				acquisti.saveAll(l);
				response.sendRedirect(request.getContextPath() + "/Carrello");
			} else if (request.getParameter("action").equals("buyAll")) {
				HashMap<Integer, CarrelloItem> carrelloByUtente = (HashMap<Integer, CarrelloItem>) request.getSession()
						.getAttribute("cart");
				String[] ids = request.getParameterValues("id");
				String[] qts = request.getParameterValues("qts");
				for (int i = 0; i < qts.length; i++) {
					carrelloByUtente.get(Integer.parseInt(ids[i])).setQuantita(Integer.parseInt(qts[i]));
				}
				request.setAttribute("daAcquistare", carrelloByUtente.values());
				forwardOnJsp(request, response, "/jsp/ConfermaAcquisto.jsp");
			} else if (request.getParameter("action").equals("buyNowAll")) {
				String mail = ((Utente) request.getSession().getAttribute("account")).getEmail();
				String[] ids = request.getParameterValues("id");
				String[] qts = request.getParameterValues("qts");
				List<Acquisto> l = new ArrayList<>();
				for (int i = 0; i < ids.length; i++) {
					int id = Integer.parseInt(ids[i]);
					l.add(new Acquisto(id, Integer.parseInt(qts[i]), mail, new Date()));
				}
				acquisti.saveAll(l);
			}
		}
	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}

}
