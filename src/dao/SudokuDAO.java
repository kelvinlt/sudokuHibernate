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
    
    //3A Insertar nuevo sudoku en la BBDD
    public void insertarSudoku(Sudoku u) {
        tx = sesion.beginTransaction();
        sesion.save(u);
        tx.commit();
    }

    //3B Conseguir un sudoku a partir de un id
    public Sudoku getSudokuById(int id) throws sudokuExceptions{
        Sudoku aux =(Sudoku) sesion.get(Sudoku.class, id);
        if(aux==null){
            throw new sudokuExceptions("No se ha encontrado el sudoku");
        }
        return aux;
    }

    //3C Conseguir todos los sudokus en la base de datos
    public List<Sudoku> getAllSudokus() {
        Query q = sesion.createQuery("select s from Sudoku s");
        return q.list();
    }

    //4A Insertar un nuevo usuario en la BBDD
    public void insertarUser(User u) throws sudokuExceptions {
        if (checkUser(u.getUsername())) {
            throw new sudokuExceptions("Ya existe el usuario");
        }
        tx = sesion.beginTransaction();
        sesion.save(u);
        tx.commit();
    }

    //funcion para comprobar que existe usuario
    public boolean checkUser(String username) throws sudokuExceptions {
        User u = (User) sesion.get(User.class, username);
        if (u != null) {
            return true;
        } else {
            return false;
        }
    }

    //4B Valida entrada de un usuario 
    public boolean login(User u, String password) throws sudokuExceptions {
        
        if (checkUser(u.getUsername()) == false) {
            throw new exceptions.sudokuExceptions("El usuario no existe");
        }

        //comparar en base de datos si username y password existen
        Query q = sesion.createQuery("select u from User u where username='" + u.getUsername() + "' and password='" + password + "'");
        if (!q.list().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    //4C Modificar el perfil de un Usuario determinado
    public void modificarUserName(User u, String newNombre) throws exceptions.sudokuExceptions {
        boolean auxBol = checkUser(u.getUsername());
        if(auxBol ==true){
            User aux = getUserByUsername(u.getUsername());
            
            tx = sesion.beginTransaction();
            aux.setName(newNombre);
            tx.commit();
        }else{
            throw new sudokuExceptions("El usuario no se ha encontrado para cambiar el nombre");
        }
    }
    
    public User getUserByUsername(String username) throws exceptions.sudokuExceptions {
        boolean auxBol = checkUser(username);
        Query q = sesion.createQuery("select u from User u where username='" + username + "'");
        User userEncontrado = new User();
        
        if(auxBol ==true){
            List<User> allUsers = q.list();
            for (User s : allUsers) {
                userEncontrado = s;
            }

            q.list();
            return userEncontrado;
        }else{
            throw new sudokuExceptions("El usuario no se ha encontrado para la obtencion de datos");
        }
    }

    //4D Modificar la contraseña de un usuario existente
    public void modificarUserPassword(User u, String newPassword) throws sudokuExceptions {
        boolean auxBol = checkUser(u.getUsername());
        if(auxBol ==true){
            User aux = getUserByUsername(u.getUsername());
            tx = sesion.beginTransaction();
            aux.setPassword(newPassword);
            tx.commit();
        }else{
            throw new sudokuExceptions("El usuario no se ha encontrado para modificar la password");
        }
    }

    //4E Eliminar un usuario
    public void eliminarUser(User u)throws sudokuExceptions{
        boolean auxBol = checkUser(u.getUsername());
        if(auxBol ==true){
            User aux = getUserByUsername(u.getUsername());
            sesion.delete(aux);
        }else{
            throw new sudokuExceptions("El usuario no existe");
        }
    }
    
    //5A Insertar partida finalizada
    public void insertarHistory(History h) throws exceptions.sudokuExceptions{
        if(checkUser(h.getUser().getUsername())==true){
            if(checkSudoku(h.getSudoku().getId())==true){
                tx = sesion.beginTransaction();
                sesion.save(h);
                tx.commit();  
            }else{
                throw new sudokuExceptions("No existe el sudoku");
            }
        }else{
            throw new sudokuExceptions("No existe el usuario");
        }
    }
    
    //5B Calcular tiempo medio de usuario
    public void tiempoMedioUser(User u) throws exceptions.sudokuExceptions{
        
    }
    
    //5C Obtener Sudoku aleatorio de los que el usuario todavia no ha jugado
    public void sudokuAleatorio(User u) throws exceptions.sudokuExceptions{
        
    }
    
    //5D Obtener ranking de usuario mas alto tiempo medio de jugador
    public void obtenerRankingTiempoMasAlto(User u) throws exceptions.sudokuExceptions{
        
    }
    
    //5E Obtener posicion dentro del ranking para un usuario en concreto
    public void obtenerPosicionFromUser(User u) throws exceptions.sudokuExceptions{
        
    }
    
    //public boolean checkSudoku(String solved) {
    //    Sudoku s;
    //    if (solved.equals("s")) {
    //        
    //    }
    //    return false;
    //}
    
    //Comprobaciond de si existe sudoku
    public boolean checkSudoku(int s){
        Sudoku u = (Sudoku) sesion.get(Sudoku.class, s);
        if (u != null) {
            return true;
        } else {
            return false;
        }
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
