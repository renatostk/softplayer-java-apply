<?php
include_once "utils.php";
include_once "backend_integration.php";

 my_start_session();
?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <style type="text/css">
        	form{
        		display: block;
        		margin:auto;
        		width: fit-content;
        	}
			span
			{
        		display: inline-block;
        		width: 200px;
        	}
			h1
			{ 
				text-align: center; 
			}
			button
			{
				float: right;
				vertical-align: right;
			}
        </style>
    </head>
    <body>
    	<form method="GET" action="source">
    		<button>Source</button>
    	</form><br /><br />
        <?php
			if(!isset($_SESSION["logado"])) {
        ?>
        <form method="POST" action="login.php">
			<div>
				<span>Usuario:</span>
				<input type="text" name="user" />
			</div>
			<div>
				<span>Senha:  </span>
				<input type="password" name="pass" />
			</div>
			<button>Login</button>
		</form>

		<?php }else if(isset($_POST["button"]) && strcmp($_POST["button"] , "cadastrar") == 0){ ?>

			<h1>Novo Cadastro</h1>
			<form method="POST" action="cadastrar.php">
				<div>
					<span>Nome:</span>
					<input type="text" name="name"/>
				</div>
				<div>
					<span>CPF:</span>
					<input type="text" name="cpf" 
					placeholder="123.456.789-10"
					/>
				</div>
				<div>
					<span>Sexo:</span>
					<select name="sexo">
						<option value="F" >Feminino</option>
						<option value="M" >Masculino</option>
					</select>		 
				</div>
				<div>
					<span>Aniversário:</span>
					<input type="text" name="birthday"
					placeholder="2020-12-31"				
					/>
				</div>
				<div>
					<span>E-Mail:</span>
					<input type="text" name="email"/>
				</div>
				<div>
					<span>Nacionalidade:</span>
					<input type="text" name="nacionalidade"/>
				</div>
				<div>
					<span>Naturalidade:</span>
					<input type="text" name="naturalidade"/> 
				</div>
				<div>
					<button>Salvar</button>
				</div>
			</form>
			<br />
			<form method="POST">
		        <div>
		        	<button name="button" value="voltar" >Voltar</button>
		        </div>
	    	</form>

		<?php }else if(isset($_POST["button"]) && strcmp($_POST["button"] , "editar") == 0){ ?>
			
			<?php $person = json_decode(readById($_POST["id"]));?>
			<h1>Editar Cadastro</h1>
			<form method="POST" action="editar.php">
				<div>
					<span>ID:</span>
					<?php echo $person->id;?>
					<input type="hidden" name="id" value="<?php echo $person->id;?>" />
				</div>				
				<div>
					<span>Nome:</span>
					<input type="text" name="name" value="<?php echo $person->name;?>" />
				</div>
				<div>
					<span>CPF:</span>
					<input type="text" name="cpf" value="<?php echo $person->cpf;?>" 
					placeholder="123.456.789-10" 
					/>
				</div>
				<div>
					<span>Sexo:</span>
					<select name="sexo">
						<option value="F" <?php if(strcmp("F",$person->sexo)==0){echo "selected";}?>>Feminino</option>
						<option value="M" <?php if(strcmp("M",$person->sexo)==0){echo "selected";}?>>Masculino</option>
					</select>
				</div>
				<div>
					<span>Aniversário:</span>
					<input type="text" name="birthday" value="<?php echo $person->birthday;?>" 
					placeholder="2020-12-31"				
					/>
				</div>
				<div>
					<span>E-Mail:</span>
					<input type="text" name="email" value="<?php echo $person->email;?>" />
				</div>
				<div>
					<span>Nacionalidade:</span>
					<input type="text" name="nacionalidade" value="<?php echo $person->nacionalidade;?>" />
				</div>
				<div>
					<span>Naturalidade: </span>
					<input type="text" name="naturalidade" value="<?php echo $person->naturalidade;?>" /> 
				</div>
				<div>
					<button>Salvar</button></div>
			</form>

	    	<br/>
			<form method="POST" action="deletar.php">
				<input type="hidden" name="id" value="<?php echo $person->id;?>" />
		        <div><button>Excluir</button></div>
	    	</form>

			<br/>
			<form method="POST">
		        <div>
		        	<button name="button" value="voltar" >Voltar</button>
		        </div>
	    	</form>


		<?php } else { ?>

		<form method="POST">
	        <button name="button" value="cadastrar" >Cadastrar Novo</button>
	    </form>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>CPF</th>
				<th>Sexo</th>
				<th>Aniversário</th>
				<th>E-Mail</th>
				<th>Nacionalidade</th>
				<th>Naturalidade</th>
				<th>Cadastro</th>
				<th>Atualização</th>
				<th></th>
			</tr>

			<?php foreach(json_decode(readAll()) as $person){?>
			<form method="POST">
				<input type="hidden" name="id" value="<?php echo $person->id;?>"/>
				<tr>
					<td><?php echo $person->id;?></td>
					<td><?php echo $person->name;?></td>
					<td><?php echo $person->cpf;?></td>
					<td><?php echo $person->sexo;?></td>
					<td><?php echo $person->birthday;?></td>
					<td><?php echo $person->email;?></td>
					<td><?php echo $person->nacionalidade;?></td>
					<td><?php echo $person->naturalidade;?></td>
					<td><?php echo $person->insertDate;?></td>
					<td><?php echo $person->updateDate;?></td>
					<td><button name="button" value="editar">Editar</button></td>	
				</tr>
			</form>
			<?php } ?>
		</table>
		<form method="POST" action="logout.php">
			<button>Logout</button>
		</form>
		<?php } ?>
    </body>
</html>
