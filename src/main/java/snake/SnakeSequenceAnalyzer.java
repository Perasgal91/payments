package snake;

import snake.entities.Board;
import snake.entities.Snake;

public class SnakeSequenceAnalyzer {
    private final Board board;
    private final Snake snake;
    private final char[] sequence;

    public SnakeSequenceAnalyzer(char[][] board, char[] sequence) {
        this.board = new Board(board);
        this.snake = new Snake(this.board);
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
