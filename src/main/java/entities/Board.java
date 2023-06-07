package entities;

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
        for (Coordinate previousCoordinate : previousBody) {
            structure[previousCoordinate.getRow()][previousCoordinate.getColumn()] = '-';
        }
        boolean firstTime = true;
        for (Coordinate newCoordinate : body) {
            if (firstTime) {
                changeStatus(newCoordinate);
            }
            if(status.equals("no change") || status.equals("food")) {
                structure[newCoordinate.getRow()][newCoordinate.getColumn()] = firstTime ? 'S' : 's';
            }
            firstTime = false;
        }
    }

    private void changeStatus(Coordinate newCoordinate) {
        try {
            var currentValueOnCoordinate = structure[newCoordinate.getRow()][newCoordinate.getColumn()];
            if (currentValueOnCoordinate == 'x') {
                status = "game over";
            } else if (currentValueOnCoordinate == 'f') {
                status = "food";
            } else if (currentValueOnCoordinate == '-') {
                status = "no change";
            }
        } catch (IndexOutOfBoundsException e) {
            status = "game over";
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
