package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

public class RegisterPageController implements Initializable {
	
	@FXML
	private AnchorPane rootPane;
	
	@FXML
	private Button register;
	
	@FXML
	private Button back;
	
	@FXML
	private TextField firstname;
	
	@FXML
	private TextField lastname;
	
	@FXML
	private TextField email;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private TextField sex;
	
	@FXML
	private TextField occupation;
	
	@FXML
	private TextField education;
	
	@FXML
	private TextField postCode;
	
	@FXML
	private DatePicker dob;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeFade();
		
		dob.setConverter(new StringConverter<LocalDate>() {
		     String pattern = "MM-dd-yyyy";
		     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

		     {
		         dob.setPromptText(pattern.toLowerCase());
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
	
	private void makeFade() {
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);
	
		fadeTransition.play();
		
	}
	
	public void RegisterAction() {
		if(firstname.getText().equals("")) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("First Name left blank");
            errorAlert.setContentText("Please enter all your details to Register");
            errorAlert.showAndWait();
		}
		else if(lastname.getText().equals("")) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Last Name left blank");
            errorAlert.setContentText("Please enter all your details to Register");
            errorAlert.showAndWait();
		}
		else if(dob.getValue() == null) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Date of Birth not specified");
            errorAlert.setContentText("Please enter all your details to Register");
            errorAlert.showAndWait();
		}
		else if(email.getText().equals("")) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Email left blank");
            errorAlert.setContentText("Please enter all your details to Register");
            errorAlert.showAndWait();
		}
		else if(password.getText().equals("")) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Password left blank");
            errorAlert.setContentText("Please enter all your details to Register");
            errorAlert.showAndWait();
		}
		else if(sex.getText().equals("")) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Sex left blank");
            errorAlert.setContentText("Please enter all your details to Register");
            errorAlert.showAndWait();
		}
		else if(!sex.getText().equals("") && !(sex.getText().equals("Male") || sex.getText().equals("Female") || sex.getText().equals("Other"))) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Entered Gender is incorrect");
            errorAlert.setContentText("Please enter either 'Male', 'Female', or 'Other'");
            errorAlert.showAndWait();
		}
		else if(occupation.getText().equals("")) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Occupation left blank");
            errorAlert.setContentText("Please enter all your details to Register");
            errorAlert.showAndWait();
		}
		else if(education.getText().equals("")) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Education left blank");
            errorAlert.setContentText("Please enter all your details to Register");
            errorAlert.showAndWait();
		}
		else if(postCode.getText().equals("")) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Postal Code left blank");
            errorAlert.setContentText("Please enter all your details to Register");
            errorAlert.showAndWait();
		}
		else {
			LocalDateTime localTime = LocalDateTime.now();
			LocalDate localDay = LocalDate.now();
			DateTimeFormatter curTime = DateTimeFormatter.ofPattern("hh:mm");
			DateTimeFormatter curDay = DateTimeFormatter.ofPattern("MM-dd-yyyy");
//			System.out.println(curTime.format(localTime));
			LocalDate date = dob.getValue();
			String formattedDate = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
			if(date.isAfter(localDay)) {
				Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	            errorAlert.setHeaderText("DOB Error");
	            errorAlert.setContentText("Date of Birth can not be after today");
	            errorAlert.showAndWait();
			}
			else {
				MaintainUserLogin register = new MaintainUserLogin(firstname.getText(), lastname.getText(), formattedDate, email.getText(), password.getText(), sex.getText(), occupation.getText(), education.getText(), postCode.getText());
				if(register.duplicate) {
					Alert errorAlert = new Alert(Alert.AlertType.ERROR);
		            errorAlert.setHeaderText("User Exists");
		            errorAlert.setContentText("A user with this email already exists. \n"
		            		+ "Please login if you already have an account or register with a new email");
		            errorAlert.showAndWait();
				}
				else {
					Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
					infoAlert.setHeaderText("Registration Successful");
					infoAlert.setContentText("You will now be taken back to the Login Page where you can Sign In with your registration details");
					infoAlert.showAndWait();
					makeFadeOutRegister();
				}
			}
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
	
	private void makeFadeOutRegister() {
		// TODO Auto-generated method stub
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(500));
		fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.setOnFinished(event -> loadLoginPage());
		fadeTransition.play();
	}

	private void loadLoginPage() {
		// TODO Auto-generated method stub
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
