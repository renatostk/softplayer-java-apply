<?php
	include_once "backend_integration.php";

echo update($_POST["id"],$_POST["name"],$_POST["cpf"],$_POST["sexo"],$_POST["birthday"],$_POST["email"],$_POST["nacionalidade"],$_POST["naturalidade"]);
?>
<script>
    setTimeout(function() {parent.location="index.php";}, 3000);
</script>