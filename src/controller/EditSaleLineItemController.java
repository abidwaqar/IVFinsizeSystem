package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Main;
import database.MySQLDatabase;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

public class EditSaleLineItemController {
	@FXML
	private TextField CodeFeild;
	@FXML
	private TextArea DescriArea;
	@FXML
	private TextField nameField;
	@FXML
	private TextField QTYField;
	@FXML
	private TextField priceField;
	@FXML
	private Button find;
	@FXML
	private Label lbl;
	@FXML
	private Button Back;
	@FXML
	private Button cancel;
	
	ProcessSaleController pt;
	// Event Listener on Button[#find].onAction
	@FXML
	public void FindInfo(ActionEvent event) throws Exception
	{
		
		if(validateInput())
		{
			String d = CodeFeild.getText();
			MySQLDatabase DB = MySQLDatabase.getInstance();
			
			ArrayList<String> data = DB.getSaleLineItemInfo(String.valueOf(DB.getCurrentSale()), CodeFeild.getText());
			
			
			if(data.size()>0 && data.size()>0)
			{
				lbl.setText("");
				System.out.println(data.get(0));
				System.out.println(data.get(1));
				System.out.println(data.get(2));
				System.out.println(data.get(3));
				nameField.setText(data.get(2));
				priceField.setText(data.get(4));
				DescriArea.setText(data.get(3));
				QTYField.setText(data.get(5));
			}
			else
			{
				Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("InvalCode E Robot Code");
        		alert.setHeaderText(null);
        		alert.setContentText("No Information Found! Please Enter Valid Robot Code");
        		alert.showAndWait();
        		nameField.clear();
				QTYField.clear();
				priceField.clear();
				DescriArea.clear();
 
        	}
		}
		
	}
	// Event Listener on Button[#UpdateButton].onAction
	// Event Listener on Button[#Back].onAction
	@FXML
	public void handleCancel(ActionEvent event) throws NumberFormatException, SQLException, Exception 
	{
		if(validateInput())
		{
		System.out.println("TableInfo");
		System.out.println("Sale No"+ pt.SaleNo);
		System.out.println("ERobot Name"+ nameField.getText());
		System.out.println("Description"+ DescriArea.getText());
		System.out.println("Price"+ priceField.getText());
		if(QTYField.getText().length()<=0 || QTYField.getText()=="")
		{
			lbl.setText("Please Enter required quantity");
		}
		else
		{
			
		lbl.setText("");
		System.out.println("QTY"+ QTYField.getText());	
		int sub_total = Integer.parseInt(QTYField.getText())*Integer.parseInt(priceField.getText());
		MySQLDatabase DB = MySQLDatabase.getInstance();
		DB.updateSaleLineItem(String.valueOf(DB.getCurrentSale()), CodeFeild.getText(), QTYField.getText(), String.valueOf(sub_total));
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/ProcessSale.fxml"));
		Scene scene = new Scene(root, 1920, 990);
		Main.Get_Stage().setScene(scene);
		Main.Get_Stage().show();
		}
		}
	//	/else
	//	{
	//		Alert alert = new Alert(AlertType.INFORMATION);
	//		alert.setTitle("Sale Line Item Not Added");
	//		alert.setHeaderText(null);
	//		alert.setContentText("Please Try Again");
	//		alert.showAndWait();
	//	}
	}
	
	@FXML
	public void handleback(ActionEvent event) throws IOException 
	{
		
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/ProcessSale.fxml"));
		Scene scene = new Scene(root, 1920, 990);
		Main.Get_Stage().setScene(scene);
		Main.Get_Stage().show();
	}
	public boolean validateInput() 
	{

        String errorMessage = "";
      
        if (CodeFeild.getText() == null || CodeFeild.getText().length()<=0) {
            errorMessage += "Please enter Credentials!\n";
            lbl.setText(errorMessage);
            nameField.clear();
			QTYField.clear();
			priceField.clear();
			DescriArea.clear();
		
            return false;
        
        }

        else
        {
            return true;
        }     }
}
