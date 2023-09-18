/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.entities.Cliente;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface ClienteDao {

    void insert(Cliente obj);

    void updateNome(Cliente obj);
    
    void updateVencimento(Cliente obj);
    
    void updateLimite(Cliente obj);

    void deleteById(Integer id);

    Cliente findById(Integer id);
    
    List<Cliente> findByNome(String descricao);

    List<Cliente> findAll();
}


