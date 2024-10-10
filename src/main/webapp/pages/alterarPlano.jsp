<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/cadastrarUsuario.css">
<div id="navbar">
    <p id="navbar-Constroo">Constroo</p>
    <div id="navbar-line"></div>
</div>
<h1 id="titulo-Cadastrar-Usuario">Alterar</h1>
<form action="AlterarPlanoServlet" method="post" >
    <h2 class="subtitulo-forms"></h2>
    <div class="infos-linha">
        <label for="Nome">Nome</label>
        <input type="text" name="nome" id="nome" value=<%=request.getAttribute("nome") %>>
        <label for="Descricao">Descricao</label>
        <input type="text" name="descricao" id="descricao" value=<%=request.getAttribute("descricao") %>>
        <label for="Duracao">Duracao</label>
        <input type="text" name="duracao" id="duracao" value=<%=request.getAttribute("duracao") %>>
        <label for="Valor">Valor</label>
        <input type="number" name="valor" id="valor" value=<%=request.getAttribute("valor") %>>
        <label for="Valor">Tipo Usuario</label>
        <input type="text" name="tipo" id="tipo" value=<%=request.getAttribute("tipo") %>>
        <input type="hidden" name="id" value=<%=request.getAttribute("id") %>>
    </div>
    <input type="submit" id="buttonCadastrar">
</form>
</body>
</html>
