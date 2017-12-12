<?php
require_once '../koneksi.php';
$id=$_POST['id_mhs'];


$sql="DELETE FROM db_mahasiswa where id='$is'";
if(mysqli_query($connect,$sql){
  echo "Berhasil dihapus";
}else {
  echo "gagal dihapus";
}

 ?>
