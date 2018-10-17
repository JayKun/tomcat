package project2.model;

import java.sql.*;

public class Post
{
    private static int postId = 0;
    private String username;
    private String title;
    private String body;
    private Timestamp modified;
    private Timestamp created;

    public Post(int postid, String username, String title, String body, Timestamp modified, Timestamp created)
    {
        super();
        this.postId = postid;
        this.username = username;
        this.title = title;
        this.body = body;
        this.modified = modified;
        this.created = created;
    }

    public static int getPostId()
    {
        return postId;
    }

    public String getTitle()
    {
        return title;
    }

    public String getBody()
    {
        return body;
    }
    
    public String getUsername()
    {
        return username;
    }

    public String getModified()
    {
        return modified.toString();
    }

    public String getCreated()
    {
        return created.toString();
    }

    public static void incrementPostId()
    {
        postId++;
    }
}
