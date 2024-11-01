<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- Definição do tipo de conteúdo e codificação -->
<%@ page errorPage="../ErrorPage.jsp" %> <!-- Página de erro a ser redirecionada em caso de falha -->
<meta charset="UTF-8"> <!-- Definição do conjunto de caracteres -->

<html>
<head>
    <title>Editar Plano</title> <!-- Título da página -->
</head>
<body>
<!-- <link rel="stylesheet" href="alterarAdministrador.css">  -->
<!-- <link rel="stylesheet" href="alterar2.css"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/alterarPlano.css"> <!-- Link para a folha de estilo -->
<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1> <!-- Título da aplicação -->
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo"> <!-- Logo da aplicação -->
    </div>
</header>

<h1 id="titulo-Editar-Administrador">Editar Plano</h1> <!-- Título principal -->
<form action="AlterarPlanoServlet" method="post"> <!-- Formulário para alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo (atualmente vazio) -->
    <div class="infos-linha"> <!-- Linha de informações -->

        <div class="gridAtualNome">
            <label>Nome Atual:</label>
            <input type="text" value="<%= request.getAttribute("nome") %>" disabled> <!-- Nome atual, campo desabilitado -->
        </div>

        <div class="gridNovoNome">
            <label for="nome">Novo Nome:</label> <!-- Rótulo para novo nome -->
            <input type="text" name="nome" id="nome" value="<%= request.getAttribute("nome") %>" placeholder="Novo nome"> <!-- Campo de entrada para novo nome -->
        </div>

        <div class="gridAtualDescricao">
            <label for="descricaoAtual">Descrição Atual:</label>
            <input type="text" id="descricaoAtual" value="<%= request.getAttribute("descricao") %>" disabled> <!-- Descrição atual, campo desabilitado -->
        </div>

        <div class="gridNovaDescricao">
            <label for="descricao">Nova Descrição:</label> <!-- Rótulo para nova descrição -->
            <input type="text" name="descricao" id="descricao" value="<%= request.getAttribute("descricao") %>" placeholder="Descrição nova"> <!-- Campo de entrada para nova descrição -->
        </div>

        <div class="gridAtualDuracao">
            <label for="duracaoAtual">Duração Atual:</label>
            <input type="number" id="duracaoAtual" value="<%= request.getAttribute("duracao") %>" disabled> <!-- Duração atual, campo desabilitado -->
        </div>

        <div class="gridNovaDuracao">
            <label for="duracao">Nova Duração:</label> <!-- Rótulo para nova duração -->
            <input type="number" name="duracao" id="duracao" value="<%= request.getAttribute("duracao") %>" placeholder="Descrição nova"> <!-- Campo de entrada para nova duração -->
        </div>

        <div class="gridAtualValor">
            <label for="valorAtual">Valor Atual:</label>
            <input type="number" id="valorAtual" step="0.01" value="<%= request.getAttribute("valor") %>" disabled> <!-- Valor atual, campo desabilitado -->
        </div>

        <div class="gridNovoValor">
            <label for="valor">Novo Valor:</label> <!-- Rótulo para novo valor -->
            <input type="number" name="valor" id="valor" step="0.01" value="<%= request.getAttribute("valor") %>" placeholder="Novo Valor"> <!-- Campo de entrada para novo valor -->
        </div>

        <div class="gridAtualTipo">
            <label for="tipoAtual">Tipo Atual:</label>
            <input type="text" name="tipoAtual" id="tipoAtual" value="<% if (request.getAttribute("tipo").equals("P")) { %>Profissional<% } else if (request.getAttribute("tipo").equals("V")) { %>Vendedor<% } %>" disabled> <!-- Tipo atual, campo desabilitado -->
        </div>

        <div class="gridNovoTipo">
            <label for="Tipo">Tipo Novo:</label>
            <select name="tipo" id="tipo"> <!-- Seleção para novo tipo -->
                <option value="" disabled selected>Selecione o tipo</option> <!-- Opção padrão -->
                <option value="P">Profissional</option> <!-- Opção Profissional -->
                <option value="V">Vendedor</option> <!-- Opção Vendedor -->
            </select>
        </div>

        <input type="hidden" name="id" value="<%= request.getAttribute("id") %>"> <!-- Campo oculto para ID -->
    </div>

    <div class="classButton">
        <input type="submit" id="buttonCadastrar"> <!-- Botão de envio -->
    </div>
</form>
</body>
</html>
