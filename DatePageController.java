package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

public class DatePageController implements Initializable{

	@FXML
	private AnchorPane rootPane;
	
	@FXML
	private DatePicker date;
	
	@FXML
	private Label title;
	
	@FXML
	private Label title2;

	@FXML
	private Button back;

	@FXML
	private Button confirm;
	
	String vaccine;
	String email;
	String dose;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeFade();
		
		date.setConverter(new StringConverter<LocalDate>() {
		     String pattern = "MM-dd-yyyy";
		     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

		     {
		         date.setPromptText(pattern.toLowerCase());
		     }

		     @Override public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 });
		
	}
	
	public void initData(String email2, String dose2, String vaccine2) {
		this.vaccine = vaccine2;
		this.email = email2;
		this.dose = dose2;
		title2.setText(this.vaccine + " VACCINE APPOINTMENT");
	}
	
	private void makeFade() {
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);
	
		fadeTransition.play();
		
	}
	
	public void ConfirmAction() {
		if(date.getValue() == null) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Date not specified");
            errorAlert.setContentText("Please enter a valid date");
            errorAlert.showAndWait();
		}
		//need to check if the date is before today. if yes then re-enter
		else {
			LocalDateTime localTime = LocalDateTime.now();
			LocalDate localDay = LocalDate.now();
			DateTimeFormatter curTime = DateTimeFormatter.ofPattern("hh:mm");
			DateTimeFormatter curDay = DateTimeFormatter.ofPattern("MM-dd-yyyy");
//			System.out.println(curTime.format(localTime));
			LocalDate date2 = date.getValue();
			String formattedDate = date2.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
			if(localDay.isAfter(date2)) {
				Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	            errorAlert.setHeaderText("Date Error");
	            errorAlert.setContentText("Date of Appointment can not be before today");
	            errorAlert.showAndWait();
			}
			else {
				Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
				infoAlert.setHeaderText("Date Confirmed");
				infoAlert.setContentText("You will now be taken back to the Booking Page where you can pick your preferred time slot for this day");
				infoAlert.showAndWait();
				makeFadeOutConfirm();
			}
		}
	}
	
	private void makeFadeOutConfirm() {
		// TODO Auto-generated method stub
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.setOnFinished(event -> loadSlotPage());
		fadeTransition.play();
	}

	private void loadSlotPage() {
		// TODO Auto-generated method stub
		try {
			LocalDateTime localTime = LocalDateTime.now();
			LocalDate localDay = LocalDate.now();
			DateTimeFormatter curTime = DateTimeFormatter.ofPattern("hh:mm");
			DateTimeFormatter curDay = DateTimeFormatter.ofPattern("MM-dd-yyyy");
//			System.out.println(curTime.format(localTime));
			LocalDate date2 = date.getValue();
			String formattedDate = date2.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("SlotPage.fxml"));
			Parent secondView = loader.load();
			Scene newScene = new Scene(secondView);
			SlotPageController controller = loader.getController();
			controller.initData(this.email, this.dose, formattedDate, this.vaccine);
			Stage curStage = (Stage) rootPane.getScene().getWindow();
			curStage.setScene(newScene);
			curStage.show();
		} catch(Exception e) {
			e.printStackTrace();
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
}