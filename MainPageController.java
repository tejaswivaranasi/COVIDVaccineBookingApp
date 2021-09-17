package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainPageController implements Initializable {
	
	
	@FXML
	private AnchorPane rootPane;
	
	@FXML
	private Button login;
	
	@FXML
	private Button register;
	
	@FXML
	private Button help;
	
	@FXML
	private Label heading;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeFade();
		
	}
	
	private void makeFade() {
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);
	
		fadeTransition.play();
		
	}
	
	public void LoginButton() {
		makeFadeOutLoginPage();
	}
	
	public void RegisterButton() {
		makeFadeOutRegisterPage();
	}
	
	public void HelpButton() {
		Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
        helpAlert.setHeaderText("Login if you already have a pre-existing account.\n"
        		+ "If not, REGISTER yourself to make an account to book your vaccine appointments.");
        helpAlert.showAndWait();
	}
	
	private void makeFadeOutRegisterPage() {
		// TODO Auto-generated method stub
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.setOnFinished(event -> loadRegisterPage());
		fadeTransition.play();
	}

	private void loadRegisterPage() {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("RegisterPage.fxml"));
			Parent secondView = loader.load();
			Scene newScene = new Scene(secondView);
			RegisterPageController controller = loader.getController();
			Stage curStage = (Stage) rootPane.getScene().getWindow();
			curStage.setScene(newScene);
			curStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void makeFadeOutLoginPage() {
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.setOnFinished(event -> loadLoginPage());
		fadeTransition.play();
	}
	
	private void loadLoginPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("LoginPage.fxml"));
			Parent secondView = loader.load();
			Scene newScene = new Scene(secondView);
			LoginPageController controller = loader.getController();
			Stage curStage = (Stage) rootPane.getScene().getWindow();
			curStage.setScene(newScene);
			curStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}