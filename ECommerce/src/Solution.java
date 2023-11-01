// ****** It's better to write code in your local code editor and paste it back here *********

// put all import statements at the top, else it will give compiler error
import java.util.*;

/**
 * - All the methods of this class will be tested in a MULTI-THREADED environment.
 * - If you are creating new interfaces, then they have to be declared on the top, even before class Solution, else it will give class not found error for classes implementing them.
 * - use helper.print("") or helper.println("") for printing logs else logs will not be visible.
 */
public class Solution implements Q04EcommerceOrdersInterface {
    private Helper04 helper;

    // constructor must always be public, don't remove the public keyword
    public Solution(){}

    public void init(Helper04 helper){
        this.helper=helper;
        // helper.println("e-commerce orders module initialized");
    }

    /**
     * creates a new seller (who sells products on website).
     * - each seller sells many products and multiple sellers can sell the product with same productId
     * @param  sellerId it will always be a non null, non blank unique string
     * @param serviceablePincodes list of pincodes where seller can deliver products
     * @param paymentModes it will be always one of  "cash", "upi", "netbanking", "debit card" and "credit card"
     */
    public void createSeller(String sellerId, List<String> serviceablePincodes, List<String> paymentModes) {

    }

    /**
     * - Creates a product. Multiple sellers can sell the same product.
     * - For simplicity lets assume that each item will have same price
     * - once a product is created, inventory for the same will be added by different sellers who will be selling that item.
     * @param productId it will always be a non null, non blank, unique string
     * @param name e.g boAt stone 650 bluetooth speaker
     * @param price price of item e.g. 1599
     */
    public void createProduct(String productId, String name, double price) {

    }

    /**
     * seller adds multiple items of a product for selling. e.g 50 grey pure cotton shirts.
     * @param delta number of items seller is adding e.g. 50 . It will always be a positive integer.
     * @return returns "inventory added", "product doesn't exist", "seller doesn't exist"
     */
    public String addInventory(String productId, String sellerId, int delta) {
        return "inventory added";
    }

    /**
     * @return returns the number of items left for a product sold by a given seller,
     *  if the product or seller doesn't exist then returns 0
     */
    public int getInventory(String productId, String sellerId) {
        return 0;
    }

    /**
     * - creates order with orderId
     * - buyer will choose both product and seller who will deliver the product and create an order.
     * - For simplicity lets assume that at this time only one product (1 or more counts) is purchased in a single order.
     * @param orderId it will always be a non null, non blank, unique string
     * @param productCount it will always be a positive integer
     * @param paymentMode it wil always be one of  "cash", "upi", "netbanking", "debit card" and "credit card"
     * @return returns (in that order) : "order placed" or "product doesn't exist" or "buyer doesn't exist" or "seller doesn't exist" or "pincode unserviceable" or "payment mode not supported"   or "insufficient product inventory"
     */
    public String createOrder(String orderId, String destinationPincode, String sellerId, String productId, int productCount, String paymentMode) {
        return "order placed";
    }

    /**
     * returns list of all sellers sorted by sellerId String in ascending order, which have >=itemCount pieces of the product with productId and also deliver to destinationPincode.
     * @param productId product which needs to be checked for purchase
     * @param itemCount number of items that we wish to check for, always be a positive integer
     * @param destinationPincode pincode of buyer where product needs to be delivered.
     */
    public List<String> getSellers(String productId, int itemCount, String destinationPincode,  String paymentType) {
        return new ArrayList<String>();
    }
}

// uncomment below code in case you are using your local ide like intellij, eclipse etc and
// comment it back again back when you are pasting completed solution in the online CodeZym editor.
// if you don't comment it back, you will get "java.lang.AssertionError: java.lang.LinkageError"
// This will help avoid unwanted compilation errors and get method autocomplete in your local code editor.

interface Q04EcommerceOrdersInterface {

    public void init(Helper04 helper);

    void createSeller(String sellerId, List<String> serviceablePincodes, List<String> paymentModes);

    void createProduct(String productId, String name, double price);

    String addInventory(String productId, String sellerId, int delta);

    int getInventory(String productId, String sellerId);

    String createOrder(String orderId, String destinationPincode, String sellerId, String productId, int productCount, String paymentMode);

    List<String> getSellers(String productId, int itemCount, String destinationPincode, String paymentMode);
}

class Helper04 {
    void print(String s){System.out.print(s);}
    void println(String s){System.out.println(s);}
}
