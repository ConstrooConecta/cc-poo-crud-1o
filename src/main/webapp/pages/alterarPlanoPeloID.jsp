<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="ErrorPage.jsp" %>
<html>
<head>
    <title>Title</title> <!-- Título da página -->
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/cadastrarUsuario.css"> <!-- Importação do CSS -->
<div id="navbar"> <!-- Navegação -->
    <p id="navbar-Constroo">Constroo</p> <!-- Nome da marca -->
    <div id="navbar-line"></div> <!-- Linha de separação -->
</div>
<h1 id="titulo-Cadastrar-Usuario">Alterar</h1> <!-- Título principal -->
<form action="${pageContext.request.contextPath}/IncluirCamposPlanoServlet" method="post"> <!-- Formulário para alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo -->
    <div class="infos-linha"> <!-- Linha de informações -->
        <label for="plano_id">Insira o ID do Plano</label> <!-- Rótulo para nome -->
        <input type="number" name="plano_id" id="plano_id" required> <!-- Campo de entrada para nome -->
    </div>

        <% if (request.getAttribute("retorno") == "erro"){
        %>
    <div>
        <P><%=request.getAttribute("mensagem") %></P>

    </div>
        <%} else if (request.getAttribute("retorno") == "notfound") {%>
    <div>
        <p>ITEM NÃO ENCONTRADO</p>
    </div>
<%}%>
    <input type="submit" id="buttonCadastrar"> <!-- Botão de envio -->
</form>
</body>
</html>
