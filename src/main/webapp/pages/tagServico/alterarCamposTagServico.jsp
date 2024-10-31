<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="../ErrorPage.jsp" %>
<html>
<head>
    <title>Editar Tag Serviço</title> <!-- Título da página -->
</head>
<section >
    <jsp:include page="alterarTagServicoPeloID.jsp" />
</section>
<body>
<!-- <link rel="stylesheet" href="alterarAdministrador.css">  -->
<!-- <link rel="stylesheet" href="alterar2.css"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/alterarTagServico.css">
<form action="AlterarTagServicoServlet" method="post"> <!-- Formulário para alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo -->
    <div class="infos-linha"> <!-- Linha de informações -->

        <div class="gridAtualNome">
            <label>Nome Atual:</label>
            <input type="text" value="<%= request.getAttribute("nome") %>" disabled>
        </div>

        <div class="gridNovoNome">
            <label for="nome">Novo Nome:</label> <!-- Rótulo para nome Atual -->
            <input type="text" name="nome" id="nome" placeholder="Novo nome"> <!-- Campo de entrada para Nome Novo -->

            <!-- ideia: no placeholder colocar as informações antigas-atuais, assim da para ele ver o que tava antes, além do que já está no atual lá -->
            <!-- *colocar comando para deixar mais linhas (descricao com menos de 8 palavras é impossível) -->
        </div>

        <div class="gridAtualDescricao">
            <label>Descrição Atual:</label>
            <input type="text" value="<%= request.getAttribute("descricao") %>" disabled>
        </div>

        <div class="gridNovaDescricao">
            <label for="descricao">Nova Descrição:</label> <!-- Rótulo para email -->
            <input type="text" name="descricao" id="descricao" placeholder="Descrição nova"> <!-- Campo de entrada para email -->
            <input type="hidden" name="id" value="<%=request.getAttribute("id") %>"> <!-- Campo oculto para ID -->
        </div>
    </div>
    <div class="classButton">
        <input type="submit" id="buttonEditar"> <!-- Botão de envio -->
    </div>
</form>
</body>
</html>