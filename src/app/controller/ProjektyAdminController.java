package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import app.database.DBConnector;
import app.model.Projekt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProjektyAdminController {
	
	private class IDClass {
		
		private int idAInt;
		private int idBInt;

		public IDClass(int idAInt, int idBInt) {
			super();
			this.idAInt = idAInt;
			this.idBInt = idBInt;
		}

		public int getIdAInt() {
			return idAInt;
		}

		public int getIdBInt() {
			return idBInt;
		}
	}

	@FXML
    private AnchorPane ap_projekty;

    @FXML
    private TableView<Projekt> tv_projekty;

    @FXML
    private TableColumn<Projekt, Integer> tvc_id_pr;

    @FXML
    private TableColumn<Projekt, String> tvc_temat;

    @FXML
    private TableColumn<Projekt, String> tvc_opis;

    @FXML
    private TableColumn<Projekt, String> tvc_deadline;

    @FXML
    private TableColumn<Projekt, Integer> tvc_id_gr;

    @FXML
    private TextField tf_temat;

    @FXML
    private TextArea ta_opis;

    @FXML
    private DatePicker dp_deadline;

    @FXML
    private TextField tf_id_gr;
    
    private ObservableList<Projekt> projektyObservableList = FXCollections.observableArrayList();
    private ArrayList<IDClass> idArrayList;
    private ViewController viewController;
    private Connection dbConnection; 
    private PreparedStatement sqlPreparedStatement;
    private Projekt selectedProjekt;
    public static int selectedProjektInt;

    @FXML
    void buttonDodajClickedMouseAction(MouseEvent event) {
    	insertProjekty();
    }

    @FXML
    void buttonOdswiezClickedMouseAction(MouseEvent event) {
    	selectProjekty();
    }

    @FXML
    void buttonUsunClickedMouseAction(MouseEvent event) {
    	deleteProjekty();
    }

    @FXML
    void tableClickedMouseAction(MouseEvent event) {  	
    	selectedProjekt = tv_projekty.getSelectionModel().getSelectedItem();	
    	if(selectedProjekt != null) {
    		setForm();
    	}
    }

    @FXML
    void tablePressedKeyAction(KeyEvent event) {
    	selectedProjekt = tv_projekty.getSelectionModel().getSelectedItem();
    	if(event.getCode() == KeyCode.DELETE && selectedProjekt != null) {
    		deleteProjekty();
    	}
    }

    @FXML
    void tableReleasedKeyAction(KeyEvent event) {
    	selectedProjekt = tv_projekty.getSelectionModel().getSelectedItem();	
    	if(selectedProjekt != null) {
    		setForm();
    	}
    }
    
    private void selectProjekty() {
    	
    	TestController.traceCounter(" IN: ProjektyAdminController selectProjekty()");
    	
    	clearForm();
    	projektyObservableList.clear();
    	tv_projekty.setItems(projektyObservableList);
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT id_pr, temat, opis, deadline, id_gr FROM projekty");
			ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
			
			while(sqlResultSet.next()) {
				projektyObservableList.add(new Projekt(
						sqlResultSet.getInt("id_pr"),
						sqlResultSet.getString("temat"),
						sqlResultSet.getString("opis"),
						sqlResultSet.getString("deadline"),
						sqlResultSet.getInt("id_gr")
						)
				);	
			}
			
			tvc_id_pr.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("id_pr"));
			tvc_temat.setCellValueFactory(new PropertyValueFactory<Projekt, String>("temat"));
			tvc_opis.setCellValueFactory(new PropertyValueFactory<Projekt, String>("opis"));
			tvc_deadline.setCellValueFactory(new PropertyValueFactory<Projekt, String>("deadline"));
			tvc_id_gr.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("id_gr"));
				
		} catch (SQLException e) {	
			e.printStackTrace();
		}
    	
    	TestController.traceCounter("OUT: ProjektyAdminController selectProjekty()");
    }

    private void insertProjekty() {
    	
    	TestController.traceCounter(" IN: ProjektyAdminController insertProjekty()");
    	
    	if(checkForm()) {
			try {
				sqlPreparedStatement = dbConnection.prepareStatement("INSERT INTO projekty (temat, opis, deadline, id_gr) VALUES (?, ?, ?, ?)");
				sqlPreparedStatement.setString(1,tf_temat.getText());
				sqlPreparedStatement.setString(2,ta_opis.getText());
				sqlPreparedStatement.setString(3,dp_deadline.getEditor().getText());
				sqlPreparedStatement.setInt(4,Integer.valueOf(tf_id_gr.getText()));
				sqlPreparedStatement.executeUpdate();
				clearForm();
			} catch(MySQLIntegrityConstraintViolationException e) {
				//e.printStackTrace();
				viewController.showAlert(AlertType.ERROR, "ERROR", "Dodawanie projektu!", "Naruszenie regu³ integralnoœci bazy danych!", "/app/pwnicon.png");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			idArrayList = new ArrayList<IDClass>();
			try {
				sqlPreparedStatement = dbConnection.prepareStatement("SELECT * FROM projektyadmin_view");
				ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
				while(sqlResultSet.next()) {
					idArrayList.add(new IDClass(
							sqlResultSet.getInt("id_pr"),
							sqlResultSet.getInt("id_kr")
							)
					);
				}
				
				for(int i=0; i<idArrayList.size(); i++) {
					sqlPreparedStatement = dbConnection.prepareStatement("INSERT INTO oceny (id_pr, id_kr) VALUES (?, ?)");
					sqlPreparedStatement.setInt(1,idArrayList.get(i).getIdAInt());
					sqlPreparedStatement.setInt(2,idArrayList.get(i).getIdBInt());
					sqlPreparedStatement.executeUpdate();
				}
				
				idArrayList.clear();

			} catch(MySQLIntegrityConstraintViolationException e) {
				//e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			selectProjekty();
    	} else {
    		
    		viewController.showAlert(AlertType.INFORMATION, "INFORMATION", "Dodawanie projektu!", "Aby dodaæ projekt wype³nij pola w formularzu!", "/app/pwnicon.png");
    	}
    	
    	TestController.traceCounter("OUT: ProjektyAdminController insertProjekty()");
    }

    private void deleteProjekty() {
    	
    	TestController.traceCounter(" IN: ProjektyAdminController deleteProjekty()");
    	
    	boolean flagBoolean = false;
    	
    	flagBoolean = viewController.showAlert(AlertType.CONFIRMATION, "CONFIRMATION", "Usuwanie projektu!", "Czy na pewno chcesz usun¹æ projekt?", "/app/pwnicon.png");
    	
    	if(flagBoolean) {
    		
	    	try {		
	    		selectedProjekt = tv_projekty.getSelectionModel().getSelectedItem();
	    		selectedProjektInt = selectedProjekt.getId_pr();
	    	}
	    	catch(Exception e) {
	    		
	    		viewController.showAlert(AlertType.INFORMATION, "INFORMATION", "Usuwanie projektu!", "Aby usun¹æ projekt musisz zaznaczyæ odpowiedni wiersz w tabeli!", "/app/pwnicon.png");
	    	}
	    	
	    	try {
	    		sqlPreparedStatement = dbConnection.prepareStatement("DELETE FROM projekty WHERE id_pr= ?");
	    		sqlPreparedStatement.setInt(1, selectedProjektInt);
	    		sqlPreparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	    	
		    selectProjekty();
    	}
    	
    	TestController.traceCounter("OUT: ProjektyAdminController deleteProjekty()");
    }
    
    private void setForm() {
    	
    	if(selectedProjekt != null) {
	    	tf_temat.setText(selectedProjekt.getTemat());
	        ta_opis.setText(selectedProjekt.getOpis());
	        dp_deadline.getEditor().setText(selectedProjekt.getDeadline());
	        tf_id_gr.setText(String.valueOf(selectedProjekt.getId_gr()));
    	}
    }
    
    private void clearForm() {

    	tf_temat.clear();
        ta_opis.clear();
        dp_deadline.getEditor().clear();
        tf_id_gr.clear();
    }
    
    private boolean checkForm() {
    	
    	boolean flagRetBoolean = false;
    	
    	if(!(
    			tf_temat.getText().equals("") || 
    			ta_opis.getText().equals("") ||
    			dp_deadline.getEditor().getText().equals("") ||
    			tf_id_gr.getText().equals("")
    	))
    		flagRetBoolean = true;

    	return flagRetBoolean;
    }
    
    public void initialize() {
    	
    	TestController.traceCounter(" IN: ProjektyAdminController initialize()");
    	
    	viewController = new ViewController();
    	dbConnection = DBConnector.staticDBConnection;
    	selectProjekty();
    	tf_id_gr.setText(String.valueOf(GrupyController.selectedGrupaInt));
    	
    	TestController.traceCounter("OUT: ProjektyAdminController initialize()");
    }

}
