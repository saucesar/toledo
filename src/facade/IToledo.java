package facade;

import exceptions.FileNotFoundException;
import exceptions.ProductNotFoundException;
import models.Product;

import java.io.IOException;

public interface IToledo {
    Product search(String code) throws ProductNotFoundException;
    void changeFile(String filePath) throws FileNotFoundException;
    void refreshProducts() throws FileNotFoundException;
    void setProductsFile(String path) throws FileNotFoundException;
    void saveAll() throws IOException;
}
