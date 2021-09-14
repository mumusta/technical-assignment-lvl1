package task.orange.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import task.orange.assignment.controller.ProductController;
import task.orange.assignment.error.ConflictException;
import task.orange.assignment.error.NotFoundException;
import task.orange.assignment.model.Product;
import task.orange.assignment.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepository productRepository;



    public Product createNewProduct(Product product){

        logger.info("ProductService.createNewProduct => is called");
        logger.debug(String.format("ProductService.createNewProduct(%s)", product.toString()));

        product = copyImportantMembers(product);
        
        if (isProductExistsByName(product.getName())){

            logger.error("Another record with the same name exists");
            throw new ConflictException("Another record with the same name exists");
        }

        else {

            return productRepository.save(product);
        }
    }



    public Product updateProductById(Long productId, Product product){

        logger.info("ProductService.updateProductById => is called");
        logger.debug(String.format("ProductService.updateProductById(%s, %s)", productId.toString(), product.toString()));

        product = copyImportantMembers(product);

        if (isProductExistsById(productId)){

            Product curr = productRepository.findById(productId).get();

            if (!curr.getName().equals(product.getName()) &&
                 isProductExistsByName(product.getName())){

                logger.error("Another record with the same name exists");
                throw new ConflictException("Another record with the same name exists");
            }
            
            curr.setName(product.getName());
            curr.setDescription(product.getDescription());
            curr.setColor(product.getColor());
            curr.setCategory(product.getCategory());
            curr.setPrice(product.getPrice());

            return productRepository.save(curr);
        }

        else {

            logger.error(String.format("No Record with id %s was found in our database", productId));
            throw new NotFoundException(String.format("No Record with id %s was found in our database", productId));
        }
    }



    public Product getProductById(Long productId){

        logger.info("ProductService.getProductById => is called");
        logger.debug(String.format("ProductService.getProductById(%s)", Long.toString(productId)));

        if (isProductExistsById(productId)){

            return productRepository.findById(productId).get();
        }

        else {

            logger.error(String.format("No Record with id %s was found in our database", productId));
            throw new NotFoundException(String.format("No Record with id %s was found in our database", productId));
        }
    }



    public Product getProductByName(String name){

        logger.info("ProductService.getProductByName => is called");
        logger.debug(String.format("ProductService.getProductByName(%s)", name));

        if (isProductExistsByName(name)){

            return productRepository.findByName(name);
        }

        else {

            logger.error(String.format("No Record with name %s was found in our database", name));
            throw new NotFoundException(String.format("No Record with name %s was found in our database", name));
        }
    }



    public Page< Product > getAllProducts(int pageNumber, int pageSize){

        logger.info("ProductService.getAllProducts => is called");
        logger.debug(String.format("ProductService.getAllProducts(%s, %s)", Integer.toString(pageNumber), Integer.toString(pageSize)));

        return productRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "productId"));
    }



    public boolean deleteProductById(Long productId){

        logger.info("ProductService.deleteProductById => is called");
        logger.debug(String.format("ProductService.deleteProductById(%s)", Long.toString(productId)));

        if (isProductExistsById(productId)){

            try {
                
                productRepository.deleteById(productId);
                return true;

            } catch (Exception e) {

                logger.error(e.getMessage());
                return false; 
            }
        }

        else {

            logger.error(String.format("No Record with id %s was found in our database", productId));
            throw new NotFoundException(String.format("No Record with id %s was found in our database", productId));
        }
    }



    public Product copyImportantMembers(Product product){

        return new Product(product.getName(),
                           product.getDescription(),
                           product.getColor(),
                           product.getCategory(),
                           product.getPrice(),
                           new ArrayList<>(),
                           new ArrayList<>());
    }

    public boolean isProductExistsById(Long productId){

        return productRepository.existsById(productId);
    }

    public boolean isProductExistsByName(String name){

        return productRepository.existsByName(name);
    }
}
