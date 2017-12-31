package app.model;

public class OcenyView {
	
	private int id_gr;
	private String nazwa;
	private int id_kr;
	private String imie;
	private String nazwisko;
	private int id_pr;
	private String temat;
	private String deadline;
	private int id_oc;
	private String ocena;
	private String uwagi;

	public OcenyView() {
		super();
	}

	public OcenyView(int id_gr, String nazwa, int id_kr, String imie, String nazwisko, int id_pr, String temat,
			String deadline, int id_oc, String ocena, String uwagi) {
		super();
		this.id_gr = id_gr;
		this.nazwa = nazwa;
		this.id_kr = id_kr;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.id_pr = id_pr;
		this.temat = temat;
		this.deadline = deadline;
		this.id_oc = id_oc;
		this.ocena = ocena;
		this.uwagi = uwagi;
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

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public int getId_oc() {
		return id_oc;
	}

	public void setId_oc(int id_oc) {
		this.id_oc = id_oc;
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
		return "OcenyView [id_gr=" + id_gr + ", nazwa=" + nazwa + ", id_kr=" + id_kr + ", imie=" + imie + ", nazwisko="
				+ nazwisko + ", id_pr=" + id_pr + ", temat=" + temat + ", deadline=" + deadline + ", id_oc=" + id_oc
				+ ", ocena=" + ocena + ", uwagi=" + uwagi + "]";
	}

}
