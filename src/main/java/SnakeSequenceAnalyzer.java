import entities.Board;
import entities.Snake;

public class SnakeSequenceAnalyzer {
    private Board board;
    private Snake snake;

    private char[] sequence;

    public SnakeSequenceAnalyzer(char[][] board, char[] sequence) {
        this.board = new Board(board);
        this.snake = Snake.generateInitialSnake(this.board);
        this.sequence = sequence;
    }

    public SnakeSequenceAnalyzer() {

    }

    public String analyze() {
        return null;
    }
}
