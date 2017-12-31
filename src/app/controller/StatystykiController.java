package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.database.DBConnector;
import app.model.StatystykiGrupView;
import app.model.StatystykiKursantowView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class StatystykiController {
	
	private class Statystyki {
		
		private int status;
		private int liczba;
		
		public Statystyki(int status, int liczba) {
			super();
			this.status = status;
			this.liczba = liczba;
		}

		public int getStatus() {
			return status;
		}

		public int getLiczba() {
			return liczba;
		}

		@Override
		public String toString() {
			return "Statystyki [status=" + status + ", liczba=" + liczba + "]";
		}

	}

    @FXML
    private AnchorPane ap_statystyki;

    @FXML
    private TextField tf_oddane;

    @FXML
    private TextField tf_nieoddane;

    @FXML
    private TableView<StatystykiGrupView> tv_dlagrup;

    @FXML
    private TableColumn<StatystykiGrupView, Integer> tvcg_id_gr;

    @FXML
    private TableColumn<StatystykiGrupView, String> tvcg_nazwa;

    @FXML
    private TableColumn<StatystykiGrupView, Integer> tvcg_status;

    @FXML
    private TableColumn<StatystykiGrupView, Integer> tvcg_liczba;

    @FXML
    private TableView<StatystykiKursantowView> tv_dlakursantow;

    @FXML
    private TableColumn<StatystykiKursantowView, Integer> tvck_id_gr;

    @FXML
    private TableColumn<StatystykiKursantowView, String> tvck_nazwa;

    @FXML
    private TableColumn<StatystykiKursantowView, Integer> tvck_id_kr;

    @FXML
    private TableColumn<StatystykiKursantowView, String> tvck_imie;

    @FXML
    private TableColumn<StatystykiKursantowView, String> tvck_nazwisko;

    @FXML
    private TableColumn<StatystykiKursantowView, Integer> tvck_status;

    @FXML
    private TableColumn<StatystykiKursantowView, Integer> tvck_liczba;

    @FXML
    void buttonOdswiezClickedMouseAction(MouseEvent event) {
    	
    	selectStatystyki();
    	selectStatystykiGrup();
    	selectStatystykiKursantow();
    }
    
    private ObservableList<StatystykiGrupView> statystykiGrupObservableList = FXCollections.observableArrayList();
    private ObservableList<StatystykiKursantowView> statystykiKursantowObservableList = FXCollections.observableArrayList();
    private ArrayList<Statystyki> statystykiArrayList; 
    private Connection dbConnection; 
    private PreparedStatement sqlPreparedStatement;
    
    private void selectStatystyki() {
    	
    	TestController.traceCounter(" IN: StatystykiController selectStatystyki()");
    	
    	statystykiArrayList = new ArrayList<Statystyki>();

    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT * FROM statystyki_view_all");
			ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
			
			while(sqlResultSet.next()) {
				statystykiArrayList.add(new Statystyki(
						sqlResultSet.getInt("status"),
						sqlResultSet.getInt("liczba")
						)
				);	
			}
		
			if(statystykiArrayList.get(0).getStatus() == 0)
				tf_nieoddane.setText(String.valueOf(statystykiArrayList.get(0).getLiczba()));
			if(statystykiArrayList.get(1).getStatus() == 1)
				tf_oddane.setText(String.valueOf(statystykiArrayList.get(1).getLiczba()));
			
			statystykiArrayList.clear();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	TestController.traceCounter("OUT: StatystykiController selectStatystyki()");
    }
    
    private void selectStatystykiGrup() {
    	
    	TestController.traceCounter(" IN: StatystykiController selectStatystykiGrup()");

    	statystykiGrupObservableList.clear();
    	tv_dlagrup.setItems(statystykiGrupObservableList);
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT * FROM statystyki_view_grupy");
			ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
			
			while(sqlResultSet.next()) {
				statystykiGrupObservableList.add(new StatystykiGrupView(
						sqlResultSet.getInt("id_gr"),
						sqlResultSet.getString("nazwa"),
						sqlResultSet.getInt("status"),
						sqlResultSet.getInt("liczba")
						)
				);	
			}
			
			tvcg_id_gr.setCellValueFactory(new PropertyValueFactory<StatystykiGrupView, Integer>("id_gr"));
			tvcg_nazwa.setCellValueFactory(new PropertyValueFactory<StatystykiGrupView, String>("nazwa"));
			tvcg_status.setCellValueFactory(new PropertyValueFactory<StatystykiGrupView, Integer>("status"));
			tvcg_liczba.setCellValueFactory(new PropertyValueFactory<StatystykiGrupView, Integer>("liczba"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	TestController.traceCounter("OUT: StatystykiController selectStatystykiGrup()");
    }
    
    private void selectStatystykiKursantow() {
    	
    	TestController.traceCounter(" IN: StatystykiController selectStatystykiKursantow()");
    	
    	statystykiKursantowObservableList.clear();
    	tv_dlakursantow.setItems(statystykiKursantowObservableList);
    	
    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT * FROM statystyki_view_kursanci");
			ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
			
			while(sqlResultSet.next()) {
				statystykiKursantowObservableList.add(new StatystykiKursantowView(
						sqlResultSet.getInt("id_gr"),
						sqlResultSet.getString("nazwa"),
						sqlResultSet.getInt("id_kr"),
						sqlResultSet.getString("imie"),
						sqlResultSet.getString("nazwisko"),
						sqlResultSet.getInt("status"),
						sqlResultSet.getInt("liczba")
						)
				);	
			}
			
			tvck_id_gr.setCellValueFactory(new PropertyValueFactory<StatystykiKursantowView, Integer>("id_gr"));
			tvck_nazwa.setCellValueFactory(new PropertyValueFactory<StatystykiKursantowView, String>("nazwa"));
			tvck_id_kr.setCellValueFactory(new PropertyValueFactory<StatystykiKursantowView, Integer>("id_kr"));
			tvck_imie.setCellValueFactory(new PropertyValueFactory<StatystykiKursantowView, String>("imie"));
			tvck_nazwisko.setCellValueFactory(new PropertyValueFactory<StatystykiKursantowView, String>("nazwisko"));
			tvck_status.setCellValueFactory(new PropertyValueFactory<StatystykiKursantowView, Integer>("status"));
			tvck_liczba.setCellValueFactory(new PropertyValueFactory<StatystykiKursantowView, Integer>("liczba"));

			tv_dlakursantow.setItems(statystykiKursantowObservableList);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	TestController.traceCounter("OUT: StatystykiController selectStatystykiKursantow()");
    }
    
    public void initialize() {
    	
    	TestController.traceCounter(" IN: StatystykiController initialize()");

    	dbConnection = DBConnector.staticDBConnection;
    	
    	selectStatystyki();
    	selectStatystykiGrup();
    	selectStatystykiKursantow();
    	
    	TestController.traceCounter("OUT: StatystykiController initialize()");
    }
    
}
