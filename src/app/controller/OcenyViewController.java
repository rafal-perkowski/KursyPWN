package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import app.database.DBConnector;
import app.model.OcenyView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class OcenyViewController {

    @FXML
    private AnchorPane ap_oceny;

    @FXML
    private TableView<OcenyView> tv_ocenyview;

    @FXML
    private TableColumn<OcenyView, Integer> tvc_id_gr;

    @FXML
    private TableColumn<OcenyView, String> tvc_nazwa;

    @FXML
    private TableColumn<OcenyView, Integer> tvc_id_kr;

    @FXML
    private TableColumn<OcenyView, String> tvc_imie;

    @FXML
    private TableColumn<OcenyView, String> tvc_nazwisko;

    @FXML
    private TableColumn<OcenyView, Integer> tvc_id_pr;

    @FXML
    private TableColumn<OcenyView, String> tvc_temat;

    @FXML
    private TableColumn<OcenyView, String> tvc_deadline;

    @FXML
    private TableColumn<OcenyView, Integer> tvc_id_oc;

    @FXML
    private TableColumn<OcenyView, String> tvc_ocena;

    @FXML
    private TableColumn<OcenyView, String> tvc_uwagi;
    
    @FXML
    private TextArea ta_uwagi;

    @FXML
    private ComboBox<Integer> cb_ocena;
    private ObservableList<Integer> ocenyObservableList = FXCollections.observableArrayList(0,2,4,6,8,10);
    private ObservableList<OcenyView> ocenyviewObservableList = FXCollections.observableArrayList();
    private ViewController viewController;
    private Connection dbConnection; 
    private PreparedStatement sqlPreparedStatement;
    private OcenyView selectedOcenyView;
    public static int selectedOcenyViewInt;
    
    @FXML
    void buttonOcenClickedMouseAction(MouseEvent event) {
    	updateOcenyView();
    }

    @FXML
    void buttonOdswiezClickedMouseAction(MouseEvent event) {
    	selectOcenyView();
    }

    @FXML
    void comboboxOnAction(ActionEvent event) {

    }
    
    @FXML
    void tableClickedMouseAction(MouseEvent event) {  	
    	selectedOcenyView = tv_ocenyview.getSelectionModel().getSelectedItem();	
    	if(selectedOcenyView != null) {
    		selectedOcenyViewInt = selectedOcenyView.getId_oc();
    	}
    }

    @FXML
    void tableReleasedKeyAction(KeyEvent event) {
    	selectedOcenyView = tv_ocenyview.getSelectionModel().getSelectedItem();	
    	if(selectedOcenyView != null) {
    		selectedOcenyViewInt = selectedOcenyView.getId_oc();
    	}
    }
    
    private void selectOcenyView() {
    	
    	TestController.traceCounter(" IN: OcenyViewController selectOceny()");
    	
    	ocenyviewObservableList.clear();
    	tv_ocenyview.setItems(ocenyviewObservableList);
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT * FROM oceny_view");
			ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();

			while(sqlResultSet.next()) {
				//int tmpInteger = sqlResultSet.getInt("ocena");
				String tmpString = String.valueOf(sqlResultSet.getInt("ocena"));
				if(sqlResultSet.wasNull()) {
					//tmpInteger = -1;
					tmpString = null;
				}
				
				ocenyviewObservableList.add(new OcenyView(
						sqlResultSet.getInt("id_gr"),
						sqlResultSet.getString("nazwa"),
						sqlResultSet.getInt("id_kr"),
						sqlResultSet.getString("imie"),
						sqlResultSet.getString("nazwisko"),
						sqlResultSet.getInt("id_pr"),
						sqlResultSet.getString("temat"),
						sqlResultSet.getString("deadline"),
						sqlResultSet.getInt("id_oc"),
						tmpString,
						sqlResultSet.getString("uwagi")
						)
				);

			}

			tvc_id_gr.setCellValueFactory(new PropertyValueFactory<OcenyView, Integer>("id_gr"));
			tvc_nazwa.setCellValueFactory(new PropertyValueFactory<OcenyView, String>("nazwa"));
			tvc_id_kr.setCellValueFactory(new PropertyValueFactory<OcenyView, Integer>("id_kr"));
			tvc_imie.setCellValueFactory(new PropertyValueFactory<OcenyView, String>("imie"));
			tvc_nazwisko.setCellValueFactory(new PropertyValueFactory<OcenyView, String>("nazwisko"));
			tvc_id_pr.setCellValueFactory(new PropertyValueFactory<OcenyView, Integer>("id_pr"));
			tvc_temat.setCellValueFactory(new PropertyValueFactory<OcenyView, String>("temat"));
			tvc_deadline.setCellValueFactory(new PropertyValueFactory<OcenyView, String>("deadline"));
			tvc_id_oc.setCellValueFactory(new PropertyValueFactory<OcenyView, Integer>("id_oc"));
			tvc_ocena.setCellValueFactory(new PropertyValueFactory<OcenyView, String>("ocena"));
			tvc_uwagi.setCellValueFactory(new PropertyValueFactory<OcenyView, String>("uwagi"));
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	TestController.traceCounter("OUT: OcenyViewController selectOceny()");
    }
    
    private void updateOcenyView() {
    	
    	TestController.traceCounter(" IN: OcenyViewController insertOcenyView()");
    	
    	if(checkForm()) {
    		
    		try {
	    		
	    		selectedOcenyView = tv_ocenyview.getSelectionModel().getSelectedItem();
	    		selectedOcenyViewInt = selectedOcenyView.getId_oc();
	    	}
	    	catch(Exception e) {
	    		
	    		viewController.showAlert(AlertType.INFORMATION, "INFORMATION", "Dodawanie oceny!", "Aby dodaæ ocenê musisz zaznaczyæ odpowiedni wiersz w tabeli!", "/app/pwnicon.png");
	    	}
    		
			try {
				
				sqlPreparedStatement = dbConnection.prepareStatement("UPDATE oceny SET ocena= ?, uwagi= ? WHERE id_oc= ?");
				sqlPreparedStatement.setInt(1,cb_ocena.getValue());
				sqlPreparedStatement.setString(2,ta_uwagi.getText());
				sqlPreparedStatement.setInt(3,selectedOcenyViewInt);
				sqlPreparedStatement.executeUpdate();
				clearForm();
			} catch(MySQLIntegrityConstraintViolationException e) {
				//e.printStackTrace();
				viewController.showAlert(AlertType.ERROR, "ERROR", "Dodawanie oceny!", "Naruszenie regu³ integralnoœci bazy danych!", "/app/pwnicon.png");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			selectOcenyView();
    	} else {
    		
    		viewController.showAlert(AlertType.INFORMATION, "INFORMATION", "Dodawanie oceny!", "Aby dodaæ ocenê wybierz odpowiedni¹ wartoœæ z listy!", "/app/pwnicon.png");
    	}
    	
    	TestController.traceCounter("OUT: OcenyViewController insertOcenyView()");
    }
    
    private void clearForm() {
    	
    	ta_uwagi.clear();
    	cb_ocena.setValue(null);
    }
    
    private boolean checkForm() {
    	
    	boolean flagRetBoolean = false;
    	
    	if(!(
    			cb_ocena.getValue() == null
    	))
    		flagRetBoolean = true;

    	return flagRetBoolean;
    }
    
    public void initialize() {
    	
    	TestController.traceCounter(" IN: OcenyViewController initialize()");
    	
    	viewController = new ViewController();
    	dbConnection = DBConnector.staticDBConnection;
    	selectOcenyView();
    	
    	cb_ocena.setItems(ocenyObservableList);
    	
    	TestController.traceCounter("OUT: OcenyViewController initialize()");
    }

}
