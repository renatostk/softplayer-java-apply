<?php 
include_once "utils.php";

my_start_session();

if(strcmp($_POST["user"] , "admin") == 0 
&& strcmp($_POST["pass"] , "admin") == 0 ){

	$_SESSION["logado"] = true;
}

?>
<script>
	parent.location="index.php";
</script>