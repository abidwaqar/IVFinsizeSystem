package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import database.MySQLDatabase;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    @FXML
    private TextField usernameField, passwordField;
    @FXML
	private MenuButton Designation;
	
	@FXML
	private MenuItem Manager, InventoryManager, Cashier;
	@FXML
    private Label errorLabel;
    
	MySQLDatabase DB;
    

    public void initialize(URL url, ResourceBundle rb)
    {
    	
		try {
			DB = MySQLDatabase.getInstance();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Designation.setText("Designation");
    	Manager.setOnAction(new EventHandler<ActionEvent>(){
	          public void handle(ActionEvent aE) {
	      			Designation.setText("Manager");
	        }
	      });
		
		InventoryManager.setOnAction(new EventHandler<ActionEvent>(){
	          public void handle(ActionEvent aE) {
	        	  Designation.setText("Inventory Manager");
	        }
	      });
		
		Cashier.setOnAction(new EventHandler<ActionEvent>(){
	          public void handle(ActionEvent aE) {
	        	  Designation.setText("Cashier");
	        }
	      });
    	
    	 usernameField.setOnKeyPressed((KeyEvent ke) -> {
             if (ke.getCode().equals(KeyCode.ENTER)) {
                 try 
                 {
                     authenticate(ke);
                 } catch (Exception ex) {
                     System.out.println(ex.getMessage());
                 }
             }
         });

         passwordField.setOnKeyPressed((KeyEvent ke) -> {
             if (ke.getCode().equals(KeyCode.ENTER)) {
                 try {
                     authenticate(ke);
                 } catch (Exception ex) {
                     System.out.println(ex.getMessage());
                 }
             }
         });
    }

   

    @FXML
    public void loginAction(ActionEvent event) throws Exception {

        authenticate(event);
    }
    
    @FXML
    public void customerScreen(ActionEvent event) throws IOException
    {
    	Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
		Scene scene = new Scene(root, 1920, 990);
		Main.Get_Stage().setScene(scene);
		Main.Get_Stage().show();
    }

    private void authenticate(Event event) throws Exception {
        if (validateInput())
        {
            	String user=usernameField.getText();
            	String pass = passwordField.getText();
            	//System.out.println(user);
            	System.out.println(pass);
            	System.out.println(Designation.getText());
            	
            	ArrayList<ArrayList<String>> id = DB.getIndexValue("employee", "Name", user);
            	
            	if(id.size()>0 && id.get(0).size()>0)
            	{
            		if(user.equals(id.get(0).get(1)) && pass.equals(id.get(0).get(2)))
            		{
            			if(Designation.getText().equals("Manager")  && (id.get(0).get(5).equals("M")))
            			{
            				System.out.println("Authenticate");
                			Parent root = FXMLLoader.load(getClass().getResource("/view/ManagerScreen.fxml"));
                			Scene scene = new Scene(root, 1920, 990);
                			Main.Get_Stage().setScene(scene);
                			Main.Get_Stage().show();
            			}
            			if(Designation.getText().equals("Inventory Manager") && (id.get(0).get(5).equals("IM")))
            			{
            				System.out.println("Authenticate");
                			Parent root = FXMLLoader.load(getClass().getResource("/view/InventoryManagerScreen.fxml"));
                			Scene scene = new Scene(root, 1920, 990);
                			Main.Get_Stage().setScene(scene);
                			Main.Get_Stage().show();
            			}
            			if(Designation.getText().equals("Cashier") && (id.get(0).get(5).equals("C")))
            			{
            				System.out.println("Authenticate");
                			Parent root = FXMLLoader.load(getClass().getResource("/view/CashierScreen.fxml"));
                			Scene scene = new Scene(root, 1920, 990);
                			Main.Get_Stage().setScene(scene);
                			Main.Get_Stage().setTitle("Process Sale");
                			Main.Get_Stage().show();
            			}
            			if(Designation.getText().equals("Designation"))
            			{
            				Alert alert = new Alert(AlertType.INFORMATION);
            				alert.setTitle("Desigination Missing");
            				alert.setHeaderText(null);
            				alert.setContentText("Enter Your Desigination");
            				alert.showAndWait();
            			}
            			
            			
            			
            		}
            	}
            	
            	else
            	{
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("Invalid UserName");
            		alert.setHeaderText(null);
            		alert.setContentText("Uername is Not Found! Please try again");
            		alert.showAndWait();
            	}
            	
         }
    }

    

    private void resetFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    @FXML
    public void cancelAction(ActionEvent event) {
        resetFields();
        Designation.setText("Designation");
    }

    @FXML
    public void closeAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void minusAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public boolean validateInput() {

        String errorMessage = "";

        if (usernameField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Please enter Credentials!\n";
        }

        if (errorMessage.length() == 0) 
        {
            return true;
        } else 
        {
            errorLabel.setText(errorMessage);
            return false;
        }
    }
    
    

 
		
	}
