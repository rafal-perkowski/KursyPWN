package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class UserController {

    @FXML
    private AnchorPane ap_user;

    @FXML
    private Button btn_dane;

    @FXML
    private Button btn_projekty;

    @FXML
    private Button btn_kontakty;

    @FXML
    private AnchorPane ap_submenu;
    
    private ViewController viewController;

    @FXML
    void buttonClickedDane(MouseEvent event) {
    	clearUser();
    	btn_dane.setEffect(new Lighting());
    	viewController.switchAnchorPane(ap_submenu, "/app/view/", "DaneUserView.fxml");
    }

    @FXML
    void buttonClickedKontakty(MouseEvent event) {
    	clearUser();
    	btn_kontakty.setEffect(new Lighting());
    	viewController.switchAnchorPane(ap_submenu, "/app/view/", "KontaktyKorespondencjaView.fxml");
    }

    @FXML
    void buttonClickedProjekty(MouseEvent event) {
    	clearUser();
    	btn_projekty.setEffect(new Lighting());
    	viewController.switchAnchorPane(ap_submenu, "/app/view/", "ProjektyUserView.fxml");
    }
    
    private void clearUser() {
 	   
 	   btn_dane.setEffect(null);
 	   btn_projekty.setEffect(null);
 	   btn_kontakty.setEffect(null);
    }
    
    public void initialize() {
    	
    	TestController.traceCounter(" IN: UserController initialize()");

    	viewController = new ViewController();
    	buttonClickedDane(null);
    	
    	TestController.traceCounter("OUT: UserController initialize()");
    }
    
}
