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
<form action="${pageContext.request.contextPath}/AlterarPlanoAtivacaoServlet" method="post"> <!-- Formulário para alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo -->
    <div class="infos-linha"> <!-- Linha de informações -->
        <label for="id_planoativacao">Insira o ID do Plano Ativação</label> <!-- Rótulo para nome -->
        <input type="number" name="id_planoativacao" id="id_planoativacao" required> <!-- Campo de entrada para nome -->
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
