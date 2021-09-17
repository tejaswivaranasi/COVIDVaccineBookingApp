package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import nonGUI.MaintainUserDoses;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DosePageController implements Initializable {
	
	
	@FXML
    private AnchorPane rootPane;
	
    @FXML
    private Label title;
    
    @FXML
    private Label title2;
    
    @FXML
    private Button dose1;
    
    @FXML
    private Button dose2;
    
    @FXML
    private Button back;
    
    String vaccine;
    String email;
    String dose;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
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
	
	public void initData(String email2, String vaccine2) {
		this.email = email2;
		this.vaccine = vaccine2;
		title2.setText("FOR " + this.vaccine + " VACCINE");
	}
	
	public void Dose1Action() {
		MaintainUserDoses check1 = new MaintainUserDoses(this.email, "1", this.vaccine);
		if(check1.incorrectName) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Incorrect Vaccine Chosen");
            errorAlert.setContentText("You have taken another vaccine and are therefore ineligible to take the " + this.vaccine + " vaccine.\n "
            		+ "You will be taken back to the vaccine selection page. \n"
            		+ "Please choose the vaccine you have taken");
            errorAlert.showAndWait();
		}
		else if(check1.incorrectNumber) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Incorrect Vaccine Dose");
            errorAlert.setContentText("You have chosen the SECOND DOSE for a vaccine for which you have not recieved the FIRST DOSE for.\n "
            		+ "Please choose the correct vaccine dose for yourself");
            errorAlert.showAndWait();
		}
		else if(check1.situation != 0){
			if(check1.situation == 1) {
				Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	            errorAlert.setHeaderText("Incorrect Vaccine Dose");
	            errorAlert.setContentText("You have chosen the SECOND DOSE for a vaccine for which you have not recieved the FIRST DOSE for.\n "
	            		+ "Please choose the correct vaccine dose for yourself");
	            errorAlert.showAndWait();
			}
			else if(check1.situation == 2) {
				Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	            errorAlert.setHeaderText("Incorrect Vaccine Dose");
	            errorAlert.setContentText("You have chosen the FIRST DOSE for a vaccine for which you have already recieved the FIRST DOSE for.\n "
	            		+ "Please choose the correct vaccine dose for yourself");
	            errorAlert.showAndWait();
			}
			else if(check1.situation == 4) {
				Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	            errorAlert.setHeaderText("FULLY VACCINATED!");
	            errorAlert.setContentText("You have already recieved TWO DOSES for this vaccine!");
	            errorAlert.showAndWait();
			}
		}
		else {
			this.dose = "1";
			makeFadeOutDatePage();
		}
	}
	
	public void Dose2Action() {
		MaintainUserDoses check2 = new MaintainUserDoses(this.email, "2", this.vaccine);
		if(check2.incorrectName) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Incorrect Vaccine Chosen");
            errorAlert.setContentText("You have taken another vaccine and are therefore ineligible to take the " + this.vaccine + " vaccine.\n "
            		+ "You will be taken back to the vaccine selection page.\n"
            		+ "Please choose the vaccine you have taken");
            errorAlert.showAndWait();
		}
		else if(check2.incorrectNumber) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Incorrect Vaccine Dose");
            errorAlert.setContentText("You have chosen the SECOND DOSE for a vaccine for which you have not recieved the FIRST DOSE for.\n "
            		+ "Please choose the correct vaccine dose for yourself");
            errorAlert.showAndWait();
		}
		else if(check2.situation != 0){
			if(check2.situation == 1) {
				Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	            errorAlert.setHeaderText("Incorrect Vaccine Dose");
	            errorAlert.setContentText("You have chose the SECOND DOSE for a vaccine for which you have not recieved the FIRST DOSE for.\n "
	            		+ "Please choose the correct vaccine dose for yourself");
	            errorAlert.showAndWait();
			}
			else if(check2.situation == 2) {
				Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	            errorAlert.setHeaderText("Incorrect Vaccine Dose");
	            errorAlert.setContentText("You have chose the FIRST DOSE for a vaccine for which you have already recieved the FIRST DOSE for.\n "
	            		+ "Please choose the correct vaccine dose for yourself");
	            errorAlert.showAndWait();
			}
			else if(check2.situation == 4) {
				Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	            errorAlert.setHeaderText("FULLY VACCINATED!");
	            errorAlert.setContentText("You have already recieved TWO DOSES for this vaccine!");
	            errorAlert.showAndWait();
			}
		}
		else {
			this.dose = "2";
			makeFadeOutDatePage();
		}
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

	private void makeFadeOutBack() {
		// TODO Auto-generated method stub
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.setOnFinished(event -> loadBackScene());
		fadeTransition.play();
	}

	private void loadBackScene() {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("SignedInPage.fxml"));
			Parent secondView = loader.load();
			Scene newScene = new Scene(secondView);
			SignedInPageController controller = loader.getController();
			controller.initData(this.email);
			Stage curStage = (Stage) rootPane.getScene().getWindow();
			curStage.setScene(newScene);
			curStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void makeFadeOutDatePage() {
		// TODO Auto-generated method stub
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.setOnFinished(event -> loadDatePage());
		fadeTransition.play();
}

	private void loadDatePage() {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("DatePage.fxml"));
			Parent secondView = loader.load();
			Scene newScene = new Scene(secondView);
			DatePageController controller = loader.getController();
			controller.initData(this.email, this.dose, this.vaccine);
			Stage curStage = (Stage) rootPane.getScene().getWindow();
			curStage.setScene(newScene);
			curStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}