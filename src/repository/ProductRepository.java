package repository;

import models.Product;

import java.io.*;
import java.util.ArrayList;

public class ProductRepository {
    private String filePath;
    ArrayList<Product> products;

    public ProductRepository(String filePath) throws IOException {
        this.filePath = filePath;
        this.products = new ArrayList<>();
        this.readFile();
    }

    public Product search(String code) {
        for(Product product:this.products){
            if(product.getCode().equals(code)){
                return product;
            }
        }

        return null;
    }

    private void readFile() throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(this.filePath));
        String line = file.readLine();

        while (line != null) {
            Product product = new Product(line);
            this.products.add(product);
            line = file.readLine();
        }

        file.close();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) throws IOException {
        this.filePath = filePath;
        this.readFile();
    }

    public void refresh() throws IOException {
        this.readFile();
    }

    public void saveAll() throws IOException {
        FileWriter file = new FileWriter(this.filePath);
        PrintWriter writer = new PrintWriter(file);

        for (Product product:this.products){
            writer.println(product.getFileLine());
        }
        file.close();
    }
}
