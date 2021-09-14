package task.orange.assignment.controller;

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

import task.orange.assignment.model.Product;
import task.orange.assignment.model.Supplier;
import task.orange.assignment.service.SupplierService;

import javax.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/suppliers")
public class SupplierController {

    private final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }


    @PostMapping(value = { "" })
    @ApiOperation(
            value = "Create new Supplier",
            notes = "Add a new Supplier to the database, by providing a single Supplier in the body of the request.",
            response = Supplier.class
    )
    public ResponseEntity< Supplier > createNewSupplier(
            @ApiParam(value = "The Supplier you need to add (you don't need to provide a supplierId).", required = true)
            @Valid @RequestBody(required = true) Supplier supplier
    ){

        logger.info("SupplierController.createNewSupplier => is called");
        logger.debug(String.format("SupplierController.createNewSupplier(%s)", supplier.toString()));

        Supplier result = supplierService.createNewSupplier(supplier);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }



    @GetMapping(value = { "" })
    @ApiOperation(
            value = "Get all Suppliers in pages",
            notes = "Retrieve all Suppliers from the database in pages, and providing page number and page size is optional.",
            response = Page.class
    )
    public ResponseEntity < Page < Supplier > > getAllSuppliers(
            @ApiParam(value = "Page - zero-based page index, must not be negative.", required = false)
            @RequestParam(required = false) Optional<Integer> pageNumber,
            @ApiParam(value = "Size – the size of the page to be returned, must be greater than 0.", required = false)
            @RequestParam(required = false) Optional <Integer> pageSize
    ){

        logger.info("SupplierController.getAllSuppliers => is called");
        logger.debug(String.format("SupplierController.getAllSuppliers(%s, %s)", pageNumber.toString(), pageSize.toString()));

        if ((pageNumber.isPresent() && pageNumber.get() < 0) ||
                (pageSize.isPresent() && pageSize.get() < 1)){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Page < Supplier > result = supplierService.getAllSuppliers(pageNumber.orElse(0), pageSize.orElse(20));
        
        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping(value = { "/{id}" })
    @ApiOperation(
            value = "Get Supplier by id",
            notes = "Retrieve a single Supplier from the database by id.",
            response = Supplier.class
    )
    public ResponseEntity < Supplier > getSupplierById(
            @ApiParam(value = "The id of the supplier you need.", required = true)
            @PathVariable(required = true) Long id
    ){

        logger.info("SupplierController.getSupplierById => is called");
        logger.debug(String.format("SupplierController.getSupplierById(%s)", Long.toString(id)));

        Supplier result = supplierService.getSupplierById(id);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @GetMapping(value = { "/{id}/products"})
    @ApiOperation(
            value = "Get all products of a supplier by id",
            notes = "Retrieve all the products of a single supplier from the database by id.",
            response = List.class
    )
    public ResponseEntity < List < Product > > getAllProductsOfSupplierById(
            @ApiParam(value = "The id of the supplier.", required = true)
            @PathVariable(required = true) Long id
    ){

        logger.info("SupplierController.getAllProductsOfSupplierById => is called");
        logger.debug(String.format("SupplierController.getAllProductsOfSupplierById(%s)", Long.toString(id)));

        Supplier result = supplierService.getSupplierById(id);
        
        return new ResponseEntity<>(result.getProducts(), HttpStatus.OK);
    }



    @PostMapping(value = { "/{supplierId}/products/{productId}" })
    @ApiOperation(
            value = "Add product to supplier",
            notes = "Connect a product to the supplier, by providing the id of both.",
            response = Supplier.class
    )
    public ResponseEntity < Supplier > addProductToSupplier(
            @ApiParam(value = "The id of the supplier.", required = true)
            @PathVariable(required = true) Long supplierId,
            @ApiParam(value = "The id of the product.", required = true)
            @PathVariable(required = true) Long productId
    ){

        logger.info("SupplierController.addProductToSupplier => is called");
        logger.debug(String.format("SupplierController.addProductToSupplier(%s, %s)", supplierId, productId));

        Supplier result = supplierService.addProductToSupplier(supplierId, productId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @DeleteMapping(value = { "/{supplierId}/products/{productId}" })
    @ApiOperation(
            value = "Remove product from supplier",
            notes = "Disconnect a product from the supplier, by providing the id of both.",
            response = Supplier.class
    )
    public ResponseEntity < Supplier > removeProductFromSupplier(
            @ApiParam(value = "The id of the supplier.", required = true)
            @PathVariable(required = true) Long supplierId,
            @ApiParam(value = "The id of the product.", required = true)
            @PathVariable(required = true) Long productId
    ){

        logger.info("SupplierController.removeProductFromSupplier => is called");
        logger.debug(String.format("SupplierController.removeProductFromSupplier(%s, %s)", supplierId, productId));

        Supplier result = supplierService.removeProductFromSupplier(supplierId, productId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @PutMapping(value = { "/{id}" })
    @ApiOperation(
            value = "Update a Supplier by id",
            notes = "Update an existing Supplier in the database by id, and by providing a single Supplier in the body of the request.",
            response = Supplier.class
    )
    public ResponseEntity< Supplier > updateSupplierById(
            @ApiParam(value = "The id of the Supplier you need to update.", required = true)
            @PathVariable(required = true) Long id,
            @ApiParam(value = "The Supplier you need to add (you don't need to provide a supplierID)", required = true)
            @Valid @RequestBody(required = true) Supplier supplier
    ) {

        logger.info("SupplierController.updateSupplierById => is called");
        logger.debug(String.format("SupplierController.updateSupplierById(%s, %s)", id.toString(), supplier.toString()));
        
        Supplier result = supplierService.updateSupplierById(id, supplier);

        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    @DeleteMapping(value = { "/{id}" })
    @ApiOperation(
            value = "Delete a supplier by id",
            notes = "Delete an existing supplier in the database by id.",
            response = ResponseEntity.class
    )
    public ResponseEntity< Void > deleteSupplierById(
            @ApiParam(value = "The id of the supplier you need to delete.", required = true)
            @PathVariable(required = true, name = "id") Long id
    ){

        logger.info("SupplierController.deleteSupplierById => is called");
        logger.debug(String.format("SupplierController.deleteSupplierById(%s)", Long.toString(id)));


        if (supplierService.deleteSupplierById(id))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}

