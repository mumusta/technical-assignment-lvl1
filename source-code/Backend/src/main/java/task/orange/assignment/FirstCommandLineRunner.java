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
public class FirstCommandLineRunner implements CommandLineRunner{

    @Autowired
    SupplierService supplierService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        /*
        supplierService.createNewSupplier(new Supplier("Ahmed", "alaa", "10166697799", "aa@gm.com", new ArrayList<>()));
        supplierService.createNewSupplier(new Supplier("Eman", "Foad", "23470001399", "bb@gm.com", new ArrayList<>()));
        supplierService.createNewSupplier(new Supplier("lucky", "Hesham", "3557023491119", "cc@gm.com", new ArrayList<>()));
        supplierService.createNewSupplier(new Supplier("Jack", "Ahmed", "4573122349629", "dd@gm.com", new ArrayList<>()));
        supplierService.createNewSupplier(new Supplier("Mostafa", "Karim", "5966922699", "ee@gm.com", new ArrayList<>()));

        productService.createNewProduct(new Product("Dell 5512 Notebook", 
                                                    "Awesome Notebook", 
                                                    "Black", 
                                                    "Notebooks", 
                                                    999.9, 
                                                    new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("Samsung Note 10000", 
                                                    "Awesome Smartphone", 
                                                    "White", 
                                                    "Smartphones", 
                                                    888.8, 
                                                    new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("HP 666 Notebook", 
                                                    "Very Awesome Notebook", 
                                                    "Red", 
                                                    "Notebooks", 
                                                    123.4, 
                                                    new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("HP 6MAX Notebook", 
                                                    "VEEEERY Awesome Notebook",
                                                    "Blue", 
                                                    "Notebooks", 
                                                    4321.0, 
                                                    new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("xdrXC1231",
                                                    "Awesome Stuff", 
                                                    "Red", 
                                                    "Other", 
                                                    0.999, 
                                                    new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("11111",
                "Awesome Stuff",
                "Red",
                "Other",
                0.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("23123",
                "Awesome Stuff",
                "Red",
                "Other",
                0.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("111111111111111",
                "Awesome Stuff",
                "Red",
                "Other",
                0.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("nameOfTheProdyct",
                "Awesome Stuff",
                "Red",
                "Other",
                0.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("XPEN",
                "Awesome Stuff",
                "Red",
                "Other",
                999.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("AAA231",
                "Awesome Stuff",
                "Red",
                "Other",
                12.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("zXC1231",
                "Awesome Stuff",
                "Red",
                "Other",
                0.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("zzXC1231",
                "Awesome Stuff",
                "Red",
                "Other",
                0.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("sssoaC1231",
                "Awesome Stuff",
                "Red",
                "Other",
                0.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("HAHXC1231",
                "Awesome Stuff",
                "Red",
                "Other",
                0.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("Damn son",
                "Awesome Stuff",
                "Red",
                "Other",
                0.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("doja",
                "Awesome Stuff",
                "Red",
                "Other",
                0.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("cat",
                "77 Stuff",
                "uas",
                "uasf",
                777.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("dummy data1",
                "Awesome Stuff",
                "Red",
                "Other",
                2315123.999,
                new ArrayList<>(), new ArrayList<>()));

        productService.createNewProduct(new Product("dojadog",
                "Awesome Stuff",
                "Red",
                "Other",
                129012873.00009,
                new ArrayList<>(), new ArrayList<>()));

        orderService.createNewOrder(new Order(null, null, null, null));
        orderService.createNewOrder(new Order(null, null, null, null));
        orderService.createNewOrder(new Order(null, null, null, null));*/
    }
}
