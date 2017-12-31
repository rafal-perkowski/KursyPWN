package app.model;

public class Logowanie {

	private int id_lg;
	private String login;
	private String passwd;
	private String rola;
	
	public Logowanie() {
		super();
	}
	
	public Logowanie(int id_lg, String login, String passwd, String rola) {
		super();
		this.id_lg = id_lg;
		this.login = login;
		this.passwd = passwd;
		this.rola = rola;
	}
	
	public int getId_lg() {
		return id_lg;
	}
	
	public void setId_lg(int id_lg) {
		this.id_lg = id_lg;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getRola() {
		return rola;
	}
	
	public void setRola(String rola) {
		this.rola = rola;
	}
	
	@Override
	public String toString() {
		return "Logowanie [id_lg=" + id_lg + ", login=" + login + ", passwd=" + passwd + ", rola=" + rola + "]";
	}

}

