package app.model;

public class Trener {
	
	private int id_tr;
	private String imie;
	private String nazwisko;
	private String telefon;
	private String email;
	private String github;
	private int id_lg;
	
	public Trener() {
		super();
	}

	public Trener(int id_tr, String imie, String nazwisko, String telefon, String email, String github, int id_lg) {
		super();
		this.id_tr = id_tr;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.telefon = telefon;
		this.email = email;
		this.github = github;
		this.id_lg = id_lg;
	}

	public int getId_tr() {
		return id_tr;
	}

	public void setId_tr(int id_tr) {
		this.id_tr = id_tr;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public int getId_lg() {
		return id_lg;
	}

	public void setId_lg(int id_lg) {
		this.id_lg = id_lg;
	}

	@Override
	public String toString() {
		return "Trenerzy [id_tr=" + id_tr + ", imie=" + imie + ", nazwisko=" + nazwisko + ", telefon=" + telefon
				+ ", email=" + email + ", github=" + github + ", id_lg=" + id_lg + "]";
	}
	
}
