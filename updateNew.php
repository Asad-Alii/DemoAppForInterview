<?php
$objConnect = mysql_connect("127.0.0.1","root","");
$objDB = mysql_select_db("students_database");

 $stmt = ("UPDATE students SET name = ?, fatherName = ?, address = ?, dateOfBirth = ?, dateOfAdmission = ?, grade = ? WHERE id = ?");
 $stmt->bind_param("ssisi", "naveed", "haroon", "korangi", "1990-06-14", "1992-06-14", "3", "4");
 if($stmt->execute())
 return true; 
 return false; 

 ?>