/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.entities.Conta;
import Model.entities.Produto;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface ProdutoDao {

    void insert(Produto obj);

    void updateDescricao(Produto obj);
    
    void updatePreco(Produto obj);

    void deleteById(Integer id);

    Produto findById(Integer id);
    
    List<Produto> findByName(String desc);

    List<Produto> findAll();
    
    

    List<Produto> findByConta(Conta Conta);

}
