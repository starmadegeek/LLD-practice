4. Design an order and inventory management system for an e-commerce website

Solution

Write code for low level design (object oriented design) of a simple e-commerce platform, where you need to have the capability of handling sellers, products and orders.

The way it works is, products are added on the website.
Sellers are also added along with the area pincodes that they are able to deliver goods in as well as the payment types which they support.
After that sellers add items they wish to sell.

Multiple sellers can sell the same item e.g the product-1 : bluetooth speaker boat stone 650 can be sold by multiple sellers throughout the country.

Multiple sellers can deliver goods to the same pincode as well.

For simplicity lets assume price of a specific product is same whether it is sold by seller-1 or seller-2 or any seller.

Your code will be tested in a MULTI-THREADED environment, so use thread safe data structures and handle synchronization properly.

Your solution should implement below methods :

Method : void init(Helper04 helper)
- use helper for printing logs else logs will not be visible.
- Use this method for initialize your global variables and all.

Method : void createSeller(String sellerId, List[String] serviceablePincodes, List[String] paymentModes)
- Creates a new seller. Each seller sells many products and multiple sellers can sell the product with same productId
- sellerId will always be a non null, non blank unique string
- serviceablePincodes is list of pincodes where seller can deliver products
- paymentModes will be always one of "cash", "upi", "netbanking", "debit card" and "credit card"

Method : void createProduct(String productId, String name, double price)
- Creates a product. Multiple sellers can sell the same product.
- For simplicity lets assume that each item will have same price.
- once a product is created, inventory for the same will be added by different sellers who will be selling that item.
- productId it will always be a non null, non blank, unique string
- name e.g boAt stone 650 bluetooth speaker
- price of item e.g. 1599

Method : String addInventory(String productId, String sellerId, int delta)
- seller adds multiple items of a product for selling. e.g 50 grey pure cotton shirts.
- delta number of items seller is adding e.g. 50 . It will always be a positive integer.
- returns "inventory added", "product doesn't exist", "seller doesn't exist".

Method : int getInventory(String productId, String sellerId)
- returns the number of items in warehouse for a product sold by a given seller,
- if the product or seller doesn't exist then returns 0

Method : createOrder(String orderId, String destinationPincode, String sellerId, String productId, int productCount, String paymentMode)
- creates order with orderId
- buyer will choose both product and seller who will deliver the product and create an order.
- For simplicity lets assume that at this time only one product (1 or more counts) is purchased in a single order.
- orderId will always be a non null, non blank, unique string
- productCount is number of items customer is ordering, it will always be a positive integer
- paymentMode will always be one of "cash", "upi", "netbanking", "debit card" and "credit card"
- returns (in that order) : "order placed" or "product doesn't exist" or "buyer doesn't exist" or "seller doesn't exist" or "pincode unserviceable" or "payment mode not supported" or "insufficient product inventory"

Method : List[String] getSellers(String productId, int itemCount, String destinationPincode, String paymentType)
- returns list of all sellers sorted by sellerId String in ascending order, which have greater than or equal to itemCount pieces of the product with productId and also deliver to destinationPincode.
- parameter productId : product which needs to be checked for purchase
- parameter itemCount : number of items that we wish to check for, always be a positive integer
- parameter destinationPincode : pincode of buyer where product needs to be delivered.

Example : Read the below method calls to get a better understanding of how this works.

init(helper) : helper is initialized.
createProduct(product-0, product-name-0, 260.0)
createSeller(seller-0, [110001, 560092, 452001, 700001], [netbanking, cash, upi])
createSeller(seller-1, [400050, 110001, 600032, 560092], [netbanking, cash, upi])
addInventory(product-0, seller-1, 52) returned "inventory added"
addInventory(product-0, seller-0, 32) returned "inventory added"
createOrder(order-1, 400050, seller-1, product-0, 5, upi) returned "order placed"
getInventory(seller-1) returned 47
createOrder(order-2, 560092, seller-0, product-0, 1, upi) returned "order placed"
getInventory(seller-0) returned 31
getSellers(product-0, 26, 110001, upi) returned [seller-0, seller-1]
getSellers(product-0, 26, 600032, upi) returned [seller-1]
getSellers(product-0, 26, 110001, upi) returned [seller-0, seller-1]
getSellers(product-0, 42, 452001, upi) returned []
getSellers(product-0, 26, 560092, upi) returned [seller-0, seller-1]
getSellers(product-0, 42, 110001, upi) returned [seller-1]
getSellers(product-0, 42, 600032, upi) returned [seller-1]
getSellers(product-0, 26, 600032, upi) returned [seller-1]
