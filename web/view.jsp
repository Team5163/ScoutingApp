<html>
<head>
<link rel="stylesheet" href="style.css" />
<title>Team #### Scouting</title>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

<script type='text/javascript' src="js/hover.js"></script>

<script>
            var URLTeamList = "Data?type=teamList&teamNumber=";
            function setFrame1(url){
                document.getElementById("teamviewframe").setAttribute("src", url);
            }
            function setTeamList(url){
                document.getElementById("teamlistframe").setAttribute("src", url + document.getElementById("search").value);
            }
</script>

</head>
<body>

    <% request.getSession().setAttribute("mode", "view"); %>
    
<header>
<a href="view.html" id="logo"></a>

<nav>

<a href="#" id="menu-icon"></a>

<ul>

<li><a href="view.html" class="current">View</a></li>
<li><a href="compare.html">Compare</a></li>
<li><a href="edit.html">Edit</a></li>
<li><a href="suggest.html">Suggest</a></li>
<li><a href="options.html">Options</a></li>

</ul>

<h3 id="pagetitle">View</h3>

</nav>
<h1 id="border"> </h1>
</header>

<section id="teamlist">
<form id="teamlistform">
<input id="search" type="text" name="teamNumber" placeholder="Search Teams" oninput="setTeamList(URLTeamList)"/>        
</form>
<iframe id="teamlistframe" src="Data?type=teamList"></iframe>

</section>

<section id="teamview">

<iframe id="teamviewframe" src="Data?type=viewPage&teamNumber=0000"></iframe>

</section>

<footer>
<ul><li><a href="about.html">About</a></li> <li><a href="login.html">Log In or Sign Up</a></li></ul>
<h2 id="credittext">@</h1>
</footer>

</body>

</html>
</html>