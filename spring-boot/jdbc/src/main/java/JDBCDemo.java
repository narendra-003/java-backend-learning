import java.sql.*;


public class JDBCDemo {

    private static final String URL = "jdbc:postgresql://localhost:5432/demo_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {


        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
            System.out.println("Connected to the Database!");

//            insertStudent(conn,"Alice", "alice@gmail.com");
//            updateStudent(conn, 2, "Bob", "bob23@gmail.com");
            selectStudent(conn);
            deleteStudent(conn, 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertStudent(Connection conn, String name, String email) {
        String SQL = String.format("INSERT INTO student (name, email) VALUES ('%s', '%s')", name, email);

        try(Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(SQL);
            System.out.println("INSERTED: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void selectStudent(Connection conn) {
        String SQL = "SELECT * FROM student";

        try(Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(SQL);

            System.out.println("Student List: ");
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                System.out.println(id + " : " + name + " : " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateStudent(Connection conn, int id, String name, String email) {
        String SQL = String.format("UPDATE student SET name = '%s', email = '%s' WHERE id = %d", name, email, id);

        try (Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(SQL);
            System.out.println("UPDATED: " + rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteStudent(Connection conn, int id) {
        String SQL = String.format("DELETE FROM student WHERE id = %d", id);

        try (Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(SQL);
            System.out.println("DELETED: " + rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


//        try {
//            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Connected to the Database!");
//        } catch (SQLException e) {
//        e.printStackTrace();
//        } finally {
//                try {
//                conn.close();
//                System.out.println("Connection closed.");
//            } catch (SQLException e) {
//        e.printStackTrace();
//            }
//         }