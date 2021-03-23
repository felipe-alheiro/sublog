/*




*/


<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html lang="pt-br">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">

    <title>C.R.U.D 02 - Contato</title>
  </head>
  <body>
  	<div class="container-fluid col-6">
		<fieldset>
			<legend>Cadastro de Contato</legend>
	  		<form action="addcontato" method="post">
	  			<div>
	  				<label for="nome_id">Nome</label>
	  				<input type="text" class="form-control" name="inputNome" id="nome_id" placeholder="informe seu nome" required>
	  			</div>
	  			<div>
	  				<label for="sobrenome_id">Sobrenome</label>
	  				<input type="text" class="form-control" name="inputSobrenome" id="sobrenome_id" placeholder="informe seu sobrenome" required>
	  			</div>
	  			<div>
	  				<label for="cpf_id">CPF</label>
	  				<input type="text" class="form-control" name="inputCPF" id="cpf_id" placeholder="informe o cpf" required>
	  			</div>
	  			<div>
	  				<label for="email_id">Email</label>
	  				<input type="email" class="form-control" name="inputEmail" id="email_id" placeholder="informe seu email" required>
	  			</div>
	  			<div>
	  				<label for="dtNascimento_id">Data de Nascimento</label>
	  				<input type="date" class="form-control" name="inputDate" id="date_id" required>
	  			</div>
	  			<div>
	  				<label for="telefone_id">Telefone</label>
	  				<input type="tel" class="form-control" name="inputddi" id="ddi_id" placeholder="informe o DDI" required>
	  				<input type="tel" class="form-control" name="inputddd" id="ddd_id" placeholder="informe o DDD" required>
	  				<input type="tel" class="form-control" name="inputTelefone" id="telefone_id" placeholder="informe o telefone" required>
	  			</div>
	  			<div>
	  				<label for="tipologradouro_id">Tipo de Logradouro</label>
	  				<input type="text" class="form-control" name="inputTipoLogradouro" id="tipologradouro_id" placeholder="informe o tipo de logradouro" required>
	  			</div>
	  			<div>
	  				<label for="logradouro_id">Logradouro</label>
	  				<input type="text" class="form-control" name="inputLogradouro" id="logradouro_id" placeholder="informe o logradouro" required>
	  			</div>
	  			<div>
	  				<label for="complemento_id">Complemento</label>
	  				<input type="text" class="form-control" name="inputComplemento" id="complemento_id" placeholder="informe complementos" required>
	  			</div>
	  			<div>
	  				<label for="bairro_id">Logradouro</label>
	  				<input type="text" class="form-control" name="inputBairro" id="bairro_id" placeholder="informe o bairro" required>
	  			</div>
	  			
	  			<div>
	  				<label for="cidade_id">Cidade</label>
	  				<input type="text" class="form-control" name="inputCidade" id="cidade_id" placeholder="informe o cidade" required>
	  			</div>
	  			<div>
	  				<label for="estado_id">Estado</label>
	  				<input type="text" class="form-control" name="inputEstado" id="estado_id" placeholder="informe o estado" required>
	  			</div>
	  			<div>
	  				<label for="pais_id">País</label>
	  				<input type="text" class="form-control" name="inputPais" id="pais_id" placeholder="informe o país" required>
	  			</div>
	  			
	  			<div class="d-grid gap-2 col-6 mx-auto my-2">
		  			<button type="submit" class="btn btn-dark">Gravar</button>
	  			</div>
	  		</form>    
		</fieldset>
  	</div>

    <!-- Optional JavaScript; choose one of the two! -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
    -->
  </body>
</html>