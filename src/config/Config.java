package config;

import exceptions.ConfigFileNotFoundException;
import models.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Config {
    private String configFilePath;
    private String productFilePath;

    public Config(String configFilePath) throws ConfigFileNotFoundException {
        this.configFilePath = configFilePath;
        this.readConfigs();
    }

    private void readConfigs() throws ConfigFileNotFoundException {
        try {
            BufferedReader file = new BufferedReader(new FileReader(this.configFilePath));
            this.productFilePath = file.readLine();
            file.close();
        } catch (IOException e) {
            throw new ConfigFileNotFoundException();
        }
    }

    public String getProductFilePath() {
        return productFilePath;
    }

    public void setProductFilePath(String productFilePath) {
        this.productFilePath = productFilePath;
    }
}
