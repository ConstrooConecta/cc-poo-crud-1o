<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
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
<form action="${pageContext.request.contextPath}/IncluirCamposTagServicoServlet" method="post"> <!-- Formulário para alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo -->
    <div class="infos-linha"> <!-- Linha de informações -->
        <label for="tag_id">Insira o ID do Tag Serviço</label> <!-- Rótulo para nome -->
        <input type="number" name="tag_id" id="tag_id" required> <!-- Campo de entrada para nome -->
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
