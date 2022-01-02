package EcommerceProject.Store.controller;

import EcommerceProject.Store.entity.Cart;
import EcommerceProject.Store.entity.User;
import EcommerceProject.Store.jwt.JwtUtils;
import EcommerceProject.Store.payload.request.LoginRequest;
import EcommerceProject.Store.payload.request.SignupRequest;
import EcommerceProject.Store.payload.response.JwtResponse;
import EcommerceProject.Store.payload.response.MessageResponse;
import EcommerceProject.Store.repository.CartRepository;
import EcommerceProject.Store.repository.UserRepository;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@RestController
public class AuthController{

    @Autowired   //for signin fetch from database
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired  //for signup
    PasswordEncoder encoder;

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
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch(BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/createCart")
    public ResponseEntity<MessageResponse> createCart(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) throws Exception {

        // fetching username from the token in postman
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        // getting user obj from username
        Optional<User> user = userRepository.findByUsername(username);

        Date date = new Date(); // This object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        if(cartRepository.findByuserId(user.get().getId()) == null){
            Cart cart = new Cart();

            //set user id
            cart.setUserId(user.get().getId());

            //set current TimeAndDate
            cart.setDateTimeCreated(formatter.format(date));

            //set hashcode for UUID
            cart.setUuid(cart.getUuid());
            String message = "Cart created successfully!";
            cartRepository.save(cart);
            return ResponseEntity.ok(new MessageResponse(cart.getUuid(),message));

        }else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("You already have cart"));
        }


    }

}
