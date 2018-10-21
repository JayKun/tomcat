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
import project2.model.Post;

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
    *    Parse and render to HTML
    *
    */
    public String parseMarkdown(String markdown)
    {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
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
        
        if (!request.getParameterMap().containsKey("username"))
        {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        String username = request.getParameter("username");
        String action = request.getParameter("action");
        request.setAttribute("username", username);
         
        switch(action)
        {
            case "open":
            {
                int postId = Integer.parseInt(request.getParameter("postid")); 
                request.setAttribute("postid", postId);
                
                Post result = null;
                if(postId > 0)
                {
                    result = PostService.getPost(username, postId);
                }
                else
                {
                    String title = request.getParameter("title");
                    String body = request.getParameter("body");
                    result = new Post(postId, username, title, body, null, null);
                }
                request.setAttribute("post", result);
                break;
            }      
            case "preview":
            {
                int postId = Integer.parseInt(request.getParameter("postid")); 
                request.setAttribute("postid", postId);

                String title = request.getParameter("title");
                request.setAttribute("title", title);
                
                String body = request.getParameter("body");
                request.setAttribute("body", body);

                String markdowntitle = parseMarkdown(title);
                String markdownbody = parseMarkdown(body);
                request.setAttribute("markdowntitle", markdowntitle);
                request.setAttribute("markdownbody", markdownbody);

		request.getRequestDispatcher("/preview.jsp").forward(request, response);
                break;
            }
            case "list":
            {
                request.setAttribute("title", "List");
                ArrayList posts = PostService.getPosts(username);
                
                request.setAttribute("posts", posts);
                request.getRequestDispatcher("/list.jsp").forward(request, response);
                break;
            }
            default:
            {
                System.out.println("Action not recognized");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }

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
        // String queryString = getQueryString(request);

        // Map<String, String> queryPairs = splitQuery(queryString);

        String action = request.getParameter("action");
        String username = request.getParameter("username");
        int postId = Integer.parseInt(request.getParameter("postid"));
   
        request.setAttribute("username", username);
        request.setAttribute("postid", postId);       
 
        switch(action)
        {
            case "open":
            {
                request.setAttribute("title", "Open");
                Post result = PostService.getPost(username, postId);
                if(result == null)
                {
                                  
                }
                request.setAttribute("post", result);
                break;
            }      
            case "save":
            {
                request.setAttribute("title", "Save");
                String title = request.getParameter("title");
                String body = request.getParameter("body");
                System.out.println("Body is " + body);
                PostService.savePost(postId, username, title, body);

                ArrayList posts = PostService.getPosts(username);
                
                request.setAttribute("posts", posts);
                request.getRequestDispatcher("/list.jsp").forward(request, response);
                break;
            }
            case "delete":
            {
                request.setAttribute("title", "Delete");
                PostService.deletePost(username, postId);                
                
                ArrayList posts = PostService.getPosts(username);
                
                request.setAttribute("posts", posts);
                request.getRequestDispatcher("/list.jsp").forward(request, response);

                break;
            }
            case "preview":
            {
                String title = request.getParameter("title");
                String body = request.getParameter("body");
                request.setAttribute("title", title);
                request.setAttribute("body", body);

                String markdowntitle = parseMarkdown(title);
                String markdownbody = parseMarkdown(body);
                request.setAttribute("markdowntitle", markdowntitle);
                request.setAttribute("markdownbody", markdownbody);

                request.getRequestDispatcher("/preview.jsp").forward(request, response);
                
                break;
            }
            case "list":
            {
                request.setAttribute("title", "List");
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

