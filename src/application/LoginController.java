package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController {
	public LoginModel LoginModel = new LoginModel();
			
    @FXML
    private TextField usernameLogin;

    @FXML
    private TextField passwordLogin;
    
    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabelLogin;

    @FXML
    public void login(ActionEvent event) {
    	try {
    		boolean authorized = LoginModel.Login(usernameLogin.getText(), passwordLogin.getText());
    		if(authorized) {
    			Stage stage = (Stage)this.passwordLogin.getScene().getWindow();
				stage.close();
				openStudents(event);
    		}else {
    			errorLabelLogin.setText("Your data is incorrect");
    		}
    	}catch(Exception e) {
    		
    	}
    }
    
    public void openStudents(ActionEvent e) throws IOException {
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(this.getClass().getResource("/inventory/Inventory.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image icon = new Image(getClass().getResourceAsStream("/images/icon.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("Simple inventory");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
    
}
