package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.NotificaDAO;
import DAO.UtenteDAO;
import data.Utente;
import persistence.DAOFactory;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtenteDAO d = DAOFactory.getDAOFactory().getUtenteDao();
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
		super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("user") != null) {
			JSONObject o;
			Utente u = null;
			try {
				o = new JSONObject(request.getParameter("user"));
				u = d.getUtente(o.getString("mail"), o.getString("pass"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if (u != null) {
				JSONObject r = new JSONObject();
				try {
					r.put("log", true);
					r.put("name", u.getNome());
					r.put("sell", u.geteVenditore());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				response.setContentType("application/json");
				response.getWriter().write(r.toString());
				request.getSession().setAttribute("account", u);
				request.getSession().setAttribute("numNotifiche", n.getUnread(u.getEmail()));
			} else
				response.getWriter().print("false");
		} else if (request.getParameter("session") != null) {
			Utente a = (Utente) request.getSession().getAttribute("account");
			if (a != null) {
				JSONObject r = new JSONObject();
				try {
					r.put("log", true);
					r.put("name", a.getNome());
					r.put("sell", a.geteVenditore());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				response.setContentType("application/json");
				response.getWriter().write(r.toString());
				request.getSession().setAttribute("account", a);
			} else
				response.getWriter().print("false");
		} else if (request.getParameter("logout") != null) {
			request.getSession().removeAttribute("account");
		} else if (request.getParameter("register") != null) {
			String s = request.getParameter("register");
			ObjectMapper mapper = new ObjectMapper();
			Utente utente = mapper.readValue(s, Utente.class);
			d.save(utente);
		}
	}

}
