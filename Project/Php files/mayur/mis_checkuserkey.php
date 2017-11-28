<?php
$response=array();

		$userId=(int)$_POST['userId'];
		$userStatus=(int)$_POST['userStatus'];

		$con=mysql_connect("localhost","root","");
				if(!$con)
				{
					die('Could not Connect'.mysql_error());
				}
		mysql_select_db("mis",$con);

		$result = mysql_query("SELECT UK_ID,UK_KEY FROM user_key WHERE (UK_L_ID='$userId' && UK_STATUS='$userStatus' ) ")or die(mysql_error());
		
		if(mysql_num_rows($result)>0)
		{
			$response["userkey_result"]=array();
				while($row=mysql_fetch_array($result))
				{
					$place=array();
					$place["UK_ID"]=$row["UK_ID"];
					$place["UK_KEY"]=$row["UK_KEY"];
					array_push($response["userkey_result"],$place);								
				}
			$response["success"]=1;
			echo json_encode($response);
		}
	else
	{
		$response["success"]=0;
		echo json_encode($response);
	}
 
		
	mysql_close($con);
	?>