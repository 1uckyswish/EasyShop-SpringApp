package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {
    @Autowired// added
    public MySqlShoppingCartDao(DataSource dataSource)
    {
        super(dataSource);
    }


    @Override
    public ShoppingCart getByUserId(int userId) {
        ShoppingCart shoppingCart = new ShoppingCart();

        String sql = "SELECT shopping_cart.user_id, shopping_cart.quantity, products.* " +
                "FROM shopping_cart " +
                "JOIN products ON shopping_cart.product_id = products.product_id " +
                "WHERE shopping_cart.user_id = ?";
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql) ){
            ps.setInt(1,userId);
            ResultSet row = ps.executeQuery();

            while(row.next()){
                ShoppingCartItem shoppingCartItem = mapRow(row);
                shoppingCart.add(shoppingCartItem);


            }

        }
        catch (SQLException exception){
            throw new RuntimeException(exception);
        }
        return shoppingCart;

    }

    @Override
    public ShoppingCart addToCart(int userId, int productId) {
        String sql = "INSERT INTO shopping_cart (user_id, product_id, quantity) VALUES (?, ?, 1) " +
                "ON DUPLICATE KEY UPDATE quantity = quantity + 1;";

        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, userId);
            ps.setInt(2,productId);


            ps.executeUpdate();


        }
        catch (SQLException exception){
            throw new RuntimeException(exception);
        }


        return null;
    }


    @Override
    public void updateCart(int userId, int productId, int newQuantity) {
        String sql = "UPDATE shopping_cart SET quantity = ? " +
                "WHERE user_id = ? AND product_id = ?;";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, newQuantity);
            ps.setInt(2, userId);
            ps.setInt(3, productId);

            // Execute the SQL query
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated == 0) {
                throw new RuntimeException("No rows updated. Shopping cart item not found.");
            }

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void clearCart(int userId) {
        String sql = "DELETE FROM shopping_cart " +
                " WHERE user_id = ?;";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
    protected static ShoppingCartItem mapRow(ResultSet row) throws  SQLException{
        int productId = row.getInt("product_id");
        int quantity = row.getInt("quantity");
        String name = row.getString("name");
        BigDecimal price = row.getBigDecimal("price");
        int categoryId = row.getInt("category_id");
        String description = row.getString("description");
        String color = row.getString("color");
        int stock = row.getInt("stock");
        boolean isFeatured = row.getBoolean("featured");
        String imageUrl = row.getString("image_url");

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        Product product = new Product(productId,name,price,categoryId,description,color,stock,isFeatured,imageUrl);
        shoppingCartItem.setProduct(product);
        shoppingCartItem.setQuantity(quantity);


        return shoppingCartItem;
    }
}
