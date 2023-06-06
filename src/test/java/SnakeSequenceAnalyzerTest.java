import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SnakeSequenceAnalyzerTest {

    private static final char[][] board = {
            {'-', '-', 'x', '-', '-', '-'},
            {'-', '-', 'x', '-', 'f', '-'},
            {'-', '-', '-', '-', '-', '-'},
            {'-', '-', 's', 's', 'S', '-'},
            {'-', '-', 'x', 'x', 'x', '-'},
            {'-', '-', '-', '-', '-', '-'}};
    private static final char[] sequence1 = {'u', 'u'}; // should hit food -> return "food"
    private static final char[] sequence2 = {'d'}; // should hit wall (x) -> return "game over"
    private static final char[] sequence3 = {'r', 'r'}; // should hit out of bounds -> return "game over"
    private static final char[] sequence4 = {'u', 'l', 'l', 'u'}; // should hit wall (x) -> return "game over"

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void evaluateSequence_givenInput_shouldOutput(char[][] board, char[] sequence, String expectedResult) {

        SnakeSequenceAnalyzer snakeSequenceAnalyzer = new SnakeSequenceAnalyzer(board, sequence);

        String result = snakeSequenceAnalyzer.analyze();

        Assertions.assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(board, sequence1, "food"),
                Arguments.of(board, sequence2, "game over"),
                Arguments.of(board, sequence3, "game over"),
                Arguments.of(board, sequence4, "game over")
        );
    }
}
