package task.orange.assignment;

import task.orange.assignment.model.Order;
import task.orange.assignment.model.Product;
import task.orange.assignment.model.Supplier;

import java.util.ArrayList;
import java.util.Random;

public class UtilTest {

    public static final String AUTHORIZATION_TOKEN  = "YWRtaW46MTIz";
    public static final String BASE_URL = "/api/v1";
    public static final String CHAR_SET_A0 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static final String CHAR_SET_0 = "1234567890";
    public static final String CHAR_SET_aA0 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static final String CHAR_SET_aA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String CHAR_SET_a =  "abcdefghijklmnopqrstuvwxyz";

    public static String getRandString(int len, String CHAR_SET) {

        StringBuilder ret = new StringBuilder();
        Random rnd = new Random();

        while (ret.length() < len) {
            int index = (int) (rnd.nextFloat() * CHAR_SET.length());
            ret.append(CHAR_SET.charAt(index));
        }

        return ret.toString();
    }

    public static Product createRandProduct(){

        return new Product(
                getRandString(44, CHAR_SET_aA0),
                getRandString(444, CHAR_SET_aA0),
                getRandString(44, CHAR_SET_aA),
                getRandString(44, CHAR_SET_aA),
                new Random().nextDouble(),
                new ArrayList<>(), new ArrayList<>()
        );
    }

    public static Supplier createRandSupplier(){

        return new Supplier(

                getRandString(33, CHAR_SET_a),

                getRandString(33, CHAR_SET_a),

                getRandString(22, CHAR_SET_0),

                getRandString(33, CHAR_SET_a) + "@gmail.com",

                new ArrayList<>());
    }

    public static Order createRandOrder(){

        return new Order(null, new Random().nextBoolean(), null, null);
    }
}
