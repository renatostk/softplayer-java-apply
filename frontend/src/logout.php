<?php 
include_once "utils.php";

my_start_session();

unlink($_SESSION["logado"]);

?>
<script>
	parent.location="index.php";
</script>