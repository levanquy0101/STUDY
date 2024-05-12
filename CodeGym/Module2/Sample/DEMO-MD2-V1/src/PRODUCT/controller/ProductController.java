package PRODUCT.controller;

import PRODUCT.model.Product;
import PRODUCT.service.IProductService;
import PRODUCT.service.ImlProductService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ProductController {

    private static IProductService iProductService = new ImlProductService();
    public static void main(String[] args) throws IOException {
        displayMenu();
    }
    public static void displayMenu() throws IOException {

        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("--------------------------Phần mềm quản lý-------------------------");
            System.out.println("Chọn các chức năng: ");
            System.out.println("1. Thêm mới");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Thoát");

            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProductList();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;

            }
        }while (true);
    }

    private static void updateProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------Cập nhật---------------");
        System.out.println("Nhập id sản phẩm: ");
        int idUpdate = Integer.parseInt(scanner.nextLine());
        boolean findId =  iProductService.checkId(idUpdate);
        if(findId){
            System.out.println("Nhập tên: ");
            String nameUpdate = scanner.nextLine();
            System.out.println("Nhập tình trạng: ");
            String statusUpdate = scanner.nextLine();
            System.out.println("Nhập loại mã: ");
            int typeCodeUpdate = Integer.parseInt(scanner.nextLine());
            System.out.println("Nhập thông tin: ");
            String aboutUpdate = scanner.nextLine();
            Product productUpdate = new Product(idUpdate,nameUpdate,statusUpdate,typeCodeUpdate,aboutUpdate);
            iProductService.save(productUpdate);
        }else {
            System.out.println("Sản phẩm không tồn tại!");
        }
    }

    private static void addProduct() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------Thêm mới-----------------");
//        System.out.println("Nhập id: ");
//        int idNew = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập tên: ");
        String nameNew = scanner.nextLine();
        System.out.println("Nhập tình trạng: ");
        String statusNew = scanner.nextLine();
        System.out.println("Nhập loại mã: ");
        int typeCodeNew = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập thông tin: ");
        String aboutNew = scanner.nextLine();
        Product productNew = new Product(0,nameNew,statusNew,typeCodeNew,aboutNew);

        iProductService.save(productNew);
        iProductService.writeFile();

    }
    private static void displayProductList() throws IOException {
        System.out.println("-----------Danh sách sản phẩm------------");
//        List<Product> productList = iProductService.findAll();
//        for (Product product : productList) {
//            System.out.println(product);
//        }
//        iProductService.writeFile();
        iProductService.readFile();
    }

}
