/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import exceptions.sudokuExceptions;
import dao.SudokuDAO;
import entities.History;
import entities.Sudoku;
import entities.User;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Kelvi
 */
public class sudokuHibernate {
    public static void main(String[] args){
        System.out.println("Estableciendo conexion con la base de datos...");
        lineBreak();
        SudokuDAO sudokuDAO = new SudokuDAO();
        lineBreak();
        System.out.println("Conexion establecida");
        User kelvin = new User("kelvinlt", "123", "kelvin");
        System.out.println("Dando de alta un usuario");
        try {
            sudokuDAO.insertarUser(kelvin);
            System.out.println("Se ha dado de alta el usuario: "+kelvin.getUsername());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        lineBreak();
        
        sudokuDAO.desconectar();
    }
    
    public static void lineBreak(){
        System.out.println("-----------------------------------------------------");
    }
}

