package app.model;

public class Ocena {
	
	private int id_oc;
	private int id_pr;
	private int id_kr;
	private int statusprj;
	private int ocena;
	private int id_tr;
	private String uwagi;
	
	public Ocena() {
		super();
	}

	public Ocena(int id_oc, int id_pr, int id_kr, int statusprj, int ocena, int id_tr, String uwagi) {
		super();
		this.id_oc = id_oc;
		this.id_pr = id_pr;
		this.id_kr = id_kr;
		this.statusprj = statusprj;
		this.ocena = ocena;
		this.id_tr = id_tr;
		this.uwagi = uwagi;
	}

	public int getId_oc() {
		return id_oc;
	}

	public void setId_oc(int id_oc) {
		this.id_oc = id_oc;
	}

	public int getId_pr() {
		return id_pr;
	}

	public void setId_pr(int id_pr) {
		this.id_pr = id_pr;
	}

	public int getId_kr() {
		return id_kr;
	}

	public void setId_kr(int id_kr) {
		this.id_kr = id_kr;
	}

	public int getStatusprj() {
		return statusprj;
	}

	public void setStatusprj(int statusprj) {
		this.statusprj = statusprj;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public int getId_tr() {
		return id_tr;
	}

	public void setId_tr(int id_tr) {
		this.id_tr = id_tr;
	}

	public String getUwagi() {
		return uwagi;
	}

	public void setUwagi(String uwagi) {
		this.uwagi = uwagi;
	}

	@Override
	public String toString() {
		return "Ocena [id_oc=" + id_oc + ", id_pr=" + id_pr + ", id_kr=" + id_kr + ", statusprj=" + statusprj
				+ ", ocena=" + ocena + ", id_tr=" + id_tr + ", uwagi=" + uwagi + "]";
	}

}

