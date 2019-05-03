package application;
	
import database.MySQLDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Stage s;
	@Override
	public void start(Stage primaryStage) 
	{
		
		try {
			MySQLDatabase.getInstance("jdbc:mysql://localhost/ivfinsizesystem", "root", "");
			Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			primaryStage.setTitle("Welcome");
			s = new Stage();
  			Scene scene = new Scene(root, 1920, 990);
  			primaryStage.setScene(scene);
  			primaryStage.show();
			s=primaryStage;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static  Stage Get_Stage()
	{
		return s;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
