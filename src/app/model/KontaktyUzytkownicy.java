package app.model;

public class KontaktyUzytkownicy {
	
	private String uzytkownik;
	private int flaga_tk;
	private int id_utk;
	private String imie;
	private String nazwisko;
	private String telefon;
	private String email;
	private String github;
	
	public KontaktyUzytkownicy() {
		super();
	}

	public KontaktyUzytkownicy(String uzytkownik, int flaga_tk, int id_utk, String imie, String nazwisko,
			String telefon, String email, String github) {
		super();
		this.uzytkownik = uzytkownik;
		this.flaga_tk = flaga_tk;
		this.id_utk = id_utk;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.telefon = telefon;
		this.email = email;
		this.github = github;
	}

	public String getUzytkownik() {
		return uzytkownik;
	}

	public void setUzytkownik(String uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

	public int getFlaga_tk() {
		return flaga_tk;
	}

	public void setFlaga_tk(int flaga_tk) {
		this.flaga_tk = flaga_tk;
	}

	public int getId_utk() {
		return id_utk;
	}

	public void setId_utk(int id_utk) {
		this.id_utk = id_utk;
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

	@Override
	public String toString() {
		return "KontaktyUzytkownicy [uzytkownik=" + uzytkownik + ", flaga_tk=" + flaga_tk + ", id_utk=" + id_utk
				+ ", imie=" + imie + ", nazwisko=" + nazwisko + ", telefon=" + telefon + ", email=" + email
				+ ", github=" + github + "]";
	}
	
}
