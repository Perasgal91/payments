package entities;

import java.util.LinkedHashSet;
import java.util.Set;

public class Snake {
    private final Set<Coordinate> body;

    public Snake() {
        body = new LinkedHashSet<>();
    }

    public void addBodyCoordinate(int row, int column) {
        body.add(new Coordinate(row, column));
    }

    public Set<Coordinate> getBody() {
        return body;
    }
}
