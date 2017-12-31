package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.database.DBConnector;
import app.model.Logowanie;
import app.model.Trener;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DaneAdminController {

    @FXML
    private AnchorPane ap_daneadmin;

    @FXML
    private TextField tf_id_tr;

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
    private TextField tf_id_lg;

    @FXML
    private TextField tf_rola;

    @FXML
    private TextField tf_login;
    
    private Connection dbConnection; 
    private PreparedStatement sqlPreparedStatement;
    private Trener danePersonalneTrener;
    private Logowanie daneLogowanie;
    
    
    private void selectDaneAdmin() {
    	
    	TestController.traceCounter(" IN: DaneAdminController selectDaneAdmin()");
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT id_tr, imie, nazwisko, telefon, email, github, id_lg FROM trenerzy WHERE id_lg=" + LoginController.selectedLoginInt);
    		ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
			if(sqlResultSet.next()) {
				danePersonalneTrener = new Trener(
					sqlResultSet.getInt("id_tr"),
					sqlResultSet.getString("imie"),
					sqlResultSet.getString("nazwisko"),
					sqlResultSet.getString("telefon"),
					sqlResultSet.getString("email"),
					sqlResultSet.getString("github"),
					sqlResultSet.getInt("id_lg"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT id_lg, login, passwd, rola FROM logowanie WHERE id_lg=" + LoginController.selectedLoginInt);
			ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
			
			if(sqlResultSet.next()) {
				daneLogowanie = new Logowanie(
					sqlResultSet.getInt("id_lg"),
					sqlResultSet.getString("login"),
					sqlResultSet.getString("passwd"),
					sqlResultSet.getString("rola")
					);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	setForm();
    	
    	TestController.traceCounter("OUT: DaneAdminController selectDaneAdmin()");
    }
    
    private void setForm() {
    	
    	if(danePersonalneTrener != null) {
	    	tf_id_tr.setText(String.valueOf(danePersonalneTrener.getId_tr()));
	    	tf_imie.setText(danePersonalneTrener.getImie());
	    	tf_nazwisko.setText(danePersonalneTrener.getNazwisko());
	    	tf_telefon.setText(danePersonalneTrener.getTelefon());
	    	tf_email.setText(danePersonalneTrener.getEmail());
	    	tf_github.setText(danePersonalneTrener.getGithub());
	    	tf_id_lg.setText(String.valueOf(danePersonalneTrener.getId_lg()));
    	}
    	
    	if(daneLogowanie != null) {
    		tf_rola.setText(daneLogowanie.getRola());
	    	tf_login.setText(daneLogowanie.getLogin());
    	}
    }
    
    private void clearForm() {
    	
    	tf_id_tr.clear();
    	tf_imie.clear();
    	tf_nazwisko.clear();
    	tf_telefon.clear();
    	tf_email.clear();
    	tf_github.clear();
    	tf_id_lg.clear();
    	tf_rola.clear();
    	tf_login.clear();
    }
    
    public void initialize() {
    	
    	TestController.traceCounter(" IN: DaneAdminController initialize()");

    	clearForm();
    	dbConnection = DBConnector.staticDBConnection;
    	selectDaneAdmin();
    	
    	TestController.traceCounter("OUT: DaneAdminController initialize()");
    }

}
