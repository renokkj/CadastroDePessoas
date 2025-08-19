<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Produto" %>

<%
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
    Produto produtoBusca = (Produto) request.getAttribute("produtoBusca");
    String mensagem = (String) request.getAttribute("mensagem");
%>

<!DOCTYPE html>

<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Produtos</title>
    <link rel="stylesheet" href="estilos/styles.css">
</head>
<body>
<div class="container">
    <h1>Produtos</h1>

    <form action="ProdutoServlet" method="get" style="margin-bottom: 20px; display: flex; gap: 10px; align-items: center;">
        <input type="hidden" name="action" value="buscar">
        <label for="idBusca">Buscar por ID:</label>
        <input type="text" id="idBusca" name="idBusca" required style="flex: 1;">
        <button type="submit" class="btn btn-primary">Buscar</button>
    </form>

    <div style="display: flex; justify-content: space-between; margin-bottom: 20px;">
        <a href="ProdutoServlet?action=novo" class="btn btn-primary">+ Novo Produto</a>
        <a href="ProdutoServlet?action=limpar" class="btn btn-danger"
           onclick="return confirm('Tem certeza que deseja excluir todos os produtos?');">Limpar Tudo</a>
    </div>

    <%
        if (mensagem != null) {
    %>
        <div style="color: red; font-weight: bold; margin-bottom: 15px;"><%= mensagem %></div>
    <%
        }
    %>

    <%
        if (produtoBusca != null) {
    %>
        <h3>Resultado da Busca:</h3>
        <table>
            <tr>
                <th>ID</th><th>Nome</th><th>Preço</th><th>Quantidade</th><th>Orçamento</th><th style="text-align: center;">Ações</th>
            </tr>
            <tr>
                <td><%= produtoBusca.getId() %></td>
                <td><%= produtoBusca.getNome() %></td>
                <td>R$ <%= String.format("%.2f", produtoBusca.getPreco()) %></td>
                <td><%= produtoBusca.getQuantidade() %></td>
                <td>R$ <%= String.format("%.2f", produtoBusca.getPreco() * produtoBusca.getQuantidade()) %></td>
                <td class="acoes">
                    <a href="ProdutoServlet?action=editar&id=<%= produtoBusca.getId() %>" class="btn btn-primary">Editar</a>
                    <a href="ProdutoServlet?action=excluir&id=<%= produtoBusca.getId() %>"
                       class="btn btn-primary" onclick="return confirm('Deseja excluir este produto?');">Excluir</a>
                </td>
            </tr>
        </table>
        <br>
    <%
        }
    %>
    <h3>Todos os Produtos</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Preço</th>
            <th>Quantidade</th>
            <th>Orçamento</th>
            <th>Ações</th>
        </tr>
        <%
            double totalOrcamento = 0.0;
            if (produtos != null) {
                for (Produto p : produtos) {
                    double orcamento = p.getOrcamento();
                    totalOrcamento += orcamento;
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getNome() %></td>
            <td>R$ <%= String.format("%.2f", p.getPreco()) %></td>
            <td><%= p.getQuantidade() %></td>
            <td>R$ <%= String.format("%.2f", orcamento) %></td>
            <td class="acoes">
                <a href="ProdutoServlet?action=editar&id=<%= p.getId() %>" class="btn btn-primary">Editar</a>
                <a href="ProdutoServlet?action=excluir&id=<%= p.getId() %>"
                   class="btn btn-primary" onclick="return confirm('Deseja excluir este produto?');">Excluir</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>

    <% if (totalOrcamento > 0) { %>
        <p style="text-align: right; font-weight: bold; margin-top: 15px;">
            Total Geral: <span style="color: green;">R$ <%= String.format("%.2f", totalOrcamento) %></span>
        </p>
    <% } %>
</div>
</body>
</html>
