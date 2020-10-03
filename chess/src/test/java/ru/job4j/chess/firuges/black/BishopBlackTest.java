package ru.job4j.chess.firuges.black;

import junit.framework.TestCase;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BishopBlackTest {

    @Test
    public void whenBeginPositionIsA1() {
        BishopBlack bishopBlack = new BishopBlack(Cell.findBy(0, 0));
        Cell pos = bishopBlack.position();
        assertThat(pos, is(Cell.A1));
    }

    @Test
    public void whenFigureToDest() {
        BishopBlack bishopBlack = new BishopBlack(Cell.findBy(0, 0));
        Figure fig = bishopBlack.copy(Cell.findBy(7, 7));
        assertThat(fig.position(), is(Cell.H8));
    }

    @Test
    public void whenWayByDiagonal() {
        BishopBlack bishopBlackLeft = new BishopBlack(Cell.C1);
        Cell[] res = bishopBlackLeft.way(Cell.C1, Cell.G5);
        Cell[] expect = {
                Cell.D2,
                Cell.E3,
                Cell.F4,
                Cell.G5};
        assertThat(res, is(expect));
    }

    @Test
    public void whenDiagonalTrue() {
        BishopBlack bishopBlack = new BishopBlack(Cell.A1);
        boolean rst = bishopBlack.isDiagonal(Cell.A1, Cell.H8);
        assertThat(rst, is(true));
    }

    @Test
    public void whenDiagonalFalse() {
        BishopBlack bishopBlack = new BishopBlack(Cell.A1);
        boolean rst = bishopBlack.isDiagonal(Cell.A1, Cell.H7);
        assertThat(rst, is(false));
    }
}