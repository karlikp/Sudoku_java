/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author piter
 */
public class SudokuGameHistory {
    
    package pl.polsl.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Karol
 */
public class ChessPositionHistory {
    public ArrayList<Pair<ChessPosition, List<ChessPosition>>> getHistory() {
        return positions;
    }
    
    public void clear() {
        positions.clear();
    }
    
    public void add(Pair<ChessPosition, List<ChessPosition>> pos) {
        positions.add(pos);
    }
    
    public int size() {
        return positions.size();
    }
    
    private final ArrayList<Pair<ChessPosition, List<ChessPosition>>> positions = new ArrayList<>();
}
    
}
