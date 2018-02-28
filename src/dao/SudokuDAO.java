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
    User aux = getUserByUsername(u.getUsername());
    if(aux != null){
        
        }
    tx = sesion.beginTransaction();
    sesion.save(u);
    tx.commit();
    }
    
    public void insertarHistory(History h){
        
    }
    
    //3A Insertar nuevo sudoku en la BBDD
    public void insertarSudoku(Sudoku u){
    Sudoku aux = getSudokuBySolved(u.getSolved());
    if(aux != null){
        
        }
    tx = sesion.beginTransaction();
    sesion.save(u);
    tx.commit();
    }
    
    //3B Conseguir un sudoku a partir de un id
    public Sudoku getSudokuById(String id){
        return (Sudoku) sesion.get(Sudoku.class, id);
    }
    
    //3C Conseguir todos los sudokus en la base de datos
    public List<Sudoku> getAllSudokus(){
        Query q = sesion.createQuery("select * from Sudoku");
        return q.list();
    }
    
    //4A Insertar un nuevo usuario en la BBDD
    
    //4B Valida entrada de un usuario 
    
    //4C Modificar el perfil de un Usuario determinado
    public void modificarUser(User u, String newNombre){
        User aux = getUserByUsername(u.getUsername());
        if(aux == null){
        
        }
        tx = sesion.beginTransaction();
        aux.setName(newNombre);
        tx.commit();
    }
    
    public User getUserByUsername(String username){
        return (User) sesion.get(User.class, username);
    }
    
    public Sudoku getSudokuBySolved(String solved){
        return (Sudoku) sesion.get(Sudoku.class, solved);
    }
    
    public List<History> getHistoryByUser(){
        Query q = sesion.createQuery("select u from User u where user=''");
        return q.list();
    }
    
}
