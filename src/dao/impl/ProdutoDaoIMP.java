/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.ProdutoDao;
import Model.entities.Conta;
import Model.entities.Produto;
import db.DB;
import db.DbException;
import db.DbIntegrityException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class ProdutoDaoIMP implements ProdutoDao {

    private Connection conn;

    public ProdutoDaoIMP(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Produto findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM produto WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Produto obj = new Produto();
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setValor(rs.getDouble("preco"));

                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Produto> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM produto ORDER BY descricao");
            rs = st.executeQuery();

            List<Produto> list = new ArrayList<>();

            while (rs.next()) {
                Produto obj = new Produto();
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void insert(Produto obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "insert into produto \n"
                    + "(descricao, preco, ativo)\n"
                    + "values \n"
                    + "(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getDescricao());
            st.setDouble(2, obj.getValor());
            st.setBoolean(3, true);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            } else {
                throw new DbException("Nenhum dado inserido!");
            }
        } catch (DbException | SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void updateDescricao(Produto obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE produto "
                    + "SET Name = ? "
                    + "WHERE Id = ?");

            st.setString(1, obj.getDescricao());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
    
    @Override
    public void updatePreco(Produto obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE produto "
                    + "SET preco = ? "
                    + "WHERE id = ?");

            st.setDouble(1, obj.getValor());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE produto "
                    + "SET ativo = ? "
                    + "WHERE id = ?");

            st.setBoolean(1, false);
            st.setInt(2, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Produto> findByConta(Conta Conta) {
        return null;
    }

    @Override
    public List<Produto> findByName(String desc) {
       
           PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM produto WHERE descricao ilike ?");
            
            st.setString(1, "%"+desc+"%".toUpperCase());
            rs = st.executeQuery();
            List<Produto> list = new ArrayList<>();
            while (rs.next()) {
                Produto obj = new Produto();
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setValor(rs.getDouble("preco"));
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

}
