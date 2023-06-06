package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SnakeTest {

    @Test
    public void addCoordinate_givenNoCoordinate_shouldContainNewCoordinate(){
        Snake snake = new Snake();
        snake.addBodyCoordinate(1, 1);

        Assertions.assertEquals(new Coordinate(1, 1), snake.getBody().stream().findFirst().get());
    }

    @Test
    public void addCoordinate_givenPreviousCoordinates_shouldContainNewCoordinateAtTheEnd(){
        Snake snake = new Snake();
        snake.addBodyCoordinate(1, 1);
        snake.addBodyCoordinate(2,2);
        snake.addBodyCoordinate(3,3);

        Assertions.assertEquals(new Coordinate(3, 3),
                snake.getBody().stream().reduce((prev, next) -> next).orElse(null));
    }
}
