package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AdminController {
	
	@FXML
    private AnchorPane ap_admin;

    @FXML
    private Button btn_dane;

    @FXML
    private Button btn_grupy;

    @FXML
    private Button btn_kursanci;

    @FXML
    private Button btn_projekty;
    
    @FXML
    private Button btn_oceny;

    @FXML
    private Button btn_statystyki;

    @FXML
    private Button btn_kontakty;

    @FXML
    private AnchorPane ap_submenu;
    
    private ViewController viewController;

    @FXML
    void buttonClickedDane(MouseEvent event) {
    	clearAdmin();
    	btn_dane.setEffect(new Lighting());
    	viewController.switchAnchorPane(ap_submenu, "/app/view/", "DaneAdminView.fxml");
    }
    
    @FXML
    void buttonClickedGrupy(MouseEvent event) {
    	clearAdmin();
    	btn_grupy.setEffect(new Lighting());
    	viewController.switchAnchorPane(ap_submenu, "/app/view/", "GrupyView.fxml");
    }

    @FXML
    void buttonClickedKontakty(MouseEvent event) {
    	clearAdmin();
    	btn_kontakty.setEffect(new Lighting());
    	viewController.switchAnchorPane(ap_submenu, "/app/view/", "KontaktyKorespondencjaView.fxml");
    }

    @FXML
    void buttonClickedKursanci(MouseEvent event) {
    	clearAdmin();
    	btn_kursanci.setEffect(new Lighting());
    	viewController.switchAnchorPane(ap_submenu, "/app/view/", "KursanciView.fxml");
    }
    
    @FXML
    void buttonClickedOceny(MouseEvent event) {
    	clearAdmin();
    	btn_oceny.setEffect(new Lighting());
    	viewController.switchAnchorPane(ap_submenu, "/app/view/", "OcenyView.fxml");
    }

    @FXML
    void buttonClickedProjekty(MouseEvent event) {
    	clearAdmin();
    	btn_projekty.setEffect(new Lighting());
    	viewController.switchAnchorPane(ap_submenu, "/app/view/", "ProjektyAdminView.fxml");
    }

    @FXML
    void buttonClickedStatystyki(MouseEvent event) {
    	clearAdmin();
    	btn_statystyki.setEffect(new Lighting());
    	viewController.switchAnchorPane(ap_submenu, "/app/view/", "StatystykiView.fxml");
    }
    
   private void clearAdmin() {
	   
	   btn_dane.setEffect(null);
	   btn_grupy.setEffect(null);
	   btn_kursanci.setEffect(null);
	   btn_projekty.setEffect(null);
	   btn_oceny.setEffect(null);
	   btn_statystyki.setEffect(null);
	   btn_kontakty.setEffect(null);
	   
   }

    public void initialize() {

    	TestController.traceCounter(" IN: AdminController initialize()");
    	
    	viewController = new ViewController();
    	buttonClickedDane(null);
    	
    	TestController.traceCounter("OUT: AdminController initialize()");
    }

}
