package facade;

import business.ProductBusiness;
import exceptions.ConfigFileNotFoundException;
import exceptions.FileNotFoundException;
import exceptions.ProductNotFoundException;
import models.Product;

import java.io.IOException;

public class Facade implements IToledo{
    private ProductBusiness products;
    private static Facade facade;

    private Facade() throws FileNotFoundException, ConfigFileNotFoundException {
        this.products = new ProductBusiness();
    }

    public static Facade getInstance() throws FileNotFoundException, ConfigFileNotFoundException {
        if(facade == null) {
            facade = new Facade();
        }
        return facade;
    }

    @Override
    public Product search(String code) throws ProductNotFoundException {
        return this.products.search(code);
    }

    @Override
    public void changeFile(String filePath) throws FileNotFoundException {
        this.products.changeFile(filePath);
    }

    @Override
    public void refreshProducts() throws FileNotFoundException {
        this.products.refreshProducts();
    }

    @Override
    public void setProductsFile(String path) throws FileNotFoundException {
        this.products.changeFile(path);
    }

    @Override
    public void saveAll() throws IOException {
        this.products.saveAll();
    }
}
