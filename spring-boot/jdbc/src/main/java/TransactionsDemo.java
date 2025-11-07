import java.sql.*;

public class TransactionsDemo {
    private static final String URL = "jdbc:postgresql://localhost:5432/demo_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to the Database!");



            try {
                // TURNED OFF AUTO COMMIT == NO AUTO SAVE
                conn.setAutoCommit(false);

                // DB table schema
                // Order(id, user_id, customer_name, total_amount)
                // OrderItems(id, order_id <- fk, product_name, quantity, price)

                // INSERT INTO ORDER
                int orderId = insertOrder(conn, 101, "Alice01", 2000.0);

                // INSERT INTO ORDER ITEM
                insertOrderItem(conn, orderId, "Laptop-1", 1, 2000.0);

                // MANUAL COMMIT
                conn.commit();
                System.out.println("Transaction Committed Successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                System.out.println("Operation rollback successfully.");
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private static int insertOrder(Connection conn, int customerId, String customerName, double price) {
        // prepared statements
        String SQL = "INSERT INTO orders (user_id, customer_name, total_amount) " +
                "VALUES (?, ?, ?)";

        try(PreparedStatement pstmt = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, customerId);
            pstmt.setString(2, customerName);
            pstmt.setDouble(3, price);

            int rows = pstmt.executeUpdate();
            System.out.println("INSERTED into orders: " + rows);

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if(rs.next()) {
                    int orderId = rs.getInt(1);
                    System.out.println("ORDER ID: " + orderId);
                    return orderId;
                } else {
                    throw new SQLException("Order ID not generated");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }


    private static void insertOrderItem(Connection conn, int orderId, String productName, int quantity, double price) {


        String SQL = "INSERT INTO order_items (order_id, product_name, quantity, price) " +
                "VALUES (?, ?, ?, ?)";

        try(PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, orderId);
            pstmt.setString(2, productName);
            pstmt.setInt(3, quantity);
            pstmt.setDouble(4, price);

            // transaction fail case
            // error
//            int x = 10/0;

            int rows = pstmt.executeUpdate();
            System.out.println("INSERTED into order_items: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
