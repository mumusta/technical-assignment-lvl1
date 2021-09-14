package task.orange.assignment;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import task.orange.assignment.model.Order;
import task.orange.assignment.model.Product;
import task.orange.assignment.model.Supplier;
import task.orange.assignment.service.OrderService;
import task.orange.assignment.service.ProductService;
import task.orange.assignment.service.SupplierService;

@Component
public class CMDLineRunner implements CommandLineRunner{

    @Autowired
    SupplierService supplierService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Override
    public void run(String... args) throws Exception {

        try {
            supplierService.createNewSupplier(new Supplier("Ahmed", "alaa", "10166697799", "aa@gm.com", new ArrayList<>()));
        }
        catch (Exception e){
            System.out.println(e);
        }
        try {
            supplierService.createNewSupplier(new Supplier("Eman", "Foad", "23470001399", "bb@gm.com", new ArrayList<>()));
        }
        catch (Exception e){
            System.out.println(e);
        }
        try {
            supplierService.createNewSupplier(new Supplier("lucky", "Hesham", "3557023491119", "cc@gm.com", new ArrayList<>()));
        }
        catch (Exception e){
            System.out.println(e);
        }
        try {
            supplierService.createNewSupplier(new Supplier("Jack", "Ahmed", "4573122349629", "dd@gm.com", new ArrayList<>()));
        }
        catch (Exception e){
            System.out.println(e);
        }
        try {
            supplierService.createNewSupplier(new Supplier("Mostafa", "Karim", "5966922699", "ee@gm.com", new ArrayList<>()));
        }
        catch (Exception e){
            System.out.println(e);
        }

        try {
            productService.createNewProduct(new Product("Dell 5512 Notebook",
                                                    "Awesome Notebook",
                                                    "Black",
                                                    "Notebooks",
                                                    2.0,
                                                    new ArrayList<>(), new ArrayList<>()));
        }
        catch (Exception e){
            System.out.println(e);
        }

        try {
            productService.createNewProduct(new Product("Samsung Note 10000",
                                                    "Awesome Smartphone",
                                                    "White",
                                                    "Smartphones",
                                                    3.0,
                                                    new ArrayList<>(), new ArrayList<>()));
        }
        catch (Exception e){
            System.out.println(e);
        }

        try {
            productService.createNewProduct(new Product("HP 666 Notebook",
                                                    "Very Awesome Notebook",
                                                    "Red",
                                                    "Notebooks",
                                                    4.0,
                                                    new ArrayList<>(), new ArrayList<>()));
        }
        catch (Exception e){
            System.out.println(e);
        }

        try {
            productService.createNewProduct(new Product("HP 6MAX Notebook",
                                                    "VEEEERY Awesome Notebook",
                                                    "Blue",
                                                    "Notebooks",
                                                    5.0,
                                                    new ArrayList<>(), new ArrayList<>()));
        }
        catch (Exception e){
            System.out.println(e);
        }

        try {
            orderService.createNewOrder(new Order(null, null, null, null));
        }
        catch (Exception e){
            System.out.println(e);
        }
        try {
            orderService.createNewOrder(new Order(null, null, null, null));
        }
        catch (Exception e){
            System.out.println(e);
        }
        try {
            orderService.createNewOrder(new Order(null, null, null, null));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
