<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page = "alterarTagServicoPeloID.jsp" />
<%@ page errorPage="ErrorPage.jsp" %>
<!-- Definição do tipo de conteúdo e linguagem -->
<html>
<head>
    <title>Title</title> <!-- Título da página -->
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/cadastrarUsuario.css"> <!-- Importação do CSS -->
<form action="AlterarTagServicoServlet" method="post"> <!-- Formulário para alteração -->
    <div class="infos-linha"> <!-- Linha de informações -->
        <label>Nome Atual:</label>
        <input type="text" value="<%= request.getAttribute("nome") %>" disabled>
        <label for="nome">Novo Nome:</label> <!-- Rótulo para nome Atual -->
        <input type="text" name="nome" id="nome" value="<%= request.getAttribute("nome") %>"> <!-- Campo de entrada para Nome Novo -->
        <label>Descricao Atual:</label>
        <input type="text" value="<%= request.getAttribute("descricao") %>" disabled>
        <label for="Descricao">Nova Descricao:</label> <!-- Rótulo para descrição -->
        <input type="text" name="descricao" id="descricao" value="<%=request.getAttribute("descricao") %>"> <!-- Campo de entrada para descrição -->
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
