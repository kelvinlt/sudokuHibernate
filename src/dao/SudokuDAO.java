/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.History;
import entities.Sudoku;
import entities.User;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Kelvi
 */
public class SudokuDAO {
    Session sesion;
    Transaction tx;
    
    public SudokuDAO(){
        sesion = HibernateUtil.getSessionFactory().openSession();
    }
    
    public void insertarUser(User u){
    
    }
    
    public void insertarHistory(History h){
    
    }
    
    public void insertarSudoku(Sudoku u){
    
    }
    
    
    public User getUserByUsername(String username){
        return (User) sesion.get(User.class, username);
    }
}
