<body data-gr-c-s-loaded="true" class="vsc-initialized">
        <div>
            <form action="post" method="POST">
                <input name="username" value="${username}" type="hidden">
                <input name="postid" value="${postid}" type="hidden">
                <input name="title" value="${title}" type="hidden">
                <input name="body" value="${body}" type="hidden">
                <button type="submit" name="action" value="open">Close Preview</button>
            </form>
        </div>
        <div>
            <h1> <%= request.getAttribute("markdowntitle") %> </h1>
            <div id="body">
                <%= request.getAttribute("markdownbody") %>
            </div>
        </div>


</body>
