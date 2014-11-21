<html>
    <head>
        <link rel="stylesheet" href="style.css" />
        <title>Team #### Scouting</title>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

        <!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

        <script type='text/javascript' src="js/hover.js"></script>

    </head>
    <body>
        <% request.getSession().setAttribute("mode", "view");
            if (request.getSession().getAttribute("login") == null) {
                request.getSession().setAttribute("login", "false");
            }
        %>
        <header>

            <a href="view.html" id="logo"></a>

            <nav>

                <a href="#" id="menu-icon"></a>

                <ul>

                    <li><a href="view.jsp">View</a></li>
                    <li><a href="compare.jsp">Compare</a></li>
                    <li><a href="rank.jsp">Rank</a></li>
                    <li><a href="edit.jsp">Edit</a></li>
                    <li><a href="suggest.jsp">Suggest</a></li>
                    <li><a href="options.jsp">Options</a></li>

                </ul>

            </nav>
            <h1 id="border"> </h1>
        </header>

        <section id="loginsection">
            <h2>If you already have an account, use the form below to sign in.</h2><br />
            <form id="login" method="POST" action="Login"><input type="textbox" placeholder="User Name" class="logininput formtop" name="user" required /><br /><input type="password" placeholder="Password" class="logininput" name="pass" required /><br /><input type="submit" value="Log In" class="loginbutton formbottom"/></form>

        </section>

        <section id="signupsection">
            <h2>At this time, signing up is only possible via an invite. To request an invite, kindly fill out the form below.</h2>
            <form id="signup"></form>
        </section>
        <footer>
            <ul><li><a href="about.jsp">About</a></li>

                <% if (!request.getSession().getAttribute("login").toString().equalsIgnoreCase("true")) {%> 
                <li><a href="login.jsp" class="current">Log In or Sign Up</a></li>
                    <%} else {%>
                <form action="Server" id="logout">
                    <input type="hidden" name="keepin" value="false" />
                    <li><a class="link" onClick="document.getElementById('logout').submit();">You are already logged in. Log Out?</a></li>
                </form>
                <%}%>
            </ul>
            <h2 id="credittext">@</h2>
        </footer>
    </body>

</html>
</html>