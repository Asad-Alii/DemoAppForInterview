<?php
$servername = "http://localhost:8080"; //replace it with your database server name
$username = "root@localhost";  //replace it with your database username
$password = "";  //replace it with your database password
$dbname = "students_database";
// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
?>