<html>
    <head>
        <link rel="stylesheet" href="style.css" />
        <title>Scouting App</title>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

        <!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

        <script type='text/javascript' src="js/hover.js"></script>
        <script>
            var URLTeamList = "Data?type=teamList&teamNumber=";
            function setFrame1(url) {
                document.getElementById("teamviewframe").setAttribute("src", url);
            }
            function setTeamList(url) {
                document.getElementById("teamlistframe").setAttribute("src", url + document.getElementById("search").value);
            }
        </script>

    </head>
    <body>

        <% //String tablename = request.getSession().getAttribute("tablename").toString();
            String tablename = "teamdata";
            
            String[][] summary = Team5163.ObjectRegistry.getDataBase().summarizeDatabase(tablename);
            out.println(summary[0][0]);
        %>

        
    </body>

</html>