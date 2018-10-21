<html>
<head>
    <meta charset="utf-8">
       <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <title>Markdown Preview</title>
</head>
<body>
        <div class="container">
            <form action="post" method="POST">
                <input name="username" value="${username}" type="hidden">
                <input name="postid" value="${postid}" type="hidden">
                <input name="title" value="${title}" type="hidden">
                <input name="body" value="${body}" type="hidden">
                <button type="submit" name="action" value="open" class="btn btn-warning">Close Preview</button>
            </form>
        </div>
        <div class="container">
            <h1> <%= request.getAttribute("markdowntitle") %> </h1>
            <div id="body">
                <%= request.getAttribute("markdownbody") %>
            </div>
        </div>
</body>
</html>
