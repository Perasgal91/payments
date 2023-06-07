package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class BoardTest {

    private static final char[][] boardArray = {
            {'-', '-', 'x', '-', '-', '-'},
            {'-', '-', 'x', '-', 'f', '-'},
            {'-', '-', '-', '-', '-', '-'},
            {'-', '-', 's', 's', 'S', '-'},
            {'-', '-', 'x', 'x', 'x', '-'},
            {'-', '-', '-', '-', '-', '-'}};

    @Test
    public void update_givenHeadAndPreviousHead_shouldChangeBoardAndUpdateStatus() {

        char[][] expectedBoard = {
                {'-', '-', 'x', '-', '-', '-'},
                {'-', '-', 'x', '-', 'f', '-'},
                {'-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', 's', 's', 'S'},
                {'-', '-', 'x', 'x', 'x', '-'},
                {'-', '-', '-', '-', '-', '-'}};

        Board board = new Board(boardArray);

        var previousSnakeBody = new ArrayList<>(
                Arrays.asList(new Coordinate(3, 4), new Coordinate(3, 3), new Coordinate(3, 2)));
        var newSnakeBody = new ArrayList<>(
                Arrays.asList(new Coordinate(3, 5), new Coordinate(3, 4), new Coordinate(3, 3)));

        board.update(newSnakeBody, previousSnakeBody);

        Assertions.assertArrayEquals(expectedBoard, board.getStructure());

        Assertions.assertEquals("no change", board.getStatus());
    }
}
