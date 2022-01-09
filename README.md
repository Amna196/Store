# Ecommerce-Project
Java Ecommerce project written and compiled in Java Spring Boot framework using intelliJ IDEA.
It is Men T-shirts Project consists of **two phases**.

## Phase1:
Create Four Models `Product` `ProductVariant` `Category` `Brand`
- every t-shirt is a Product, with fixed attribute
- **Product:** [Title, Description, Slug, New "Boolean", Featured "Boolean", Active "Boolean", category, Brand, Image, price, ProductVariant]
- every t-shirt has a ProductVariant 
-  **ProductVariant:** [sku, size, quantity].
-  every t-shirt has a Category
-  **Category:** [ Title, Description,BannerImage ]
-  every t-shirt has a Brand
-  **Brand:** [ Title, Description,BannerImage ]
- APIs Required for Phase1:
- Catalog APIs are paginated. 
    - The required APIs are: 
        * all product list 
        * new products
        * featured products 
        * products per category 
        * products per brand 
        * all products sorted by price low 
        * all products sorted by price high 

## Phase2:
Create Four Models `Cart` `CartItem` `User` `Signin` `Signup`
- User can add items to cart
- **Cart:** [UUID, date and time created, user,total, CartItem]
- every cart has cartItems
-  **CartItem:** [cart, product variant, quantity , price , active].
- APIs Required for Phase2:
- Catalog APIs are paginated. 
    - The required APIs are: 
        * cart
        * signin api
        * signout api 
        * create cart 
        * add item in cart 
        * increase item quantity in cart
        * decrease item quantity in cart 
        * delete item in cart 
      
 ## Phase3:
Create two Models `Ordering` `OrderingItem` `SendPayment` `PaymentRequest`
Use MyFatoorah to integrate payment gateway
- user can have multiple order
- **Ordering:** [uuid, user, paymentStatus, itemsTotal, shippingTotal, grandTotal, dateTimeCreated, orderingItems, paymentLink]
- every oerder has oerderItems 
- **OrderingItem:** [id, productName, color, price, quantity, status, ordering]
- mendatory paramenters to call openfeign microservice 
- **SendPayment:** [username, notificationOption, email, total, phoneNumber]
- values returned from sendpayment request
-  **PaymentRequest:** [inoiceId, orderId, grandTotal, status].
- APIs Required for Phase3:
- Catalog APIs are paginated. 
    - The required APIs are: 
        * Order summary
        * Order History
        * return paymentLink from MyFatoorah payment gateway

### Database schema
Database schema created in dbdesigner.net [Ecommerce-Project](https://dbdesigner.page.link/fB7bvUUEwWb6Tcbq8)
