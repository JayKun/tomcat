<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List Post</title>
</head>
<body>
    <%= ArrayList<Post> posts = getAttribute("results") %>
    <%= request.getAttribute("title") %> ! <br>
    <div><h1>Post List</h1></div>
        <table>
        <tbody><tr><th>Title</th><th>Created</th><th>Modified</th><th>&nbsp;</th></tr>
        <%= for(post in posts) { %>
        <tr>
            <form id=<%= post.getPostId() %> action="post" method="POST"></form> 
                <input name="username" value=<%= post.getUsername() %> type="hidden">
                <input name="postid" value=<%= post.getPostId() %> type="hidden">
                <td></td>
                <td><%= post.getCreated() %></td>
                <td><%= post.getModified() %>></td>
                <td>
                    <button type="submit" name="action" value="open">Open</button>
                    <button type="submit" name="action" value="delete">Delete</button>
                </td>
        <%= } %>
        </tr>
    </tbody></table>
</body>
</html>
