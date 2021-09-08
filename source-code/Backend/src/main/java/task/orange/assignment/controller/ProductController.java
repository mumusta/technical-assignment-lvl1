package task.orange.assignment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import task.orange.assignment.error.NotFoundException;
import task.orange.assignment.model.Product;
import task.orange.assignment.model.Supplier;
import task.orange.assignment.model.SupplierHasProduct;
import task.orange.assignment.service.ProductService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     *
     * @param product
     * @return
     */
    @PostMapping(value = { "" })
    @ApiOperation(
            value = "Create new Product",
            notes = "Add a new Product to the database, by providing a single Product in the body of the request.",
            response = Product.class
    )
    public ResponseEntity< Product > createNewProduct(
            @ApiParam(value = "The Product you need to add (you don't need to provide a productID).", required = true)
            @Valid @RequestBody(required = true) Product product
    ){

        logger.info("ProductController.createNewProduct => is called");
        logger.debug(String.format("ProductController.createNewProduct(%s)", product.toString()));

        Product result = productService.createNewProduct(product);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }



    /**
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping(value = { "" })
    @ApiOperation(
            value = "Get all products in pages",
            notes = "Retrieve all products from the database in pages, and providing page number and page size is optional.",
            response = Page.class
    )
    public ResponseEntity < Page < Product > > getAllProducts(
            @ApiParam(value = "Page - zero-based page index, must not be negative.", required = false)
            @RequestParam(required = false) Optional <Integer> pageNumber,
            @ApiParam(value = "Size – the size of the page to be returned, must be greater than 0.", required = false)
            @RequestParam(required = false) Optional <Integer> pageSize
    ){

        logger.info("ProductController.getAllProducts => is called");
        logger.debug(String.format("ProductController.getAllProducts(%s, %s)", pageNumber.toString(), pageSize.toString()));

        if ((pageNumber.isPresent() && pageNumber.get() < 0) ||
                (pageSize.isPresent() && pageSize.get() < 1)){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Page< Product > result = productService.getAllProducts(pageNumber.orElse(0), pageSize.orElse(20));

        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = { "/{id}" })
    @ApiOperation(
            value = "Get product by id",
            notes = "Retrieve a single product from the database by id.",
            response = Product.class
    )
    public ResponseEntity < Product > getProductById(
            @ApiParam(value = "The id of the product you need.", required = true)
            @PathVariable(required = true) Long id
    ){

        logger.info("ProductController.getProductById => is called");
        logger.debug(String.format("ProductController.getProductById(%s)", Long.toString(id)));

        Product result = productService.getProductById(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = { "/{id}/suppliers" })
    @ApiOperation(
            value = "Get all suppliers of a product by id",
            notes = "Retrieve all the suppliers of a single product from the database by id.",
            response = List.class
    )
    public ResponseEntity < List < Supplier > > getAllSuppliersOfProductById(
            @ApiParam(value = "The id of the product.", required = true)
            @PathVariable(required = true) Long id
    ){

        logger.info("ProductController.getAllSuppliersOfProductById => is called");
        logger.debug(String.format("ProductController.getAllSuppliersOfProductById(%s)", Long.toString(id)));

        Product result = productService.getProductById(id);

        List < Supplier > ret = new ArrayList<>();

        for (SupplierHasProduct x: result.getSuppliers())
            ret.add(x.getSupplier());

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }


    /**
     *
     * @param id
     * @param product
     * @return
     */
    @PutMapping(value = { "/{id}" })
    @ApiOperation(
            value = "Update a product by id",
            notes = "Update an existing Product in the database by id, and by providing a single Product in the body of the request.",
            response = Product.class
    )
    public ResponseEntity< Product > updateProductById(
            @ApiParam(value = "The id of the product you need to update.", required = true)
            @PathVariable(required = true) Long id,
            @ApiParam(value = "The Product you need to add (you don't need to provide a productID)", required = true)
            @Valid @RequestBody(required = true) Product product
    ) {

        logger.info("ProductController.updateProductById => is called");
        logger.debug(String.format("ProductController.updateProductById(%s, %s)", id.toString(), product.toString()));

        Product result = productService.updateProductById(id, product);

        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    /**
     *
     * @param id xx
     * @return xx
     */
    @DeleteMapping(value = { "/{id}" })
    @ApiOperation(
            value = "Delete a product by id",
            notes = "Delete an existing Product in the database by id.",
            response = ResponseEntity.class
    )
    public ResponseEntity< Void > deleteProductById(
            @ApiParam(value = "The id of the product you need to delete.", required = true)
            @PathVariable(required = true, name = "id") Long id
    ){

        logger.info("ProductController.deleteProductById => is called");
        logger.debug(String.format("ProductController.deleteProductById(%s)", Long.toString(id)));

        //have to delete all the relations first??

        if (productService.deleteProductById(id))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
