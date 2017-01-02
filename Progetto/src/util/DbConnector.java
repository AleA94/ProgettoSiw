package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbConnector {
	private Statement s;
	private Connection c;

	public DbConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			c = DriverManager.getConnection("jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8151699", "sql8151699",
					"9AZKtF5ZyT");
			s = c.createStatement();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> executeQuery(String q, String columnName) {

		q = q.substring(1, q.length() - 1);
		ArrayList<String> l = new ArrayList<>();
		try {
			ResultSet r = s.executeQuery(q);
			while (r.next()) {
				l.add(r.getString(columnName));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;

	}

	public void registraNuovoAccount(String nome, String cognome, String indirizzo, String email, String password) {
		try {
			PreparedStatement p = c
					.prepareStatement("insert into Utente (email,password,nome,cognome,indirizzo) values(?,?,?,?,?)");
			p.setString(1, email);
			p.setString(2, password);
			p.setString(3, nome);
			p.setString(4, cognome);
			p.setString(5, indirizzo);

			p.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
