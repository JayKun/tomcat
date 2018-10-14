package project2.service;

import project2.model.Post;

import java.sql.*;

public class PostService
{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/CS144";
    
    // DB Credentials
    static final String username = "cs144";
    static final String password = "";   
    
    public static Connection getConnection()
    {
        Connection conn = null;
        /* load the driver */
        try
        {
            Class.forName("JDBC_DRIVER");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println(ex);
            return null;
        }
        
        /* create an instance of a Connection object */
        try
        {
            conn = DriverManager.getConnection(DB_URL, username, password);
            return conn;
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }
        
    public static void addPost(String user, String title, int postId)
    {
        Connection conn = null;
        try
        {
            Class.forName(JDBC_DRIVER);
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println(ex);
        }
        
        /* create an instance of a Connection object */
        try
        {
            conn = DriverManager.getConnection(DB_URL, username, password);
            Statement s = null; 
            s = conn.createStatement();
            if(postId <= 0)
            {
                String sqlQuery = 
      "INSERT INTO Posts(username, title, postid, body) VALUES('das', 'ds', 1, 'das')";
                Post.incrementPostId();

                try
	        {
		    s.executeUpdate(sqlQuery);
                    System.out.println("writeJavaObject");
	        }
	        catch (SQLException ex)
	        {
	            System.out.println(ex);
	            return;
	        }
            }
            try { conn.close(); } catch (Exception e) {}
            try { s.close(); } catch (Exception e) {}
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
            return;
        }
   
    }   
}
