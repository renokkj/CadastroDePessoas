<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="model.Produto" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Adicionar Produto</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/estilos/styles.css">
</head>
<body>
<div class="container">
    <h1>Adicionar Produto</h1>
    <form action="ProdutoServlet" method="post">
        <input type="hidden" name="action" value="salvar">
        <label>Nome:</label>
        <input type="text" name="nome" required>

        <label>Descriçao:</label>
        <textarea name="descricao" required></textarea>

        <label>Preço:</label>
        <input type="number" step="0.01" name="preco" required>

        <label>Quantidade:</label>
        <input type="number" name="quantidade" required>

        <label>Categoria:</label>
        <input type="text" name="categoria" required>

        <label>Código de Barras:</label>
        <input type="text" name="codigoBarras" required>

        <label>Fornecedor:</label>
        <input type="text" name="fornecedor" required>

        <label>Data de Validade:</label>
        <input type="date" name="dataValidade" required>

        <label>Lote:</label>
        <input type="text" name="lote" required>

        <button type="submit" class="azul-btn" style="width: 100%;">Salvar</button>
    </form>

    <br>
    <a href="ProdutoServlet" class="azul-btn">Voltar</a>
</div>
</body>
</html>
