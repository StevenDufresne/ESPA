package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EverythingManager {
	public EverythingManager(Stage primaryStage) {
		try {
			LoginManager lm = new LoginManager();
			Scene scene = lm.returner();
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	public static void testprint() {
		System.out.println("TEST PRINTER: requested to print");
	}
}
