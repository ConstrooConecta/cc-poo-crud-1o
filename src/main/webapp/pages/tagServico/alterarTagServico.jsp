<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="../ErrorPage.jsp" %> <!-- Página de erro em caso de falha -->
<html>
<head>
    <title>Editar Tag Serviço</title> <!-- Título da página -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/alterarTagServico.css"> <!-- Estilo da página -->
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
            <li><a href="${pageContext.request.contextPath}/pages/administrador/listagemAdministradores.jsp">Adms</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/tagServico/listagemTagServico.jsp" class="active">Tag Serviço</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/planoAtivacao/listagemPlanosAtivacao.jsp">Planos Ativação</a></li>
        </ul>
    </div>
</header>

<h1 id="titulo-Editar-Tag-Servico">Editar Tag Serviço</h1> <!-- Título principal -->
<form action="AlterarTagServicoServlet" method="post"> <!-- Formulário para alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo -->
    <div class="infos-linha"> <!-- Linha de informações -->

        <div class="gridAtualNome">
            <label>Nome Atual:</label>
            <input type="text" value="<%= request.getAttribute("nome") %>" disabled> <!-- Nome atual exibido como desabilitado -->
        </div>

        <div class="gridNovoNome">
            <label for="nome">Novo Nome:</label> <!-- Rótulo para novo nome -->
            <input type="text" name="nome" id="nome" value="<%= request.getAttribute("nome") %>" placeholder="Novo nome" required> <!-- Campo de entrada para novo nome, agora obrigatório -->
        </div>

        <div class="gridAtualDescricao">
            <label>Descrição Atual:</label>
            <input type="text" value="<%= request.getAttribute("descricao") %>" disabled> <!-- Descrição atual exibida como desabilitada -->
        </div>

        <div class="gridNovaDescricao">
            <label for="descricao">Nova Descrição:</label> <!-- Rótulo para nova descrição -->
            <input type="text" name="descricao" id="descricao" value="<%= request.getAttribute("descricao") %>" placeholder="Descrição nova" required> <!-- Campo de entrada para nova descrição, agora obrigatório -->
            <input type="hidden" name="id" value="<%= request.getAttribute("id") %>"> <!-- Campo oculto para ID -->
        </div>

    </div>

    <div class="classButton">
        <input type="submit" id="buttonEditar" value="Editar"> <!-- Botão de envio -->
    </div>
</form>
</body>
</html>
