package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import nonGUI.MaintainUserDoses;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

public class SignedInPageController implements Initializable {
	
	@FXML
	private AnchorPane rootPane;
	
	@FXML
	private Button pfizer;
	
	@FXML
	private Button moderna;
	
	@FXML
	private Button astraZeneca;
	
	@FXML
	private Button johnson;
	
	@FXML
	private Button logOut;
	
	@FXML
	private Button help;
	
	String vaccine;
	String email;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeFade();
		
	}
	
	public void initData(String email2) {
		this.email = email2;
	}
	
	private void makeFade() {
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);
	
		fadeTransition.play();
		
	}
	
	public void LogOutAction() {
		ButtonType YES = new ButtonType("Yes");
		ButtonType NO = new ButtonType("No");
		AlertType type = AlertType.WARNING; 
		Alert alert = new Alert(type, "Would you like to Log Out?", YES, NO);
		alert.setHeaderText("Confirmation");
		alert.getDialogPane();
		alert.showAndWait().ifPresent(response ->{
			if(response == YES) {
				makeFadeOutLogOut();
			}
			if(response == NO) {
				
			}
			
		});;
	}
	
	private void makeFadeOutLogOut() {
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
	
	public void PfizerAction() {
		this.vaccine = "Pfizer BioNTech";
		makeFadeOutDosePage();
	}
	
	private void makeFadeOutDosePage() {
		// TODO Auto-generated method stub
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.setOnFinished(event -> loadDosePage());
		fadeTransition.play();
		
	}

	private void loadDosePage() {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("DosePage.fxml"));
			Parent secondView = loader.load();
			Scene newScene = new Scene(secondView);
			DosePageController controller = loader.getController();
			controller.initData(this.email, this.vaccine);
			Stage curStage = (Stage) rootPane.getScene().getWindow();
			curStage.setScene(newScene);
			curStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void ModernaAction() {
		this.vaccine = "Moderna";
		makeFadeOutDosePage();
	}
	
	public void AstraZenecaAction() {
		this.vaccine = "AstraZeneca";
		makeFadeOutDosePage();
	}
	
	public void JohnsonAction() {
		this.vaccine = "Johnson & Johnson";
		makeFadeOutDosePage();
	}
	
	public void HelpAction() {
		Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
        helpAlert.setHeaderText("Pick any one of the following vaccines listed below to book an appointment for."
        		+ "Or hit the LOGOUT button if you wish to log out of your account.");
        helpAlert.showAndWait();
	}

}