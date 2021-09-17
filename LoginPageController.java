package application;

import java.net.URL;
import java.util.ResourceBundle;

import nonGUI.MaintainUserLogin;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginPageController implements Initializable {
	
	@FXML
	private AnchorPane rootPane;
	
	@FXML
	private Button login;
	
	@FXML
	private Button register;
	
	@FXML
	private Button back;
	
	@FXML
	private TextField email;
	
	@FXML
	private PasswordField password;
	
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
	
	public void LoginAction() {
		if(email.getText().equals("") && password.getText().equals("")) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Login Credentials");
            errorAlert.setContentText("Please enter your Login details to Sign in");
            errorAlert.showAndWait();
		}
		else if(email.getText().equals("") && !password.getText().equals("")) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Email given");
            errorAlert.setContentText("Please enter your Email to Sign in");
            errorAlert.showAndWait();
		}
		else if(!email.getText().equals("") && password.getText().equals("")) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Password given");
            errorAlert.setContentText("Please enter your Password to Sign in");
            errorAlert.showAndWait();
		}
		else {
			MaintainUserLogin login = new MaintainUserLogin(email.getText(), password.getText());
			if(login.signIn) {
				makeFadeOutSignedInPage();
			}
			else {
				Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	            errorAlert.setHeaderText("Incorrect Login Credentials");
	            errorAlert.setContentText("Please recheck the information entered. If you are a new user, please hit the Register button.");
	            errorAlert.showAndWait();
			}
		}
	}
	
	public void RegisterAction() {
		makeFadeOutRegisterPage();
	}

	public void BackAction() {
		ButtonType YES = new ButtonType("Yes");
		ButtonType NO = new ButtonType("No");
		AlertType type = AlertType.WARNING; 
		Alert alert = new Alert(type, "Would you like to go back?", YES, NO);
		alert.setHeaderText("Confirmation");
		alert.getDialogPane();
		alert.showAndWait().ifPresent(response ->{
			if(response == YES) {
				makeFadeOutBack();
			}
			if(response == NO) {
				
			}
			
		});;
	}
	
	private void makeFadeOutSignedInPage() {
		// TODO Auto-generated method stub
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.setOnFinished(event -> loadSignedInPage());
		fadeTransition.play();
	}

	private void loadSignedInPage() {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("SignedInPage.fxml"));
			Parent secondView = loader.load();
			Scene newScene = new Scene(secondView);
			SignedInPageController controller = loader.getController();
			controller.initData(email.getText());
			Stage curStage = (Stage) rootPane.getScene().getWindow();
			curStage.setScene(newScene);
			curStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
	
	private void makeFadeOutBack() {
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.setOnFinished(event -> loadBackScene());
		fadeTransition.play();
	}
	
	private void loadBackScene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MainPage.fxml"));
			Parent secondView = loader.load();
			Scene newScene = new Scene(secondView);
			MainPageController controller = loader.getController();
			Stage curStage = (Stage) rootPane.getScene().getWindow();
			curStage.setScene(newScene);
			curStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
