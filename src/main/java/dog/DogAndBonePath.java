package dog;

import common.Coordinate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Assumption
// I do not remember what the exercise was, given the title (Dog & Bone) and the fact that it´s supposed to be
// dynamic programming, I will assume that we have a 2D matrix and we want to see the longest distance between a Dog and a Bone
// Given there are multiple dogs and one bones (might do multiple bones after)
public class DogAndBonePath {
    private final char[][] matrix;
    private final Set<Coordinate> dogs = new HashSet<>();
    private final Set<Coordinate> bones = new HashSet<>();

    public DogAndBonePath(char[][] matrix) {
        this.matrix = matrix;
        findDogsAndBones();
    }

    public int longestPath() {
        return findLongestPath();
    }

    public int shortestPath() {
        return findShortestPath();
    }

    public BigDecimal averagePath() {
        return BigDecimal.valueOf(findAveragePath()).setScale(2, RoundingMode.FLOOR);
    }

    private double findAveragePath() {
        List<Integer> distances = new ArrayList<>();
        for (Coordinate dog : dogs) {
            for (Coordinate bone : bones) {
                distances.add(calculateDistance(dog, bone));
            }
        }
        return distances.stream().mapToDouble(d -> d).average().orElse(0.0);
    }

    private int findLongestPath() {
        int maxDistance = Integer.MIN_VALUE;
        for (Coordinate dog : dogs) {
            for (Coordinate bone : bones) {
                maxDistance = Math.max(maxDistance, calculateDistance(dog, bone));
            }
        }
        return maxDistance;
    }

    private int findShortestPath() {
        List<Integer> minDistances = new ArrayList<>();
        for (Coordinate dog : dogs) {
            for (Coordinate bone : bones) {
                minDistances.add(calculateDistance(dog, bone));
            }
        }
        return minDistances.stream().reduce(Integer.MAX_VALUE, Math::min);
    }

    private int calculateDistance(Coordinate dog, Coordinate bone) {
        int upDownMovements = Math.abs(dog.getRow() - bone.getRow());
        int leftRightMovements = Math.abs(dog.getColumn() - bone.getColumn());
        return upDownMovements + leftRightMovements;
    }

    private void findDogsAndBones() {
        int rowNumber = -1;
        int columnNumber;
        for (char[] row : matrix) {
            rowNumber++;
            columnNumber = -1;
            for (char character : row) {
                columnNumber++;
                if (character == 'd') { // dog
                    dogs.add(new Coordinate(rowNumber, columnNumber));
                } else if (character == 'b') { // bone
                    bones.add(new Coordinate(rowNumber, columnNumber));
                }
            }
        }
    }
}
