import java.sql.*

public class Post
{
    private int postid
    private String username;
    private String title;
    private String body;
    private TimeStamp modified;
    private TimStamp created;

    public Post(int id, String username, String title, String body)
    {
        super();
        this.postid = id;
        this.username = username;
        this.title = title;
        this.body = body;
    }

    public int getPostId()
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

    public String getModified()
    {
        return modified.toString();
    }

    public String getCreated()
    {
        return created.toString();
    }
}
