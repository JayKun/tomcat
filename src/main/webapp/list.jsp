<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>
<head>
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <title>Post List</title>
</head>
<body>
    <div class="container">
    <form action="post" method="POST">
        <input name="username" value=<%= request.getAttribute("username") %> type="hidden">
        <input name="postid" value="0" type="hidden">
        <button type="submit" name="action" value="open" class="btn btn-info">New Post</button>
    </form>
    </div>
    <div class="container">
        <h1>Post List</h1>
        <table class="table">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Title</th>
                <th scope="col">Created</th>
                <th scope="col">Modified</th>
                <th scope="col">&nbsp;</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${posts}" var="post">
        <tr>
        <form action="post" method="POST">
        <input name="username" value=<%= request.getAttribute("username")%> type="hidden">
        <input name="postid" value="${post.getPostId()}" type="hidden">
        <input name="title" value="${post.getTitle()}" type="hidden">
        <input name="body" value="${post.getBody()}" type="hidden">
        <td><c:out value="${post.getTitle()}"/></td>
        <td><c:out value="${post.getCreated()}"/></td>
        <td><c:out value="${post.getModified()}"/></td>
        <td>
            <button type="submit" name="action" value="open" class="btn btn-primary">Open</button>
            <button type="submit" name="action" value="delete" class="btn btn-delete">Delete</button>
        </td>
        </form>
        </tr>
        </c:forEach>
        </tbody>
        </table>
    </div>
</body>
</html>
