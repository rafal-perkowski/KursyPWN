package app.model;

public class StatystykiKursantowView {
	
	private int id_gr;
	private String nazwa;
	private int id_kr;
	private String imie;
	private String nazwisko;
	private int status;
	private int liczba;
	
	public StatystykiKursantowView() {
		super();
	}

	public StatystykiKursantowView(int id_gr, String nazwa, int id_kr, String imie, String nazwisko, int status,
			int liczba) {
		super();
		this.id_gr = id_gr;
		this.nazwa = nazwa;
		this.id_kr = id_kr;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.status = status;
		this.liczba = liczba;
	}

	public int getId_gr() {
		return id_gr;
	}

	public void setId_gr(int id_gr) {
		this.id_gr = id_gr;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLiczba() {
		return liczba;
	}

	public void setLiczba(int liczba) {
		this.liczba = liczba;
	}

	@Override
	public String toString() {
		return "StatystykiKursantowView [id_gr=" + id_gr + ", nazwa=" + nazwa + ", id_kr=" + id_kr + ", imie=" + imie
				+ ", nazwisko=" + nazwisko + ", status=" + status + ", liczba=" + liczba + "]";
	}

}
