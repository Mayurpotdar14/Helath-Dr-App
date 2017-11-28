<?php
$response=array();

		$userId=$_POST['userId'];				
		$con=mysql_connect("localhost","root","");
				if(!$con)
				{
					die('Could not Connect'.mysql_error());
				}
		mysql_select_db("mis",$con);

		$result = mysql_query("SELECT distinct uri.URI_NAME,uri.URI_L_ID FROM medicine_info mi, user_reg_info uri WHERE (mi.MI_L_PATIENT_ID ='$userId' && mi.MI_L_DOCTOR_ID = uri.URI_L_ID) ")or die(mysql_error());
		
		if(mysql_num_rows($result)>0)
		{
			$response["doctorName"]=array();
				while($row=mysql_fetch_array($result))
				{
					$place=array();
					$place["URI_NAME"]=$row["URI_NAME"];
					$place["URI_ID"]=$row["URI_L_ID"];
					array_push($response["doctorName"],$place);

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