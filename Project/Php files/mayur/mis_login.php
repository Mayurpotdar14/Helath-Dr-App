<?php
$response=array();

		$username=$_POST['username'];
		$roll=$_POST['roll'];
		
		
		$con=mysql_connect("localhost","root","");
				if(!$con)
				{
					die('Could not Connect'.mysql_error());
				}
		mysql_select_db("mis",$con);

		$result = mysql_query("SELECT L_ID,L_USERNAME,L_ROLL FROM login WHERE (L_USERNAME='$username' && L_ROLL='$roll' ) ")or die(mysql_error());
		
		if(mysql_num_rows($result)>0)
		{
			$response["login_result"]=array();
				while($row=mysql_fetch_array($result))
				{
					$place=array();
					$place["L_ID"]=$row["L_ID"];
					$place["L_USERNAME"]=$row["L_USERNAME"];
					$place["L_ROLL"]=$row["L_ROLL"];
					array_push($response["login_result"],$place);
					
					
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