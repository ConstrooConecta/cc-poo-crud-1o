<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page = "alterarPlanoPeloID.jsp" />
<%@ page errorPage="ErrorPage.jsp" %>
<!-- Definição do tipo de conteúdo e linguagem -->
<html>
<head>
    <title>Title</title> <!-- Título da página -->
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/cadastrarUsuario.css"> <!-- Importação do CSS -->
<form action="AlterarPlanoServlet" method="post"> <!-- Formulário para alteração -->
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

    <% if (request.getAttribute("retorno") == "erro"){
    %>
    <div>
        <P><%=request.getAttribute("mensagem")%></P>

    </div>
    <%}%>
    </div>
    <input type="submit" id="buttonCadastrar"> <!-- Botão de envio -->
</form>
</body>
</html>
