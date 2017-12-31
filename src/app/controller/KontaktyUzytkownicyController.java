package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import app.database.DBConnector;
import app.model.KontaktyUzytkownicy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class KontaktyUzytkownicyController {

	@FXML
    private AnchorPane ap_uzytkownicy;

    @FXML
    private TableView<KontaktyUzytkownicy> tv_uzytkownicy;

    @FXML
    private TableColumn<KontaktyUzytkownicy, String> tvc_uzytkownik;
    
    @FXML
    private TableColumn<KontaktyUzytkownicy, Integer> tvc_flaga_tk;

    @FXML
    private TableColumn<KontaktyUzytkownicy, Integer> tvc_id_utk;

    @FXML
    private TableColumn<KontaktyUzytkownicy, String> tvc_imie;

    @FXML
    private TableColumn<KontaktyUzytkownicy, String> tvc_nazwisko;

    @FXML
    private TableColumn<KontaktyUzytkownicy, String> tvc_telefon;

    @FXML
    private TableColumn<KontaktyUzytkownicy, String> tvc_email;

    @FXML
    private TableColumn<KontaktyUzytkownicy, String> tvc_github;

    @FXML
    private TextField tf_temat;

    @FXML
    private TextArea ta_tresc;
    
    private ObservableList<KontaktyUzytkownicy> uzytkownicyObservableList = FXCollections.observableArrayList();
    private ViewController viewController;
  	private Connection dbConnection; 
  	private PreparedStatement sqlPreparedStatement;
    private KontaktyUzytkownicy selectedKontaktyUzytkownicy;
    public static String selectedKontaktyUzytkownicyString;
    public static int selectedKontaktyUzytkownicyInt;
    public static int selectedKontaktyFlagaInt;

    @FXML
    void buttonOdswiezClickedMouseAction(MouseEvent event) {
    	selectUzytkownicy();
    }

    @FXML
    void buttonWyslijClickedMouseAction(MouseEvent event) {
    	insertUzytkownicy();
    }
    
    private void selectUzytkownicy() {
    	
    	TestController.traceCounter(" IN: KontaktyUzytkownicyController selectUzytkownicy()");
    	//clearForm();
    	
    	uzytkownicyObservableList.clear();
    	tv_uzytkownicy.setItems(uzytkownicyObservableList);

    	try {
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT uzytkownik, flaga_tk, id_utk, imie, nazwisko, telefon, email, github FROM uzytkownicy_view");
    		ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
			while(sqlResultSet.next()) {
				uzytkownicyObservableList.add(new KontaktyUzytkownicy(
						sqlResultSet.getString("uzytkownik"),
						sqlResultSet.getInt("flaga_tk"),
						sqlResultSet.getInt("id_utk"),
						sqlResultSet.getString("imie"),
						sqlResultSet.getString("nazwisko"),
						sqlResultSet.getString("telefon"),
						sqlResultSet.getString("email"),
						sqlResultSet.getString("github")
						)
				);	
			}

			tvc_uzytkownik.setCellValueFactory(new PropertyValueFactory<KontaktyUzytkownicy, String>("uzytkownik"));
			tvc_flaga_tk.setCellValueFactory(new PropertyValueFactory<KontaktyUzytkownicy, Integer>("flaga_tk"));
			tvc_id_utk.setCellValueFactory(new PropertyValueFactory<KontaktyUzytkownicy, Integer>("id_utk"));
			tvc_imie.setCellValueFactory(new PropertyValueFactory<KontaktyUzytkownicy, String>("imie"));
			tvc_nazwisko.setCellValueFactory(new PropertyValueFactory<KontaktyUzytkownicy, String>("nazwisko"));
			tvc_telefon.setCellValueFactory(new PropertyValueFactory<KontaktyUzytkownicy, String>("telefon"));
			tvc_email.setCellValueFactory(new PropertyValueFactory<KontaktyUzytkownicy, String>("email"));
			tvc_github.setCellValueFactory(new PropertyValueFactory<KontaktyUzytkownicy, String>("github"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	TestController.traceCounter("OUT: KontaktyUzytkownicyController selectUzytkownicy()");
    }
    
    private void insertUzytkownicy() {
    	
    	TestController.traceCounter(" IN: KontaktyUzytkownicyController insertUzytkownicy()");
    	
    	if(checkForm()) {
    		
    		try {
	    		
    			selectedKontaktyUzytkownicy = tv_uzytkownicy.getSelectionModel().getSelectedItem();
    			selectedKontaktyUzytkownicyInt = selectedKontaktyUzytkownicy.getId_utk();
    			selectedKontaktyFlagaInt = selectedKontaktyUzytkownicy.getFlaga_tk();
	    	}
	    	catch(Exception e) {
	    		
	    		viewController.showAlert(AlertType.INFORMATION, "INFORMATION", "Wysy³anie wiadomoœci!", "Aby wys³aæ wiadomoœæ wype³nij pola w formularzu!", "/app/pwnicon.png");
	    	}
    		try {
				sqlPreparedStatement = dbConnection.prepareStatement("INSERT INTO kontakty (id_nadawcy, flaga_ntk, id_odbiorcy, flaga_otk, temat, tresc, datetimetag) VALUES (?, ?, ?, ?, ?, ?, ?)");
				
				sqlPreparedStatement.setInt(1,LoginController.selectedUzytkownikInt);
				sqlPreparedStatement.setInt(2,LoginController.selectedFlagaInt);
				sqlPreparedStatement.setInt(3,selectedKontaktyUzytkownicyInt);
				sqlPreparedStatement.setInt(4,selectedKontaktyFlagaInt);
				sqlPreparedStatement.setString(5,tf_temat.getText());
				sqlPreparedStatement.setString(6,ta_tresc.getText());
				sqlPreparedStatement.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
				//sqlPreparedStatement.setString(7,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
				sqlPreparedStatement.executeUpdate();
				clearForm();
				viewController.showAlert(AlertType.INFORMATION, "INFORMATION", "Wysy³anie wiadomoœci!", "Wiadomoœæ zosta³a wys³ana. Aby przeczytaæ jej treœæ odœwie¿ okno korespondencji!", "/app/pwnicon.png");
			} catch(MySQLIntegrityConstraintViolationException e) {
				//e.printStackTrace();
				viewController.showAlert(AlertType.ERROR, "ERROR", "Wysy³anie wiadomoœci!", "Naruszenie regu³ integralnoœci bazy danych!", "/app/pwnicon.png");
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		
    	} else {
    		
    		viewController.showAlert(AlertType.INFORMATION, "INFORMATION", "Wysy³anie wiadomoœci!", "Aby wys³aæ wiadomoœæ wype³nij pola w formularzu!", "/app/pwnicon.png");
    	}
    	
    	TestController.traceCounter("OUT: KontaktyUzytkownicyController insertUzytkownicy()");
    	
    }
    
    private boolean checkForm() {
    	
    	boolean flagRetBoolean = false;
    	
    	if(!(
    			tf_temat.getText().equals("") || 
    			ta_tresc.getText().equals("")
    	))
    		flagRetBoolean = true;

    	return flagRetBoolean;
    }
    
    private void clearForm() {
    	
    	tf_temat.clear();
    	ta_tresc.clear();
    }
    
    public void initialize() {
    	
    	TestController.traceCounter(" IN: KontaktyUzytkownicyController initialize()");

    	viewController = new ViewController();
    	
    	dbConnection = DBConnector.staticDBConnection;
    	selectUzytkownicy();
    	
    	TestController.traceCounter("OUT: KontaktyUzytkownicyController initialize()");
    }

}
