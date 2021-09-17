package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import nonGUI.MaintainUserDoses;

public class SlotPageController implements Initializable{
	
	@FXML
	private AnchorPane rootPane;

	@FXML
	private Button back;

	@FXML
	private Button confirm;
	
	@FXML
	private ListView slots;

	@FXML
	private Label title;
	
	@FXML
	private Label title2;
	
	@FXML
	private Label title3;
	
	String date;
	String vaccine;
	String email;
	String dose;
	String time;
//	ArrayList<String> list = new ArrayList<String>();
	
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
	
	public void initData(String email2, String dose2, String date2, String vaccine2) {
		this.email = email2;
		this.date = date2;
		this.vaccine = vaccine2;
		this.dose = dose2;
		title2.setText(date2);
		
		title3.setText("VACCINE: " + this.vaccine);
		
		String[] list = date.split("-");
		int n = Integer.parseInt(list[1]);
		if((n%2) == 0) {
			slots.getItems().add("	                  10:00");
		}
		if((n%3) == 0) {
			slots.getItems().add("	                  11:20");
		}
		if((n%5) == 0) {
			slots.getItems().add("	                  14:10");	
		}
		if((n%7) == 0) {
			slots.getItems().add("	                  15:40");
		}
		if((n%11) == 0) {
			slots.getItems().add("	                  17:30");
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
	
	public void ConfirmAction() {
		if(!slots.getSelectionModel().isEmpty()) {
			time = slots.getSelectionModel().getSelectedItem().toString();
			if(time.equals("	                  10:00")) {
				MaintainUserDoses enter = new MaintainUserDoses(this.email, this.dose, this.vaccine, this.date, "10:00");
				Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
				infoAlert.setHeaderText("Time Confirmed");
				infoAlert.setContentText("You have booked your DOSE " + this.dose + " for " + this.vaccine + " on " + this.date + " at 10:00 \n"
						+ "You will now be redirected to the Main Menu");
				infoAlert.showAndWait();
				makeFadeOutConfirm();
			}
			else if(time.equals("	                  11:20")) {
				MaintainUserDoses enter = new MaintainUserDoses(this.email, this.dose, this.vaccine, this.date, "11:20");
				Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
				infoAlert.setHeaderText("Time Confirmed");
				infoAlert.setContentText("You have booked your DOSE " + this.dose + " for " + this.vaccine + " on " + this.date + " at 11:20 \n"
						+ "You will now be redirected to the Main Menu");
				infoAlert.showAndWait();
				makeFadeOutConfirm();
			}
			else if(time.equals("	                  14:10")) {
				MaintainUserDoses enter = new MaintainUserDoses(this.email, this.dose, this.vaccine, this.date, "14:10");
				Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
				infoAlert.setHeaderText("Time Confirmed");
				infoAlert.setContentText("You have booked your DOSE " + this.dose + " for " + this.vaccine + " on " + this.date + " at 14:10 \n"
						+ "You will now be redirected to the Main Menu");
				infoAlert.showAndWait();
				makeFadeOutConfirm();
			}
			else if(time.equals("	                  15:40")) {
				MaintainUserDoses enter = new MaintainUserDoses(this.email, this.dose, this.vaccine, this.date, "15:40");
				Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
				infoAlert.setHeaderText("Time Confirmed");
				infoAlert.setContentText("You have booked your DOSE " + this.dose + " for " + this.vaccine + " on " + this.date + " at 15:40 \n"
						+ "You will now be redirected to the Main Menu");
				infoAlert.showAndWait();
				makeFadeOutConfirm();
			}
			else if(time.equals("	                  17:30")) {
				MaintainUserDoses enter = new MaintainUserDoses(this.email, this.dose, this.vaccine, this.date, "17:30");
				Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
				infoAlert.setHeaderText("Time Confirmed");
				infoAlert.setContentText("You have booked your DOSE " + this.dose + " for " + this.vaccine + " on " + this.date + " at 17:30 \n"
						+ "You will now be redirected to the Main Menu");
				infoAlert.showAndWait();
				makeFadeOutConfirm();
			}
			else {
				Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	            errorAlert.setHeaderText("No time chosen");
	            errorAlert.setContentText("Please choose a time slot to confirm");
	            errorAlert.showAndWait();
			}
		}
		else {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No time chosen");
            errorAlert.setContentText("Please choose a time slot to confirm");
            errorAlert.showAndWait();
		}
	}
	
	private void makeFadeOutConfirm() {
		// TODO Auto-generated method stub
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.setOnFinished(event -> loadFinalPage());
		fadeTransition.play();
	}

	private void loadFinalPage() {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("SignedInPage.fxml"));
			Parent secondView = loader.load();
			Scene newScene = new Scene(secondView);
			SignedInPageController controller = loader.getController();
			Stage curStage = (Stage) rootPane.getScene().getWindow();
			curStage.setScene(newScene);
			curStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}