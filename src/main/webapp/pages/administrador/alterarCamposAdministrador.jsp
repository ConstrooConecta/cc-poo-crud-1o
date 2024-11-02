<%@ page errorPage="../ErrorPage.jsp" %>
<html>
<head>
    <title>Editar Administrador</title> <!-- Título da página -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/alterarAdministrador.css"> <!-- Importação do CSS -->
</head>
<body>

<!-- Inclusão da página que contém o formulário ou outras informações -->
<section>
    <jsp:include page="alterarAdministradorPeloID.jsp" />
</section>

<!-- Formulário para alteração das informações do administrador -->
<form action="AlterarAdministradorServlet" method="post"> <!-- Ação do formulário -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo (vazio) -->

    <div class="infos-linha"> <!-- Linha que agrupa as informações a serem editadas -->
        <!-- Seção para exibir e editar o nome do administrador -->
        <div class="gridAtualNome">
            <label>Nome Atual:</label>
            <input type="text" value="<%= request.getAttribute("nome") %>" disabled> <!-- Nome atual, campo desabilitado -->
        </div>

        <div class="gridNovoNome">
            <label for="nome">Novo Nome:</label> <!-- Rótulo para o campo de novo nome -->
            <input type="text" name="nome" id="nome" value="<%= request.getAttribute("nome") %>" placeholder="Novo nome" required> <!-- Campo para inserir o novo nome -->
        </div>

        <!-- Seção para exibir e editar o e-mail do administrador -->
        <div class="gridAtualEmail">
            <label>Email Atual:</label>
            <input type="text" value="<%= request.getAttribute("email") %>" disabled> <!-- E-mail atual, campo desabilitado -->
        </div>

        <div class="gridNovoEmail">
            <label for="email">Novo Email:</label> <!-- Rótulo para o campo de novo e-mail -->
            <input type="email" name="email" id="email" value="<%= request.getAttribute("email") %>" placeholder="Email novo" required> <!-- Campo para inserir o novo e-mail -->
        </div>

        <!-- Seção para inserir uma nova senha -->
        <div class="gridNovaSenha">
            <label for="senha">Nova Senha:</label> <!-- Rótulo para o campo de nova senha -->
            <input type="password" name="senha" id="senha" placeholder="Senha nova"> <!-- Campo para inserir a nova senha -->
            <input type="hidden" name="id" value="<%= request.getAttribute("id") %>"> <!-- Campo oculto que armazena o ID do administrador -->
        </div>
    </div>

    <div class="classButton">
        <input type="submit" id="buttonCadastrar" value="Atualizar"> <!-- Botão para enviar o formulário -->
    </div>
</form>

<!-- Bloco para exibir mensagens de retorno após a submissão do formulário -->
<% if ("erro".equals(request.getAttribute("retorno"))) { %>
<div>
    <p><%= request.getAttribute("mensagem") %></p> <!-- Mensagem de erro a ser exibida -->
</div>
<% } %>
</body>
</html>
