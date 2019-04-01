package it.polito.tdp.lab04.controller;
import it.polito.tdp.lab04.model.SegreteriaStudentiModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SegreteriaStudenti.fxml"));
			BorderPane root = (BorderPane) loader.load();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			SegreteriaStudentiController controller = (SegreteriaStudentiController)loader.getController();
			SegreteriaStudentiModel model = new SegreteriaStudentiModel();
	
			controller.setModel(model);

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
