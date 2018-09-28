<?php
$objConnect = mysql_connect("127.0.0.1","root","");
$objDB = mysql_select_db("students_database");

$strID = isset($_POST["id"]);
$strName = isset($_POST["name"]);
$strFatherName = isset($_POST["fatherName"]);
$strAddress = isset($_POST["address"]);
$strDateOfBirth = isset($_POST["dateOfBirth"]);
$strDateOfAdmission = isset($_POST["dateOfAdmission"]);
$strGrade = isset($_POST["grade"]);

/*** Update ***/
$strSQL = " UPDATE students SET
name = '".$strName."',
fatherName = '".$strFatherName."',
address = '".$strAddress."',
dateOfBirth = '".$strDateOfBirth."',
dateOfAdmission = '".$strDateOfAdmission."',
grade = '".$strGrade."'
WHERE id = '".$strID."' ";

$objQuery = mysql_query($strSQL);
if(!$objQuery)
{
     $arr['StatusID'] = "0";
     $arr['Message'] = "Cannot save data!";   
}
else
{
     $arr['StatusID'] = "1";
     $arr['Message'] = "updated";
}
mysql_close($objConnect);
echo json_encode($arr);
?>