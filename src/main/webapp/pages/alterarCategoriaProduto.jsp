<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<link rel="stylesheet" href="../cascading-style-sheets/cadastrarUsuario.css">
<div id="navbar">
    <p id="navbar-Constroo">Constroo</p>
    <div id="navbar-line"></div>
</div>
<h1 id="titulo-Cadastrar-Usuario">Alterar</h1>
<form action="AlterarAdministradorServlet" method="post" >
    <h2 class="subtitulo-forms"></h2>
    <div class="infos-linha">
        <label for="Nome">Nome</label>
        <input type="text" name="nome" id="nome">
        <label for="Nome">Descricao</label>
        <input type="text" name="descricao" id="descricao">
        <input type="hidden" name="id" value=<%=request.getAttribute("id") %>>
    </div>
    <input type="submit" id="buttonCadastrar">
</form>
</body>
</html>
