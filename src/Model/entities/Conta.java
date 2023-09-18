/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Bruno
 */
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private double quantidade;
    private double valor_produto;
    private Integer id_produto;
    private Integer id_cliente;
    private double limite_cliente;
    private double valor_total;
    private Cliente cliente;
    private Produto produto;
    private Date dataCompra;

    public Conta() {
    }

    public Conta(double quantidade, double valor_produto, Integer id_produto, Integer id_cliente, double valor_total, double limite_cliente) {
        this.quantidade = quantidade;
        this.valor_produto = produto.getValor();
        this.id_cliente = cliente.getId();
        this.limite_cliente = cliente.getLimite();
        this.id_produto = produto.getId();
        this.valor_total = quantidade * produto.getValor();

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Conta other = (Conta) obj;
        if (getId() == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + getId() + ", quantidade=" + quantidade + ", valor_produto=" + valor_produto + ", id_produto="
                + id_produto + ", id_cliente=" + id_cliente + ", valor_total=" + valor_total + ", cliente=" + cliente
                + "]";
    }

    /**
     * @return the quantidade
     */
    public double getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the valor_produto
     */
    public double getValor_produto() {
        return valor_produto;
    }

    /**
     * @param valor_produto the valor_produto to set
     */
    public void setValor_produto(double valor_produto) {
        this.valor_produto = valor_produto;
    }

    /**
     * @return the id_produto
     */
    public Integer getId_produto() {
        return id_produto;
    }

    /**
     * @param id_produto the id_produto to set
     */
    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    /**
     * @return the id_cliente
     */
    public Integer getId_cliente() {
        return id_cliente;
    }

    /**
     * @param id_cliente the id_cliente to set
     */
    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * @return the valor_total
     */
    public double getValor_total() {
        return valor_total;
    }

    /**
     * @param valor_total the valor_total to set
     */
    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the limite_cliente
     */
    public double getLimite_cliente() {
        return limite_cliente;
    }

    /**
     * @param limite_cliente the limite_cliente to set
     */
    public void setLimite_cliente(double limite_cliente) {
        this.limite_cliente = limite_cliente;
    }

    /**
     * @return the dataCompra
     */
    public Date getDataCompra() {
        return dataCompra;
    }

    /**
     * @param dataCompra the dataCompra to set
     */
    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

}
