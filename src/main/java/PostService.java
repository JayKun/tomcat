import java.sql.*

public class PostService
{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/CS144";
    
    // DB Credentials
    static final String username = "cs144";
    static final String password = "";   
    static Connection c = null;
    static Statement s = null;
    static ResultSet rs = null;
    
    public static void initConnection()
    {
        /* load the driver */
        try
        {
            Class.forName("JDBC_DRIVER");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println(ex);
            return;
        }
        
        /* create an instance of a Connection object */
        c = DriverManager.getConnection("DB_URL, username, password);
    }

    public static void closeConnection()
    {
        try { rs.close(); } catch (Exception e) { /* ignored */ }
        try { s.close(); } catch (Exception e) { /* ignored */ }
        try { c.close(); } catch (Exception e) { /* ignored */ }
    }
        
    public static void addPost(String username, String title, int postId)
    {
        initConnection();   
        Post p = new Post(username, title, postId);
        s = c.createStatement();
        
        String sqlQuery = String.format("INSERT INTO Posts VALUES(%s, %s, %2d)", username, title, postId);
        s.executeUpdate(sqlQuery);

        closeConnection();
    }   
}
