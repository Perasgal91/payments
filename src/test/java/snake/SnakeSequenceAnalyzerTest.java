package snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class SnakeSequenceAnalyzerTest {

    private static char[][] board = null;
    private static final char[] sequence1 = {'u', 'u'}; // should hit food -> return "food"
    private static final char[] sequence2 = {'d'}; // should hit wall (x) -> return "game over"
    private static final char[] sequence3 = {'r', 'r'}; // should hit out of bounds -> return "game over"
    private static final char[] sequence4 = {'u', 'l', 'l', 'u'}; // should hit wall (x) -> return "game over"
    private static final char[] sequence5 = {'u', 'l', 'd'}; // should hit snake body (s) -> return "game over"

    @ParameterizedTest
    @MethodSource("provideSequences")
    public void evaluateSequence_givenInput_shouldOutput(char[] sequence, String expectedResult) {
        resetBoard();

        SnakeSequenceAnalyzer snakeSequenceAnalyzer = new SnakeSequenceAnalyzer(board, sequence);

        String result = snakeSequenceAnalyzer.analyze();

        Assertions.assertEquals(expectedResult, result);
    }

    private void resetBoard() {
        board = new char[][]{
                {'-', '-', 'x', '-', '-', '-'},
                {'-', '-', 'x', '-', 'f', '-'},
                {'-', '-', '-', '-', '-', '-'},
                {'-', 's', 's', 's', 'S', '-'},
                {'-', '-', 'x', 'x', 'x', '-'},
                {'-', '-', '-', '-', '-', '-'}};
    }

    private static Stream<Arguments> provideSequences() {
        return Stream.of(
                Arguments.of(sequence1, "food"),
                Arguments.of(sequence2, "game over"),
                Arguments.of(sequence3, "game over"),
                Arguments.of(sequence4, "game over"),
                Arguments.of(sequence5, "game over")
        );
    }
}
