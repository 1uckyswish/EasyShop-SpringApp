package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.ShoppingCartDto;
import org.yearup.data.UserDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.User;

import java.security.Principal;

// convert this class to a REST controller
// only logged in users should have access to these actions
@RestController
@RequestMapping("cart")
@CrossOrigin
public class ShoppingCartController
{
    // a shopping cart requires
    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private ProductDao productDao;
    @Autowired
    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao){
        this.shoppingCartDao = shoppingCartDao;
        this.productDao = productDao;
        this.userDao = userDao;
    }



    // each method in this controller requires a Principal object as a parameter
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ShoppingCart getCart(Principal principal)
    {
        try
        {
            // get the currently logged in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            // use the shoppingcartDao to get all items in the cart and return the cart
            return shoppingCartDao.getByUserId(userId);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops...");
        }
    }

    // add a POST method to add a product to the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be added
    @PostMapping("/products/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ShoppingCart addProductToCart(@PathVariable int id,Principal principal){
        try{
            String userName = principal.getName();

            User user = userDao.getByUserName(userName);
            int userId = user.getId();
            shoppingCartDao.addToCart(userId,id);
            return shoppingCartDao.getByUserId(userId);


        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"My bad");
        }
    }



    // add a PUT method to update an existing product in the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be updated)
    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated
    @PutMapping("/products/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ShoppingCart updateCart(@PathVariable int id, @RequestBody ShoppingCartDto shoppingCartDto, Principal principal){
        try{
            String userName = principal.getName();

            User user = userDao.getByUserName(userName);
            int userId = user.getId();
            shoppingCartDao.updateCart(userId,id,shoppingCartDto.getQuantity());
            return shoppingCartDao.getByUserId(userId);

        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"My bad");
        }
    }

    // add a DELETE method to clear all products from the current users cart
    // https://localhost:8080/cart
    @DeleteMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public ShoppingCart clearCart(Principal principal){
        try {
            // get the currently logged in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            shoppingCartDao.clearCart(userId);
            return shoppingCartDao.getByUserId(userId);

        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"delete error");
        }
    }
}
