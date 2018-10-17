import java.io.IOException;
import java.io.BufferedReader;
import java.sql.* ;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.LinkedHashMap;
import java.lang.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import project2.service.PostService;

public class Editor extends HttpServlet {
    public Editor() {}

    public void init() throws ServletException
    {
        /*  write any servlet initialization code here or remove this function */
    }
    
    public void destroy()
    {
        /*  write any servlet cleanup code here or remove this function */
    }

    /**
     * Handles HTTP GET requests
     * 
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
	// implement your GET method handling code here
	// currently we simply show the page generated by "edit.jsp"
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }
    
    public static Map<String, String> splitQuery(String queryString)
    {
        Map<String, String> queryPairs = new LinkedHashMap<String, String>();
        String[] pairs = queryString.split("&");
        for(String pair : pairs)
        {
            int index = pair.indexOf("=");
            queryPairs.put(pair.substring(0, index), pair.substring(index+1));
        }
        return queryPairs;
    }

    public static String getQueryString(HttpServletRequest request)
        throws IOException
    { 
	// Parse query string
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while((line = reader.readLine()) != null)
        {
            buffer.append(line);
        }
        return buffer.toString();
    }
    /**
     * Handles HTTP POST requests
     * 
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        String queryString = getQueryString(request);

        Map<String, String> queryPairs = splitQuery(queryString);
        String action = queryPairs.get("action");
        
        request.setAttribute("username", queryPairs.get("username"));
        
        switch(action)
        {
            case "open":
            {
                String username = queryPairs.get("username");
                int postId = Integer.parseInt(queryPairs.get("postid"));
                request.setAttribute("title", "Open");
                break;
            }      
            case "save":
            {
                request.setAttribute("title", "Save");

                int postId = Integer.parseInt(queryPairs.get("postid"));
                String username = queryPairs.get("username");
                String title = queryPairs.get("title");
                String body = queryPairs.get("body");

                PostService.addPost(postId, username, title, body);

                break;
            }
            case "delete":
            {
                request.setAttribute("title", "Delete");
                break;
            }
            case "preview":
            {
                request.setAttribute("title", "Preview");
                break;
            }
            case "list":
            {
                request.setAttribute("title", "List");
                String username = queryPairs.get("username");
                ArrayList posts = PostService.getPosts(username);
                System.out.println("Size of array is " + posts.size()); 
                request.setAttribute("posts", posts);
                request.getRequestDispatcher("/list.jsp").forward(request, response);
                break;
            }
            default:
            {
                System.out.println("Action not recognized");
            }
        }

	// Render "edit.jsp"
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }
}

