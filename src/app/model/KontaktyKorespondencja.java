package app.model;

public class KontaktyKorespondencja {
	
	private String nadawca;
	private String imie_nadawcy;
	private String nazwisko_nadawcy;
	private String odbiorca;
	private String imie_odbiorcy;
	private String nazwisko_odbiorcy;
	private String temat;
	private String tresc;
	private String datetimetag;
	
	public KontaktyKorespondencja() {
		super();
	}

	public KontaktyKorespondencja(String nadawca, String imie_nadawcy, String nazwisko_nadawcy, String odbiorca,
			String imie_odbiorcy, String nazwisko_odbiorcy, String temat, String tresc, String datetimetag) {
		super();
		this.nadawca = nadawca;
		this.imie_nadawcy = imie_nadawcy;
		this.nazwisko_nadawcy = nazwisko_nadawcy;
		this.odbiorca = odbiorca;
		this.imie_odbiorcy = imie_odbiorcy;
		this.nazwisko_odbiorcy = nazwisko_odbiorcy;
		this.temat = temat;
		this.tresc = tresc;
		this.datetimetag = datetimetag;
	}

	public String getNadawca() {
		return nadawca;
	}

	public void setNadawca(String nadawca) {
		this.nadawca = nadawca;
	}

	public String getImie_nadawcy() {
		return imie_nadawcy;
	}

	public void setImie_nadawcy(String imie_nadawcy) {
		this.imie_nadawcy = imie_nadawcy;
	}

	public String getNazwisko_nadawcy() {
		return nazwisko_nadawcy;
	}

	public void setNazwisko_nadawcy(String nazwisko_nadawcy) {
		this.nazwisko_nadawcy = nazwisko_nadawcy;
	}

	public String getOdbiorca() {
		return odbiorca;
	}

	public void setOdbiorca(String odbiorca) {
		this.odbiorca = odbiorca;
	}

	public String getImie_odbiorcy() {
		return imie_odbiorcy;
	}

	public void setImie_odbiorcy(String imie_odbiorcy) {
		this.imie_odbiorcy = imie_odbiorcy;
	}

	public String getNazwisko_odbiorcy() {
		return nazwisko_odbiorcy;
	}

	public void setNazwisko_odbiorcy(String nazwisko_odbiorcy) {
		this.nazwisko_odbiorcy = nazwisko_odbiorcy;
	}

	public String getTemat() {
		return temat;
	}

	public void setTemat(String temat) {
		this.temat = temat;
	}

	public String getTresc() {
		return tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public String getDatetimetag() {
		return datetimetag;
	}

	public void setDatetimetag(String datetimetag) {
		this.datetimetag = datetimetag;
	}

	@Override
	public String toString() {
		return "KontaktyKorespondencja [nadawca=" + nadawca + ", imie_nadawcy=" + imie_nadawcy + ", nazwisko_nadawcy="
				+ nazwisko_nadawcy + ", odbiorca=" + odbiorca + ", imie_odbiorcy=" + imie_odbiorcy
				+ ", nazwisko_odbiorcy=" + nazwisko_odbiorcy + ", temat=" + temat + ", tresc=" + tresc
				+ ", datetimetag=" + datetimetag + "]";
	}
	
}
