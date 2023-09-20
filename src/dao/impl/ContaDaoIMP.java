/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.ContaDao;
import Model.entities.Conta;
import db.DB;
import db.DbException;
import db.DbIntegrityException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class ContaDaoIMP implements ContaDao {
    

    private Connection conn;

    public ContaDaoIMP(Connection conn) {
        this.conn = conn;
    }


    @Override
    public Conta findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM conta WHERE id_cliente = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Conta obj = new Conta();
                obj.setId(rs.getInt("id"));
                obj.setId_cliente(rs.getInt("id_cliente"));
                obj.setId_produto(rs.getInt("id_produto"));
                obj.setDataCompra(rs.getDate("data_compra"));
                obj.setValor_total(rs.getDouble("valor"));
                obj.setLimite_cliente(rs.getDouble("limite_total"));

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
    public List<Conta> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM conta ORDER BY id_cliente");
            rs = st.executeQuery();

            List<Conta> list = new ArrayList<>();

            while (rs.next()) {
                Conta obj = new Conta();
                obj.setId(rs.getInt("id"));
                obj.setId_cliente(rs.getInt("id_cliente"));
                obj.setId_produto(rs.getInt("id_produto"));
                obj.setDataCompra(rs.getDate("data_compra"));
                obj.setValor_total(rs.getDouble("valor"));
                obj.setLimite_cliente(rs.getDouble("limite_total"));
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
    public void insert(Conta obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "insert into conta (id_cliente, id_produto,  valor, limite_total)\n"
                    + "values\n"
                    + "(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getId_cliente());
            st.setInt(2, obj.getId_produto());
            st.setDouble(3, obj.getValor_produto());
            st.setDouble(4, obj.getValor_total());

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
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "");

            st.setInt(2, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}
