package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!isDiagonal(source, dest)) {
            throw new IllegalStateException(
                    String.format("Could not way by diagonal from %s to %s", source, dest)
            );
    }
        int size = Math.abs(dest.x - source.x);
        Cell[] steps = new Cell[size];
        int deltaX = (dest.x - source.x) / Math.abs(dest.x - source.x);     //используем модуль, чтобы получить дельту с нужным знаком
        int deltaY = (dest.y - source.y) / Math.abs(dest.y - source.y);;
        for (int i = 0; i < size; i++) {
            int x = source.x + (i + 1) * deltaX;        //нулевое значение i в данном случае не нужно, используем (i + 1), т.к. сразу прыгаем на др клетку
            int y = source.y + (i + 1) * deltaY;
            steps[i] = Cell.findBy(x, y);
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        int size = 8;
        boolean d = false;
        for (int i = 0; i < size; i++) {
            if ((source.x - dest.x + source.y - dest.y) % 2 != 0) {     //сумма разницы x и y должна быть чётной
                d = false;
                break;
            }
            int toX;    //перемещение по x
            int toY;    //перемещение по y
            if (dest.x > source.x && dest.y > source.y) {   //если конечная клетка вверх вправо
                toX = source.x + (i + 1);
                toY = source.y + (i + 1);
                if (dest.x == toX && dest.y == toY) {
                    d = true;
                }
            }
            if (dest.x > source.x && dest.y < source.y) {   //если конечная клетка вниз вправо
                toX = source.x + (i + 1);
                toY = source.y - (i + 1);
                if (dest.x == toX && dest.y == toY) {
                    d = true;
                }
            }
            if (dest.x < source.x && dest.y > source.y) {   //если конечная клетка вверх влево
                toX = source.x - (i + 1);
                toY = source.y + (i + 1);
                if (dest.x == toX && dest.y == toY) {
                    d = true;
                }
            }
            if (dest.x < source.x && dest.y < source.y) {   //если конечная клетка вниз влево
                toX = source.x - (i + 1);
                toY = source.y - (i + 1);
                if (dest.x == toX && dest.y == toY) {
                    d = true;
                }
            }
        }
        return d;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
