<?php
	include_once "backend_integration.php";

echo delete($_POST["id"]);
?>
<script>
    setTimeout(function() {parent.location="index.php";}, 3000);
</script>