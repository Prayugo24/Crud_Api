<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

  $id_mhs=$_POST['id_mhs'];
  $nama_mhs=$_POST['nama_mhs'];
  $kelas_mhs=$_POST['kelas_mhs'];
  require_once '../koneksi.php';
  $sql="UPDATE db_mahasiswa SET nama='$nama_mhs',kelas='$kelas_mhs' where id=$id_mhs";

  if(mysqli_query($connect,$sql)){
    echo "Berhasil ubah data";
  }else {
    echo "gagal ubah data";
  }

}

// if($_SERVER['REQUEST_METHOD']=='GET'){
//
//   $id_mhs=$_GET['id_mhs'];
//   $nama_mhs=$_GET['nama_mhs'];
//   $kelas_mhs=$_GET['kelas_mhs'];
//   require_once '../koneksi.php';
//   $sql="UPDATE db_mahasiswa SET nama='$nama_mhs',kelas='$kelas_mhs' where id=$id_mhs";
//
//   if(mysqli_query($connect,$sql)){
//     echo "Berhasil ubah data";
//   }else {
//     echo "gagal ubah data";
//   }
//
// }
 ?>
