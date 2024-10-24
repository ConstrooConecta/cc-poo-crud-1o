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
<form action="AlterarPlanoServlet" method="post"> <!-- Formulário para alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo -->
    <div class="infos-linha"> <!-- Linha de informações -->
        <label>Nome Atual:</label>
        <input type="text" value="<%= request.getAttribute("nome") %>" disabled>
        <label for="nome">Nome Novo</label> <!-- Rótulo para nome Atual -->
        <input type="text" name="nome" id="nome" value="<%= request.getAttribute("nome") %>"> <!-- Campo de entrada para Nome Novo -->
        <label>Descricao Atual:</label>
        <input type="text" value="<%= request.getAttribute("descricao") %>" disabled>
        <label for="Descricao">Nova Descricao:</label> <!-- Rótulo para descrição -->
        <input type="text" name="descricao" id="descricao" value="<%=request.getAttribute("descricao") %>"> <!-- Campo de entrada para descrição -->
        <label>Duracao Atual:</label>
        <input type="text" value="<%= request.getAttribute("duracao") %>" disabled>
        <label for="Duracao">Nova Duracao:</label> <!-- Rótulo para duração -->
        <input type="text" name="duracao" id="duracao" value="<%=request.getAttribute("duracao")%>"> <!-- Campo de entrada para duração -->
        <label>Valor Atual:</label>
        <input type="text" value="<%= request.getAttribute("valor") %>" disabled>
        <label for="Valor">Novo Valor:</label> <!-- Rótulo para valor -->
        <input type="number" name="valor" id="valor" value="<%=request.getAttribute("valor")%>"> <!-- Campo de entrada para valor -->
        <label>Tipo Usuario Atual:</label>
        <input type="text" value="<%= request.getAttribute("tipo") %>" disabled>
        <label for="Tipo">Novo Tipo Usuario:</label> <!-- Rótulo para tipo de usuário -->
        <input type="text" name="tipo" id="tipo" value="<%=request.getAttribute("tipo") %>"> <!-- Campo de entrada para tipo de usuário -->
        <input type="hidden" name="id" value="<%=request.getAttribute("id") %>"> <!-- Campo oculto para ID -->
    </div>
    <input type="submit" id="buttonCadastrar"> <!-- Botão de envio -->
</form>
</body>
</html>
