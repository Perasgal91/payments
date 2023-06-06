package entities;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class Snake {
    private final Set<Coordinate> body;

    public Snake() {
        body = new LinkedHashSet<>();
    }


    public Set<Coordinate> getBody() {
        return body;
    }

    public void addBodyCoordinate(Coordinate coordinate) {
        body.add(coordinate);
    }

    public static Snake generateInitialSnake(Board board) {
        Snake snake = new Snake();
        setHead(board, snake);
        setBody(board, snake);
        return snake;
    }

    private static void setBody(Board board, Snake snake) {
        Optional<Coordinate> head = snake.getBody().stream().findFirst();
        head.ifPresent(coordinate -> findAndAddPreviousBodyCoordinate(board, coordinate, snake, '0'));
    }

    private static void findAndAddPreviousBodyCoordinate(Board board, Coordinate head, Snake snake, char previousMovement) {
        if (previousMovement != 'd' &&
                board.getStructure()[head.getRow() - 1][head.getColumn()] == 's') { //up
            Coordinate coordinateToAdd = new Coordinate(head.getRow() - 1, head.getColumn());
            snake.addBodyCoordinate(coordinateToAdd);
            findAndAddPreviousBodyCoordinate(board, coordinateToAdd, snake, 'u');
        } else if (previousMovement != 'u' &&
                board.getStructure()[head.getRow() + 1][head.getColumn()] == 's') { // down
            Coordinate coordinateToAdd = new Coordinate(head.getRow() + 1, head.getColumn());
            snake.addBodyCoordinate(coordinateToAdd);
            findAndAddPreviousBodyCoordinate(board, coordinateToAdd, snake, 'd');
        } else if (previousMovement != 'r' &&
                board.getStructure()[head.getRow()][head.getColumn() - 1] == 's') { //left
            Coordinate coordinateToAdd = new Coordinate(head.getRow(), head.getColumn() - 1);
            snake.addBodyCoordinate(coordinateToAdd);
            findAndAddPreviousBodyCoordinate(board, coordinateToAdd, snake, 'l');
        } else if (previousMovement != 'l' &&
                board.getStructure()[head.getRow()][head.getColumn() + 1] == 's') { //right
            Coordinate coordinateToAdd = new Coordinate(head.getRow(), head.getColumn() + 1);
            snake.addBodyCoordinate(coordinateToAdd);
            findAndAddPreviousBodyCoordinate(board, coordinateToAdd, snake, 'r');
        }
    }

    private static void setHead(Board board, Snake snake) {
        int rowNumber = -1;
        int columnNumber = -1;
        for (char[] row : board.getStructure()) {
            rowNumber++;
            columnNumber = -1;
            for (char character : row) {
                columnNumber++;
                if (character == 'S') {
                    snake.addBodyCoordinate(new Coordinate(rowNumber, columnNumber));
                }
            }
        }
    }
}
