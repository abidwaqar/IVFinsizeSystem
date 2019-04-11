package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class PaymentController implements Initializable {
	@FXML
	private TextField total;
	@FXML
	private TextField netpay;
	@FXML
	private TextField returns;
	@FXML
	private Button payment;
	@FXML
	private TextField discount;
	@FXML
	private Label lbl;
	@FXML
	private Button cancel;
	static public String tot,np,rt;
	// Event Listener on Button[#payment].onAction
	@FXML
	public void makepayment(ActionEvent event) throws IOException 
	{
		System.out.println("Total"+total.getText());
		System.out.println("NetPay"+netpay.getText());
		System.out.println("Discount"+discount.getText());
		int r = Integer.parseInt(netpay.getText())-Integer.parseInt(total.getText())+Integer.parseInt(discount.getText());
		System.out.println("SaleNO"+ProcessSaleController.SaleNo);		
		System.out.println("returns" +r);
		tot = total.getText();
		np= netpay.getText();
		rt = Integer.toString(r);
		returns.setText(Integer.toString(r));
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Payment Details");
		alert.setHeaderText(null);
		alert.setContentText("Payment Successfully Done!!");
		alert.showAndWait();
		//Funciton to set Total crossponding to Sale 
		//Query to insert the Payment in crossponding Sale using Slae NO
		
	}
	
	
	// Event Listener on Button[#cancel].onAction
	@FXML
	public void handleCancel(ActionEvent event) throws IOException 
	{
		
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/ProcessSale.fxml"));
		Scene scene = new Scene(root, 1000, 550);
		Main.Get_Stage().setScene(scene);
		Main.Get_Stage().show();
	}
	public boolean validateInput() 
	{

        String errorMessage = "";
      
        if (total.getText() == null || total.getText().length()<=0 || netpay.getText() == null || netpay.getText().length()<=0) {
            errorMessage += "Please enter Credentials!\n";
            lbl.setText(errorMessage);
            return false;
        
        }

        else
        {
            return true;
        }     }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
}


