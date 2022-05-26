package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	@Override
	public void init() {
		System.out.println("Leer el archivo");	
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

			Parent root = FXMLLoader.load(getClass().getResource("/view/Sample.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		
	}
	
	@Override
	public void stop() {
		System.out.println("Creamos la nomina");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
