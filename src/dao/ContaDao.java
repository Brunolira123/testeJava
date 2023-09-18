/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.entities.Conta;
import Model.entities.Conta;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface ContaDao {

    void insert(Conta obj);

    void deleteById(Integer id);

    Conta findById(Integer id);

    List<Conta> findAll();


}
