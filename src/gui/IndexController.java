package gui;

import exceptions.ConfigFileNotFoundException;
import exceptions.FileNotFoundException;
import facade.Facade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import main.Main;
import models.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable{
    @FXML
    private TextField search;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private TextField daysOfValidity;
    @FXML
    private CheckBox withDate;

    private Facade facade;
    private FileChooser fileChooser;
    private Product product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.fileChooser = new FileChooser();
            this.facade = Facade.getInstance();
        } catch (ConfigFileNotFoundException e) {
            try {

                this.fileChooser.setTitle("Selecione o arquivo de itens");
                File file = this.fileChooser.showOpenDialog(Main.getStage());
                if(file != null){
                    FileWriter fileWriter = new FileWriter(".config.txt");
                    PrintWriter writer = new PrintWriter(fileWriter);

                    writer.print(file.getAbsoluteFile());
                    fileWriter.close();
                    this.facade = Facade.getInstance();
                    this.facade.changeFile(file.getAbsolutePath());
                } else {
                    throw new ConfigFileNotFoundException();
                }
            } catch (Exception ex) {
                Main.showErrorAlert(ex.getMessage());
            }
        } catch (Exception e){
            Main.showErrorAlert(e.getMessage());
        }
    }

    public void buscar(){
        try {
            this.product = this.facade.search(this.search.getText());
            this.description.setText(this.product.getDescription());
            this.price.setText(String.valueOf(this.product.getPrice()));
            this.daysOfValidity.setText(String.valueOf(this.product.getDaysOfValidity()));
            this.withDate.setSelected(this.product.isWithDate());

        }
        catch (Exception e) {
            this.reset();
            Main.showErrorAlert(e.getMessage());
        }
    }

    public void save() {
        try {
            this.product.setDescription(this.description.getText());
            this.product.setCode(this.search.getText());
            this.product.setDaysOfValidity(Integer.parseInt(this.daysOfValidity.getText()));
            this.product.setWithDate(this.withDate.isSelected());
            this.getPrice();
            this.reset();

            this.facade.saveAll();

            Main.showSuccessAlert("As alterações foram salvas!");
        }  catch (NullPointerException ne){
            Main.showErrorAlert("Nenhum produto selecionado!");
        }  catch (Exception e) {
            Main.showErrorAlert(e.getMessage());
        }
    }

    private void reset(){
        this.product = null;
        this.search.setText("");
        this.description.setText("");
        this.price.setText("");
        this.daysOfValidity.setText("");
        this.withDate.setSelected(false);
    }

    private void getPrice(){
        try{
            this.product.setPrice(Double.parseDouble(this.price.getText()));
        } catch (NumberFormatException ne){
            this.product.setPrice(Double.parseDouble(this.price.getText().replace(',', '.')));
        } catch (Exception e) {
            Main.showErrorAlert(e.getMessage());
        }
    }
}
