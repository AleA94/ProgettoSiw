package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import DAO.AstaDAO;
import DAO.CategoriaDAO;
import DAO.VendeProdottoDAO;
import data.Asta;
import data.Prodotto;
import data.Utente;
import data.VendeProdotto;
import persistence.MySQLDaoFactory;

/**
 * Servlet implementation class ShopManager
 */
public class ShopManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ProdottoDAO d = MySQLDaoFactory.getDAOFactory().getProdottoDao();
	VendeProdottoDAO v = MySQLDaoFactory.getDAOFactory().getVendeProdottoDAO();
	CategoriaDAO c = MySQLDaoFactory.getDAOFactory().getCategoriaDao();
	AstaDAO a = MySQLDaoFactory.getDAOFactory().getAstaDao();

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
		Utente utente = (Utente) request.getSession().getAttribute("account");
		if (utente != null) {
			if (request.getParameter("action") != null) {
				if (request.getParameter("action").equals("edit")) {
					VendeProdotto productbyId = v.getProductbyId(utente.getEmail(),
							Integer.parseInt(request.getParameter("id")));
					request.getSession().setAttribute("p", productbyId);
					request.setAttribute("subCategorie", c.getSottoCategorie());
					if (productbyId.getProdotto().getInAsta() == 1)
						request.getSession().setAttribute("a", a.getAsta(Integer.parseInt(request.getParameter("id"))));
					else
						request.getSession().setAttribute("a", null);
					forwardOnJsp(request, response, "/jsp/EditItem.jsp");
				} else if (request.getParameter("action").equals("new")) {
					request.setAttribute("subCategorie", c.getSottoCategorie());
					forwardOnJsp(request, response, "/jsp/newItem.jsp");
				}
			} else if (utente.geteVenditore() == 1) {
				request.setAttribute("prodotti", v.getProdottibyNegozio(utente.getEmail()));
				forwardOnJsp(request, response, "/jsp/ShopManager.jsp");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("account");
		if (request.getParameter("idRemove") != null) {
			String idS = request.getParameter("idRemove");
			v.removeProduct(utente.getEmail(), Integer.parseInt(idS));
		} else if (request.getParameter("par") != null) {
			if (request.getParameter("par").equals("edit")) {
				Asta as = (Asta) request.getSession().getAttribute("a");
				VendeProdotto vende = (VendeProdotto) request.getSession().getAttribute("p");
				Prodotto p = vende.getProdotto();
				p.setIdProdotto(Integer.parseInt(request.getParameter("id")));
				p.setNome(request.getParameter("nome"));
				p.setDescrizione(request.getParameter("descrizione"));
				p.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
				if (request.getParameter("photo") != null)
					p.setImmagine(request.getParameter("photo"));
				if (request.getParameterValues("photos") != null)
					p.addImmaginiAggiuntive(request.getParameterValues("photos"));

				if (p.getInAsta() == 1) {
					Asta asta = new Asta();
					asta.setIdProdotto(p.getIdProdotto());
					asta.setBaseAsta(Float.parseFloat(request.getParameter("baseAsta")));
					asta.setDataFine(as.getDataFine());
					asta.setDataInizio(as.getDataInizio());
					asta.setPrezzoRiserva(Float.parseFloat(request.getParameter("riserva")));
					a.edit(asta);
					p.setPrezzo(Float.parseFloat(request.getParameter("riserva")));
				} else {
					vende.setQuantita(Integer.parseInt(request.getParameter("quantita")));
					p.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
				}
				vende.setNegozio(((Utente) request.getSession().getAttribute("account")).getEmail());
				vende.setProdotto(p);
				v.edit(vende);
				response.sendRedirect(request.getContextPath() + "/ShopManager");
			} else if (request.getParameter("par").equals("new")) {
				VendeProdotto vende = new VendeProdotto();
				Prodotto p = new Prodotto();
				p.setNome(request.getParameter("nome"));
				p.setDescrizione(request.getParameter("descrizione"));
				p.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
				p.setImmagine(request.getParameter("mainPhoto"));
				p.setImmaginiAggiuntive(request.getParameterValues("photos"));
				if (request.getParameter("inAsta") != null) {
					p.setInAsta(1);
				} else {
					p.setInAsta(0);
					vende.setQuantita(Integer.parseInt(request.getParameter("quantita")));
				}
				p.setPrezzo(Float.parseFloat("prezzo"));
				vende.setNegozio(((Utente) request.getSession().getAttribute("account")).getEmail());
				vende.setProdotto(p);

				int id = v.saveProdottoinNegozio(vende);
				if (p.getInAsta() == 1) {
					Asta asta = new Asta();
					asta.setIdProdotto(id);
					asta.setBaseAsta(p.getPrezzo());
					asta.setPrezzoCorrente(p.getPrezzo());
					asta.setDataFine(new DateTime(request.getParameter("dataFine")).toDate());
					asta.setDataInizio(new DateTime(request.getParameter("dataInizio")).toDate());
					asta.setPrezzoRiserva(Float.parseFloat(request.getParameter("riserva")));
					a.save(asta);
				}
				request.getSession().setAttribute("a", null);

				response.sendRedirect(request.getContextPath() + "/ShopManager");

			}
		} else if (request.getParameter("rmvImages") != null) {
			VendeProdotto v = (VendeProdotto) request.getSession().getAttribute("p");
			v.getProdotto().getImmaginiAggiuntive().remove(request.getParameter("rmvImages"));
			System.out.println(request.getParameter("rmvImages"));
		} else if (request.getParameter("rmvImage") != null) {
			VendeProdotto v = (VendeProdotto) request.getSession().getAttribute("p");
			v.getProdotto().setImmagine(null);
		}
	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}
}
