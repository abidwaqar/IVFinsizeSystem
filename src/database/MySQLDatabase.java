package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLDatabase {
	private Connection dbConn;
	private Statement stmt;
	
	private static MySQLDatabase dbConnection;
	public static MySQLDatabase getInstance(String url,String user,String password)
	{
		if (dbConnection == null)
		{
			dbConnection = new MySQLDatabase(url , user, password);
		}
		return dbConnection;
	}
	
	public static MySQLDatabase getInstance() throws Exception
	{
		if (dbConnection == null)
		{
			throw new Exception();
		}
		return dbConnection;
	}
	
	
	private MySQLDatabase(String url, String user, String password)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbConn = DriverManager.getConnection(url, user, password);
			stmt = dbConn.createStatement();
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ArrayList<String>> getRows(String tableName) throws SQLException
	{
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		String sqlQuery = new String();
		sqlQuery = "SELECT * FROM " + tableName;
		ResultSet rsRows = stmt.executeQuery(sqlQuery);
		while (rsRows.next()){
			rows.add(rowToColumns(rsRows));
		}
		rsRows.close();
		return rows;
	}
	
	public ArrayList<ArrayList<String>> getIndexValue(String tableName, String colName, String value) throws SQLException
	{
		String sqlQuery = new String();
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		sqlQuery = "Select * FROM " + tableName + " Where " + colName + " = '" + value + "'";
		ResultSet rsRows = stmt.executeQuery(sqlQuery);
		while (rsRows.next()){
			rows.add(rowToColumns(rsRows));
		}
		rsRows.close();
		return rows;
	}
	
	public int removeRow(String tableName, String colName, String value) throws SQLException
	{
		String sqlQuery = new String();
		sqlQuery = "Delete FROM " + tableName + " Where " + colName + " = '" + value + "'";
		return stmt.executeUpdate(sqlQuery);
	}
	
	public int addEmployee(String name, String password, String phone, String address) throws SQLException
	{
		String sqlQuery = new String();
		sqlQuery = "INSERT INTO `employee`(`ID`, `Name`, `Password`, `Phone`, `Address`) "
				+ "VALUES (NULL, '" + name + "', '" + password + "', '" + phone + "', '" + address + "' )";               
		return stmt.executeUpdate(sqlQuery);
	}
	
	public int updateEmployee(String ID, String name, String password, String phone, String address) throws SQLException
	{
		String sqlQuery = new String();
		sqlQuery = "INSERT INTO `employee`(`ID`, `Name`, `Password`, `Phone`, `Address`) "
				+ "VALUES (NULL, '" + name + "', '" + password + "', '" + phone + "', '" + address + "' )";     
		sqlQuery = "UPDATE `employee` SET `Name` = '" + name + "',"
				+ " `Password` = '" + password + "', `Phone` = '" + phone +"', `Address` = '" + address +"' WHERE `employee`.`ID` = "+ ID +";";
		return stmt.executeUpdate(sqlQuery);
	}
	
	
	public int addERobot(String code, String name, String desc, String price, String qty) throws SQLException
	{
		String sqlQuery = new String();
		sqlQuery = "INSERT INTO `e_robot` (`Code`, `Name`, `Description`, `Price`, `Qty`) VALUES ('" + code + "', '" + name + "', '" + desc + "', '" + price + "', '" + qty + "')";
//		System.out.println(sqlQuery);
		return stmt.executeUpdate(sqlQuery);
	}
	
	public int addSale(String Ecode, String Edesc, String EName, int price, int qty, int subtotal, int payment, String sale_code) throws SQLException
	{
		String sqlQuery = new String();
		sqlQuery = "INSERT INTO `sale`(`ERobot_Code`, `Description`, `Robot_Name`, `PricePerItem`, `ERobot_Qty`, `Sub_Total`, `Payment`, `Sale_Code`) "
				+ "VALUES ('"+ Ecode +"', '"+ Edesc +"','"+ EName +"','"+ price +"','"+ qty +"','"+ subtotal 
				+"','"+ payment +"','"+sale_code+"');";
		System.out.println(sqlQuery);
		return stmt.executeUpdate(sqlQuery);
	}
	
	public int updateERobot(String code, String name, String desc, String price, String qty) throws SQLException
	{
		String sqlQuery = new String();
		sqlQuery = "UPDATE `e_robot` SET `Code`= '" + code + "',`Name`= '" + name 
				+"' ,`Description`= '" + desc + "',`Price`= '" + price + "',`Qty`= '" + qty + "' WHERE `Code`='" + code +"'";
//		System.out.println(sqlQuery);
		return stmt.executeUpdate(sqlQuery);
	}
	
	public int executeUpdate(String query)
	{
		try{
			return stmt.executeUpdate(query);
		}
		catch (SQLException ex){
			return -1;
		}
	}
	
	public int setSaleLineItemOnRobotCode(String sale_code, String ERobot_Code, String qty) throws SQLException
	{
		String sqlQuery = new String();
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		sqlQuery = "UPDATE `sale` SET `ERobot_Qty`=" + qty + " WHERE `Sale_Code` = " + sale_code + " AND `ERobot_Code`= " + ERobot_Code+ ";";
		return stmt.executeUpdate(sqlQuery);
	}

//	public 
//	{
//		String sqlQuery = new String();
//		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
//		sqlQuery = "UPDATE `sale` SET `ERobot_Qty`=" + qty + " WHERE `Sale_Code` = " + sale_code + " AND `ERobot_Code`= " + ERobot_Code+ ";";
//		return stmt.executeUpdate(sqlQuery);
//		while (rsRows.next()){
//			rows.add(rowToColumns(rsRows));
//		}
//		rsRows.close();
//		return rows;
//	}
	
	
//	public void editSaleLineItem(String sale_code)
//	{
//		try {
//			String sqlQuery = "SELECT * From SALE WHERE `Sale_Code` = '" + sale_code + "';";
//			ResultSet rs = stmt.executeQuery(sqlQuery);
//			rs.getInt(1);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	//helper
	private ArrayList<String> rowToColumns(ResultSet row) throws SQLException
	{
		int columnCount = row.getMetaData().getColumnCount();
		ArrayList<String> rowData = new ArrayList<String>();
		for (int j = 1; j < columnCount+1; ++j){
			rowData.add(row.getString(j));
		}
		return rowData;
	}
}
