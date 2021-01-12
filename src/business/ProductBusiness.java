package business;

import config.Config;
import exceptions.ConfigFileNotFoundException;
import exceptions.FileNotFoundException;
import exceptions.ProductNotFoundException;
import models.Product;
import repository.ProductRepository;
import java.io.IOException;

public class ProductBusiness {
    private ProductRepository products;
    private Config config;

    public ProductBusiness() throws FileNotFoundException, ConfigFileNotFoundException {
        try {
            this.config = new Config(".config.txt");
            this.products = new ProductRepository(this.config.getProductFilePath());
        } catch (IOException e) {
            throw  new FileNotFoundException(this.config.getProductFilePath());
        }
    }

    public Product search(String code) throws ProductNotFoundException {
        Product product = this.products.search(code);

        if(product == null) {
            throw new ProductNotFoundException();
        }

        return product;
    }

    public void changeFile(String filePath) throws FileNotFoundException{
        try {
            this.products.setFilePath(filePath);
        } catch (IOException e) {
            throw new FileNotFoundException(this.products.getFilePath());
        }
    }

    public void refreshProducts() throws FileNotFoundException{
        try {
            this.products.refresh();
        } catch (IOException e) {
            throw new FileNotFoundException(this.products.getFilePath());
        }
    }

    public void saveAll() throws IOException {
        this.products.saveAll();
    }
}
