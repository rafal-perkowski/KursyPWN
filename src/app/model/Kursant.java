package app.model;

public class Kursant {
	
	private int id_kr;
	private String imie;
	private String nazwisko;
	private String telefon;
	private String email;
	private String github;
	private int id_gr;
	private int id_lg;
	
	public Kursant() {
		super();
	}

	public Kursant(int id_kr, String imie, String nazwisko, String telefon, String email, String github, int id_gr, int id_lg) {
		super();
		this.id_kr = id_kr;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.telefon = telefon;
		this.email = email;
		this.github = github;
		this.id_gr = id_gr;
		this.id_lg = id_lg;
	}

	public int getId_kr() {
		return id_kr;
	}

	public void setId_kr(int id_kr) {
		this.id_kr = id_kr;
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

	public int getId_gr() {
		return id_gr;
	}

	public void setId_gr(int id_gr) {
		this.id_gr = id_gr;
	}

	public int getId_lg() {
		return id_lg;
	}

	public void setId_lg(int id_lg) {
		this.id_lg = id_lg;
	}

	@Override
	public String toString() {
		return "Kursanci [id_kr=" + id_kr + ", imie=" + imie + ", nazwisko=" + nazwisko + ", telefon=" + telefon
				+ ", email=" + email + ", github=" + github + ", id_gr=" + id_gr + ", id_lg=" + id_lg + "]";
	}

}
