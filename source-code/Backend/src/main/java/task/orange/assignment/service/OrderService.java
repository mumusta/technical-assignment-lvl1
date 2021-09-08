package task.orange.assignment.service;

import java.util.ArrayList;
import java.util.Date;
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
import task.orange.assignment.model.Order;
import task.orange.assignment.model.OrderLine;
import task.orange.assignment.model.OrderLineKey;
import task.orange.assignment.model.Product;
import task.orange.assignment.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

    Logger logger = LoggerFactory.getLogger(OrderService.class);
    
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;



    public Order addProductToOrder(Long orderId, Long productId, int quantity) {

        logger.info("OrderService.addProductToOrder => is called");
        logger.debug(String.format("OrderService.addProductToOrder(%s, %s, %s)", orderId, productId, quantity));
        
        Order order = getOrderById(orderId);
        Product product = productService.getProductById(productId);

        for (int i = 0; i < order.getOrderLines().size(); i ++){

            OrderLine currOrderLine = order.getOrderLines().get(i);

            if (currOrderLine.getProduct().getProductId().equals(product.getProductId())){

                currOrderLine.setProductQuantity(currOrderLine.getProductQuantity() + quantity);
                order.setTotalAmount(order.getTotalAmount() + (product.getPrice() * (double)quantity));

                return orderRepository.save(order);
            }
        }

        order.getOrderLines().add(
            
            new OrderLine(new OrderLineKey(order.getOrderId(), product.getProductId()), order, product, quantity)
        );

        order.setTotalAmount(order.getTotalAmount() + (product.getPrice() * (double)quantity));

        return orderRepository.save(order);
    }


    public Order removeProductFromOrder(Long orderId, Long productId, int quantity) {

        logger.info("OrderService.removeProductFromOrder => is called");
        logger.debug(String.format("OrderService.removeProductFromOrder(%s, %s, %s)", orderId, productId, quantity));

        Order order = getOrderById(orderId);
        Product product = productService.getProductById(productId);

        for (int i = 0; i < order.getOrderLines().size(); i ++){

            OrderLine currOrderLine = order.getOrderLines().get(i);

            if (currOrderLine.getProduct().getProductId().equals(product.getProductId())){

                if (quantity > currOrderLine.getProductQuantity()){

                    logger.error("This quantity of the product is greater than the actual quantity of that product in the order");
                    throw new ConflictException("This quantity of the product is greater than the actual quantity of that product in the order");
                }

                else {

                    currOrderLine.setProductQuantity(currOrderLine.getProductQuantity() - quantity);
                    order.setTotalAmount(order.getTotalAmount() - (product.getPrice() * (double)quantity));

                    if (currOrderLine.getProductQuantity() == 0)
                        order.getOrderLines().remove(i);

                    return orderRepository.save(order);
                }
            }
        }

        logger.error("This product doesn't exist in that order");
        throw new ConflictException("This product doesn't exist in that order");
    }



    public Order createNewOrder(Order order){

        logger.info("OrderService.createNewOrder => is called");
        logger.debug(String.format("OrderService.createNewOrder(%s)", order.toString()));

        order = copyIsDoneAndFill(order);

        return orderRepository.save(order);
    }



    public Order updateOrderById(Long orderId, Order order){

        logger.info("OrderService.updateOrderById => is called");
        logger.debug(String.format("OrderService.updateOrderById(%s, %s)", orderId.toString(), order.toString()));

        if (isOrderExistsById(orderId)){

            Order curr = orderRepository.findById(orderId).get();

            curr.setIsDone(order.getIsDone());

            return orderRepository.save(curr);
        }

        else {

            logger.error(String.format("No Record with id %s was found in our database", orderId));
            throw new NotFoundException(String.format("No Record with id %s was found in our database", orderId));
        }
    }



    public Order getOrderById(Long orderId){

        logger.info("OrderService.getOrderById => is called");
        logger.debug(String.format("OrderService.getOrderById(%s)", Long.toString(orderId)));

        if (isOrderExistsById(orderId)){

            return orderRepository.findById(orderId).get();
        }

        else {

            logger.error(String.format("No Record with id %s was found in our database", orderId));
            throw new NotFoundException(String.format("No Record with id %s was found in our database", orderId));
        }
    }



    public Page< Order > getAllOrders(int pageNumber, int pageSize){

        logger.info("OrderService.getAllOrders => is called");
        logger.debug(String.format("OrderService.getAllOrders(%s, %s)", Integer.toString(pageNumber), Integer.toString(pageSize)));

        return orderRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "orderId"));
    }



    public boolean deleteOrderById(Long orderId){

        logger.info("OrderService.deleteOrderById => is called");
        logger.debug(String.format("OrderService.deleteOrderById(%s)", Long.toString(orderId)));

        if (isOrderExistsById(orderId)){

            try {
                
                orderRepository.deleteById(orderId);
                return true;

            } catch (Exception e) {

                logger.error(e.getMessage());
                return false; 
            }
        }

        else {

            logger.error(String.format("No Record with id %s was found in our database", orderId));
            throw new NotFoundException(String.format("No Record with id %s was found in our database", orderId));
        }
    }


    public Order copyIsDoneAndFill(Order order){

        if (order.getIsDone() == null)
            order.setIsDone(false);

        return new Order(new Date(), order.getIsDone(), 0.0, new ArrayList<>());
    }


    public boolean isOrderExistsById(Long orderId){

        return orderRepository.existsById(orderId);
    }
}
