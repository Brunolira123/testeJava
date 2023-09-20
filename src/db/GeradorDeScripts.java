/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Statement;
import java.sql.Connection;

/**
 *
 * @author Bruno
 */
public class GeradorDeScripts {

    Connection conn = null;

    public GeradorDeScripts(Connection connection) {
        this.conn = connection;
    }

    public void criarTabelaCliente() throws Exception {
        try (Statement stm = conn.createStatement()) {
            stm.execute(
                    "CREATE TABLE IF NOT EXISTS \n"
                    + "cliente  \n"
                    + "(id serial not null primary key, \n"
                    + "nome varchar (100), \n"
                    + "limite real , \n"
                    + "vencimento int, \n"
                    + "ativo boolean) ;"
            );
        }
    }

    public void criarTabelaProduto() throws Exception {
        try (Statement stm = conn.createStatement()) {
            stm.execute(
                    "CREATE TABLE IF NOT EXISTS \n"
                    + "produto \n"
                    + "(id serial not null primary key,\n"
                    + "ativo boolean,\n"
                    + "descricao varchar (100), \n"
                    + "preco real);"
            );
        }
    }

    public void criarTabelaConta() throws Exception {
        try (Statement stm = conn.createStatement()) {
            stm.execute(
                    "CREATE TABLE IF NOT EXISTS \n"
                    + "conta \n"
                    + "(id serial not null primary key,\n"
                    + "id_cliente  int references cliente(id),\n"
                    + "id_produto int references produto(id),\n"
                    + "quantidade float,\n"
                    + "valor real ,\n"
                    + "data_compra date,\n"
                    + "limite_total real);"
            );
        }
    }

}
