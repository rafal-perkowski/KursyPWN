package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.database.DBConnector;
import app.model.ProjektyView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProjektyUserController {

    @FXML
    private AnchorPane ap_projekty;

    @FXML
    private TableView<ProjektyView> tv_projekty;

    @FXML
    private TableColumn<ProjektyView, Integer> tvc_id_pr;

    @FXML
    private TableColumn<ProjektyView, String> tvc_temat;

    @FXML
    private TableColumn<ProjektyView, String> tvc_opis;

    @FXML
    private TableColumn<ProjektyView, String> tvc_deadline;

    @FXML
    private TableColumn<ProjektyView, String> tvc_ocena;

    @FXML
    private TableColumn<ProjektyView, String> tvc_uwagi;
    
    private ObservableList<ProjektyView> projektyViewObservableList = FXCollections.observableArrayList();
    //private ViewController viewController;
    private Connection dbConnection; 
    private PreparedStatement sqlPreparedStatement;

    @FXML
    void buttonOdswiezClickedMouseAction(MouseEvent event) {
    	selectProjekty();
    }
    
    private void selectProjekty() {
    	
    	TestController.traceCounter(" IN: ProjektyUserController selectProjekty()");
    	
    	projektyViewObservableList.clear();
    	tv_projekty.setItems(projektyViewObservableList);
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT id_pr, temat, opis, deadline, ocena, uwagi FROM projektyuser_view WHERE id_kr=" + LoginController.selectedLoginInt);
			ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();

			while(sqlResultSet.next()) {
				//int tmpInteger = sqlResultSet.getInt("ocena");
				String tmpString = String.valueOf(sqlResultSet.getInt("ocena"));
				if(sqlResultSet.wasNull()) {
					//tmpInteger = -1;
					tmpString = null;
				}
				projektyViewObservableList.add(new ProjektyView(
						sqlResultSet.getInt("id_pr"),
						sqlResultSet.getString("temat"),
						sqlResultSet.getString("opis"),
						sqlResultSet.getString("deadline"),
						tmpString,
						sqlResultSet.getString("uwagi")
						)
				);
			}
			
			tvc_id_pr.setCellValueFactory(new PropertyValueFactory<ProjektyView, Integer>("id_pr"));
			tvc_temat.setCellValueFactory(new PropertyValueFactory<ProjektyView, String>("temat"));
			tvc_opis.setCellValueFactory(new PropertyValueFactory<ProjektyView, String>("opis"));
			tvc_deadline.setCellValueFactory(new PropertyValueFactory<ProjektyView, String>("deadline"));
			tvc_ocena.setCellValueFactory(new PropertyValueFactory<ProjektyView, String>("ocena"));
			tvc_uwagi.setCellValueFactory(new PropertyValueFactory<ProjektyView, String>("uwagi"));

		} catch (SQLException e) {	
			e.printStackTrace();
		}
    	
    	TestController.traceCounter("OUT: ProjektyUserController selectProjekty()");
    }
    
    public void initialize() {
    	
    	TestController.traceCounter(" IN: ProjektyUserController initialize()");
    	
    	dbConnection = DBConnector.staticDBConnection;
    	selectProjekty();
    	
    	TestController.traceCounter("OUT: ProjektyUserController initialize()");
    }

}
