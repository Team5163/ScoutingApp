<html>
    <head>
        <link rel="stylesheet" href="style.css" />
        <title>Scouting App</title>

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
            <a href="view.jsp" id="logo"></a>

            <nav>

                <a href="#" id="menu-icon"></a>

                <ul>

                    <li><a href="view.jsp">View</a></li>
                    <li><a href="compare.jsp">Compare</a></li>
                    <li><a href="rank.jsp" class="current">Rank</a></li>
                    <li><a href="edit.jsp">Edit</a></li>
                    <li><a href="suggest.jsp">Suggest</a></li>
                    <li><a href="options.jsp">Options</a></li>

                </ul>

                <h3 id="pagetitle">View</h3>

            </nav>
            <h1 id="border"> </h1>
        </header>

        <section id="ranking">

            <table>

                <tr><td>Test</td><td>Test</td></tr>

            </table>
            <!-- Replace this with servlet after testing -->
            <%
            
            out.println(Team5163.ObjectRegistry.getDataBase().getTeamMatches("1934").length);
            
            %>
        </section>

        <footer>
            <ul><li><a href="about.jsp">About</a></li>

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