package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.database.DBConnector;
import app.model.KontaktyKorespondencja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class KontaktyKorespondencjaController {

    @FXML
    private AnchorPane ap_korespondencja;

    @FXML
    private TableView<KontaktyKorespondencja> tv_korespondencja;

    @FXML
    private TableColumn<KontaktyKorespondencja, String> tvc_nadawca;

    @FXML
    private TableColumn<KontaktyKorespondencja, String> tvc_imie_nadawcy;

    @FXML
    private TableColumn<KontaktyKorespondencja, String> tvc_nazwisko_nadawcy;

    @FXML
    private TableColumn<KontaktyKorespondencja, String> tvc_odbiorca;

    @FXML
    private TableColumn<KontaktyKorespondencja, String> tvc_imie_odbiorcy;

    @FXML
    private TableColumn<KontaktyKorespondencja, String> tvc_nazwisko_odbiorcy;

    @FXML
    private TableColumn<KontaktyKorespondencja, String> tvc_temat;

    @FXML
    private TableColumn<KontaktyKorespondencja, String> tvc_tresc;

    @FXML
    private TableColumn<KontaktyKorespondencja, String> tvc_datetimetag;
    
    @FXML
    private TextArea ta_tresc;
    
    private ObservableList<KontaktyKorespondencja> korespondencjaObservableList = FXCollections.observableArrayList();
    private ViewController viewController;
  	private Connection dbConnection; 
  	private PreparedStatement sqlPreparedStatement;
    private Stage kontaktyStage;
    private KontaktyKorespondencja selectedKontaktyKorespondencja;
    public static int selectedKontaktyKorespondencjaInt;

    @FXML
    void buttonNowaWiadomoscClickedMouseAction(MouseEvent event) {
    	viewController.showView(kontaktyStage, "/app/view/", "KontaktyUzytkownicyView.fxml", LoginController.uzytkownikString.toUpperCase(), "/app/pwnicon.png");
    }

    @FXML
    void buttonOdswiezClickedMouseAction(MouseEvent event) {
    	selectKorespondencja();
    }
    
    @FXML
    void tableClickedMouseAction(MouseEvent event) {
    	selectedKontaktyKorespondencja = tv_korespondencja.getSelectionModel().getSelectedItem();	
    	if(selectedKontaktyKorespondencja != null) {
    		setForm();
    	}
    }

    @FXML
    void tableReleasedKeyAction(KeyEvent event) {
    	selectedKontaktyKorespondencja = tv_korespondencja.getSelectionModel().getSelectedItem();	
    	if(selectedKontaktyKorespondencja != null) {
    		setForm();
    	}
    }
    
    private void selectKorespondencja() {
    	
    	TestController.traceCounter(" IN: KontaktyKorespondencjaController selectKorespondencja()");
    	
    	clearForm();
    	
    	korespondencjaObservableList.clear();
    	tv_korespondencja.setItems(korespondencjaObservableList);

    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT nadawca, imie_nadawcy, nazwisko_nadawcy, odbiorca, imie_odbiorcy, nazwisko_odbiorcy, temat, tresc, datetimetag FROM "
    				+ "korespondencja_view WHERE id_nadawcy=" + LoginController.selectedUzytkownikInt + " AND flaga_ntk=" + LoginController.selectedFlagaInt + " OR "
    				+ "id_odbiorcy=" + LoginController.selectedUzytkownikInt + " AND flaga_otk=" + LoginController.selectedFlagaInt);
    		ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
			while(sqlResultSet.next()) {
				korespondencjaObservableList.add(new KontaktyKorespondencja(
						sqlResultSet.getString("nadawca"),
						sqlResultSet.getString("imie_nadawcy"),
						sqlResultSet.getString("nazwisko_nadawcy"),
						sqlResultSet.getString("odbiorca"),
						sqlResultSet.getString("imie_odbiorcy"),
						sqlResultSet.getString("nazwisko_odbiorcy"),
						sqlResultSet.getString("temat"),
						sqlResultSet.getString("tresc"),
						sqlResultSet.getString("datetimetag")
						)
				);	
			}

			tvc_nadawca.setCellValueFactory(new PropertyValueFactory<KontaktyKorespondencja, String>("nadawca"));
			tvc_imie_nadawcy.setCellValueFactory(new PropertyValueFactory<KontaktyKorespondencja, String>("imie_nadawcy"));
			tvc_nazwisko_nadawcy.setCellValueFactory(new PropertyValueFactory<KontaktyKorespondencja, String>("nazwisko_nadawcy"));
			tvc_odbiorca.setCellValueFactory(new PropertyValueFactory<KontaktyKorespondencja, String>("odbiorca"));
			tvc_imie_odbiorcy.setCellValueFactory(new PropertyValueFactory<KontaktyKorespondencja, String>("imie_odbiorcy"));
			tvc_nazwisko_odbiorcy.setCellValueFactory(new PropertyValueFactory<KontaktyKorespondencja, String>("nazwisko_odbiorcy"));
			tvc_temat.setCellValueFactory(new PropertyValueFactory<KontaktyKorespondencja, String>("temat"));
			tvc_tresc.setCellValueFactory(new PropertyValueFactory<KontaktyKorespondencja, String>("tresc"));
			tvc_datetimetag.setCellValueFactory(new PropertyValueFactory<KontaktyKorespondencja, String>("datetimetag"));
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	TestController.traceCounter("OUT: KontaktyKorespondencjaController selectKorespondencja()");
    }
    
    private void setForm() {
    	
    	if(selectedKontaktyKorespondencja != null) {
	    	ta_tresc.setText(selectedKontaktyKorespondencja.getTresc());
    	}
    }
    
    private void clearForm() {
    	
    	ta_tresc.clear();
    }
    
    public void initialize() {
    	
    	TestController.traceCounter(" IN: KontaktyKorespondencjaController initialize()");

    	viewController = new ViewController();
    	kontaktyStage = new Stage();
    	
    	dbConnection = DBConnector.staticDBConnection;
    	selectKorespondencja();
    	
    	TestController.traceCounter("OUT: KontaktyKorespondencjaController initialize()");
    }

}
