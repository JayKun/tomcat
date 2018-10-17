<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Post List</title>
</head>
<body>
    <%= request.getAttribute("title") %> ! <br>
    <div>
    <form action="post" method="POST">
        <input name="username" value=<%= request.getAttribute("username") %> type="hidden">
        <input name="postid" value="0" type="hidden">
        <button type="submit" name="action" value="open">New Post</button>
    </form>
    </div>
    <div><h1>Post List</h1></div>
        <table>
        <tbody><tr><th>Title</th><th>Created</th><th>Modified</th><th>&nbsp;</th></tr>
        <c:forEach items="${posts}" var="post">
        <tr>
        <form action="post" method="POST">
        <input name="username" value=<%= request.getAttribute("username")%> type="hidden">
        <input name="postid" value="${post.getPostId()}" type="hidden">
        <td><c:out value="${post.getTitle()}"/></td>
        <td><c:out value="${post.getCreated()}"/></td>
        <td><c:out value="${post.getModified()}"/></td>
        <td>
            <button type="submit" name="action" value="open">Open</button>
            <button type="submit" name="action" value="delete">Delete</button>
        </td>
        </form>
        </tr>
        </c:forEach>
    </tbody></table>
</body>
</html>
