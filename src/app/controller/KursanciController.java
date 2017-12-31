package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import app.database.DBConnector;
import app.model.Kursant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class KursanciController {

	@FXML
    private AnchorPane ap_kursanci;

    @FXML
    private TableView<Kursant> tv_kursanci;

    @FXML
    private TableColumn<Kursant, Integer> tvc_id_kr;

    @FXML
    private TableColumn<Kursant, String> tvc_imie;

    @FXML
    private TableColumn<Kursant, String> tvc_nazwisko;

    @FXML
    private TableColumn<Kursant, String> tvc_telefon;

    @FXML
    private TableColumn<Kursant, String> tvc_email;
    
    @FXML
    private TableColumn<Kursant, String> tvc_github;

    @FXML
    private TableColumn<Kursant, Integer> tvc_id_gr;

    @FXML
    private TableColumn<Kursant, Integer> tvc_id_lg;

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
    private ComboBox<Integer> cb_id_lg;
    
    private ObservableList<Integer> newlogidObservableList = FXCollections.observableArrayList(); 
    private ObservableList<Kursant> kursanciObservableList = FXCollections.observableArrayList();
    private ViewController viewController;
    private Connection dbConnection; 
    private PreparedStatement sqlPreparedStatement;
    private Kursant selectedKursant;
    public static int selectedKursantInt;

    @FXML
    void buttonDodajClickedMouseAction(MouseEvent event) {
    	insertKursanci();
    }

    @FXML
    void buttonOdswiezClickedMouseAction(MouseEvent event) {
    	selectKursanci();
    }

    @FXML
    void buttonUsunClickedMouseAction(MouseEvent event) {
    	deleteKursanci();
    }

    @FXML
    void tableClickedMouseAction(MouseEvent event) {  	
    	selectedKursant = tv_kursanci.getSelectionModel().getSelectedItem();	
    	if(selectedKursant != null) {
    		setForm();
    	}
    }

    @FXML
    void tablePressedKeyAction(KeyEvent event) {
    	selectedKursant = tv_kursanci.getSelectionModel().getSelectedItem();
    	if(event.getCode() == KeyCode.DELETE && selectedKursant != null) {
    		deleteKursanci();
    	}
    }

    @FXML
    void tableReleasedKeyAction(KeyEvent event) {
    	selectedKursant = tv_kursanci.getSelectionModel().getSelectedItem();	
    	if(selectedKursant != null) {
    		setForm();
    	}
    }
    
    @FXML
    void comboboxOnAction(ActionEvent event) {
    	if(cb_id_lg.getValue() != null)
    		tf_id_lg.setText(String.valueOf(cb_id_lg.getValue()));
    }

    
    private void selectKursanci() {
    	
    	TestController.traceCounter(" IN: KursanciController selectKursanci()");
    	
    	clearForm();
    	
    	kursanciObservableList.clear();
    	tv_kursanci.setItems(kursanciObservableList);
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT id_kr, imie, nazwisko, telefon, email, github, id_gr, id_lg FROM kursanci");
			ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
			
			while(sqlResultSet.next()) {
				kursanciObservableList.add(new Kursant(
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
			
			tvc_id_kr.setCellValueFactory(new PropertyValueFactory<Kursant, Integer>("id_kr"));
			tvc_imie.setCellValueFactory(new PropertyValueFactory<Kursant, String>("imie"));
			tvc_nazwisko.setCellValueFactory(new PropertyValueFactory<Kursant, String>("nazwisko"));
			tvc_telefon.setCellValueFactory(new PropertyValueFactory<Kursant, String>("telefon"));
			tvc_email.setCellValueFactory(new PropertyValueFactory<Kursant, String>("email"));
			tvc_github.setCellValueFactory(new PropertyValueFactory<Kursant, String>("github"));
			tvc_id_gr.setCellValueFactory(new PropertyValueFactory<Kursant, Integer>("id_gr"));
			tvc_id_lg.setCellValueFactory(new PropertyValueFactory<Kursant, Integer>("id_lg"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	newlogidObservableList.clear();
    	cb_id_lg.setItems(newlogidObservableList);
	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT * FROM freelogins_view");
			ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
			
			while(sqlResultSet.next()) {
				newlogidObservableList.add(new Integer(
						sqlResultSet.getInt("id_lg")
						)
				);	
			}

			//cb_id_lg.setItems(newlogidObservableList);				
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	TestController.traceCounter("OUT: KursanciController selectKursanci()");
    }

    private void insertKursanci() {
    	
    	TestController.traceCounter(" IN: KursanciController insertKursanci()");
    	
    	if(checkForm()) {
			try {
				sqlPreparedStatement = dbConnection.prepareStatement("INSERT INTO kursanci (imie, nazwisko, telefon, email, github, id_gr, id_lg) VALUES (?, ?, ?, ?, ?, ?, ?)");
				sqlPreparedStatement.setString(1,tf_imie.getText());
				sqlPreparedStatement.setString(2,tf_nazwisko.getText());
				sqlPreparedStatement.setString(3,tf_telefon.getText());
				sqlPreparedStatement.setString(4,tf_email.getText());
				sqlPreparedStatement.setString(5,tf_github.getText());	
				try {
					sqlPreparedStatement.setInt(6,Integer.valueOf((tf_id_gr.getText())));
				} catch(NumberFormatException e) {
					sqlPreparedStatement.setNull(6,Types.INTEGER);
				}
				try {
					sqlPreparedStatement.setInt(7,Integer.valueOf((tf_id_lg.getText())));
				} catch(NumberFormatException e) {
					sqlPreparedStatement.setNull(7,Types.INTEGER);
				}
					
				sqlPreparedStatement.executeUpdate();
				clearForm();
			} catch(MySQLIntegrityConstraintViolationException e) {
				//e.printStackTrace();
				viewController.showAlert(AlertType.ERROR, "ERROR", "Dodawanie kursanta!", "Naruszenie regu³ integralnoœci bazy danych!", "/app/pwnicon.png");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			selectKursanci();
    	} else {
    		
    		viewController.showAlert(AlertType.INFORMATION, "INFORMATION", "Dodawanie kursanta!", "Aby dodaæ kursanta wype³nij pola w formularzu!", "/app/pwnicon.png");
    	}
    	
    	TestController.traceCounter("OUT: KursanciController insertKursanci()");

    }

    private void deleteKursanci() {
    	
    	TestController.traceCounter(" IN: KursanciController deleteKursanci()");
    	
    	boolean flagBoolean = false;

    	flagBoolean = viewController.showAlert(AlertType.CONFIRMATION, "CONFIRMATION", "Usuwanie kursanta!", "Czy na pewno chcesz usun¹æ kursanta?", "/app/pwnicon.png");
    	
    	if(flagBoolean) {
    		
	    	try {
	    		
	    		selectedKursant = tv_kursanci.getSelectionModel().getSelectedItem();
	    		selectedKursantInt = selectedKursant.getId_kr();
	    	}
	    	catch(Exception e) {
	    		
	    		viewController.showAlert(AlertType.INFORMATION, "INFORMATION", "Usuwanie kursanta!", "Aby usun¹æ kursanta musisz zaznaczyæ odpowiedni wiersz w tabeli!", "/app/pwnicon.png");
	    	}
	    	
	    	try {
	    		sqlPreparedStatement = dbConnection.prepareStatement("DELETE FROM kursanci WHERE id_kr= ?");
	    		sqlPreparedStatement.setInt(1, selectedKursantInt);
	    		sqlPreparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	    	
		    selectKursanci();
    	}
    	
    	TestController.traceCounter("OUT: KursanciController deleteKursanci()");
    }
    
    private void setForm() {
    	
    	if(selectedKursant != null) {
	    	tf_imie.setText(selectedKursant.getImie());
	        tf_nazwisko.setText(selectedKursant.getNazwisko());
	        tf_telefon.setText(selectedKursant.getTelefon());
	        tf_email.setText(selectedKursant.getEmail());
	        tf_github.setText(selectedKursant.getGithub());
	        tf_id_gr.setText(String.valueOf(selectedKursant.getId_gr()));
	        tf_id_lg.setText(String.valueOf(selectedKursant.getId_lg()));
    	}
    }
    
    private void clearForm() {
    	
    	tf_imie.clear();
        tf_nazwisko.clear();
        tf_telefon.clear();
        tf_email.clear();
        tf_github.clear();
        tf_id_gr.clear();
        tf_id_lg.clear();
    }
    
    private boolean checkForm() {
    	
    	boolean flagRetBoolean = false;
    	
    	if(!(
    			tf_imie.getText().equals("") || 
    			tf_nazwisko.getText().equals("") ||
    			tf_nazwisko.getText().equals("") ||
    			tf_nazwisko.getText().equals("") ||
    			tf_nazwisko.getText().equals("") ||
    			tf_nazwisko.getText().equals("") ||
    			tf_nazwisko.getText().equals("") 
    	))
    		flagRetBoolean = true;

    	return flagRetBoolean;
    }
    
    public void initialize() {
    	
    	TestController.traceCounter(" IN: KursanciController initialize()");
    	
    	viewController = new ViewController();
    	dbConnection = DBConnector.staticDBConnection;
    	selectKursanci();
    	tf_id_gr.setText(String.valueOf(GrupyController.selectedGrupaInt));
    	
    	TestController.traceCounter("OUT: KursanciController initialize()");
    }
    
}