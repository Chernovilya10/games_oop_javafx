package ru.job4j.chess.firuges;

public class OccupiedCellException extends Exception {
    public OccupiedCellException(String massage) {
        super(massage);
    }
}
