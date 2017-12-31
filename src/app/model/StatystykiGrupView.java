package app.model;

public class StatystykiGrupView {
	
	private int id_gr;
	private String nazwa;
	private int status;
	private int liczba;
	
	public StatystykiGrupView() {
		super();
	}

	public StatystykiGrupView(int id_gr, String nazwa, int status, int liczba) {
		super();
		this.id_gr = id_gr;
		this.nazwa = nazwa;
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
		return "StatystykiGrupView [id_gr=" + id_gr + ", nazwa=" + nazwa + ", status=" + status + ", liczba=" + liczba
				+ "]";
	}

}
