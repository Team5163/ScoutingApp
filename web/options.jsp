<html>
<head>
<link rel="stylesheet" href="style.css" />
<title>Team #### Scouting</title>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

<script type='text/javascript' src="js/hover.js"></script>

</head>
<body>
        <% request.getSession().setAttribute("mode", "options");
            if (request.getSession().getAttribute("login") == null) {
                request.getSession().setAttribute("login", "false");
            }%>
<header>

<a href="view.html" id="logo"></a>

<nav>

<a href="#" id="menu-icon"></a>

<ul>

<li><a href="view.html">View</a></li>
<li><a href="compare.html">Compare</a></li>
<li><a href="edit.html">Edit</a></li>
<li><a href="suggest.html">Suggest</a></li>
<li><a href="options.html" class="current">Options</a></li>

</ul>

</nav>
<h1 id="border"> </h1>
</header>

<section>

<h1>Placeholder Text</h1>

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
</html>