package dog;

import common.Coordinate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

// Assumption
// I do not remember what the exercise was, given the title (Dog & Bone) and the fact that it´s supposed to be
// dynamic programming, I will assume that we have a 2D matrix and we want to see the longest distance between a Dog and a Bone
// Given there are multiple dogs and one bones (might do multiple bones after)
public class DogAndBonePath {
    private final char[][] matrix;

    public DogAndBonePath(char[][] matrix) {
        this.matrix = matrix;
    }

    public int longestPath() {
        List<Coordinate> dogs = new ArrayList<>();
        List<Coordinate> bones = new ArrayList<>();
        findDogsAndBones(dogs, bones);
        return findLongestPath(dogs, bones);
    }

    public int shortestPath() {
        List<Coordinate> dogs = new ArrayList<>();
        List<Coordinate> bones = new ArrayList<>();
        findDogsAndBones(dogs, bones);
        return findShortestPath(dogs, bones);
    }

    public BigDecimal averagePath() {
        List<Coordinate> dogs = new ArrayList<>();
        List<Coordinate> bones = new ArrayList<>();
        findDogsAndBones(dogs, bones);
        return BigDecimal.valueOf(findAveragePath(dogs, bones)).setScale(2, RoundingMode.FLOOR);
    }

    private double findAveragePath(List<Coordinate> dogs, List<Coordinate> bones) {
        List<Integer> distances = new ArrayList<>();
        for (Coordinate dog : dogs) {
            for (Coordinate bone : bones) {
                distances.add(calculateDistance(dog, bone));
            }
        }
        return distances.stream().mapToDouble(d -> d).average().orElse(0.0);
    }

    private int findLongestPath(List<Coordinate> dogs, List<Coordinate> bones) {
        int maxDistance = Integer.MIN_VALUE;
        for (Coordinate dog : dogs) {
            for (Coordinate bone : bones) {
                maxDistance = Math.max(maxDistance, calculateDistance(dog, bone));
            }
        }
        return maxDistance;
    }

    private int findShortestPath(List<Coordinate> dogs, List<Coordinate> bones) {
        int minDistance = Integer.MAX_VALUE;
        for (Coordinate dog : dogs) {
            for (Coordinate bone : bones) {
                minDistance = Math.min(minDistance, calculateDistance(dog, bone));
            }
        }
        return minDistance;
    }

    private int calculateDistance(Coordinate dog, Coordinate bone) {
        int upDownMovements = Math.abs(dog.getRow() - bone.getRow());
        int leftRightMovements = Math.abs(dog.getColumn() - bone.getColumn());
        return upDownMovements + leftRightMovements;
    }

    private void findDogsAndBones(List<Coordinate> dogs, List<Coordinate> bones) {
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
