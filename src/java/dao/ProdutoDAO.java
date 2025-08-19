package dao; 

import java.sql.*; 
import java.util.*; 
import model.Produto; 
import util.Conexao;

public class ProdutoDAO { 

    public void inserirProduto(Produto p) throws SQLException { 
        String sql = "INSERT INTO produto (nome, descricao, preco, quantidade, categoria, codigoBarras, fornecedor, dataValidade, lote) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) { 
            stmt.setString(1, p.getNome()); 
            stmt.setString(2, p.getDescricao()); 
            stmt.setDouble(3, p.getPreco()); 
            stmt.setInt(4, p.getQuantidade()); 
            stmt.setString(5, p.getCategoria()); 
            stmt.setString(6, p.getCodigoBarras()); 
            stmt.setString(7, p.getFornecedor()); 
            stmt.setDate(8, new java.sql.Date(p.getDataValidade().getTime())); 
            stmt.setString(9, p.getLote()); 
            stmt.executeUpdate(); 
        } 
    } 

    public List<Produto> listarTodos() throws SQLException { 
        List<Produto> lista = new ArrayList<>(); 
        String sql = "SELECT * FROM produto"; 
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) { 
            while (rs.next()) { 
                Produto p = new Produto(); 
                p.setId(rs.getInt("id")); 
                p.setNome(rs.getString("nome")); 
                p.setDescricao(rs.getString("descricao")); 
                p.setPreco(rs.getDouble("preco")); 
                p.setQuantidade(rs.getInt("quantidade")); 
                p.setCategoria(rs.getString("categoria")); 
                p.setCodigoBarras(rs.getString("codigoBarras")); 
                p.setFornecedor(rs.getString("fornecedor")); 
                p.setDataValidade(rs.getDate("dataValidade")); 
                p.setLote(rs.getString("lote")); 
                lista.add(p); 
            } 
        } 
        return lista; 
    } 

    public Produto buscarPorId(Produto p) throws SQLException { 
        String sql = "SELECT * FROM produto WHERE id=?"; 
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) { 
            stmt.setInt(1, p.getId()); 
            ResultSet rs = stmt.executeQuery(); 
            if (rs.next()) { 
                p.setNome(rs.getString("nome")); 
                p.setDescricao(rs.getString("descricao")); 
                p.setPreco(rs.getDouble("preco")); 
                p.setQuantidade(rs.getInt("quantidade")); 
                p.setCategoria(rs.getString("categoria")); 
                p.setCodigoBarras(rs.getString("codigoBarras")); 
                p.setFornecedor(rs.getString("fornecedor")); 
                p.setDataValidade(rs.getDate("dataValidade")); 
                p.setLote(rs.getString("lote")); 
            } else { 
                p = null; 
            } 
        } 
        return p; 
    } 
 
    public void atualizarProduto(Produto p) throws SQLException { 
        String sql = "UPDATE produto SET nome=?, descricao=?, preco=?, quantidade=?, categoria=?, codigoBarras=?, fornecedor=?, dataValidade=?, lote=? WHERE id=?"; 
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) { 
            stmt.setString(1, p.getNome()); 
            stmt.setString(2, p.getDescricao()); 
            stmt.setDouble(3, p.getPreco()); 
            stmt.setInt(4, p.getQuantidade()); 
            stmt.setString(5, p.getCategoria()); 
            stmt.setString(6, p.getCodigoBarras()); 
            stmt.setString(7, p.getFornecedor()); 
            stmt.setDate(8, new java.sql.Date(p.getDataValidade().getTime())); 
            stmt.setString(9, p.getLote()); 
            stmt.setInt(10, p.getId()); 
            stmt.executeUpdate(); 
        } 
    } 

    public void deletarProduto(Produto p) throws SQLException { 
        String sql = "DELETE FROM produto WHERE id=?"; 
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) { 
            stmt.setInt(1, p.getId()); 
            stmt.executeUpdate(); 
        } 
    }
    public void deletarTodos() throws SQLException {
        String sql = "DELETE FROM produto";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }

    public void resetarAutoIncremento() throws SQLException {
        String sql = "ALTER TABLE produto AUTO_INCREMENT = 1";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }
} 