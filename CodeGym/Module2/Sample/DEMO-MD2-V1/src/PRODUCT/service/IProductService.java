package PRODUCT.service;

import PRODUCT.model.Product;

import java.io.IOException;
import java.util.List;

public interface IProductService {

    List<Product> findAll();

    void save(Product productNew);

    boolean checkId(int idUpdate);
    void writeFile() throws IOException;
    void readFile();
}
