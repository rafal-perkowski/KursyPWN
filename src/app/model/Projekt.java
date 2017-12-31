package app.model;

public class Projekt {
	
	private int id_pr;
	private String temat;
	private String opis;
	private String deadline;
	private int id_gr;
	
	public Projekt() {
		super();
	}

	public Projekt(int id_pr, String temat, String opis, String deadline, int id_gr) {
		super();
		this.id_pr = id_pr;
		this.temat = temat;
		this.opis = opis;
		this.deadline = deadline;
		this.id_gr = id_gr;
	}

	public int getId_pr() {
		return id_pr;
	}

	public void setId_pr(int id_pr) {
		this.id_pr = id_pr;
	}

	public String getTemat() {
		return temat;
	}

	public void setTemat(String temat) {
		this.temat = temat;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public int getId_gr() {
		return id_gr;
	}

	public void setId_gr(int id_gr) {
		this.id_gr = id_gr;
	}

	@Override
	public String toString() {
		return "Projekty [id_pr=" + id_pr + ", temat=" + temat + ", opis=" + opis + ", deadline=" + deadline
				+ ", id_gr=" + id_gr + "]";
	}
	
}
