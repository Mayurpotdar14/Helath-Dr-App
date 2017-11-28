<?php
$response=array();

		$userKey=(int)$_POST['userKey'];					
		$con=mysql_connect("localhost","root","");
				if(!$con)
				{
					die('Could not Connect'.mysql_error());
				}
		mysql_select_db("mis",$con);

		$result = mysql_query("SELECT UK_L_ID FROM user_key WHERE (UK_KEY='$userKey' && UK_STATUS=0 ) ")or die(mysql_error());
		
		if(mysql_num_rows($result)>0)
		{
			//$response["key_result"]=array();
				while($row=mysql_fetch_array($result))
				{
					//$place=array();
					//$place["UK_L_ID"]=$row["UK_L_ID"];					
					//array_push($response["key_result"],$place);


					$resultId=$row["UK_L_ID"];
					$response["key_result"]=$resultId;
					
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