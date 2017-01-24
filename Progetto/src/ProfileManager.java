
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UtenteDAO;
import data.Utente;
import persistence.DAOFactory;

/**
 * Servlet implementation class ProfileManager
 */
@WebServlet("/ProfileManager")
public class ProfileManager extends HttpServlet {
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
		if (request.getSession().getAttribute("account") != null)
			forwardOnJsp(request, response, "/jsp/EditProfile.jsp");
		else
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
			s = s.substring(1, s.length() - 1);
			if (s.equals(((Utente) request.getSession().getAttribute("account")).getPassword()))
				response.getWriter().print("true");
			else
				response.getWriter().print("false");
		} else if (request.getParameter("newPass") != null) {
			String s = request.getParameter("newPass");
			d.editPass(((Utente) request.getSession().getAttribute("account")).getEmail(), s);
		}
	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}
}
