<?php
require_once '../koneksi.php';

$id=$_GET['id'];

$query="Select * from db_mahasiswa where id='$id'";

$sql=mysqli_query($connect,$query);

$array=array();

while($row=mysqli_fetch_array($sql)){
  array_push($array,array(
    'id_mhs'=>$row['id'],
    'nama_mhs'=>$row['nama'],
    'kelas_mhs'=>$row['kelas']

  ));

}
echo json_encode($array);
mysqli_close($connect);



 ?>
