<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page errorPage="../ErrorPage.jsp" %>
<meta charset="UTF-8">


<html>
<head>
    <title>Editar Plano</title> <!-- Título da página -->
</head>
<section>
    <jsp:include page = "alterarPlanoPeloID.jsp" />
</section>
<body>
<!-- <link rel="stylesheet" href="alterarAdministrador.css">  -->
<!-- <link rel="stylesheet" href="alterar2.css"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/alterarPlano.css">
<form action="AlterarPlanoServlet" method="post"> <!-- Formulário para alteração -->
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
            <label for="descricaoAtual">Descrição Atual:</label>
            <input type="text" id="descricaoAtual" value="<%= request.getAttribute("descricao") %>" disabled>
        </div>

        <div class="gridNovaDescricao">
            <label for="descricao">Nova Descrição:</label> <!-- Rótulo para email -->
            <input type="text" name="descricao" id="descricao" value="<%= request.getAttribute("descricao") %>" placeholder="Descrição nova"> <!-- Campo de entrada para email -->
        </div>

        <div class="gridAtualDuracao">
            <label for="duracaoAtual">Duração Atual:</label>
            <input type="number" id="duracaoAtual"  value="<%=request.getAttribute("duracao")%>"  disabled>
        </div>

        <div class="gridNovaDuracao">
            <label for="duracao">Nova Duração:</label> <!-- Rótulo para duração -->
            <input type="number" name="duracao" id="duracao" value="<%= request.getAttribute("duracao") %>" placeholder="Descrição nova" > <!-- Campo de entrada para email -->
        </div>


        <div class="gridAtualValor">
            <label for="valorAtual">Valor Atual:</label>
            <input type="number" id="valorAtual" step="0.01" value="<%= request.getAttribute("valor") %>" disabled>
        </div>

        <div class="gridNovoValor">
            <label for="valor">Novo Valor:</label> <!-- Rótulo para senha -->
            <input type="number" name="valor" id="valor" step="0.01" value="<%= request.getAttribute("valor") %>" placeholder="Novo Valor"> <!-- Campo de entrada para senha -->
        </div>

        <div class="gridAtualTipo">
            <label for="tipoAtual">Tipo Atual:</label>
            <input type="text" name="tipoAtual" id="tipoAtual" value="<%if (request.getAttribute("tipo").equals("P"))
                    {%>Profissional<% }else if (request.getAttribute("tipo").equals("V"))
                    {%>Vendedor<%}%>"  disabled>
        </div>

        <div class="gridNovoTipo">
            <label for="Tipo">Tipo Novo:</label>
            <select name="tipo" id="tipo">
                <option value="" disabled selected>Selecione o tipo</option>
                <option value="P">Profissional</option>
                <option value="V">Vendedor</option>
            </select>
        </div>
        <input type="hidden" name="id" value="<%=request.getAttribute("id") %>"> <!-- Campo oculto para ID -->

    </div>

    <div class="classButton">
        <input type="submit" id="buttonCadastrar"> <!-- Botão de envio -->
    </div>


</form>
</body>
</html>