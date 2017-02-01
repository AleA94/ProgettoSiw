package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import DAO.AcquistaProdottoDAO;
import DAO.NotificaDAO;
import DAO.UtenteDAO;
import DAO.WishlistProdottoDAO;
import data.Utente;
import persistence.DAOFactory;

/**
 * Servlet implementation class ProfileManager
 */
public class ProfileManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UtenteDAO d = DAOFactory.getDAOFactory().getUtenteDao();
	AcquistaProdottoDAO a = DAOFactory.getDAOFactory().getAcquistaProdottoDAO();
	WishlistProdottoDAO w = DAOFactory.getDAOFactory().getWishlistProdottoDAO();
	NotificaDAO n = DAOFactory.getDAOFactory().getNotificaDao();

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
		if (request.getSession().getAttribute("account") != null) {
			String Utente = ((Utente) request.getSession().getAttribute("account")).getEmail();
			if (request.getParameter("action") != null) {
				if (request.getParameter("action").equals("purchases")) {
					request.setAttribute("acquisti", a.getAcquisti(Utente));
					forwardOnJsp(request, response, "/jsp/completePurchases.jsp");
				} else if (request.getParameter("action").equals("wish")) {
					request.getSession().setAttribute("wishlist", w.getWishByUtente(Utente));
					forwardOnJsp(request, response, "/jsp/Wishlist.jsp");
				} else if (request.getParameter("action").equals("notifica")) {
					request.getSession().setAttribute("notifiche", n.getNotifichebyUtente(Utente));
					n.clear(Utente);
					request.getSession().setAttribute("numNotifiche", 0);
					forwardOnJsp(request, response, "/jsp/Notifiche.jsp");
				}
			} else {
				forwardOnJsp(request, response, "/jsp/EditProfile.jsp");
			}
		} else
			response.sendRedirect(request.getContextPath() + "/");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("nome") != null) {
			Utente u = (Utente) request.getSession().getAttribute("account");
			u.setNome(request.getParameter("nome"));
			u.setCognome(request.getParameter("cognome"));
			u.setIndirizzo(request.getParameter("indirizzo"));
			d.updateDatas(u);
			response.sendRedirect(request.getContextPath() + "/ProfileManager");
		} else if (request.getParameter("passCheck") != null) {
			String s = request.getParameter("passCheck");
			if (s.equals(((Utente) request.getSession().getAttribute("account")).getPassword()))
				response.getWriter().print("true");
			else
				response.getWriter().print("false");
		} else if (request.getParameter("newPass") != null) {
			String s = request.getParameter("newPass");
			d.editPass(((Utente) request.getSession().getAttribute("account")).getEmail(), s);
		} else if (request.getParameter("rimuovi") != null) {
			String utente = ((Utente) request.getSession().getAttribute("account")).getEmail();
			int id = Integer.parseInt(request.getParameter("rimuovi"));
			w.removeItem(utente, id);
		} else if (request.getParameter("addCart") != null) {
			include(request, response, "/Carrello");

			Utente u = (Utente) request.getSession().getAttribute("account");

			try {
				JSONObject o = new JSONObject(request.getParameter("addCart"));
				w.removeItem(u.getEmail(), o.getInt("id"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (request.getParameter("buyNow") != null) {
			include(request, response, "/Carrello");

			Utente u = (Utente) request.getSession().getAttribute("account");
			try {
				JSONObject o = new JSONObject(request.getParameter("buyNow"));
				w.removeItem(u.getEmail(), o.getInt("id"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("wishlist") != null) {
			try {
				JSONObject o = new JSONObject(request.getParameter("wishlist"));
				Utente u = (Utente) request.getSession().getAttribute("account");
				w.add(u.getEmail(), o.getInt("id"), o.getInt("qt"));

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (request.getParameter("action") != null) {
			if (request.getParameter("action").equals("buyNowAll")) {
				include(request, response, "/Carrello");

				response.sendRedirect(request.getContextPath() + "/ProfileManager?action=wish");
			}
		}
	}

	private void include(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.include(request, response);
	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}
}
