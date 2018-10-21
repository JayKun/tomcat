<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <title>Edit Post</title>
</head>
<body>
    <div class="container">
    <div class="display-4"><h1>Edit Post</h1></div>
    <form action="post" method="POST" class="form-group">
        <div>
            <button name="action" type="submit" value="save" class="btn btn-info">Save</button>
            <button name="action" type="submit" value="list" class="btn btn-secondary">Close</button>
            <button name="action" type="submit" value="preview" class="btn btn-success">Preview</button>
            <button name="action" type="submit" value="delete" class="btn btn-danger">Delete</button>
        </div>
        <div class="jumbotron">
                <label for="title">Title</label>
                <input name="title" type="text" id="title" value="${post.getTitle()}" class="form-control">
                <label for="body">Body</label>
                <textarea name="body" style="height: 20rem;" id="body" class="form-control"/><c:out value="${post.getBody()}"/></textarea>
        </div>
            <input name="username" type="hidden" value=<%=request.getAttribute("username")%> class="form-control" />
            <input name="postid" type="hidden" value=<%=request.getAttribute("postid")%> class="form-control" />
    </form>
    </div>
</body>
</html>
