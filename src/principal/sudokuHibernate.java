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
import java.util.*;

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
        lineBreak();
        
        //(3A)Entrada de un sudoku en base de datos (1)
        Sudoku sudokuOne = new Sudoku("Medium (Level 7)", 7, "5......4129.7.4..83...1.....4...2...6.2...5.9...8...3.....8...34..6.9.5283......7", "578263941291754368364918725743592186682341579159876234925487613417639852836125497");
        System.out.println("Dando de alta un sudoku: "+sudokuOne.getUnsolved());
        try {
            sudokuDAO.insertarSudoku(sudokuOne);
            System.out.println("Se ha dado de alta el sudoku: "+sudokuOne.getUnsolved());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        lineBreak(); 
        //(3A)Entrada de un sudoku en base de datos (2)
        Sudoku sudokuTwo = new Sudoku("Medium (Level 6)", 6, ".3.....6..518.723.2.7...8.4...754.......3.......692...3.6...5.2.852.371..4.....9.", "839425167451867239267319854623754981794138625518692473376981542985243716142576398");
        System.out.println("Dando de alta un sudoku: "+sudokuTwo.getUnsolved());
        try {
            sudokuDAO.insertarSudoku(sudokuTwo);
            System.out.println("Se ha dado de alta el sudoku: "+sudokuTwo.getUnsolved());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        lineBreak(); 
        //(3B)Obtener sudoku por id
        System.out.println("Cogiendo de la base de datos el sudoku con id:1");
        try {
            System.out.println("Buscando sudoku...");
            Sudoku getSudokuOne=sudokuDAO.getSudokuById(1);
            System.out.println("Sudoku encontrado:");
            System.out.println(getSudokuOne);
        } catch (Exception e) {
        }
        //(3C)Obtener lista de todos los sudokus
        try {
            System.out.println("Cogiendo todos los sudokus");
            List<Sudoku> allSudokus = sudokuDAO.getAllSudokus();
            for (Sudoku s : allSudokus) {
                System.out.println(s);
            }
        } catch (Exception e) {
        }
        
        //(4A)Entrada de usuario en base de datos (1)
        User kelvin = new User("kelvinlt", "123", "kelvin");
        System.out.println("Dando de alta un usuario: "+kelvin.getUsername());
        try {
            sudokuDAO.insertarUser(kelvin);
            System.out.println("Se ha dado de alta el usuario: "+kelvin.getUsername());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        lineBreak();
        
        //(4A)Entrada de usuario en base de datos (2)
        User mar = new User("mardelmar", "123", "mar");
        System.out.println("Dando de alta un usuario: "+mar.getUsername());
        try {
            sudokuDAO.insertarUser(mar);
            System.out.println("Se ha dado de alta el usuario: "+mar.getUsername());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        lineBreak();        
        
        //(4B) Validacion de usuario
        try {
            System.out.println("Logeando usuario:"+kelvin.getUsername()+"...");
            
            boolean test = sudokuDAO.login(kelvin, "1");
            if(test != true){
                System.out.println("Usuario erroneo");
            }else{
                System.out.println("Usuario logeado");
            }
            
        } catch (Exception e) {
            
        }
        lineBreak();
        //(4C) Modificacion de nombre de usuario existente
        System.out.println("Modificando usuario kelvinlt :nombre");
        try {
            sudokuDAO.modificarUserName(kelvin, "algoAlgo");
            System.out.println("Modificacion correcta");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        lineBreak();
        
        //(4D) Modificacion de contraseña de usuario existente
        System.out.println("Modificacion usuario kelvinlt :password");
        try{
            sudokuDAO.modificarUserPassword(kelvin, "1234");
            System.out.println("Modificacion correcta");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        lineBreak();
        
        //(4E) Eliminacion de un usuario
        System.out.println("Eliminacion de usuario");
        try {
            sudokuDAO.eliminarUser(mar);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        lineBreak();
        
        //(5A) Insert de history en base de datos
        System.out.println("Insertar history de user kelvinlt con sudoku 1");
        History historyKelvin1 = new History(sudokuOne, kelvin, 100);
        try {
            sudokuDAO.insertarHistory(historyKelvin1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        lineBreak();
        
        
        System.out.println("Cerrando session...");
        sudokuDAO.desconectar();
        lineBreak();  
        System.out.println("Cerrado");
        lineBreak();  
    }
    
    public static void lineBreak(){
        System.out.println("-----------------------------------------------------");
    }
}

