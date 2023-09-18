/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.impl.ClienteDaoIMP;
import dao.impl.ProdutoDaoIMP;
import db.DB;
import db.GeradorDeScripts;

/**
 *
 * @author Bruno
 */
public class DaoFactory {

    public static ClienteDao createClienteDao() {
        return new ClienteDaoIMP(DB.getConnection());
    }

    public static ProdutoDao createProdutoDao() {
        return new ProdutoDaoIMP(DB.getConnection());
    }

    public static GeradorDeScripts createScript() {
        return new GeradorDeScripts(DB.getConnection());
    }

}
