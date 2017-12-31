package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.database.DBConnector;
import app.model.Grupa;
import app.model.Kontakty;
import app.model.Kursant;
import app.model.Logowanie;
import app.model.Ocena;
import app.model.Projekt;
import app.model.Trener;

public class DBController {
	
    private List<Grupa> grupyArrayList;
    private List<Projekt> projektyArrayList;
    private List<Logowanie> logowanieArrayList;
    private List<Trener> trenerzyArrayList;
    private List<Kursant> kursanciArrayList;
    private List<Ocena> ocenyArrayList;
    private List<Kontakty> kontaktyArrayList;
    
    private Connection dbConnection;
    private PreparedStatement sqlPreparedStatement;

    public DBController() {
    	
		super();
    	dbConnection = DBConnector.staticDBConnection;
	}

	public void selectGrupy(){
       	
    	grupyArrayList = new ArrayList<Grupa>();
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT * FROM grupy;");
    		ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
    		
    		while(sqlResultSet.next()){
    			grupyArrayList.add(
    					new Grupa(
    							sqlResultSet.getInt("id_gr"),
    							sqlResultSet.getString("nazwa"),
    							sqlResultSet.getString("nazwa")
    							)
    					);
    		}
    	}
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	public void selectProjekty(){

    	projektyArrayList = new ArrayList<Projekt>();
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT * FROM projekty;");
    		ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
    		
    		while(sqlResultSet.next()) {
        		projektyArrayList.add(
        				new Projekt(
        						sqlResultSet.getInt("id_pr"),
        						sqlResultSet.getString("temat"),
        						sqlResultSet.getString("opis"),
        						sqlResultSet.getString("deadline"),
        						sqlResultSet.getInt("id_gr")
        						)
        				);
        	}
        	 		
    	}
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	public void selectLogowanie(){

    	logowanieArrayList = new ArrayList<Logowanie>();
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT * FROM logowanie;");
    		ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
    		
    		while(sqlResultSet.next()) {
        		logowanieArrayList.add(
        				new Logowanie(
        						sqlResultSet.getInt("id_lg"),
        						sqlResultSet.getString("login"),
        						sqlResultSet.getString("passwd"),
        						sqlResultSet.getString("rola")
        						)
        				);
        	}
        	 		
    	}
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	public void selectTrenerzy(){
    	
    	trenerzyArrayList = new ArrayList<Trener>();
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT * FROM trenerzy;");
    		ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
    		
    		while(sqlResultSet.next()){
    			trenerzyArrayList.add(
    					new Trener(
    							sqlResultSet.getInt("id_tr"),
    							sqlResultSet.getString("imie"),
    							sqlResultSet.getString("nazwisko"),
    							sqlResultSet.getString("telefon"),
    							sqlResultSet.getString("email"),
    							sqlResultSet.getString("github"),
    							sqlResultSet.getInt("id_lg")
    							)
    					);
    		}
    	}
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	public void selectKursanci(){

    	kursanciArrayList = new ArrayList<Kursant>();
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT * FROM kursanci;");
    		ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
    		
    		while(sqlResultSet.next()) {
        		kursanciArrayList.add(
        				new Kursant(
        						sqlResultSet.getInt("id_kr"),
        						sqlResultSet.getString("imie"),
        						sqlResultSet.getString("nazwisko"),
        						sqlResultSet.getString("telefon"),
        						sqlResultSet.getString("email"),
        						sqlResultSet.getString("github"),
        						sqlResultSet.getInt("id_gr"),
        						sqlResultSet.getInt("id_lg")
        						)
        				);
        	}
        	 		
    	}
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }

	public void selectOceny(){

    	ocenyArrayList = new ArrayList<Ocena>();
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT * FROM oceny;");
    		ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
    		
    		while(sqlResultSet.next()) {
        		ocenyArrayList.add(
        				new Ocena(
        						sqlResultSet.getInt("id_oc"),
        						sqlResultSet.getInt("id_pr"),
        						sqlResultSet.getInt("id_kr"),
        						sqlResultSet.getInt("statusprj"),
        						sqlResultSet.getInt("ocena"),
        						sqlResultSet.getInt("id_tr"),
        						sqlResultSet.getString("uwagi")
        						)
        				);
        	}
        	 		
    	}
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	public void selectKontakty(){

		kontaktyArrayList = new ArrayList<Kontakty>();
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT * FROM kontakty;");
    		ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
    		
    		while(sqlResultSet.next()) {
    			kontaktyArrayList.add(
        				new Kontakty(
        						sqlResultSet.getInt("id_ko"),
        						sqlResultSet.getInt("id_nadawcy"),
        						sqlResultSet.getInt("flaga_ntk"),
        						sqlResultSet.getInt("id_odbiorcy"),
        						sqlResultSet.getInt("flaga_otk"),
        						sqlResultSet.getString("temat"),
        						sqlResultSet.getString("tresc"),
        						sqlResultSet.getString("datetimetag")
        						)
        				);
        	}
        	 		
    	}
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	public void printGrupy() {
		String tmpString;
		int sizeInt = grupyArrayList.size();
		for(int i=0; i<sizeInt; i++) {
    		tmpString = grupyArrayList.get(i).toString();
    		System.out.println(tmpString);
    	}		
	}
	
	public void printProjekty() {
		String tmpString;
		int sizeInt = projektyArrayList.size();
		for(int i=0; i<sizeInt; i++) {
    		tmpString = projektyArrayList.get(i).toString();
    		System.out.println(tmpString);
    	}	
	}
	
	public void printLogowanie() {	
		String tmpString;
		int sizeInt = logowanieArrayList.size();
		for(int i=0; i<sizeInt; i++) {
    		tmpString = logowanieArrayList.get(i).toString();
    		System.out.println(tmpString);
    	}		
	}
	
	public void printTrenerzy() {
		String tmpString;
		int sizeInt = trenerzyArrayList.size();
		for(int i=0; i<sizeInt; i++) {
    		tmpString = trenerzyArrayList.get(i).toString();
    		System.out.println(tmpString);
    	}		
	}
	
	public void printKursanci() {
		String tmpString;
		int sizeInt = kursanciArrayList.size();
		for(int i=0; i<sizeInt; i++) {
    		tmpString = kursanciArrayList.get(i).toString();
    		System.out.println(tmpString);
    	}		
	}
	
	public void printOceny() {
		String tmpString;
		int sizeInt = ocenyArrayList.size();
		for(int i=0; i<sizeInt; i++) {
    		tmpString = ocenyArrayList.get(i).toString();
    		System.out.println(tmpString);
    	}			
	}
	
	public void printKontakty() {
		String tmpString;
		int sizeInt = kontaktyArrayList.size();
		for(int i=0; i<sizeInt; i++) {
    		tmpString = kontaktyArrayList.get(i).toString();
    		System.out.println(tmpString);
    	}		
	}
    
	public void selectDB() {

    	if(TestController.isCheckdbFlagBool()) {
 
	    	selectGrupy();
	    	selectProjekty();
	    	selectLogowanie();
	    	selectTrenerzy();
	    	selectKursanci();
	    	selectOceny();
	    	selectKontakty();
    		
	    	printGrupy();
	    	printProjekty();
	    	printLogowanie();
	    	printTrenerzy();
	    	printKursanci();
	    	printOceny();
	    	printKontakty();
    	}
    	
    }

}
