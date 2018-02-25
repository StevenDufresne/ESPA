package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginManager {
	public Scene scene;
	public LoginManager() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("xmldoc.fxml"));
			scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Scene returner() {
		return scene;
	}
}
