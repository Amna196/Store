package EcommerceProject.Store.controller;

import EcommerceProject.Store.entity.*;
import EcommerceProject.Store.exception.EmptyInputException;
import EcommerceProject.Store.jwt.JwtUtils;
import EcommerceProject.Store.payload.request.LoginRequest;
import EcommerceProject.Store.payload.request.SignupRequest;
import EcommerceProject.Store.payload.response.JwtResponse;
import EcommerceProject.Store.payload.response.MessageResponse;
import EcommerceProject.Store.repository.*;
import EcommerceProject.Store.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class AuthController{

    @Autowired   //for signin fetch from database
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductVariantRepository variantRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    OrderingItemRepository orderingItemRepository;

    @Autowired
    OrderingRepository orderingRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtTokenUtil;

    @GetMapping("/users")
    public ResponseEntity<Page<User>> retrieveUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok().body(userRepository.findAll(paging));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

//            if(loginRequest.getUsername().length() <20){
//                System.out.println("inside " + loginRequest);
//                throw new EmptyInputException("400","Input Field is empty");
//            }

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        final Date jwt_expiryDate = jwtTokenUtil.extractExpiration(jwt);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        final String refreshJwt = jwtTokenUtil.refreshToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwt, formatter.format(jwt_expiryDate), refreshJwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), bCryptPasswordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // Creating cart per user and returning hashcode String
    @PostMapping("/createCart")
    public ResponseEntity<MessageResponse> createCart(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){

        // fetching username from the token in postman
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        // getting user obj from username
        Optional<User> user = userRepository.findByUsername(username);

        Date date = new Date(); // This object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        if(!cartRepository.existsByuserId(user.get().getId())){
            Cart cart = new Cart();
            // set user id
            cart.setUserId(user.get().getId());
            // set current TimeAndDate
            cart.setDateTimeCreated(formatter.format(date));
            // set hashcode for UUID
            cart.setUuid(cart.getUuid());
            cart.setTotal(BigDecimal.valueOf(0));
            String message = "Cart created successfully!";
            cartRepository.save(cart);
            return ResponseEntity.ok(new MessageResponse(cart.getUuid(),message));

        }else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("You already have cart"));
        }
    }

    // Return cart per userId
    @PostMapping("/cart")
    public ResponseEntity<?> retrieveCart(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {

        //fetch username from Authentication
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        // fetch user object from username
        Optional<User> user = userRepository.findByUsername(username);

        // fetch sku parameter from productVariant repository
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok().body(cartRepository.findByuserId(user.get().getId(), paging));

    }

    // Adding the item in the cart or increasing its number of unit (nou) if it is already available in cart
    @PostMapping("/addItemToCart/{sku}")
    public ResponseEntity<?> addItem(@PathVariable String sku, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {

            //fetch username from Authentication
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication()
                    .getPrincipal();
            String username = userDetails.getUsername();

            // fetch user object from username
            Optional<User> user = userRepository.findByUsername(username);
            Pageable paging = PageRequest.of(page, size);

            // check if given sku is available in variant object
            Optional<ProductVariant> variant = Optional.ofNullable(variantRepository.findById(sku).orElseThrow(() -> new NoSuchElementException("Incorrect sku: " + sku)));


            // fetch cart of user per userId and create one if not available
            if (!cartRepository.existsByuserId(user.get().getId())) {
                Date date = new Date(); // This object contains the current date value
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Cart cart = new Cart();
                // set user id
                cart.setUserId(user.get().getId());
                // set current TimeAndDate
                cart.setDateTimeCreated(formatter.format(date));
                // set hashcode for UUID
                cart.setUuid(cart.getUuid());
                cart.setTotal(BigDecimal.valueOf(0));
                cartRepository.save(cart);
            }
            Cart cart = cartRepository.findByuserId(user.get().getId());

            /* if sku value is available in productVariant table
                and number of available quantity is greater than the required, increase number of quantity in the cartItem
             */

            if (variant.get().getQuantity() > 0 && cartItemRepository.existsBySkuAndCart(sku, cart)) { // increase item quantity in cart

                CartItem cartItem = cartItemRepository.findBySkuAndCart(sku, cart);
                // if variant quanity < nou return soldout peices
                if (cartItem.getNou() < variant.get().getQuantity()) { // check available quantity with number of requested unit
                    cartItem.setActive(true);// set as active
                    cartItem.setPrice(variant.get().getProduct().getPrice());//get price
                    cartItem.setNou(cartItem.getNou() + 1);
                    cartItem.setProduct(variant.get().getProduct());
                    cartItem.setStatus(CartItem.cartItemStatus.INCART);
                    cartItemRepository.save(cartItem);// update cart item by increasing nou
                    updateCartTotal(cart);// call update total in cart method
                    cartRepository.save(cart);// update total in database
                    return ResponseEntity.ok().body(cartRepository.findByuserId(user.get().getId(), paging));
                }

            } else if (variant.get().getQuantity() > 0 && !cartItemRepository.existsBySkuAndCart(sku, cart)) {  // add item in cart
                CartItem cartItem = new CartItem();
                cartItem.setSku(sku);
                cartItem.setActive(true);// set as active
                cartItem.setProduct(variant.get().getProduct());
                cartItem.setStatus(CartItem.cartItemStatus.INCART);
                cartItem.setPrice(variant.get().getProduct().getPrice());//get price
                cartItem.setNou(cartItem.getNou() + 1);

                // fetch cart of user per userId
                cartItem.setCart(cart);
                cartItemRepository.save(cartItem);// add item in cartItem
                updateCartTotal(cart);// update total in cart
                cartRepository.save(cart);// update total in database
                return ResponseEntity.ok().body(cartRepository.findByuserId(user.get().getId(), paging));
            }
            return ResponseEntity
                    .badRequest()
                    .body(cartRepository.findByuserId(user.get().getId(), paging));
    }

    // Remove items in cart by decreasing number of unit or deleting the item
    @PostMapping("/removeItemFromCart/{sku}")
    public ResponseEntity<Page<Cart>> removeItem(@PathVariable String sku, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) throws Exception {
        //fetch username from Authentication
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        // fetch user object from username
        Optional<User> user = userRepository.findByUsername(username);
        Pageable paging = PageRequest.of(page, size);

        // check if given sku is available and store in variant object
        Optional<ProductVariant> variant = Optional.ofNullable(variantRepository.findById(sku)
                .orElseThrow(() -> new NoSuchElementException("Incorrect sku: " + sku)));

        // fetch cart of user per userId
        if(!cartRepository.existsByuserId(user.get().getId())){
            Date date = new Date(); // This object contains the current date value
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Cart cart = new Cart();
            // set user id
            cart.setUserId(user.get().getId());
            // set current TimeAndDate
            cart.setDateTimeCreated(formatter.format(date));
            // set hashcode for UUID
            cart.setUuid(cart.getUuid());
            cart.setTotal(BigDecimal.valueOf(0));
            String message = "Cart created successfully!";
            cartRepository.save(cart);
        }
        Cart cart = cartRepository.findByuserId(user.get().getId());

        // if sku is available in cartitem && nou > 1 decrease nou in cart item & update cartTotal
        if (cartItemRepository.existsBySkuAndCart(sku, cart)) { // decrease item quantity in cart

            CartItem cartItem = cartItemRepository.findBySkuAndCart(sku, cart);
            // if nou > 1 return decrease item
            if(cartItem.getNou() > 1) { // check available quantity with number of requested unit
                cartItem.setNou(cartItem.getNou() - 1);
                cartItemRepository.save(cartItem);// update cart item by decreasing nou
                updateCartTotal(cart);// call update total in cart method
                cartRepository.save(cart);// update total in database
                return ResponseEntity.ok().body(cartRepository.findByuserId(user.get().getId(), paging));
            }else if(cartItem.getNou() == 1){
                cartItemRepository.delete(cartItem);// delete item
                updateCartTotal(cart);// call update total in cart method
                cartRepository.save(cart);// update total in database
                return ResponseEntity.ok().body(cartRepository.findByuserId(user.get().getId(), paging));
            }
        }
        return ResponseEntity
                .badRequest()
                .body(cartRepository.findByuserId(user.get().getId(), paging));
    }

    // Deleting items in cart
    @PostMapping("/deleteItemInCart/{sku}")
    public ResponseEntity<Page<Cart>> deleteItem(@PathVariable String sku, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        // fetch username from Authentication
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        // fetch user object from username
        Optional<User> user = userRepository.findByUsername(username);
        Pageable paging = PageRequest.of(page, size);

        // check if given sku is available and store in variant object
        Optional<ProductVariant> variant = Optional.ofNullable(variantRepository.findById(sku)
                .orElseThrow(() -> new NoSuchElementException("Incorrect sku: " + sku)));

        // fetch cart of user per userId
        if(!cartRepository.existsByuserId(user.get().getId())){
            Date date = new Date(); // This object contains the current date value
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Cart cart = new Cart();
            // set user id
            cart.setUserId(user.get().getId());
            // set current TimeAndDate
            cart.setDateTimeCreated(formatter.format(date));
            // set hashcode for UUID
            cart.setUuid(cart.getUuid());
            cart.setTotal(BigDecimal.valueOf(0));
            String message = "Cart created successfully!";
            cartRepository.save(cart);
        }
        Cart cart = cartRepository.findByuserId(user.get().getId());

        // if sku is available in cartitem, delete item & update cartTotal
        if (cartItemRepository.existsBySkuAndCart(sku, cart)) {

            CartItem cartItem = cartItemRepository.findBySkuAndCart(sku, cart);
            // delete item
            cartItemRepository.delete(cartItem);// delete item
            updateCartTotal(cart);// call update total in cart method
            cartRepository.save(cart);// update total in database
            return ResponseEntity.ok().body(cartRepository.findByuserId(user.get().getId(), paging));
        }
        return ResponseEntity
                .badRequest()
                .body(cartRepository.findByuserId(user.get().getId(), paging));
    }

    // checkout return order Summary
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Pageable paging = PageRequest.of(page, size);

        //fetch username from Authentication
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        // fetch user object from username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Incorrect username: " + username));

        // fetch cart by userId
        Cart cart = cartRepository.findByuserId(user.getId());
        if (cartItemRepository.existsByCart_id(cart.getId())) {
            // get all items in user's cart
            List<CartItem> cartItems = cartItemRepository.findAllByCart_idAndStatusLikeOrStatusLike(cart.getId(), CartItem.cartItemStatus.INCART, CartItem.cartItemStatus.SOLDOUT); // fetch by cartId And status=[In cart - Sold out]
            System.out.println(cartItems);
            Ordering order = createOrdering(user);
            // todo: get list of variants from skuIn cartItem
            for (CartItem cartItem : cartItems) {
                System.out.println("inside loop..................");
                Optional<ProductVariant> variant = variantRepository.findById(cartItem.getSku());
                System.out.println(variant.get().getQuantity() >= cartItem.getNou());
                //todo: compare quantity in variant with nou in cartItem
                if (variant.get().getQuantity() >= cartItem.getNou()) {

                    cartItem.setStatus(CartItem.cartItemStatus.CHECKOUT);
                    cartItemRepository.save(cartItem);
                    variant.get().setQuantity(variant.get().getQuantity() - cartItem.getNou()); // newQuantity = oldQuantity - numberOfUnit in cartItem
                    variantRepository.save(variant.get());// update quantity in database
                    OrderingItem orderingItem = new OrderingItem();
                    System.out.println("created orderingItem..................");
                    orderingItem.setPrice(cartItem.getPrice());
                    orderingItem.setQuantity(cartItem.getNou());
                    orderingItem.setProductName(cartItem.getProduct().getTitle());
                    orderingItem.setColor(cartItem.getProduct().getColor());
                    orderingItem.setStatus(OrderingItem.OrderItemStatus.CHECKOUT);
                    // set orderItem in order
                    orderingItem.setOrdering(order);
                    System.out.println("set orderingItem in order..................");
                    orderingItemRepository.save(orderingItem);
                    System.out.println("saved orderingItem..................");
                }
            }
        }
        if(cartItemRepository.existsByStatusAndCart(CartItem.cartItemStatus.CHECKOUT,cart)){// if cart has items in a checkout status
            //todo: find orderingtime for user
            //todo: update paumentstatus=checkout for ordering class
            Ordering ordering =  orderingRepository.findByUserAndPaymentStatusLike(user, Ordering.orderPaymentStatus.CREATED);
            List<OrderingItem> orderingItems = orderingItemRepository.findAllByOrdering(ordering);
            // set orderItems into the order
            ordering.setOrderingItems(orderingItems);
            ordering.setPaymentStatus(Ordering.orderPaymentStatus.CHECKOUT);
            System.out.println("setPaymentStatus to checkout..................");

            //todo: update item total in ordering class
            updateOrderTotal(ordering);
            System.out.println("updateOrderTotal..................");


            //todo: update grid total in ordering class
            ordering.setGrindTotal(ordering.getItemsTotal().add(ordering.getShippingTotal()));
            System.out.println("setGrindTotal..................");

            //todo: return ordering class
            orderingRepository.save(ordering);


            //todo: delete items in cart that has checkout status <cartItemRepository.findByStatusAndCart("checkout",cart)>
            List<CartItem> cartItems = cartItemRepository.findByStatusAndCart(CartItem.cartItemStatus.CHECKOUT,cart);
            for(CartItem cartItem:cartItems){
                cartItemRepository.delete(cartItem);
            }
            return ResponseEntity.ok().body(orderingRepository.findByuuidAndUser(ordering.getUuid(),ordering.getUser(), paging));

        }
        else {
            System.out.println("inside null..................");
            throw new EmptyInputException("400", "Empty OrderingItems");
        }

    }

    private Ordering createOrdering(User user) {
        Ordering ordering = new Ordering();
        System.out.println("create ordering..................");
        Date date = new Date(); // This object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//            // set hashcode for UUID
//            ordering.setUuid(ordering.getUuid());
        // set user id
        ordering.setUser(user);
        System.out.println("create set user..................");
        // set current TimeAndDate
        ordering.setDateTimeCreated(formatter.format(date));
        ordering.setShippingTotal(BigDecimal.valueOf(3));
        System.out.println("create setShippingTotal..................");
        ordering.setPaymentStatus(Ordering.orderPaymentStatus.CREATED);
        System.out.println("setPaymentStatus for ordering to created..................");
        orderingRepository.save(ordering);
        return ordering;
    }

    // Calculate total amount in cart
    public void updateCartTotal(Cart cart) {
        if(cart.getCartItems() == null) return;
        BigDecimal total = BigDecimal.valueOf(0);
        List<CartItem> cartItemList = cart.getCartItems();
        for(CartItem items: cartItemList){
            total = total.add(items.getPrice().multiply(new BigDecimal(items.getNou())));
        }
        cart.setTotal(total);
    }

    // Calculate total amount in Ordering
    public void updateOrderTotal(Ordering ordering) {
        System.out.println("ordering = " + ordering.getOrderingItems());
        if(ordering.getOrderingItems() == null) return;
        BigDecimal total = BigDecimal.valueOf(0);
        List<OrderingItem> orderingItems = ordering.getOrderingItems();
        for(OrderingItem items: orderingItems){
            System.out.println("inside loop of total = " + ordering);
            total = total.add(items.getPrice().multiply(new BigDecimal(items.getQuantity())));
        }
        ordering.setItemsTotal(total);
    }

    // Return orders per userId
    @PostMapping("/OrderHistory")
    public ResponseEntity<Page<Ordering>> retrieveOrders(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {

        //fetch username from Authentication
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        // fetch user object from username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Incorrect username: " + username));

        // fetch sku parameter from productVariant repository
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok().body(orderingRepository.findAllByUserAndPaymentStatus(user, Ordering.orderPaymentStatus.CLOSED, paging)); //closed

    }

}
