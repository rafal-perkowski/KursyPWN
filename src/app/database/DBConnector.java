package app.database;

import java.io.File;
import java.io.FileNotFoundException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConnector {
	
	String dburlString;
	String loginString;
	String passwordString;
	
	Connection dbConnection;
	boolean flagReadFileBoolean = true;
	static public Connection staticDBConnection;
	
	private void registerDBDriver() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.out.println("Nie uda³o siê zarejestrowaæ klasy sterownika!");
		}
	}

	public DBConnector() {
		
		super();
		this.dburlString = "";
		this.loginString = "";
		this.passwordString = "";
		dbConnection = null;
		staticDBConnection = null;
		
		registerDBDriver();
		//openDBConnection();
	}
	
	public DBConnector(Connection dbConnection) {
		
		super();
		this.dbConnection = dbConnection;
		staticDBConnection = dbConnection;
	}

	public Connection getDbConnection() {
		
		return dbConnection;
	}

	public void setDbConnection(Connection dbConnection) {
		
		this.dbConnection = dbConnection;
		staticDBConnection = dbConnection;
	}

	public void setDbconnConnection(Connection dbconnConnection) {
		this.dbConnection = dbconnConnection;
	}
	
	public Connection openDBConnection() {
		
//		this.dburlString = "jdbc:mysql://localhost:3306/kursypwn";
//		this.loginString = "root";
//		this.passwordString = "***";
		dbConnection = null;
		
		if(flagReadFileBoolean) {
			readLoginFile();
		}
		
		try {
			dbConnection = DriverManager.getConnection(this.dburlString, this.loginString , this.passwordString);
			System.out.println("Po³¹czenie z baz¹ danych zosta³o zestawione!");
		} catch(SQLException e) {
			//e.printStackTrace();
			System.out.println("Po³¹czenie z baz¹ danych nie zosta³o zestawione!");
		}
		
		staticDBConnection = dbConnection;		
		return dbConnection;
	}
	
	public Connection openDBConnection(String urlString, String userString, String passwordString) {
		
		try {
			dbConnection = DriverManager.getConnection(urlString, userString, passwordString);
			System.out.println("Po³¹czenie z baz¹ danych zosta³o zestawione!");
		} catch(SQLException e) {
			//e.printStackTrace();
			System.out.println("Po³¹czenie z baz¹ danych nie zosta³o zestawione!");
		}
		
		staticDBConnection = dbConnection;		
		return dbConnection;
	}
	
	public void closeDBConnection() {
		
		if(dbConnection != null) {
			try {
				this.dbConnection.close();
				System.out.println("Po³¹czenie z baz¹ danych zosta³o zakoñczone!");
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.println("Problem z zamkniêciem po³¹czenia z baz¹ danych!");
			}
		}
	}
	
	private void readLoginFile() {
		
		File inputFile;
		String inputFileString = "src/app/logowanie.txt";
		Scanner fileScanner;
		String inputString;
		String [] paramString = null;
		
//		Path currentRelativePath = Paths.get("");
//		String pathString = currentRelativePath.toAbsolutePath().toString();
//		System.out.println("Current relative path is: " + pathString);

		try {
			
			inputFile = new File(inputFileString);
			fileScanner = new Scanner(inputFile);
			
			while(fileScanner.hasNextLine()) {
				inputString = fileScanner.nextLine();
				paramString = inputString.split("=");
				if(paramString[0].trim().equals("url"))
					dburlString = paramString[1].trim();
				else if(paramString[0].trim().equals("login"))
					loginString = paramString[1].trim();
				else if(paramString[0].trim().equals("password"))
					passwordString = paramString[1].trim();
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("Nie mo¿na odnalezc okreœlonego pliku.");
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("B³êdny odczyt danych z pliku.");
		}
	}
	
}
