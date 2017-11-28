<?php

$response=array();

if(isset($_POST['medicinelist'])){
$medicineList=$_POST['medicinelist'];
$diseaseName=$_POST['diseaseName']; 
$diseaseDescription=$_POST['diseaseDescription']; 

$con=mysql_connect("localhost","root","");
				if(!$con)
				{
					die('Could not Connect'.mysql_error());
				}
		mysql_select_db("mis",$con);

	   $result = mysql_query("INSERT INTO disease_info (DI_ID,DI_NAME,DI_DESCRIPTION) VALUES('NULL','$diseaseName','$diseaseDescription')")or die(mysql_error());
       $newId=mysql_insert_id();

$array = json_decode($medicineList);

$sql="INSERT INTO medicine_info (";
foreach ($array as $key => $jsons) { 
     foreach($jsons as $key => $value) {
		$sql .=$key.",";		
    }
	break;	
}
$sql .="MI_DI_ID";

$sql =$sql . ")VALUES(";

foreach ($array as $key => $jsons) { 
     foreach($jsons as $key => $value) {
		$sql .="'".$value."'".",";
		// echo $key;
    }

$sql .=$newId;

$sql =$sql . "),(";		
}

$sql=trim($sql, ",");
$sql=trim($sql, "(");
$sql=trim($sql, ",");

$finalresult = mysql_query($sql) or die(mysql_error());

 // check if row inserted or not
	if ($finalresult) 
	{
		$response["success"] = 1;
		echo json_encode($response);
	} 
	else 
	{
		$response["success"] = 0;
		echo json_encode($response);
	}


mysql_close($con);
//echo $sql;

}	
	?>