package entities;

public class Board {
    private char[][] structure;

    public Board ( char [][] structure){
        this.structure = structure;
    }

    public char[][] getStructure() {
        return structure;
    }
}
