<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../ErrorPage.jsp" %>
<meta charset="UTF-8">
<html>
<head>
    <title>Editar Administrador</title> <!-- Título da página -->
</head>

<section >
    <jsp:include page="alterarCategoriaProdutoPeloID.jsp" />
</section>
<body>

<!-- <link rel="stylesheet" href="alterarAdministrador.css">  -->
<!-- <link rel="stylesheet" href="alterar2.css"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/alterarCategoriaProduto.css">

<form action="AlterarCategoriaProdutoServlet" method="post"> <!-- Formulário para alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo -->
    <div class="infos-linha"> <!-- Linha de informações -->

        <div class="gridAtualNome">
            <label>Nome Atual:</label>
            <input type="text" value="<%= request.getAttribute("nome") %>" disabled>
        </div>

        <div class="gridNovoNome">
            <label for="nome">Novo Nome:</label> <!-- Rótulo para nome Atual -->
            <input type="text" name="nome" id="nome" value="<%= request.getAttribute("nome") %>" placeholder="Novo nome"> <!-- Campo de entrada para Nome Novo -->
        </div>

        <div class="gridAtualDescricao">
            <label>Descrição Atual:</label>
            <input type="text"  value="<%= request.getAttribute("descricao") %>" disabled>
        </div>

        <div class="gridNovaDescricao">
            <label for="descricao">Nova Descrição:</label> <!-- Rótulo para email -->
            <input type="text" name="descricao" id="descricao" value="<%= request.getAttribute("descricao") %>" placeholder="Descrição nova"> <!-- Campo de entrada para email -->
        </div>
        <input type="hidden" name="id" value="<%=request.getAttribute("id") %>"> <!-- Campo oculto para ID -->

    </div>

    <div class="classButton">
        <input type="submit" id="buttonEditar"> <!-- Botão de envio -->
    </div>


</form>
</body>
</html>