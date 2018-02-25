package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EverythingManager {
	@FXML
	TextField username;
	@FXML
	PasswordField pwfield;
	@FXML
	Stage stage;
	@FXML
	Label msg_error;

	public void start(Stage primaryStage) {
		try {
			Parent root;
			Scene scene;
			stage = primaryStage;
			root = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
			scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("E: twrp6");
		}
	}

	@FXML
	public void testprint(ActionEvent ae) {
		try {
			System.out.println("TEST PRINTER: requested to print");
			Node source = (Node) ae.getSource();
			Stage temp = (Stage) source.getScene().getWindow();
			LoginManager lm = new LoginManager();
			boolean loginpass = lm.validator(username.getText(), pwfield.getText());
			if (loginpass) {
				Parent root;
				Scene scene;
				root = FXMLLoader.load(getClass().getResource("LoadUI.fxml"));
				scene = new Scene(root, 600, 400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				temp.setScene(scene);
				temp.show();
			}else {
				msg_error.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("E: twrp7");
		}
	}
}
