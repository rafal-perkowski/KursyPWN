package app;

import javafx.application.Application;
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
		dbDBConnector.openDBConnection();
		
		viewController = new ViewController();
		viewController.showView(stage, "/app/view/", "LoginView.fxml", "Logowanie do Bazy Danych Kursów", "/app/pwnicon.png");
		
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
