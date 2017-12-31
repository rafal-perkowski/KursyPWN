package app.model;

public class Kontakty {
	
	private int id_ko;
	private int id_nadawcy;
	private int flaga_ntk;
	private int id_odbiorcy;
	private int flaga_otk;
	private String temat;
	private String tresc;
	private String datetimetag;
	
	public Kontakty() {
		super();
	}

	public Kontakty(int id_ko, int id_nadawcy, int flaga_ntk, int id_odbiorcy, int flaga_otk, String temat, String tresc, String datetimetag) {
		
		super();
		this.id_ko = id_ko;
		this.id_nadawcy = id_nadawcy;
		this.flaga_ntk = flaga_ntk;
		this.id_odbiorcy = id_odbiorcy;
		this.flaga_otk = flaga_otk;
		this.temat = temat;
		this.tresc = tresc;
		this.datetimetag = datetimetag;
	}

	public int getId_ko() {
		return id_ko;
	}

	public void setId_ko(int id_ko) {
		this.id_ko = id_ko;
	}

	public int getId_nadawcy() {
		return id_nadawcy;
	}

	public void setId_nadawcy(int id_nadawcy) {
		this.id_nadawcy = id_nadawcy;
	}

	public int getFlaga_ntk() {
		return flaga_ntk;
	}

	public void setFlaga_ntk(int flaga_ntk) {
		this.flaga_ntk = flaga_ntk;
	}

	public int getId_odbiorcy() {
		return id_odbiorcy;
	}

	public void setId_odbiorcy(int id_odbiorcy) {
		this.id_odbiorcy = id_odbiorcy;
	}

	public int getFlaga_otk() {
		return flaga_otk;
	}

	public void setFlaga_otk(int flaga_otk) {
		this.flaga_otk = flaga_otk;
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
		return "Korespondencja [id_ko=" + id_ko + ", id_nadawcy=" + id_nadawcy + ", flaga_ntk=" + flaga_ntk
				+ ", id_odbiorcy=" + id_odbiorcy + ", flaga_otk=" + flaga_otk + ", temat=" + temat + ", tresc=" + tresc
				+ ", datetimetag=" + datetimetag + "]";
	}
	
}



