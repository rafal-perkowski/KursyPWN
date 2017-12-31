package app.model;

public class ProjektyView {
	
	private int id_pr;
	private String temat;
	private String opis;
	private String deadline;
	private String ocena;
	private String uwagi;
	
	public ProjektyView() {
		super();
	}

	public ProjektyView(int id_pr, String temat, String opis, String deadline, String ocena, String uwagi) {
		super();
		this.id_pr = id_pr;
		this.temat = temat;
		this.opis = opis;
		this.deadline = deadline;
		this.ocena = ocena;
		this.uwagi = uwagi;
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

	public String getOcena() {
		return ocena;
	}

	public void setOcena(String ocena) {
		this.ocena = ocena;
	}

	public String getUwagi() {
		return uwagi;
	}

	public void setUwagi(String uwagi) {
		this.uwagi = uwagi;
	}

	@Override
	public String toString() {
		return "ProjektyView [id_pr=" + id_pr + ", temat=" + temat + ", opis=" + opis + ", deadline=" + deadline
				+ ", ocena=" + ocena + ", uwagi=" + uwagi + "]";
	}

}
