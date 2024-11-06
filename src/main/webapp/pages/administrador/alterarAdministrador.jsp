<%@ page errorPage="../ErrorPage.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <!-- Define tipo de conteúdo e codificação para UTF-8 -->

<html>
<head>
    <title>Editar Administrador</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/alterarAdministrador.css"> <!-- Importação do CSS -->
</head>
<body>

<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1>
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo">  <!-- Logo da aplicação -->
    </div>

    <div class="cruds"> <!-- Navegação principal com links para diferentes seções -->
        <ul>
            <li><a href="${pageContext.request.contextPath}/pages/plano/listagemPlanos.jsp">Planos</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/categoriaProduto/listagemCategoriaProdutos.jsp">Categorias</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/administrador/listagemAdministradores.jsp" class="active">Adms</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/tagServico/listagemTagServico.jsp">Tag Serviço</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/planoAtivacao/listagemPlanosAtivacao.jsp">Planos Ativação</a></li>
        </ul>
    </div>
</header>

<h1 id="titulo-Editar-Administrador">Editar Administrador</h1> <!-- Título principal -->

<!-- Formulário para alteração dos dados do administrador -->
<form action="AlterarAdministradorServlet" method="post">
    <div class="infos-linha"> <!-- Contêiner para as informações do administrador -->

        <!-- Nome atual e novo nome -->
        <div class="gridAtualNome">
            <label>Nome Atual:</label>
            <input type="text" value="<%= request.getAttribute("nome") %>" disabled> <!-- Nome atual, campo desabilitado -->
        </div>
        <div class="gridNovoNome">
            <label for="nome">Novo Nome:</label>
            <input type="text" name="nome" id="nome" value="<%= request.getAttribute("nome") %>" placeholder="Novo nome" required> <!-- Campo para novo nome -->
        </div>

        <!-- Email atual e novo email -->
        <div class="gridAtualEmail">
            <label>Email Atual:</label>
            <input type="text" value="<%= request.getAttribute("email") %>" disabled> <!-- Email atual, campo desabilitado -->
        </div>
        <div class="gridNovoEmail">
            <label for="email">Novo Email:</label>
            <input type="email" name="email" id="email" value="<%= request.getAttribute("email") %>" placeholder="Email novo" required> <!-- Campo para novo email -->
        </div>

        <!-- Nova senha -->
        <div class="gridNovaSenha">
            <label for="senha">Nova Senha:</label>
            <input type="password" name="senha" id="senha" placeholder="Senha nova"> <!-- Campo para nova senha -->
            <input type="hidden" name="id" value="<%= request.getAttribute("id") %>"> <!-- ID do administrador -->
        </div>
    </div>

    <div class="classButton">
        <input type="submit" id="buttonCadastrar" value="Atualizar"> <!-- Botão de envio -->
    </div>
</form>

<!-- Mensagens de erro, se houver -->
<% if ("erro".equals(request.getAttribute("retorno"))) { %>
<div>
    <p><%= request.getAttribute("mensagem") %></p> <!-- Mensagem de erro -->
</div>
<% } %>
</body>
</html>
