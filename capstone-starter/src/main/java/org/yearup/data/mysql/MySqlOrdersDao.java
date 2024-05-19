package org.yearup.data.mysql;

import org.yearup.data.OrderDao;

import javax.sql.DataSource;
import java.sql.*;

public class MySqlOrdersDao extends MySqlDaoBase implements OrderDao {

    public MySqlOrdersDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void addOrder(int userId) {
        String insertOrderSql = "INSERT INTO orders (user_id, date, address, city, state, zip, shipping_amount) " +
                "SELECT ?, NOW(), p.address, p.city, p.state, p.zip, 5.99 AS shipping_amount " +
                "FROM profiles AS p WHERE p.user_id = ?";

        String insertOrderLineItemSql = "INSERT INTO order_line_items (order_id, product_id, sales_price, quantity) " +
                "VALUES (?, ?, ?, ?)";

        String deleteCartItemSql = "DELETE FROM shopping_cart WHERE user_id = ? AND product_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement insertOrderStatement = connection.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertOrderLineItemStatement = connection.prepareStatement(insertOrderLineItemSql);
             PreparedStatement deleteCartItemStatement = connection.prepareStatement(deleteCartItemSql)) {

            // Start transaction
            connection.setAutoCommit(false);

            // Step 1: Insert into orders table
            insertOrderStatement.setInt(1, userId);
            insertOrderStatement.setInt(2, userId);
            insertOrderStatement.executeUpdate();

            // Get the auto-generated order ID
            ResultSet generatedKeys = insertOrderStatement.getGeneratedKeys();
            int orderId = 0;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }

            // Step 2: Retrieve shopping cart items
            String selectCartItemsSql = "SELECT product_id, quantity FROM shopping_cart WHERE user_id = ?";
            try (PreparedStatement selectCartItemsStatement = connection.prepareStatement(selectCartItemsSql)) {
                selectCartItemsStatement.setInt(1, userId);
                try (ResultSet cartItemsResultSet = selectCartItemsStatement.executeQuery()) {
                    // Step 3: Insert into order_line_items and delete from shopping cart
                    while (cartItemsResultSet.next()) {
                        int productId = cartItemsResultSet.getInt("product_id");
                        int quantity = cartItemsResultSet.getInt("quantity");

                        // Insert into order_line_items
                        insertOrderLineItemStatement.setInt(1, orderId);
                        insertOrderLineItemStatement.setInt(2, productId);
                        insertOrderLineItemStatement.setDouble(3, getProductPriceFromDatabase(productId)); // Retrieve product price
                        insertOrderLineItemStatement.setInt(4, quantity);
                        insertOrderLineItemStatement.executeUpdate();

                        // Delete from shopping cart
                        deleteCartItemStatement.setInt(1, userId);
                        deleteCartItemStatement.setInt(2, productId);
                        deleteCartItemStatement.executeUpdate();
                    }
                }
            }

            // Commit transaction
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            // Rollback transaction if any exception occurs
        }
    }

    // Helper method to get the price of a product from the database
    private double getProductPriceFromDatabase(int productId) {
        double price = 0.0;
        String selectProductPriceSql = "SELECT price FROM products WHERE product_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement selectProductPriceStatement = connection.prepareStatement(selectProductPriceSql)) {
            selectProductPriceStatement.setInt(1, productId);
            try (ResultSet resultSet = selectProductPriceStatement.executeQuery()) {
                if (resultSet.next()) {
                    price = resultSet.getDouble("price");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }
}
