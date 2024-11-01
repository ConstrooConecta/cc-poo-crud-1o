<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="../ErrorPage.jsp" %> <!-- Página de erro a ser redirecionada em caso de falha -->
<html>
<head>
  <title>Deletar Categoria Produto pelo ID</title> <!-- Título da página -->
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/id.css"> <!-- Link para a folha de estilo -->
<header>
  <div class="titulo-constroo"> <!-- Div para o título da aplicação -->
    <h1>Constroo</h1> <!-- Nome da aplicação -->
    <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo"> <!-- Logo da aplicação -->
  </div>
</header>
<h1 id="titulo-Editar-Pelo-ID">Deletar Categoria Produto por ID</h1> <!-- Título principal da seção -->
<form action="${pageContext.request.contextPath}/DeletarCategoriaProdutoServlet" method="post"> <!-- Formulário para deletar categoria -->
  <h2 class="subtitulo-forms"></h2> <!-- Subtítulo (atualmente vazio) -->
  <div class="infos-linha1"> <!-- Div que agrupa as informações do formulário -->

    <div class="ID"> <!-- Div para o campo ID -->
      <label for="id">ID:</label> <!-- Rótulo para o campo ID -->
      <input type="text" name="categoria_id" id="id" placeholder="Insira o ID"> <!-- Campo de entrada para o ID da categoria -->
    </div>

    <div class="classButton1"> <!-- Div para o botão de ação -->
      <input type="submit" id="buttonCadastrar" value="Deletar"> <!-- Botão de envio para deletar a categoria -->
    </div>

    <%
      // Verifica se há mensagens de retorno da operação
      if (request.getAttribute("retorno") == "erro") {
    %>
    <div> <!-- Div para exibir mensagem de erro -->
      <p><%= request.getAttribute("mensagem") %></p> <!-- Mensagem de erro retornada -->
    </div>
    <%
    } else if (request.getAttribute("retorno") == "notfound") {
    %>
    <div> <!-- Div para exibir mensagem de item não encontrado -->
      <p>ITEM NÃO ENCONTRADO</p> <!-- Mensagem quando o item não é encontrado -->
    </div>
    <%
      }
    %>
  </div>
</form>
</body>
</html>
