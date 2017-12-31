package app;

import javafx.application.Application;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import app.controller.TestController;
import app.controller.ViewController;
import app.database.DBConnector;

public class Main extends Application {
	
	private ViewController viewController;
	private DBConnector dbDBConnector;
	
	@Override
    public void start(Stage stage) {
		
		TestController.traceCounter(" IN: Main start(" + stage + ")");

		dbDBConnector = new DBConnector();
		viewController = new ViewController();
		
		if(dbDBConnector.openDBConnection() != null) {
			viewController.showView(stage, "/app/view/", "LoginView.fxml", "Logowanie do Bazy Danych Kursów", "/app/pwnicon.png");
		}
		else {
			viewController.showAlert(AlertType.ERROR, "ERROR", "Baza danych!", "Po³¹czenie z baz¹ danych nie zosta³o zestawione!\nSprawdz wartoœci parametrów konfiguracyjnych w pliku logowanie.txt!", "/app/pwnicon.png");
		}
		
		TestController.traceCounter("OUT: Main start(" + stage + ")");
    }
	
	@Override
    public void stop() {
		
		TestController.traceCounter(" IN: Main stop()");
		
		dbDBConnector.closeDBConnection();
		
		TestController.traceCounter("OUT: Main stop()");
    }
	
	public static void main(String[] args) {
		
		TestController.traceCounter(" IN: Main main(" + args + ")");
		
		launch(args);
		
		TestController.traceCounter("OUT: Main main(" + args + ")");
		
	}
}
