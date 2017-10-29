package utils;

import data_objects.Products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DBHelper {

    public static List<Products> getProducts() throws SQLException {
        String query = "SELECT * FROM `lc_products`";
        ResultSet rs = executeQuery(query);
        List<Products> result = new ArrayList<>();

        while (rs.next()) {
            Products currentProduct = new Products();
            currentProduct.setId(rs.getString("id"));
            currentProduct.setCode(rs.getString("code"));
            currentProduct.setQuantity(rs.getString("quantity"));
            result.add(currentProduct);
        }

        return result;
    }

    public static List<String> getProductNames() throws SQLException {
        String query = "SELECT name FROM `lc_products_info`";
        ResultSet rs = executeQuery(query);
        List<String> response = new ArrayList<>();

        while (rs.next()) {
            response.add(rs.getString("name"));
        }
        return response;
    }

    public static List<Float> getProductPrices() throws SQLException {
        String query = "SELECT USD FROM `lc_products_prices`";
        ResultSet rs = executeQuery(query);
        List<Float> response = new ArrayList<>();

        while (rs.next()) {
            response.add(rs.getFloat("USD"));
        }
        return response;
    }
    public static Map<String,Float> getProductPricesToMap() throws SQLException {
        String query = "SELECT product.name, price.USD \n" +
                "FROM `lc_products_info` product\n" +
                "JOIN `lc_products_prices` price \n" +
                "WHERE price.product_id = product.product_id";
        ResultSet rs = executeQuery(query);
        Map<String,Float> response = new TreeMap<>();

        while (rs.next()) {
            response.put(rs.getString("name"),rs.getFloat("USD"));
        }
        return response;
    }

    private static ResultSet executeQuery(String query) {
        Connection connection = getDatabaseConnection();
        ResultSet response = null;
        try {
            response = connection.prepareStatement(query).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static Connection getDatabaseConnection() {
        String databaseUrl = PropertyHelper.getProperty("dbUrl");
        String dbUser = PropertyHelper.getProperty("dbUser");
        String dbPassword = PropertyHelper.getProperty("dbPassword");
        Connection connection = null;
        try {
            //DriverManager.getDriver(PropertyHelper.getProperty("dbDriverUrl"));
            connection = DriverManager.getConnection(databaseUrl, dbUser, dbPassword);
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return connection;
    }
}
