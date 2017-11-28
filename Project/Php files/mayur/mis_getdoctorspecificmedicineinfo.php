<?php
$response=array();

		$userId=$_POST['userId'];
		$doctorId=$_POST['doctorId'];

		$con=mysql_connect("localhost","root","");
				if(!$con)
				{
					die('Could not Connect'.mysql_error());
				}
		mysql_select_db("mis",$con);

		$result = mysql_query("SELECT mi.MI_MEDICINE_NAME, di.DI_NAME,di.DI_DESCRIPTION,mi.MI_DI_ID, mi.MI_MEDICINE_TYPE, mi.MI_MEDICINE_QUANTITY, mi.MI_MEDICINE_DAYS, mi.MI_MEDICINE_DOSE, mi.MI_DATE FROM medicine_info mi, disease_info di WHERE (
		mi.MI_L_PATIENT_ID ='$userId' && mi.MI_L_DOCTOR_ID ='$doctorId' && mi.MI_STATUS =0 && di.DI_ID = mi.MI_DI_ID)")or die(mysql_error());
		
		if(mysql_num_rows($result)>0)
		{
			$response["medicineInfo"]=array();
				while($row=mysql_fetch_array($result))
				{
					$place=array();
					$place["MI_MEDICINE_NAME"]=$row["MI_MEDICINE_NAME"];
					$place["DI_NAME"]=$row["DI_NAME"];
					$place["DI_DESCRIPTION"]=$row["DI_DESCRIPTION"];					
					$place["MI_DI_ID"]=$row["MI_DI_ID"];
					$place["MI_MEDICINE_TYPE"]=$row["MI_MEDICINE_TYPE"];
					$place["MI_MEDICINE_QUANTITY"]=$row["MI_MEDICINE_QUANTITY"];
					$place["MI_MEDICINE_DAYS"]=$row["MI_MEDICINE_DAYS"];
					$place["MI_MEDICINE_DOSE"]=$row["MI_MEDICINE_DOSE"];
					$place["MI_DATE"]=$row["MI_DATE"];
					array_push($response["medicineInfo"],$place);
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