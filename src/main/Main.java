package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {
    private static Parent root;
    private static Stage palco;

    public static Stage getStage(){ return palco;}

    public static void main(String args[]) {
        try {launch(args); }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO!!!");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        palco = primaryStage;
        root = FXMLLoader.load(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("gui/index.fxml")));
        palco.setScene(new Scene(root));
        palco.show();
    }

    public static void newWindow(Parent janela) {
        try {
            root = janela;
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void setWindow(Parent janela) {
        try {
            root = janela;
            palco.setScene(new Scene(root));
            palco.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void showErrorAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("ERRO");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void showSuccessAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("TUDO CERTO!");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}