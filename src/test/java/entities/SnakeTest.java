package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

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

        List<Coordinate> expectedBody = new ArrayList<>(
                Arrays.asList(new Coordinate(3, 4), new Coordinate(3, 3), new Coordinate(3, 2)));

        Assertions.assertEquals(expectedBody, snake.getBody());
    }

    @ParameterizedTest
    @MethodSource("provideDirections")
    public void update_givenSnakeAndDirection_shouldUpdateCoordinates(char direction, List<Coordinate> expectedBody) {
        Board board = new Board(boardArray);
        Snake snake = Snake.generateInitialSnake(board);

        snake.update(direction);

        Assertions.assertEquals(expectedBody, snake.getBody());
    }


    private static Stream<Arguments> provideDirections() {
        return Stream.of(
                Arguments.of('u', Arrays.asList(new Coordinate(2, 4), new Coordinate(3, 4), new Coordinate(3, 3))),
                Arguments.of('d', Arrays.asList(new Coordinate(4, 4), new Coordinate(3, 4), new Coordinate(3, 3))),
                Arguments.of('l', Arrays.asList(new Coordinate(3, 4), new Coordinate(3, 3), new Coordinate(3, 2))),
                Arguments.of('r', Arrays.asList(new Coordinate(3, 5), new Coordinate(3, 4), new Coordinate(3, 3)))
        );
    }
}
