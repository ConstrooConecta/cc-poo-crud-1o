<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Ocorreu um erro no JSP</title>
</head>
<body>
<h1>Ocorreu um erro no JSP!</h1>
<p><strong>Erro:</strong> <%= exception.getMessage() %></p>
<p><strong>Tipo do erro:</strong> <%= exception.getClass().getName() %></p>

<h2>Detalhes do erro:</h2>
<%
    StackTraceElement[] stackTrace = exception.getStackTrace();
    for (StackTraceElement element : stackTrace) {
        if (element.getFileName() != null && element.getFileName().endsWith("_jsp.java")) {
%>
            <p><strong>Arquivo:</strong> <%= element.getFileName() %></p>
            <p><strong>Linha do erro:</strong> <%= element.getLineNumber() %></p>  <!-- Sairá a linha onde ocorreu o erro no arquivo java que é uma conversão do arquivo jsp ao ser cmopilado. -->
<%
            break; // Sai do loop ao encontrar a primeira linha relacionada ao JSP
        }
    }
%>
</body>
</html>
