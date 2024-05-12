package PRODUCT.repository;

import PRODUCT.model.Product;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ImlProductRepository implements IProductRepository{
    private static List<Product> productList = new ArrayList<>();
    private static final String FILE_PATH= "D:\\STUDY\\CodeGym\\Module2\\Sample\\DEMO-MD2-V1\\src\\PRODUCT\\data\\data_product.csv";
    static {
        productList.add(new Product(1,"Product A", "Good", 2,"Mới được sản xuất"));
        productList.add(new Product(2,"Product B", "Good", 1,"Mới được sản xuất"));
        productList.add(new Product(3,"Product C", "Bad", 4,"Mới được sản xuất"));
        productList.add(new Product(4,"Product D", "Good", 5,"Mới được sản xuất"));
        productList.add(new Product(5,"Product E", "Bad", 2,"Mới được sản xuất"));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void save(Product productNew){
        if(productNew.getId() <= 0){
            productNew.setId(productList.getLast().getId()+1);
            productList.add(productNew);
        }else {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getId() == productNew.getId()) {
                    productList.set(i, productNew);
                }
            }
        }
    }

    @Override
    public boolean checkId(int idUpdate) {
        for (Product product : productList) {
            if (product.getId() == idUpdate) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void writeFile() throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for(Product product: productList){
            bufferedWriter.write(product.getId() + "," + product.getName() + ","+ product.getStatus() + ","+ product.getTypeCode() + ","+product.getAbout() + "\n");
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    @Override
    public void readFile() {
        try {
            // Đọc file theo đường dẫn
            File file = new File(FILE_PATH);
            // Kiểm tra nếu file không tồn tại thì ném ra ngoại lệ.
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            // Đọc từng dòng của file
            BufferedReader bufferedReader = Files.newBufferedReader(file.toPath());
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();

        } catch (Exception e) {
            // TH file không tồn tại hoặc nội dung file có lỗi thì sẽ hiển thị thông báo lỗi.
            System.err.println("Fie không tồn tại or nội dung có lỗi!");
        }
    }

}
