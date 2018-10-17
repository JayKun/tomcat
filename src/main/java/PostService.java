package project2.service;

import project2.model.Post;

import java.sql.*;
import java.util.ArrayList;

public class PostService
{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/CS144";
    
    // DB Credentials
    static final String dbUsername = "cs144";
    static final String dbPassword = "";   
    
    public static Connection getConnection()
    {
        Connection conn = null;
        
        /* load the driver */
        try
        {
            Class.forName(JDBC_DRIVER);
            
            /* create an instance of a Connection object */
            conn = DriverManager.getConnection(DB_URL, dbUsername, dbPassword);
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println(ex);
        }
        return conn;
    }
        
    public static void addPost(int postId, String username, String title, String body)
    {
        try
        {   
            Connection conn = null;
            conn = getConnection();
            PreparedStatement stmt = null; 

            stmt = conn.prepareStatement(
                "INSERT INTO Posts(postid, username, title, body, created, modified) " +
                "VALUES(?, ?, ?, ?, NOW(), NOW())");

            stmt.setInt(1, Post.getPostId());
            stmt.setString(2, username);
            stmt.setString(3, title);
            stmt.setString(4, body);

            Post.incrementPostId();

            stmt.executeUpdate();
            System.out.println("writeJavaObject");

            try { conn.close(); } catch (Exception e) {}
            try { stmt.close(); } catch (Exception e) {}
        }       
        catch (SQLException ex)
        {
            System.out.println(ex);
            return;
        }   
    }

    // Get all the posts associated with a username
    public static ArrayList<Post> getPosts(String username)
    {
        try
        {   
            System.out.println("Username is: " + username);
            Connection conn = null;
            conn = getConnection();
            PreparedStatement stmt = null; 
            String sqlQuery = "SELECT * FROM Posts WHERE username=?";

            stmt = conn.prepareStatement(sqlQuery);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            System.out.println("ReadJavaObject");
            
            ArrayList<Post> results = new ArrayList<Post>();
            
            while(rs.next())
            {
                String title = rs.getString("title");
                int postId = rs.getInt("postid");
                String body = rs.getString("body");
                Timestamp created = rs.getTimestamp("created");
                Timestamp modified = rs.getTimestamp("modified");             
                Post post = new Post(postId, username, title, body, created, modified);
                results.add(post);
                System.out.println(title);
            }

            try { conn.close(); } catch (Exception e) {}
            try { stmt.close(); } catch (Exception e) {}
            try { rs.close(); } catch (Exception e) {}
            
            return results;
        }       
        catch (SQLException ex)
        {
            System.out.println(ex);
            return null;
        }   

    }   
    
    // Delete post associated with a username and a postid
    public static void deletePost(String username, int postId)
    {
        try
        {   
            System.out.println("Username is: " + username);
            Connection conn = null;
            conn = getConnection();
            PreparedStatement stmt = null; 
            String sqlQuery = "DELETE FROM Posts WHERE username=? AND postid=?";

            stmt = conn.prepareStatement(sqlQuery);

            stmt.setString(1, username);
            stmt.setString(2, postId);

            ResultSet rs = stmt.executeQuery();
            System.out.println("DeleteJavaObject");
            
            try { conn.close(); } catch (Exception e) {}
            try { stmt.close(); } catch (Exception e) {}
            try { rs.close(); } catch (Exception e) {}
            
            return;
        }       
        catch (SQLException ex)
        {
            System.out.println(ex);
            return null;
        }   

    }   
}
