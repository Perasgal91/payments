import entities.Board;
import entities.Snake;

public class SnakeSequenceAnalyzer {
    private final Board board;
    private final Snake snake;

    private final char[] sequence;

    public SnakeSequenceAnalyzer(char[][] board, char[] sequence) {
        this.board = new Board(board);
        this.snake = Snake.generateInitialSnake(this.board);
        this.sequence = sequence;
    }

    public String analyze() {
        for (char movementDirection : sequence) {
            if (!"game over".equals(board.getStatus())) {
                snake.update(movementDirection);
                board.update(snake.getBody(), snake.getPreviousBody());
            }
        }
        return board.getStatus();
    }
}
