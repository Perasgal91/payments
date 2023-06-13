package snake.entities;

import common.Coordinate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Board {
    private final char[][] structure;

    private String status;

    public Board(char[][] structure) {
        this.structure = structure;
    }

    public char[][] getStructure() {
        return structure;
    }

    public String getStatus() {
        return status;
    }

    public void update(List<Coordinate> body, List<Coordinate> previousBody) {
        if (previousBody.contains(body.get(0))) {
            status = "game over";
            return;
        }
        for (Coordinate previousCoordinate : previousBody) {
            structure[previousCoordinate.getRow()][previousCoordinate.getColumn()] = '-';
        }
        boolean isHeadMovement = true;
        for (Coordinate newCoordinate : body) {
            if (isHeadMovement) {
                changeStatus(newCoordinate);
            }
            if (status.equals("no change") || status.equals("food")) {
                structure[newCoordinate.getRow()][newCoordinate.getColumn()] = isHeadMovement ? 'S' : 's';
            }
            isHeadMovement = false;
        }
    }

    private void changeStatus(Coordinate newCoordinate) {
        if (structure.length <= newCoordinate.getRow() || structure[0].length <= newCoordinate.getColumn()) {
            status = "game over";
        } else {
            var currentValueOnCoordinate = structure[newCoordinate.getRow()][newCoordinate.getColumn()];
            if (currentValueOnCoordinate == 'x') {
                status = "game over";
            } else if (currentValueOnCoordinate == 'f') {
                status = "food";
            } else if (currentValueOnCoordinate == '-') {
                status = "no change";
            }
        }
    }

    @Override
    public String toString() {
        return "Board{" +
                "structure=" + Arrays.toString(structure) +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        Board board = (Board) o;
        return Arrays.deepEquals(structure, board.structure) && Objects.equals(status, board.status);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(status);
        result = 31 * result + Arrays.deepHashCode(structure);
        return result;
    }
}
