package snake.entities;

import common.Coordinate;

import java.util.*;

public class Snake {
    private final List<Coordinate> body;

    private List<Coordinate> previousBody;

    public Snake(Board board) {
        body = new ArrayList<>();
        previousBody = new ArrayList<>();
        generateInitialSnake(board);
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

    private void generateInitialSnake(Board board) {
        if(board != null) {
            setHead(board);
            setBody(board);
        }
    }

    private void setHead(Board board) {
        int rowNumber = -1;
        int columnNumber;
        for (char[] row : board.getStructure()) {
            rowNumber++;
            columnNumber = -1;
            for (char character : row) {
                columnNumber++;
                if (character == 'S') {
                    addBodyCoordinate(new Coordinate(rowNumber, columnNumber));
                    return;
                }
            }
        }
    }

    private void setBody(Board board) {
        Optional<Coordinate> head = body.stream().findFirst();
        head.ifPresent(coordinate -> findAndAddPreviousBodyCoordinate(board, coordinate, '0'));
    }

    private void findAndAddPreviousBodyCoordinate(Board board, Coordinate head, char previousMovement) {
        if (previousMovement != 'd' && board.getStructure()[head.getRow() - 1][head.getColumn()] == 's') { //up
            Coordinate coordinateToAdd = new Coordinate(head.getRow() - 1, head.getColumn());
            addBodyCoordinate(coordinateToAdd);
            findAndAddPreviousBodyCoordinate(board, coordinateToAdd, 'u');
        } else if (previousMovement != 'u' && board.getStructure()[head.getRow() + 1][head.getColumn()] == 's') { // down
            Coordinate coordinateToAdd = new Coordinate(head.getRow() + 1, head.getColumn());
            addBodyCoordinate(coordinateToAdd);
            findAndAddPreviousBodyCoordinate(board, coordinateToAdd, 'd');
        } else if (previousMovement != 'r' && board.getStructure()[head.getRow()][head.getColumn() - 1] == 's') { //left
            Coordinate coordinateToAdd = new Coordinate(head.getRow(), head.getColumn() - 1);
            addBodyCoordinate(coordinateToAdd);
            findAndAddPreviousBodyCoordinate(board, coordinateToAdd, 'l');
        } else if (previousMovement != 'l' && board.getStructure()[head.getRow()][head.getColumn() + 1] == 's') { //right
            Coordinate coordinateToAdd = new Coordinate(head.getRow(), head.getColumn() + 1);
            addBodyCoordinate(coordinateToAdd);
            findAndAddPreviousBodyCoordinate(board, coordinateToAdd, 'r');
        }
    }


    public void update(char movementDirection) {

        updatePreviousBody();

        Coordinate newHead;
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
