/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exceptions.sudokuExceptions;
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

    public SudokuDAO() {
        sesion = HibernateUtil.getSessionFactory().openSession();
    }

    public void insertarHistory(History h) {
        tx = sesion.beginTransaction();
        sesion.save(h);
        tx.commit();
    }

    //3A Insertar nuevo sudoku en la BBDD
    public void insertarSudoku(Sudoku u) {
//        Sudoku aux = getSudokuBySolved(u.getSolved());
//        if (aux != null) {
//          throw new RestaurantException("Ya existe un cocinero con ese nombre");
//        }
        tx = sesion.beginTransaction();
        sesion.save(u);
        tx.commit();
    }

    //3B Conseguir un sudoku a partir de un id
    public Sudoku getSudokuById(int id) {
        return (Sudoku) sesion.get(Sudoku.class, id);
    }

    //3C Conseguir todos los sudokus en la base de datos
    public List<Sudoku> getAllSudokus() {
        Query q = sesion.createQuery("select s from Sudoku s");
        return q.list();
    }

    //4A Insertar un nuevo usuario en la BBDD
    public void insertarUser(User u) throws sudokuExceptions {
        if (existeUsername(u.getUsername())) {
            throw new sudokuExceptions("Ya existe el usuario");
        }
        tx = sesion.beginTransaction();
        sesion.save(u);
        tx.commit();
    }

    public boolean existeUsername(String username) throws sudokuExceptions {
        User u = (User) sesion.get(User.class, username);
        if (u != null) {
            return true;
        } else {
            return false;
        }
    }

    //4B Valida entrada de un usuario 

    public boolean login(User u, String password) throws sudokuExceptions {
        System.out.println("Logeando...");
        if (existeUsername(u.getUsername()) == false) {
            throw new exceptions.sudokuExceptions("El usuario no existe");
        }

        //comparar en base de datos si username y password existen
        Query q = sesion.createQuery("select u from User u where username='" + u.getUsername() + "' and password='" + password + "'");
        if (q.list() != null) {
            return true;
        } else {
            return false;
        }
    }

    //4C Modificar el perfil de un Usuario determinado
//    public void modificarUser(User u, String newNombre) {
//        User aux = getUserByUsername(u.getUsername());
//        if (aux == null) {
//
//        }
//        tx = sesion.beginTransaction();
//        aux.setName(newNombre);
//        tx.commit();
//    }
    //4D Modificar la contraseña de un usuario existente
    //4E Eliminar un usuario
    //5A Insertar partida finalizada
    //5B Calcular tiempo medio de usuario
    //5C Obtener Sudoku aleatorio de los que el usuario todavia no ha jugado
    //5D Obtener ranking de usuario mas alto tiempo medio de jugador
    //5E
    public boolean existeSudoku(String solved) {
        Sudoku s;
        if (solved.equals("s")) {

        }
        return false;
    }

    public List<User> getUserByUsername(String username) {
        Query q = sesion.createSQLQuery("select * from User where username=:param1");
        q.setString("param1", username);
        return q.list();
    }

    public List<Sudoku> getSudokuBySolved(String solved) {
        Query q = sesion.createQuery("select s from Sudoku where solved='" + solved + "'");
        return q.list();
    }

    public List<History> getHistoryByUser() {
        Query q = sesion.createQuery("select u from User u where user=''");
        return q.list();
    }

    public List<User> getAllUsuarios() {
        Query q = sesion.createQuery("select * from user");
        return q.list();
    }

    public void desconectar() {
        sesion.close();
        HibernateUtil.close();
    }

}
