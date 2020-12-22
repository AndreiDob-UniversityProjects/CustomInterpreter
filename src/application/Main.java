package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("ListView.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ListView.fxml"));
			StackPane root = (StackPane)loader.load();
			
			ListViewController controller= loader.getController();
			controller.setMainStage(primaryStage);
			Scene scene = new Scene(root,800,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			 primaryStage.setTitle("MyInterpreter");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			launch(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
