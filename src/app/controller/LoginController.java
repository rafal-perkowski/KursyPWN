package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
import app.database.DBConnector;
//import app.model.Query;
import javafx.application.Platform;
//import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
//import javafx.stage.WindowEvent;

public class LoginController {
	
	@FXML
    private AnchorPane ap_login;

    @FXML
    private ImageView iv_reaktor;

    @FXML
    private Label lbl_login;

    @FXML
    private TextField tf_login;

    @FXML
    private Label lbl_password;

    @FXML
    private PasswordField pf_password;

    @FXML
    private Button btn_zaloguj;

    @FXML
    private Button btn_wyjdz;
    
    private ViewController viewController;
    private Connection dbConnection; 
    private PreparedStatement sqlPreparedStatement;
    public static String rolaString = null;
    public static String uzytkownikString = null;
    public static int selectedLoginInt = 0;
    public static int selectedUzytkownikInt = 0;
    public static int selectedFlagaInt = 0;
    
    @FXML
    void loginButtonAction(MouseEvent event) {
    	
    	if(loginMethod()) {
    		viewController.hideView(event);
    	}
    }
    
    @FXML
    void exitButtonAction(MouseEvent event) {
    	
    	viewController.hideView(event);
    }
    
    @FXML
    void loginKeyAction(KeyEvent event) {
    	
    	if(event.getCode() == KeyCode.ENTER) {
    		if(loginMethod()) {
        		viewController.hideView(event);
        	}
    	}
    }
    
    private boolean loginMethod() {
    	
    	TestController.traceCounter(" IN: LoginController loginMethod()");
		
		boolean flagBoolean = false;

    	try {
    		
//    		Query tmpQuery = new Query();
//    		List<String> sqlList = new ArrayList<String>();
//    		sqlList.add("logowanie_view");
//    		sqlList.add("login");
//    		sqlList.add(tf_login.getText());
//    		sqlList.add("passwd");
//    		sqlList.add(pf_password.getText());
//    		sqlPreparedStatement = dbConnection.prepareStatement(tmpQuery.genStatement(tmpQuery.getSelectAllFromWhere().get(1), sqlList));
    		
    		sqlPreparedStatement = dbConnection.prepareStatement("SELECT id_lg, login, passwd, rola, uzytkownik, flaga_utk, id_utk FROM logowanie_view WHERE login=? AND passwd=?");
    		sqlPreparedStatement.setString(1, tf_login.getText());
    		sqlPreparedStatement.setString(2, pf_password.getText());
			
    		ResultSet sqlResultSet = sqlPreparedStatement.executeQuery();
    		
			if(sqlResultSet.next()){
				
				selectedLoginInt = sqlResultSet.getInt("id_lg");
				rolaString = sqlResultSet.getString("rola");
				uzytkownikString = sqlResultSet.getString("uzytkownik");	
				selectedFlagaInt = sqlResultSet.getInt("flaga_utk");
				selectedUzytkownikInt = sqlResultSet.getInt("id_utk");
				
				if(rolaString.equals("admin")){
					
					Stage adminStage = new Stage();
					viewController.showView(adminStage, "/app/view/", "AdminView.fxml", "TRENER", "/app/pwnicon.png");
					
					
					flagBoolean = true;

					adminStage.setOnCloseRequest(e -> { Platform.exit(); });
					//adminStage.setOnCloseRequest(new EventHandler<WindowEvent>() { @Override public void handle(WindowEvent t) {Platform.exit();} });
				} 
				else if(rolaString.equals("user")) {
					
					Stage userStage = new Stage();
					viewController.showView(userStage, "/app/view/", "UserView.fxml", "KURSANT", "/app/pwnicon.png");
					
					flagBoolean = true;

					userStage.setOnCloseRequest(e -> { Platform.exit(); });
					//userStage.setOnCloseRequest(new EventHandler<WindowEvent>() { @Override public void handle(WindowEvent t) {Platform.exit();} });
				}
				else {
					viewController.showAlert(AlertType.ERROR, "ERROR", "B³¹d logowania!", "Niezdefiniowana rola dla konta!\nSpróbuj zalogowaæ siê z innego konta lub skontaktuj z administratorem!", "/app/pwnicon.png");
				}
			} else {
				viewController.showAlert(AlertType.ERROR, "ERROR", "B³¹d logowania!", "Has³o lub login s¹ niepoprawne!\nSpróbuj zalogowaæ siê jeszcze raz lub skontaktuj z administratorem!", "/app/pwnicon.png");
				clearLogin();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	TestController.traceCounter("OUT: LoginController loginMethod()");
    	
    	return flagBoolean;
    }
    
    private void clearLogin() {
    	
    	tf_login.clear();
		pf_password.clear();
    }
    
    public void initialize() {
    	
    	TestController.traceCounter(" IN: LoginController initialize()");
    	
    	viewController = new ViewController();
    	dbConnection = DBConnector.staticDBConnection;
    	
    	TestController.traceCounter("OUT: LoginController initialize()");
    }
 
}