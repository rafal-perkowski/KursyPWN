package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.database.DBConnector;
import app.model.Kursant;
import app.model.Logowanie;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DaneUserController {

    @FXML
    private AnchorPane ap_daneuser;

    @FXML
    private TextField tf_id_kr;

    @FXML
    private TextField tf_imie;

    @FXML
    private TextField tf_nazwisko;

    @FXML
    private TextField tf_telefon;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_github;

    @FXML
    private TextField tf_id_gr;

    @FXML
    private TextField tf_id_lg;

    @FXML
    private TextField tf_rola;

    @FXML
    private TextField tf_login;
    
    private Connection dbConnection; 
    private PreparedStatement sqlPreparedStatement;
    private Kursant danePersonalneKursant;
    private Logowanie daneLogowanie;
    
    private void selectDaneUser() {
    	
    	TestController.traceCounter(" IN: DaneUserController selectDaneUser()");
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT id_kr, imie, nazwisko, telefon, email, github, id_gr, id_lg FROM kursanci WHERE id_lg=" + LoginController.selectedLoginInt);
			ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
			if(sqlResultSet.next())
				danePersonalneKursant = new Kursant(
					sqlResultSet.getInt("id_kr"),
					sqlResultSet.getString("imie"),
					sqlResultSet.getString("nazwisko"),
					sqlResultSet.getString("telefon"),
					sqlResultSet.getString("email"),
					sqlResultSet.getString("github"),
					sqlResultSet.getInt("id_gr"),
					sqlResultSet.getInt("id_lg"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT id_lg, login, passwd, rola FROM logowanie WHERE id_lg=" + LoginController.selectedLoginInt);
			ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
			
			if(sqlResultSet.next())
				daneLogowanie = new Logowanie(
					sqlResultSet.getInt("id_lg"),
					sqlResultSet.getString("login"),
					sqlResultSet.getString("passwd"),
					sqlResultSet.getString("rola")
					);

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	setForm();
    	
    	TestController.traceCounter("OUT: DaneUserController selectDaneUser()");
    }
    
    private void setForm() {
    	
    	if(danePersonalneKursant != null) {
	    	tf_id_kr.setText(String.valueOf(danePersonalneKursant.getId_kr()));
	    	tf_imie.setText(danePersonalneKursant.getImie());
	    	tf_nazwisko.setText(danePersonalneKursant.getNazwisko());
	    	tf_telefon.setText(danePersonalneKursant.getTelefon());
	    	tf_email.setText(danePersonalneKursant.getEmail());
	    	tf_github.setText(danePersonalneKursant.getGithub());
	    	tf_id_gr.setText(String.valueOf(danePersonalneKursant.getId_gr()));
	    	tf_id_lg.setText(String.valueOf(danePersonalneKursant.getId_lg()));
    	}
    	
    	if(daneLogowanie != null) {
    		tf_rola.setText(daneLogowanie.getRola());
	    	tf_login.setText(daneLogowanie.getLogin());
    	}
    }
    
    private void clearForm() {
    	
    	tf_id_kr.clear();
    	tf_imie.clear();
    	tf_nazwisko.clear();
    	tf_telefon.clear();
    	tf_email.clear();
    	tf_github.clear();
    	tf_id_gr.clear();
    	tf_id_lg.clear();
    	tf_rola.clear();
    	tf_login.clear();
    }
    
    public void initialize() {
    	
    	TestController.traceCounter(" IN: DaneUserController initialize()");

    	clearForm();
    	dbConnection = DBConnector.staticDBConnection;
    	selectDaneUser();
    	
    	TestController.traceCounter("OUT: DaneUserController initialize()");
    }

}
