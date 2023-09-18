/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.ClienteDao;
import Model.entities.Cliente;
import db.DB;
import db.DbException;
import db.DbIntegrityException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Bruno
 */
public class ClienteDaoIMP implements ClienteDao {

    private Connection conn;

    public ClienteDaoIMP(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Cliente findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM cliente WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("nome"));
                obj.setLimite(rs.getDouble("limite"));
                obj.setAtivo(rs.getBoolean("ativo"));
                obj.setVencimento(rs.getInt("vencimento"));
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
    public List<Cliente> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM cliente ORDER by id");
            rs = st.executeQuery();

            List<Cliente> list = new ArrayList<>();

            while (rs.next()) {
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("nome"));
                obj.setLimite(rs.getDouble("limite"));
                obj.setAtivo(rs.getBoolean("ativo"));
                obj.setVencimento(rs.getInt("vencimento"));
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
    public List<Cliente> findByNome(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "select * from cliente \n"
                    + "where nome ilike ?"
            );
            st.setString(1, "%"+nome+"%".toUpperCase());
            rs = st.executeQuery();
            List<Cliente> list = new ArrayList<>();

            while (rs.next()) {
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("nome"));
                obj.setLimite(rs.getDouble("limite"));
                obj.setAtivo(rs.getBoolean("ativo"));
                obj.setVencimento(rs.getInt("vencimento"));
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
    public void insert(Cliente obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "insert into cliente (nome, limite, vencimento,ativo)\n"
                    + "values\n"
                    + "(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setDouble(2, obj.getLimite());
            st.setInt(3, obj.getVencimento());
            st.setBoolean(4, true);

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
    public void updateNome(Cliente obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE cliente "
                    + "SET nome = ? "
                    + "WHERE id = ?");

            st.setString(1, obj.getNome());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void updateVencimento(Cliente obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE cliente "
                    + "SET vencimento = ? "
                    + "WHERE id = ?");

            st.setInt(1, obj.getVencimento());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void updateLimite(Cliente obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE cliente "
                    + "SET limite = ? "
                    + "WHERE id = ?");

            st.setDouble(1, obj.getLimite());
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
                    "UPDATE cliente "
                    + "SET ativo = ? "
                    + "WHERE id = ?");

            st.setBoolean(1, false);
            st.setInt(2, id);

            st.executeUpdate();
        } catch (Exception e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

}
