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
import task.orange.assignment.error.ConflictException;
import task.orange.assignment.error.NotFoundException;
import task.orange.assignment.model.Product;
import task.orange.assignment.model.Supplier;
import task.orange.assignment.model.SupplierHasProduct;
import task.orange.assignment.model.SupplierHasProductKey;
import task.orange.assignment.repository.SupplierHasProductRepository;
import task.orange.assignment.repository.SupplierRepository;

@Service
@Transactional
public class SupplierService {

    private final Logger logger = LoggerFactory.getLogger(SupplierService.class);
    
    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ProductService productService;

    @Autowired
    SupplierHasProductRepository supplierHasProductRepository;



    public Supplier addProductToSupplier(Long supplierId, Long productId) {

        logger.info("SupplierService.addProductToSupplier => is called");
        logger.debug(String.format("SupplierService.addProductToSupplier(%s, %s)", supplierId, productId));

        Supplier supplier = getSupplierById(supplierId);
        Product product = productService.getProductById(productId);

        for (int i = 0; i < supplier.getOwnedProducts().size(); i ++){

            if (supplier.getOwnedProducts().get(i).getProduct().getProductId().equals(product.getProductId())){

                logger.error("This product is already exists in the supplier's list");
                throw new ConflictException("This product is already exists in the supplier's list");
            }
        }

        supplier.getOwnedProducts().add(
                                        new SupplierHasProduct(
                                            new SupplierHasProductKey(supplierId, productId), 
                                            supplier, product)
                                        );

        return supplierRepository.save(supplier);
    }



    public Supplier removeProductFromSupplier(Long supplierId, Long productId) {

        logger.info("SupplierService.removeProductFromSupplier => is called");
        logger.debug(String.format("SupplierService.removeProductFromSupplier(%s, %s)", supplierId, productId));
        
        Supplier supplier = getSupplierById(supplierId);
        Product product = productService.getProductById(productId);

        for (int i = 0; i < supplier.getOwnedProducts().size(); i ++){

            if (supplier.getOwnedProducts().get(i).getProduct().getProductId().equals(product.getProductId())){

                supplier.getOwnedProducts().remove(i);

                return supplierRepository.save(supplier);
            }
        }

        logger.error("This product doesn't exist in the supplier's list");
        throw new ConflictException("This product doesn't exist in the supplier's list");
    }



    public Supplier createNewSupplier(Supplier supplier){

        logger.info("SupplierService.createNewSupplier => is called");
        logger.debug(String.format("SupplierService.createNewSupplier(%s)", supplier.toString()));

        supplier = copyImportantMembers(supplier);

        if (isSupplierExistsByPhoneNumber(supplier.getPhoneNumber())){

            logger.error("Another record with the same Phone Number exists");
            throw new ConflictException("Another record with the same Phone Number exists");
        }

        if (isSupplierExistsByEmail(supplier.getEmail())){

            logger.error("Another record with the same Email exists");
            throw new ConflictException("Another record with the same Email exists");
        }

        return supplierRepository.save(supplier);
    }



    public Supplier updateSupplierById(Long supplierId, Supplier supplier){

        logger.info("SupplierService.updateSupplierById => is called");
        logger.debug(String.format("SupplierService.updateSupplierById(%s, %s)", supplierId.toString(), supplier.toString()));

        supplier = copyImportantMembers(supplier);

        if (isSupplierExistsById(supplierId)){

            Supplier curr = supplierRepository.findById(supplierId).get();

            if (!curr.getEmail().equals(supplier.getEmail()) &&
                 isSupplierExistsByEmail(supplier.getEmail())){

                logger.error("Another record with the same email exists");
                throw new ConflictException("Another record with the same email exists");
            }

            if (!curr.getPhoneNumber().equals(supplier.getPhoneNumber()) &&
                 isSupplierExistsByPhoneNumber(supplier.getPhoneNumber())){

                logger.error("Another record with the same phone number exists");
                throw new ConflictException("Another record with the same phone number exists");
            }
            
            curr.setFirstName(supplier.getFirstName());
            curr.setLastName(supplier.getLastName());
            curr.setEmail(supplier.getEmail());
            curr.setPhoneNumber(supplier.getPhoneNumber());

            return supplierRepository.save(curr);
        }

        else {

            logger.error(String.format("No Record with id %s was found in our database", supplierId));
            throw new NotFoundException(String.format("No Record with id %s was found in our database", supplierId));
        }
    }



    public Supplier getSupplierById(Long supplierId){

        logger.info("SupplierService.getSupplierById => is called");
        logger.debug(String.format("SupplierService.getSupplierById(%s)", Long.toString(supplierId)));

        if (isSupplierExistsById(supplierId)){

            return supplierRepository.findById(supplierId).get();
        }

        else {

            logger.error(String.format("No Record with id %s was found in our database", supplierId));
            throw new NotFoundException(String.format("No Record with id %s was found in our database", supplierId));
        }
    }


    public Supplier getSupplierByEmail(String email) {

        logger.info("SupplierService.getSupplierById => is called");
        logger.debug(String.format("SupplierService.getSupplierById(%s)", email));

        if (isSupplierExistsByEmail(email)){

            return supplierRepository.findByEmail(email);
        }

        else {

            logger.error(String.format("No Record with email %s was found in our database", email));
            throw new NotFoundException(String.format("No Record with email %s was found in our database", email));
        }
    }


    public Page< Supplier > getAllSuppliers(int pageNumber, int pageSize){

        logger.info("SupplierService.getAllSuppliers => is called");
        logger.debug(String.format("SupplierService.getAllSuppliers(%s, %s)", Integer.toString(pageNumber), Integer.toString(pageSize)));

        return supplierRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "supplierId"));
    }



    public boolean deleteSupplierById(Long supplierId){

        logger.info("SupplierService.deleteSupplierById => is called");
        logger.debug(String.format("SupplierService.deleteSupplierById(%s)", Long.toString(supplierId)));

        if (isSupplierExistsById(supplierId)){

            try {
                
                supplierRepository.deleteById(supplierId);
                return true;

            } catch (Exception e) {

                logger.error(e.getMessage());
                return false;
            }
        }

        else {

            logger.error(String.format("No Record with id %s was found in our database", supplierId));
            throw new NotFoundException(String.format("No Record with id %s was found in our database", supplierId));
        }
    }


    public Supplier copyImportantMembers(Supplier supplier){

        return new Supplier(supplier.getFirstName(), 
                            supplier.getLastName(), 
                            supplier.getPhoneNumber(), 
                            supplier.getEmail(), 
                            new ArrayList<>());
    }


    public boolean isSupplierExistsById(Long supplierId){

        return supplierRepository.existsById(supplierId);
    }

    public boolean isSupplierExistsByPhoneNumber(String phoneNumber){

        return supplierRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean isSupplierExistsByEmail(String email){

        return supplierRepository.existsByEmail(email);
    }

}
