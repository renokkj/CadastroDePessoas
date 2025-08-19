<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="model.Produto" %>
<%
    Produto p = (Produto) request.getAttribute("produto");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Produto</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/estilos/styles.css">
</head>
<body>
<div class="container">
    <h1>Editar Produto</h1>
    <form action="ProdutoServlet" method="post">
        <input type="hidden" name="action" value="atualizar">
        <input type="hidden" name="id" value="<%= p.getId() %>">

        <label>Nome:</label>
        <input type="text" name="nome" value="<%= p.getNome() %>" required>

        <label>Descriçao:</label>
        <textarea name="descricao" required><%= p.getDescricao() %></textarea>

        <label>Preço:</label>
        <input type="number" step="0.01" name="preco" value="<%= p.getPreco() %>" required>

        <label>Quantidade:</label>
        <input type="number" name="quantidade" value="<%= p.getQuantidade() %>" required>

        <label>Categoria:</label>
        <input type="text" name="categoria" value="<%= p.getCategoria() %>" required>

        <label>Código de Barras:</label>
        <input type="text" name="codigoBarras" value="<%= p.getCodigoBarras() %>" required>

        <label>Fornecedor:</label>
        <input type="text" name="fornecedor" value="<%= p.getFornecedor() %>" required>

        <label>Data de Validade:</label>
        <input type="date" name="dataValidade" value="<%= p.getDataValidade() %>" required>

        <label>Lote:</label>
        <input type="text" name="lote" value="<%= p.getLote() %>" required>

        <button type="submit" class="azul-btn" style="width: 100%;">Atualizar</button>
    </form>

    <br>
    <a href="ProdutoServlet" class="azul-btn">Voltar</a>
</div>
</body>
</html>
