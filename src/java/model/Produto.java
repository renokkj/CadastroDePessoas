package model; 

import java.util.Date; 

public class Produto { 

    private int id; 
    private String nome; 
    private String descricao; 
    private double preco; 
    private int quantidade; 
    private String categoria; 
    private String codigoBarras; 
    private String fornecedor; 
    private Date dataValidade; 
    private String lote; 

    public Produto() {} 

    public int getId() { return id; } 
    public void setId(int id) { this.id = id; } 

    public String getNome() { return nome; } 
    public void setNome(String nome) { this.nome = nome; } 

    public String getDescricao() { return descricao; } 
    public void setDescricao(String descricao) { this.descricao = descricao; } 

    public double getPreco() { return preco; } 
    public void setPreco(double preco) { this.preco = preco; } 

    public int getQuantidade() { return quantidade; } 
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; } 

    public String getCategoria() { return categoria; } 
    public void setCategoria(String categoria) { this.categoria = categoria; } 
 
    public String getCodigoBarras() { return codigoBarras; } 
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; } 
 
    public String getFornecedor() { return fornecedor; } 
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; } 

    public Date getDataValidade() { return dataValidade; } 
    public void setDataValidade(Date dataValidade) { this.dataValidade = dataValidade; } 
 
    public String getLote() { return lote; } 
    public void setLote(String lote) { this.lote = lote; } 
    
    public double getOrcamento() { return preco * quantidade; }

} 
