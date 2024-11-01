<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../ErrorPage.jsp" %>
<meta charset="UTF-8">
<html>
<head>
    <title>Editar Administrador</title> <!-- Título da página -->
</head>

<section>
    <jsp:include page="alterarCategoriaProdutoPeloID.jsp" /> <!-- Inclui a página de alteração de categoria de produto -->
</section>

<body>
<!-- <link rel="stylesheet" href="alterarAdministrador.css">  -->
<!-- <link rel="stylesheet" href="alterar2.css"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/alterarCategoriaProduto.css"> <!-- Link para a folha de estilo -->

<form action="AlterarCategoriaProdutoServlet" method="post"> <!-- Formulário para alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo da seção -->

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
