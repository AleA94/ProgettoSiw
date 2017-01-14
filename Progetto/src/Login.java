
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.UtenteDAO;
import data.Utente;
import persistence.DAOFactory;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtenteDAO d = DAOFactory.getDAOFactory().getUtenteDao();

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
			String s = request.getParameter("user");
			s = s.substring(1, s.length() - 1);
			String[] par = s.split(";");
			Utente u = d.getUtente(par[0], par[1]);
			if (u != null) {
				response.getWriter().print("true;" + u.getNome());
				request.getSession().setAttribute("account", u);
			} else
				response.getWriter().print("false");
		} else if (request.getParameter("session") != null) {
			Utente a = (Utente) request.getSession().getAttribute("account");
			if (a != null) {
				response.getWriter().print("true;" + a.getNome());
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
