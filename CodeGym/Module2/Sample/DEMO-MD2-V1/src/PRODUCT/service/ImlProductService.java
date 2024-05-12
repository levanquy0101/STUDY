package PRODUCT.service;

import PRODUCT.model.Product;
import PRODUCT.repository.IProductRepository;
import PRODUCT.repository.ImlProductRepository;

import java.io.IOException;
import java.util.List;

public class ImlProductService implements IProductService{
    private static IProductRepository iProductRepository = new ImlProductRepository();

    @Override
    public List<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public void save(Product productNew) {
        iProductRepository.save(productNew);
    }

    @Override
    public boolean checkId(int idUpdate) {
        return iProductRepository.checkId(idUpdate);
    }

    @Override
    public void writeFile() throws IOException {
        iProductRepository.writeFile();
    }

    @Override
    public void readFile(){
        iProductRepository.readFile();
    }


}
