<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page = "alterarAdministradorPeloID.jsp" />
<%@ page errorPage="ErrorPage.jsp" %>
<!-- Definição do tipo de conteúdo e linguagem -->
<html>
<head>
    <title>Title</title> <!-- Título da página -->
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/cadastrarUsuario.css"> <!-- Importação do CSS -->
<form action="AlterarAdministradorServlet" method="post"> <!-- Formulário para alteração -->
    <div class="infos-linha"> <!-- Linha de informações -->
        <label>Nome Atual:</label>
        <input type="text" value="<%= request.getAttribute("nome") %>" disabled>
        <label for="nome">Novo Nome:</label> <!-- Rótulo para nome Atual -->
        <input type="text" name="nome" id="nome" value="<%= request.getAttribute("nome") %>"> <!-- Campo de entrada para Nome Novo -->
        <label>Email Atual:</label>
        <input type="text" value="<%= request.getAttribute("email") %>" disabled>
        <label for="email">Novo Email:</label> <!-- Rótulo para email -->
        <input type="email" name="email" id="email" value="<%=request.getAttribute("email")%>"> <!-- Campo de entrada para email -->
        <label for="senha">Nova Senha:</label> <!-- Rótulo para senha -->
        <input type="password" name="senha" id="senha" value=""> <!-- Campo de entrada para senha -->
        <input type="hidden" name="id" value=<%=request.getAttribute("id") %>> <!-- Campo oculto para ID -->
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
