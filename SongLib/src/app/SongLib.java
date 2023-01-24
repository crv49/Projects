package app;

//SongLib by Haoran Lyu and Chris Velasquez

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import misc.ListController;

public class SongLib extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();   
	      loader.setLocation(
	         getClass().getResource("/misc/Library.fxml"));
	      BorderPane root = (BorderPane)loader.load();

	      ListController listController = 
	         loader.getController();
	      listController.start(primaryStage);

	      Scene scene = new Scene(root, 450, 500);
	      primaryStage.setTitle("Song Library - Haoran Lyu & Chris Velasquez ");
	      primaryStage.setScene(scene);
	      primaryStage.setResizable(false);
	      primaryStage.show(); 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
