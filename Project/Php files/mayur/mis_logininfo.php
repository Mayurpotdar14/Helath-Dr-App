<?php
$response=array();
if(isset($_POST['username']) && isset($_POST['password']) && isset($_POST['roll']) && isset($_POST['name']) && isset($_POST['address']) && isset($_POST['mobileno']) && isset($_POST['age']) && isset($_POST['gender']) &&  isset($_POST['city']))  
{	
	$regno='';
	if( isset($_POST['regno'])){
		$regno=$_POST['regno'];
	}

		$username=$_POST['username'];
		$password=$_POST['password'];
		$roll=$_POST['roll'];
		$name=$_POST['name'];
		$address=$_POST['address'];
		$mobileno=$_POST['mobileno'];
		$age=$_POST['age'];
		$city=$_POST['city'];
		$gender=$_POST['gender'];

		$con=mysql_connect("localhost","root","");
				if(!$con)
				{
					die('Could not Connect'.mysql_error());
				}
		mysql_select_db("mis",$con);

	   $result = mysql_query("INSERT INTO login VALUES('NULL','$username','$password','$roll')");
       $newId=mysql_insert_id();

	   $finalresult = mysql_query("INSERT INTO user_reg_info (URI_ID,URI_L_ID,URI_NAME,URI_ADDRESS,URI_AGE,URI_GENDER,URI_CITY,URI_REG_NO,URI_MOB_NO)
	   VALUES('NULL','$newId','$name','$address','$age','$gender','$city','$regno','$mobileno')")or die(mysql_error());
 
				 // check if row inserted or not
					if ($finalresult) 
					 {
				 // successfully inserted into database
					$response["success"] = 1;
				
					// echoing JSON response
			        echo json_encode($response);
					} 
					else 
					{
			        // failed to insert row
			        $response["success"] = 0;
			       
			        // echoing JSON response
			        echo json_encode($response);
					}

	}
else 
	{
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
	}
	mysql_close($con);
	?>