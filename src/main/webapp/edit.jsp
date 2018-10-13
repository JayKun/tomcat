<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Post</title>
</head>
<body>
    <%= request.getAttribute("title") %> ! <br>
    <div><h1>Edit Post</h1></div>
    <form action="post" method="POST">
        <div>
            <button name="action" type="submit" value="save">Save</button>
            <button name="action" type="submit" value="list">Close</button>
            <button name="action" type="submit" value="preview">Preview</button>
            <button name="action" type="submit" value="delete">Delete</button>
        </div>
        <div>
            <label for="title">Title</label>
            <input name="title" type="text" id="text">
        </div>
        <div>
            <label for="body">Body</label>
            <textarea name="body" style="height: 20rem;" id="body"></textarea>
        </div>
    </form>
</body>
</html>
