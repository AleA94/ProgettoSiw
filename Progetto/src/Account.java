
public class Account {
	
	String user,password;
	
	public Account(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	
	public Account() {
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		return user.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Account a=(Account) obj;
		return (a.user.equals(user))&&(a.password.equals(password));
	}
}
