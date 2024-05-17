package org.yearup.data;

import org.springframework.stereotype.Repository;
import org.yearup.models.ShoppingCart;

@Repository
public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
    ShoppingCart addToCart(int userId, int productId);

    void updateCart(int userId, int productId, int quantity);

    void clearCart(int userId);

}
