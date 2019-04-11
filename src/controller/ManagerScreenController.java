package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.IOException;
import java.sql.ResultSet;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.layout.BorderPane;

public class ManagerScreenController {
	@FXML
	private BorderPane welcomeBorder;
	@FXML
	private Button addemployee;
	@FXML
	private Button viewemployee;
	@FXML
	private Button editemployee;
	@FXML
	private Button salereport;
	@FXML
	private Button deleteemployee;
	@FXML
	private Button logout;
	private ObservableList<ObservableList> data;
	@FXML 
	private TableView tableview;
	@FXML
	private Button genreport;
	
	
	
	public void get_genreport(ActionEvent e) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/view/SaleReport.fxml"));
		Scene scene = new Scene(root, 1000, 550);
		Main.Get_Stage().setScene(scene);
		Main.Get_Stage().show();
	}
	// Event Listener on Button[#addemployee].onAction
	@FXML
	public void registerEmployee(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AddEmployee.fxml"));
		
		Scene scene = new Scene(root, 800, 500);
		Main.Get_Stage().setScene(scene);
		Main.Get_Stage().show();
	}
	// Event Listener on Button[#viewemployee].onAction
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void ViewEmployee(ActionEvent event) throws SQLException, IOException 
	{
		Parent root = FXMLLoader.load(getClass().getResource("/view/ViewEmp.fxml"));
		Scene scene = new Scene(root, 800, 500);
		Main.Get_Stage().setScene(scene);
		Main.Get_Stage().show();	
	}
	// Event Listener on Button[#editemployee].onAction
	@FXML
	public void EditEmployee(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/UpdateEmployee.fxml"));
		Scene scene = new Scene(root, 800, 500);
		Main.Get_Stage().setScene(scene);
	
	}
	// Event Listener on Button[#salereport].onAction
	//@FXML
	//public void GenerateReport(ActionEvent event) {
		// TODO Autogenerated
	//}
	
	// Event Listener on Button[#deleteemployee].onAction
	@FXML
	public void DeleteEmployee(ActionEvent event) throws IOException
	{
	Parent root = FXMLLoader.load(getClass().getResource("/view/DeleteEmp.fxml"));
	Scene scene = new Scene(root, 800, 500);
	Main.Get_Stage().setScene(scene);// TODO Autogenerated
	}
	// Event Listener on Button[#logout].onAction
	@FXML
	public void Logou(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene scene = new Scene(root, 800, 500);
		Main.Get_Stage().setScene(scene);
		Main.Get_Stage().show();
		// TODO Autogenerated
	}
}
