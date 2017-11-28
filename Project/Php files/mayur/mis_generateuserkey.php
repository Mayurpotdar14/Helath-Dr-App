<?php
$response=array();
if(isset($_POST['userId']) && isset($_POST['status']) &&  isset($_POST['key']) )  
{	
	

		$userId=(int)$_POST['userId'];
		$status=(int)$_POST['status'];
		$key=(double)$_POST['key'];
		

		$con=mysql_connect("localhost","root","");
				if(!$con)
				{
					die('Could not Connect'.mysql_error());
				}
		mysql_select_db("mis",$con);

	  

	   $result = mysql_query("INSERT INTO user_key (UK_ID,UK_L_ID,UK_STATUS,UK_KEY)
	   VALUES('NULL','$userId','$status','$key')")or die(mysql_error());
 
				 // check if row inserted or not
					if ($result) 
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