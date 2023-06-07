import entities.Board;
import entities.Snake;

import java.util.Objects;

public class SnakeSequenceAnalyzer {
    private Board board;
    private Snake snake;

    private char[] sequence;

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
