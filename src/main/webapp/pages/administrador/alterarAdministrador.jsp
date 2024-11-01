<%@ page errorPage="../ErrorPage.jsp" %>
<html>
<head>
    <title>Editar Administrador</title> <!-- Título da página -->
</head>
<body>
<!-- Importação do arquivo CSS para estilizar a página de edição do administrador -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/alterarAdministrador.css">

<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1> <!-- Nome do aplicativo -->
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo"> <!-- Logo do aplicativo -->
    </div>
</header>

<h1 id="titulo-Editar-Administrador">Editar Administrador</h1> <!-- Título principal da seção de edição -->

<!-- Formulário que será enviado ao servlet para alteração dos dados do administrador -->
<form action="AlterarAdministradorServlet" method="post"> <!-- Ação do formulário, que chama o servlet responsável pela alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo (vazio) -->

    <div class="infos-linha"> <!-- Contêiner para as informações do administrador -->
        <!-- Seção para exibir e editar o nome do administrador -->
        <div class="gridAtualNome">
            <label>Nome Atual:</label>
            <input type="text" value="<%= request.getAttribute("nome") %>" disabled> <!-- Nome atual, campo desabilitado -->
        </div>

        <div class="gridNovoNome">
            <label for="nome">Novo Nome:</label> <!-- Rótulo para o campo de novo nome -->
            <input type="text" name="nome" id="nome" value="<%= request.getAttribute("nome") %>" placeholder="Novo nome"> <!-- Campo para inserir o novo nome -->
        </div>

        <!-- Seção para exibir e editar o email do administrador -->
        <div class="gridAtualEmail">
            <label>Email Atual:</label>
            <input type="text" value="<%= request.getAttribute("email") %>" disabled> <!-- Email atual, campo desabilitado -->
        </div>

        <div class="gridNovoEmail">
            <label for="email">Novo Email:</label> <!-- Rótulo para o campo de novo email -->
            <input type="email" name="email" id="email" value="<%= request.getAttribute("email") %>" placeholder="Email novo"> <!-- Campo para inserir o novo email -->
        </div>

        <!-- Seção para alterar a senha do administrador -->
        <div class="gridNovaSenha">
            <label for="senha">Nova Senha:</label> <!-- Rótulo para o campo de nova senha -->
            <input type="password" name="senha" id="senha" placeholder="Senha nova"> <!-- Campo para inserir a nova senha -->
            <input type="hidden" name="id" value="<%= request.getAttribute("id") %>"> <!-- Campo oculto para passar o ID do administrador -->
        </div>
    </div>

    <div class="classButton">
        <input type="submit" id="buttonCadastrar"> <!-- Botão de envio do formulário -->
    </div>
</form>

<!-- Exibe mensagens de erro, se houver -->
<% if (request.getAttribute("retorno") == "erro") { %>
<div>
    <p><%= request.getAttribute("mensagem") %></p> <!-- Mensagem de erro a ser exibida -->
</div>
<% } %>
</body>
</html>
