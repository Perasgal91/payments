package entities;

import java.util.*;

public class Snake {
    private List<Coordinate> body;

    private List<Coordinate> previousBody;

    public Snake() {
        body = new ArrayList<>();
        previousBody = new ArrayList<>();
    }


    public List<Coordinate> getBody() {
        return body;
    }

    public List<Coordinate> getPreviousBody() {
        return previousBody;
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

    private static void setBody(Board board, Snake snake) {
        Optional<Coordinate> head = snake.getBody().stream().findFirst();
        head.ifPresent(coordinate -> findAndAddPreviousBodyCoordinate(board, coordinate, snake, '0'));
    }

    private static void findAndAddPreviousBodyCoordinate(Board board, Coordinate head, Snake snake, char previousMovement) {
        if (previousMovement != 'd' && board.getStructure()[head.getRow() - 1][head.getColumn()] == 's') { //up
            Coordinate coordinateToAdd = new Coordinate(head.getRow() - 1, head.getColumn());
            snake.addBodyCoordinate(coordinateToAdd);
            findAndAddPreviousBodyCoordinate(board, coordinateToAdd, snake, 'u');
        } else if (previousMovement != 'u' && board.getStructure()[head.getRow() + 1][head.getColumn()] == 's') { // down
            Coordinate coordinateToAdd = new Coordinate(head.getRow() + 1, head.getColumn());
            snake.addBodyCoordinate(coordinateToAdd);
            findAndAddPreviousBodyCoordinate(board, coordinateToAdd, snake, 'd');
        } else if (previousMovement != 'r' && board.getStructure()[head.getRow()][head.getColumn() - 1] == 's') { //left
            Coordinate coordinateToAdd = new Coordinate(head.getRow(), head.getColumn() - 1);
            snake.addBodyCoordinate(coordinateToAdd);
            findAndAddPreviousBodyCoordinate(board, coordinateToAdd, snake, 'l');
        } else if (previousMovement != 'l' && board.getStructure()[head.getRow()][head.getColumn() + 1] == 's') { //right
            Coordinate coordinateToAdd = new Coordinate(head.getRow(), head.getColumn() + 1);
            snake.addBodyCoordinate(coordinateToAdd);
            findAndAddPreviousBodyCoordinate(board, coordinateToAdd, snake, 'r');
        }
    }


    public void update(char movementDirection) {

        updatePreviousBody();

        Coordinate newHead = null;
        switch (movementDirection) {
            case 'u':
                newHead = getNewHead(-1, 0);
                if (newHead != null) {
                    moveCoordinates(newHead);
                }
                break;
            case 'd':
                newHead = getNewHead(1, 0);
                if (newHead != null) {
                    moveCoordinates(newHead);
                }
                break;
            case 'r':
                newHead = getNewHead(0, 1);
                if (newHead != null) {
                    moveCoordinates(newHead);
                }
                break;
            case 'l':
                newHead = getNewHead(0, -1);
                if (newHead != null) {
                    moveCoordinates(newHead);
                }
                break;
        }
    }

    private void updatePreviousBody() {
        previousBody = new ArrayList<>(body);
    }

    private void moveCoordinates(Coordinate newHead) {
        var index = 1;
        for (Coordinate oldCoordinate : new ArrayList<>(body)) {
            if (index < body.size()) {
                body.set(index++, oldCoordinate);
            }
        }
        body.set(0, newHead);
    }

    private Coordinate getNewHead(int rowAddition, int columnAddition) {
        Optional<Coordinate> head = body.stream().findFirst();
        if (head.isPresent()) {
            Coordinate newCoordinate = new Coordinate(head.get().getRow() + rowAddition, head.get().getColumn() + columnAddition);
            return body.contains(newCoordinate) ? null : newCoordinate;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snake snake = (Snake) o;
        return Objects.equals(body, snake.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }
}
