<?php 

	// $con = mysqli_connect("hostname","username","password","database_name");

   $con = mysqli_connect("localhost","id20964230_darshan","Darshan@2905","id20964230_darshan");

   $temp   = array();

   if($con)
   {
    $temp['connection']  = 1;
   }
   else 
   {
    $temp['connection']  = 0;
   }
	$loginid = $_POST['userid'];
	$name = $_POST['pname'];
	$price = $_POST['pprize'];
	$description = $_POST['pdes'];
	$imagedata = $_POST['productimage'];

		$realimage = base64_decode($imagedata);

		$imagename = "ProductImage/".$name.rand(0,10000).rand(0,10000).".jpg";

		file_put_contents($imagename, $realimage);

		$qry = "insert into Products(UID,PRO_NAME,PRO_DES,PRO_PRICE,PRO_IMAGE) values ('$loginid','$name','$description','$price','$imagename')";
		
		$sql = mysqli_query($con, $qry);
		if($sql)
		{
			$temp['productaddd'] = 1;
		}
		else
		{
			$temp['productaddd'] = 0;
		}
	echo json_encode($temp);
 ?>