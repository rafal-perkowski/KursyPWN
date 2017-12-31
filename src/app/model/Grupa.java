package app.model;

public class Grupa {
	
	private int id_gr;
	private String nazwa;
	private String opis;
	
	public Grupa() {
		super();
	}

	public Grupa(int id_gr, String nazwa, String opis) {
		super();
		this.id_gr = id_gr;
		this.nazwa = nazwa;
		this.opis = opis;
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	@Override
	public String toString() {
		return "Grupy [id_gr=" + id_gr + ", nazwa=" + nazwa + ", opis=" + opis + "]";
	}
	
}
