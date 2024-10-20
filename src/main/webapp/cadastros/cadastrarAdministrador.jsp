<%--
  Created by IntelliJ IDEA.
  User: matheusueno-ieg
  Date: 19/10/2024
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/cadastrarUsuario.css">
<div id="navbar">
    <p id="navbar-Constroo">Constroo</p>
    <div id="navbar-line"></div>
</div>
<h1 id="titulo-Cadastrar-Usuario">Cadastrar Administrador</h1>
<form action="${pageContext.request.contextPath}/InserirAdministradorServlet" method="post" >
    <h2 class="subtitulo-forms"></h2>

    <div class="infos-linha">
        <label for="Nome">Nome</label>
        <input type="text" name="nome" id="nome">
        <label for="email">Email</label>
        <input type="text" name="email" id="email">
        <label for="senha">Senha</label>
        <input type="password" name="senha" id="senha">

    </div>

        <% if (request.getAttribute("retorno") == "erro"){
        %>
    <div>
        <P><%=request.getAttribute("mensagem")%></P>

    </div>
<%}%>

    <input type="submit" id="buttonCadastrar">


</form>
</body>
</html>