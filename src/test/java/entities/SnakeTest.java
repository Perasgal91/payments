package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

class SnakeTest {

    @Test
    public void addCoordinate_givenNoCoordinate_shouldContainNewCoordinate() {
        Snake snake = new Snake();
        snake.addBodyCoordinate(new Coordinate(1, 1));

        Assertions.assertEquals(new Coordinate(1, 1), snake.getBody().stream().findFirst().get());
    }

    @Test
    public void addCoordinate_givenPreviousCoordinates_shouldContainNewCoordinateAtTheEnd() {
        Snake snake = new Snake();
        snake.addBodyCoordinate(new Coordinate(1, 1));
        snake.addBodyCoordinate(new Coordinate(2, 2));
        snake.addBodyCoordinate(new Coordinate(3, 3));

        Assertions.assertEquals(new Coordinate(3, 3),
                snake.getBody().stream().reduce((prev, next) -> next).orElse(null));
    }


    private static final char[][] boardArray = {
            {'-', '-', 'x', '-', '-', '-'},
            {'-', '-', 'x', '-', 'f', '-'},
            {'-', '-', '-', '-', '-', '-'},
            {'-', '-', 's', 's', 'S', '-'},
            {'-', '-', 'x', 'x', 'x', '-'},
            {'-', '-', '-', '-', '-', '-'}};

    @Test
    public void generateSnake_givenBoard_shouldGenerateSnakeCoordinates() {
        Board board = new Board(boardArray);
        Snake snake = Snake.generateInitialSnake(board);

        Set<Coordinate> expectedBody = new LinkedHashSet<>(
                Arrays.asList(new Coordinate(3, 4), new Coordinate(3, 3), new Coordinate(3, 2)));

        Assertions.assertEquals(expectedBody, snake.getBody());
    }
}
