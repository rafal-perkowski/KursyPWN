package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import app.database.DBConnector;
import app.model.Grupa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class GrupyController {

	@FXML
    private AnchorPane ap_grupy;

    @FXML
    private TableView<Grupa> tv_grupy;

    @FXML
    private TableColumn<Grupa, Integer> tvc_id_gr;

    @FXML
    private TableColumn<Grupa, String> tvc_nazwa;

    @FXML
    private TableColumn<Grupa, String> tvc_opis;
    
    @FXML
    private TextField tf_nazwa;

    @FXML
    private TextArea ta_opis;
    
    private ObservableList<Grupa> grupyObservableList = FXCollections.observableArrayList();
    private ViewController viewController;
    private Connection dbConnection; 
    private PreparedStatement sqlPreparedStatement;
    private Grupa selectedGrupa;
    public static int selectedGrupaInt;
    
    @FXML
    void buttonDodajClickedMouseAction(MouseEvent event) {
    	insertGrupy();
    }

    @FXML
    void buttonOdswiezClickedMouseAction(MouseEvent event) {
    	selectGrupy();
    }

    @FXML
    void buttonUsunClickedMouseAction(MouseEvent event) {
    	deleteGrupy();
    }

    @FXML
    void tableClickedMouseAction(MouseEvent event) {  	
    	selectedGrupa = tv_grupy.getSelectionModel().getSelectedItem();	
    	if(selectedGrupa != null) {
    		setForm();
    		selectedGrupaInt = selectedGrupa.getId_gr();
    	}
    }

    @FXML
    void tablePressedKeyAction(KeyEvent event) {
    	selectedGrupa = tv_grupy.getSelectionModel().getSelectedItem();
    	if(event.getCode() == KeyCode.DELETE && selectedGrupa != null) {
    		deleteGrupy();
    		selectedGrupaInt = selectedGrupa.getId_gr();
    	}
    }

    @FXML
    void tableReleasedKeyAction(KeyEvent event) {
    	selectedGrupa = tv_grupy.getSelectionModel().getSelectedItem();	
    	if(selectedGrupa != null) {
    		setForm();
    		selectedGrupaInt = selectedGrupa.getId_gr();
    	}
    }
    
    private void selectGrupy() {
    	
    	TestController.traceCounter(" IN: GrupyController selectGrupy()");
    	
    	clearForm();
    	grupyObservableList.clear();
    	tv_grupy.setItems(grupyObservableList);
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT id_gr, nazwa, opis FROM grupy");
			ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
			
			while(sqlResultSet.next()) {
				grupyObservableList.add(new Grupa(
						sqlResultSet.getInt("id_gr"),
						sqlResultSet.getString("nazwa"),
						sqlResultSet.getString("opis")
						)
				);	
			}
			
			tvc_id_gr.setCellValueFactory(new PropertyValueFactory<Grupa, Integer>("id_gr"));
			tvc_nazwa.setCellValueFactory(new PropertyValueFactory<Grupa, String>("nazwa"));
			tvc_opis.setCellValueFactory(new PropertyValueFactory<Grupa, String>("opis"));
				
		} catch (SQLException e) {	
			e.printStackTrace();
		}
    	
    	TestController.traceCounter("OUT: GrupyController selectGrupy()");
    }
    
    private void insertGrupy() {
    	
    	TestController.traceCounter(" IN: GrupyController insertGrupy()");
    	
    	if(checkForm()) {
			try {
				
				sqlPreparedStatement = dbConnection.prepareStatement("INSERT INTO grupy (nazwa, opis) VALUES (?, ?)");
				sqlPreparedStatement.setString(1,tf_nazwa.getText());
				sqlPreparedStatement.setString(2,ta_opis.getText());
				sqlPreparedStatement.executeUpdate();
				clearForm();
			} catch(MySQLIntegrityConstraintViolationException e) {
				//e.printStackTrace();
				viewController.showAlert(AlertType.ERROR, "ERROR", "Dodawanie grupy!", "Naruszenie regu³ integralnoœci bazy danych!", "/app/pwnicon.png");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			selectGrupy();
    	} else {
    		viewController.showAlert(AlertType.INFORMATION, "INFORMATION", "Dodawanie grupy!", "Aby dodaæ grupê wype³nij pola w formularzu!", "/app/pwnicon.png");
    	}
    	
    	TestController.traceCounter("OUT: GrupyController insertGrupy()");

    }

    private void deleteGrupy() {
    	
    	boolean flagBoolean = false;
    	
    	TestController.traceCounter(" IN: GrupyController deleteGrupy()");
    	
    	flagBoolean = viewController.showAlert(AlertType.CONFIRMATION, "CONFIRMATION", "Usuwanie grupy!", "Czy na pewno chcesz usun¹æ grupê?", "/app/pwnicon.png");
    	
    	if(flagBoolean) {
    		
	    	try {
	    		
	    		selectedGrupa = tv_grupy.getSelectionModel().getSelectedItem();
	    		selectedGrupaInt = selectedGrupa.getId_gr();
	    	}
	    	catch(Exception e) {
	    		
	    		viewController.showAlert(AlertType.INFORMATION, "INFORMATION", "Usuwanie grupy!", "Aby usun¹æ grupê musisz zaznaczyæ odpowiedni wiersz w tabeli!", "/app/pwnicon.png");
	    	}
	    	
	    	try {
	    		sqlPreparedStatement = dbConnection.prepareStatement("DELETE FROM grupy WHERE id_gr= ?");
	    		sqlPreparedStatement.setInt(1, selectedGrupaInt);
	    		sqlPreparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	    	
		    selectGrupy();
		    
		    TestController.traceCounter("OUT: GrupyController deleteGrupy()");
    	}
    }
    
    private void setForm() {
    	if(selectedGrupa != null) {
	    	tf_nazwa.setText(selectedGrupa.getNazwa());
	    	ta_opis.setText(selectedGrupa.getOpis());
    	}
    }
    
    private void clearForm() {
    	
    	tf_nazwa.clear();
    	ta_opis.clear();
    }
    
    private boolean checkForm() {
    	
    	boolean flagRetBoolean = false;
    	
    	if(!(
    			tf_nazwa.getText().equals("") || 
    			ta_opis.getText().equals("")
    	))
    		flagRetBoolean = true;

    	return flagRetBoolean;
    }
    
    public void initialize() {
    	
    	TestController.traceCounter(" IN: GrupyController initialize()");
    	
    	viewController = new ViewController();
    	dbConnection = DBConnector.staticDBConnection;
    	selectGrupy();
    	
    	TestController.traceCounter("OUT: GrupyController initialize()");
    }

}
