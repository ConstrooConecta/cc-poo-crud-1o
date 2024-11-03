<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="../ErrorPage.jsp" %>
<meta charset="UTF-8">
<html>
<head>
    <title>Editar Categotia Produto</title> <!-- Título da página -->
</head>
<body>
<!-- <link rel="stylesheet" href="alterarAdministrador.css">  -->
<!-- <link rel="stylesheet" href="alterar2.css"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/alterarCategoriaProduto.css"> <!-- Link para a folha de estilo -->

<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1>
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo">  <!-- Logo da aplicação -->
    </div>

    <div class="cruds"> <!-- Navegação principal com links para diferentes seções -->
        <ul>
            <li><a href="${pageContext.request.contextPath}/pages/plano/listagemPlanos.jsp">Planos</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/categoriaProduto/listagemCategoriaProdutos.jsp" class="active">Categorias</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/administrador/listagemAdministradores.jsp" >Adms</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/tagServico/listagemTagServico.jsp">Tag Serviço</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/planoAtivacao/listagemPlanosAtivacao.jsp">Planos Ativação</a></li>
        </ul>
    </div>
</header>

<h1 id="titulo-Editar-Categoria-Produto">Editar Categoria Produto</h1> <!-- Título principal da seção -->

<form action="AlterarCategoriaProdutoServlet" method="post"> <!-- Formulário para alteração da categoria -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo (atualmente vazio) -->
    <div class="infos-linha"> <!-- Linha que agrupa as informações do formulário -->

        <div class="gridAtualNome"> <!-- Div para o nome atual -->
            <label>Nome Atual:</label>
            <input type="text" value="<%= request.getAttribute("nome") %>" disabled> <!-- Campo de entrada para nome atual, desabilitado -->
        </div>

        <div class="gridNovoNome"> <!-- Div para o novo nome -->
            <label for="nome">Novo Nome:</label> <!-- Rótulo para o campo de novo nome -->
            <input type="text" name="nome" id="nome" value="<%= request.getAttribute("nome") %>" placeholder="Novo nome"> <!-- Campo de entrada para novo nome -->
        </div>

        <div class="gridAtualDescricao"> <!-- Div para a descrição atual -->
            <label>Descrição Atual:</label>
            <input type="text" value="<%= request.getAttribute("descricao") %>" disabled> <!-- Campo de entrada para descrição atual, desabilitado -->
        </div>

        <div class="gridNovaDescricao"> <!-- Div para a nova descrição -->
            <label for="descricao">Nova Descrição:</label> <!-- Rótulo para o campo de nova descrição -->
            <input type="text" name="descricao" id="descricao" value="<%= request.getAttribute("descricao") %>" placeholder="Descrição nova"> <!-- Campo de entrada para nova descrição -->
        </div>

        <input type="hidden" name="id" value="<%=request.getAttribute("id") %>"> <!-- Campo oculto para ID da categoria -->

    </div>

    <div class="classButton">
        <input type="submit" id="buttonEditar"> <!-- Botão de envio do formulário -->
    </div>

</form>
</body>
</html>
