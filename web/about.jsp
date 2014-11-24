<html>
    <head>
        <link rel="stylesheet" href="style.css" />
        <title>Team #### Scouting</title>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

        <!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

        <script type='text/javascript' src="js/hover.js"></script>

    </head>
    <body>
        <% request.getSession().setAttribute("mode", "about");
            if (request.getSession().getAttribute("login") == null) {
                request.getSession().setAttribute("login", "false");
            }%>
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

        <section>

            <h1>About</h1>
            <div id="teamimage" class="inline">
                <img src="img/team.jpg" class="inlineimage"/>
                <center class="captioncenter"><h2 class="caption">The Team</h2></center></div> <!-- CSS wouldn't work here so <center> it is -->
                <p>This app was developed starting in the 2013-2014 post-season and is currently undergoing development. It aims to simplify and consolidate the scouting efforts of FRC teams like us. The source code for this app can be found <a href="http://github.com/team5163/scoutingapp">Here</a>.</p><br />
                <p>Thank you to the following people for helping test the application: <!--Table here--></p>
        </section>

        <footer>
            <ul><li><a href="about.jsp" class="current">About</a></li>

                <% if (!request.getSession().getAttribute("login").toString().equalsIgnoreCase("true")) {%> 
                <li><a href="login.jsp">Log In or Sign Up</a></li>
                    <%} else {%>
                <form action="Server" id="logout">
                    <input type="hidden" name="keepin" value="false" />
                    <li><a class="link" onClick="document.getElementById('logout').submit();">Log Out</a></li>
                </form>
                <%}%>
            </ul>
            <h2 id="credittext">@</h2>
        </footer>

    </body>

</html>
</html>
