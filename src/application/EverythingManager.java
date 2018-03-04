package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

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
	String superpath;
	static String home;
	static String OS;
	public void start(Stage primaryStage) {
		try {
			Parent root;
			Scene scene;
			stage = primaryStage;
			root = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
			scene = new Scene(root, 650, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
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
	public void OSdetection() {
		OS = System.getProperty("os.name");
	}
	public static void syscrashmsg(Exception e) {
		long time = System.currentTimeMillis();
		String errorData = e.toString();
		String errorfile = "syscriticfail" + time + ".txt";
		try {
			print("System error:");
			e.printStackTrace();
			fileWriter(errorfile, errorData);
		} catch (Exception e1) {
			print("File writing error:");
			e1.printStackTrace();
		}
		print("Failure data will be stored in: errlog-espa/syscriticfail" + time + ".txt");
	}
	public static void print(String a) {
		System.out.println(a);
	}
	public static void fileWriter(String filename, String contents) {
		try {
			print("Writing file under FusionShield directory...: " + filename);
			if (OS.startsWith("Windows")) {
				String fspath = "C://errlog-espa//" + filename;
				Writer writer = null;
				File newfile = new File(filename);
				if (newfile.exists()) {
					System.out.println("File already exists!");
				} else {
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fspath), "utf-8"));
					writer.write(contents);
					writer.close();
				}
			} else if (OS.startsWith("Mac")) {
				String fspath = home + "/errlog-espa/" + filename;
				Writer writer = null;
				File newfile = new File(fspath);
				if (newfile.exists()) {
					System.out.println("File already exists!");
				} else {
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fspath), "utf-8"));
					writer.write(contents);
					writer.close();
				}
			} else if (OS.contains("linux")) {
				String fspath = home + "/errlog-espa/" + filename;
				Writer writer = null;
				File newfile = new File(fspath);
				if (newfile.exists()) {
					System.out.println("File already exists!");
				} else {
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fspath), "utf-8"));
					writer.write(contents);
					writer.close();
				}
			} else {
				print("Unknown OS. Unable to find FusionShieldPath.");
			}
		} catch (Exception e) {
			syscrashmsg(e);
		}
		
	}
}
