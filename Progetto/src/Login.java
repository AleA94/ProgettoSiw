
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map<String, Account> dbAccount;

	public Login() {
		super();
		dbAccount = new HashMap<>();
		dbAccount.put("asd@g.comasd", new Account("asd@g.com", "asd"));
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
			ObjectMapper o = new ObjectMapper();
			Account a = o.readValue(request.getParameter("user"), Account.class);
			if (dbAccount.containsKey(a.user + a.password)) {
				response.getWriter().print("true;" + a.user);
				request.getSession().setAttribute("account", a);
			} else
				response.getWriter().print("false");
		} else if (request.getParameter("session") != null) {
			Account a = (Account) request.getSession().getAttribute("account");
			if (a != null) {
				response.getWriter().print("true;" + a.user);
				request.getSession().setAttribute("account", a);
			} else
				response.getWriter().print("false");
		} else if (request.getParameter("logout") != null) {
			request.getSession().removeAttribute("account");
		}
	}

}
