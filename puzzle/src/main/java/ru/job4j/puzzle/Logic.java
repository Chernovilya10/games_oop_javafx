package ru.job4j.puzzle;

import ru.job4j.puzzle.firuges.Cell;
import ru.job4j.puzzle.firuges.Figure;

import java.util.Arrays;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final int size;
    private final Figure[] figures;
    private int index = 0;

    public Logic(int size) {
        this.size = size;
        this.figures = new Figure[size * size];
    }

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (this.isFree(steps)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }

    public boolean isFree(Cell ... cells) {
        boolean result = cells.length > 0;
        for (Cell cell : cells) {
            if (this.findBy(cell) != -1) {
               result = false;
               break;
            }
        }
        return result;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    public boolean monoHorizontal(int[][] data, int row) {
        boolean rst = true;
        for (int i = 0; i < data.length; i++) {
                if (data[row][i] != 1) {
                    rst = false;
                    break;
                }
        }
        return rst;
    }

    public boolean monoVertical(int[][] data, int column) {
        boolean rst = true;
        for (int j = 0; j < data.length; j++) {
                if (data[j][column] != 1) {
                    rst = false;
                    break;
            }
        }
        return rst;
    }

    public boolean isWin() {
        int[][] table = this.convert();
        boolean result = false;
        for (int index = 0; index < table.length; index++) {
            if (table[index][index] == 1) {
                if (monoHorizontal(table, index) || monoVertical(table, index)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
//        for (int row = 0; row < table.length; row++) {
//            for (int cell = 0; cell < table.length; cell++) {
//                if (table[row][cell] == 1) {
//                    boolean r = true;
//                    boolean c = true;
//                    for (int first = 0; first < table.length; first++) {    //проверка заполненности строки
//                        if (table[row][first] != 1) {
//                            r = false;
//                            break;
//                        }
//                    }
//                    for (int second = 0; second < table.length; second++) {    //проверка заполненности столбца
//                        if (table[second][cell] != 1) {
//                            c = false;
//                            break;
//                        }
//                    }
//                    result = r || c;    //если столбец или строка заполнены, то result будет true
//                }
//            }
//        }
//        return result;
//    }

    public int[][] convert() {
        int[][] table = new int[this.size][this.size];
        for (int row = 0; row != table.length; row++) {
            for (int cell = 0; cell != table.length; cell++) {
                int position = this.findBy(new Cell(row, cell));
                if (position != -1 && this.figures[position].movable()) {
                    table[row][cell] = 1;
                }
            }
        }
        return table;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.convert());
    }
}
