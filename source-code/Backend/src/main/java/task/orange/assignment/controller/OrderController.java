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

import task.orange.assignment.model.Order;
import task.orange.assignment.model.Product;
import task.orange.assignment.model.Supplier;
import task.orange.assignment.service.OrderService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/orders")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }



    @PostMapping(value = { "" })
    @ApiOperation(
            value = "Create new Order",
            notes = "Add a new Order to the database, by providing a single Order in the body of the request."
                    + "You don't need to provide isDone in the body (will be accepted),"
                    + " and you don't need to provide productID, dateOrder or totalAmount also (will be ignored);"
                    + " empty {} or { with isDone } will work fine.",
            response = Order.class
    )
    public ResponseEntity< Order > createNewOrder(
            @ApiParam(value = "The Order you need to add.", required = true)
            @Valid @RequestBody(required = true) Order order
    ){

        logger.info("OrderController.createNewOrder => is called");
        logger.debug(String.format("OrderController.createNewOrder(%s)", order.toString()));

        Order result = orderService.createNewOrder(order);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }



    @GetMapping(value = { "" })
    @ApiOperation(
            value = "Get all orders in pages",
            notes = "Retrieve all orders from the database in pages, and providing page number and page size is optional.",
            response = Page.class
    )
    public ResponseEntity <Page< Order >> getAllOrders(
            @ApiParam(value = "Page - zero-based page index, must not be negative.", required = false)
            @RequestParam(required = false) Optional<Integer> pageNumber,
            @ApiParam(value = "Size – the size of the page to be returned, must be greater than 0.", required = false)
            @RequestParam(required = false) Optional <Integer> pageSize
    ){

        logger.info("OrderController.getAllOrders => is called");
        logger.debug(String.format("OrderController.getAllOrders(%s, %s)", pageNumber.toString(), pageSize.toString()));

        if ((pageNumber.isPresent() && pageNumber.get() < 0) ||
                (pageSize.isPresent() && pageSize.get() < 1)){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Page < Order > result = orderService.getAllOrders(pageNumber.orElse(0), pageSize.orElse(20));
        
        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping(value = { "/{id}" })
    @ApiOperation(
            value = "Get an Order by id",
            notes = "Retrieve a single Order from the database by id.",
            response = Order.class
    )
    public ResponseEntity < Order > getOrderById(
            @ApiParam(value = "The id of the order you need.", required = true)
            @PathVariable(required = true) Long id
    ){

        logger.info("OrderController.getOrderById => is called");
        logger.debug(String.format("OrderController.getOrderById(%s)", Long.toString(id)));

        Order result = orderService.getOrderById(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @PostMapping(value = { "/{orderId}/products/{productId}" })
    @ApiOperation(
            value = "Add product to order",
            notes = "Connect a product to an order, by providing the id of both, and the quantity of the product to be added in this order.",
            response = Order.class
    )
    public ResponseEntity < Order > addProductToOrder(
            @ApiParam(value = "The id of the order.", required = true)
            @PathVariable(required = true) Long orderId,
            @ApiParam(value = "The id of the product.", required = true)
            @PathVariable(required = true) Long productId,
            @ApiParam(value = "The quantity of the product to be added in this order.", required = false)
            @RequestParam(name = "quantity", defaultValue = "1") int quantity
    ){

        logger.info("OrderController.addProductToOrder => is called");
        logger.debug(String.format("OrderController.addProductToOrder(%s, %s, %s)", orderId, productId, quantity));

        if (quantity < 1)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Order result = orderService.addProductToOrder(orderId, productId, quantity);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @DeleteMapping(value = { "/{orderId}/products/{productId}" })
    @ApiOperation(
            value = "Remove product from order",
            notes = "Disconnect a product from an order, by providing the id of both, and the quantity of the product to be removed from this order.",
            response = Order.class
    )
    public ResponseEntity < Order > removeProductFromOrder(
            @ApiParam(value = "The id of the order.", required = true)
            @PathVariable(required = true) Long orderId,
            @ApiParam(value = "The id of the product.", required = true)
            @PathVariable(required = true) Long productId,
            @ApiParam(value = "The quantity of the product to be removed from this order.", required = false)
            @RequestParam(name = "quantity", defaultValue = "1") int quantity
    ){

        logger.info("OrderController.removeProductFromOrder => is called");
        logger.debug(String.format("OrderController.removeProductFromOrder(%s, %s, %s)", orderId, productId, quantity));

        if (quantity < 1)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Order result = orderService.removeProductFromOrder(orderId, productId, quantity);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @PutMapping(value = { "/{id}" })
    @ApiOperation(
            value = "Update an Order by id",
            notes = "Update an existing Order in the database by id, " +
                    "and by providing a single Order (you are allowed to provide isDone only) in the body of the request.",
            response = Product.class
    )
    public ResponseEntity< Order > updateOrderById(
            @ApiParam(value = "The id of the order you need to update.", required = true)
            @PathVariable(required = true) Long id,
            @ApiParam(value = "The Order you need to add (you are allowed to provide isDone only)", required = true)
            @Valid @RequestBody(required = true) Order order
    ) {

        logger.info("OrderController.updateOrderById => is called");
        logger.debug(String.format("OrderController.updateOrderById(%s, %s)", id.toString(), order.toString()));

        if (order.getIsDone() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Order result = orderService.updateOrderById(id, order);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }



    @DeleteMapping(value = { "/{id}" })
    @ApiOperation(
            value = "Delete an order by id",
            notes = "Delete an existing order in the database by id.",
            response = ResponseEntity.class
    )
    public ResponseEntity< Void > deleteOrderById(
            @ApiParam(value = "The id of the order you need to delete.", required = true)
            @PathVariable(required = true, name = "id") Long id
    ){

        logger.info("OrderController.deleteOrderById => is called");
        logger.debug(String.format("OrderController.deleteOrderById(%s)", Long.toString(id)));


        if (orderService.deleteOrderById(id))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
