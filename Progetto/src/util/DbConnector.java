package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbConnector {
	private Statement s;

	public DbConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/siw?user=root");
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

}
