package app.controller;

import java.io.IOException;
import java.util.Optional;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewController {
	
	public void showView(Stage paramStage, String pathString, String fileString, String titleString, String iconpathString) {
		
		TestController.traceCounter(" IN: ViewController showView(" + paramStage + ", " + pathString + ", " + fileString + ", " + titleString + ", " + iconpathString + ")");
		
		Parent tmpParent;
		Scene tmpScene;
		
		try {
			tmpParent = (Parent) FXMLLoader.load(getClass().getResource(pathString + fileString));
			tmpScene = new Scene(tmpParent);
			paramStage.setScene(tmpScene);
	        paramStage.setTitle(titleString);
	        paramStage.getIcons().add(new Image(iconpathString));
	        paramStage.show();
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("IOException w funkcji showView() dla widoku: " + fileString);
		}
		
		TestController.traceCounter("OUT: ViewController showView(" + paramStage + ", " + pathString + ", " + fileString + ", " + titleString + ", " + iconpathString + ")");
	}
	
	public void hideView(Event event) {
		
		TestController.traceCounter(" IN: ViewController hideView(" + event + ")");
		
    	((Node)(event.getSource())).getScene().getWindow().hide();
    
    	TestController.traceCounter("OUT: ViewController hideView(" + event + ")");
	}
	
	public void switchAnchorPane(AnchorPane paramAnchorPane, String pathString, String fileString) {
		
		TestController.traceCounter(" IN: ViewController switchAnchorPane(" + paramAnchorPane + ", " + pathString + ", " + fileString + ")");
		
    	try {
			AnchorPane tmpAnchorPane = FXMLLoader.load(getClass().getResource(pathString + fileString));
			paramAnchorPane.getChildren().setAll(tmpAnchorPane);
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("IOException w funkcji switchAnchorPane() dla widoku: " + fileString);
		}
    	
    	TestController.traceCounter("OUT: ViewController switchAnchorPane(" + paramAnchorPane + ", " + pathString + ", " + fileString + ")");
	}

	public boolean showAlert(AlertType paramAlertType, String titleString, String headerString, String contentString, String iconpathString) {
		
		TestController.traceCounter(" IN: ViewController showAlert(" + paramAlertType + ", " + titleString + ", " + headerString + ", " + contentString + ", " + iconpathString + ")");
    	
		Alert tmpAlert = new Alert(paramAlertType);
		Stage tmpStage;
		Optional<ButtonType> optionalButtonType;
		boolean flagReturnBoolean = false;
		
		tmpAlert.setTitle(titleString);
		tmpAlert.setHeaderText(headerString);
		tmpAlert.setContentText(contentString);
		tmpStage = (Stage) tmpAlert.getDialogPane().getScene().getWindow();
		tmpStage.getIcons().add(new Image(iconpathString));
		optionalButtonType = tmpAlert.showAndWait();
		
		if(optionalButtonType.get() == ButtonType.OK)
			flagReturnBoolean = true;
		else if(optionalButtonType.get() == ButtonType.CANCEL)
			flagReturnBoolean = false;
		else
			flagReturnBoolean = false;
		
		TestController.traceCounter("OUT: ViewController showAlert(" + paramAlertType + ", " + titleString + ", " + headerString + ", " + contentString + ", " + iconpathString + ")");
		
		return flagReturnBoolean;
    }
	
}
