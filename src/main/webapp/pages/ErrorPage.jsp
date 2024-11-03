<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %> <!-- Indica que esta página é uma página de erro -->
<html>
<head>
    <title>Ocorreu um erro no JSP</title>
</head>
<body>
<h1>Ocorreu um erro no JSP!</h1>
<p><strong>Erro:</strong> <%= exception.getMessage() %></p> <!-- Mensagem do erro -->
<p><strong>Tipo do erro:</strong> <%= exception.getClass().getName() %></p> <!-- Tipo da exceção -->

<h2>Detalhes do erro:</h2>
<%
    StackTraceElement[] stackTrace = exception.getStackTrace(); // Obtém a pilha de execução da exceção
    for (StackTraceElement element : stackTrace) {
        // Verifica se o arquivo da pilha é um arquivo JSP compilado
        if (element.getFileName() != null && element.getFileName().endsWith("_jsp.java")) {
%>
<p><strong>Arquivo:</strong> <%= element.getFileName() %></p> <!-- Nome do arquivo -->
<p><strong>Linha do erro:</strong> <%= element.getLineNumber() %></p> <!-- Linha onde ocorreu o erro -->
<%
            break; // Sai do loop ao encontrar a primeira linha relacionada ao JSP
        }
    }
%>
</body>
</html>
