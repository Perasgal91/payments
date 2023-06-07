package dog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

class DogAndBonePathTest {
    @ParameterizedTest
    @MethodSource("provideMaxPathMatrix")
    public void longestPath_givenMatrix_shouldGiveLongestPath(char[][] incomingMatrix, int expectedLongestPath) {
        DogAndBonePath dogAndBonePath = new DogAndBonePath(incomingMatrix);

        int result = dogAndBonePath.longestPath();

        Assertions.assertEquals(expectedLongestPath, result);
    }

    @ParameterizedTest
    @MethodSource("provideMinPathMatrix")
    public void shortestPath_givenMatrix_shouldGiveShortestPath(char[][] incomingMatrix, int expectedShortestPath) {
        DogAndBonePath dogAndBonePath = new DogAndBonePath(incomingMatrix);

        int result = dogAndBonePath.shortestPath();

        Assertions.assertEquals(expectedShortestPath, result);
    }

    @ParameterizedTest
    @MethodSource("provideAveragePathMatrix")
    public void averagePath_givenMatrix_shouldGiveAveragePath(char[][] incomingMatrix, BigDecimal expectedAveragePath) {
        DogAndBonePath dogAndBonePath = new DogAndBonePath(incomingMatrix);

        BigDecimal result = dogAndBonePath.averagePath();

        Assertions.assertEquals(expectedAveragePath, result);
    }

    private static Stream<Arguments> provideMaxPathMatrix() {
        return Stream.of(
                Arguments.of(matrix1, 5),
                Arguments.of(matrix2, 7),
                Arguments.of(matrix3, 6),
                Arguments.of(matrix4, 2)
        );
    }

    private static Stream<Arguments> provideMinPathMatrix() {
        return Stream.of(
                Arguments.of(matrix1, 3),
                Arguments.of(matrix2, 3),
                Arguments.of(matrix3, 1),
                Arguments.of(matrix4, 1)
        );
    }

    private static Stream<Arguments> provideAveragePathMatrix() {
        return Stream.of(
                Arguments.of(matrix1, new BigDecimal("4.00").setScale(2, RoundingMode.FLOOR)),
                Arguments.of(matrix2, new BigDecimal("5.00").setScale(2, RoundingMode.FLOOR)),
                Arguments.of(matrix3, new BigDecimal("3.33").setScale(2, RoundingMode.FLOOR)),
                Arguments.of(matrix4, new BigDecimal("1.5").setScale(2, RoundingMode.FLOOR))
        );
    }

    private static final char[][] matrix1 = { // 5 max, 3 min, 4,0 ave
            {'-', '-', '-', '-', '-', '-'},
            {'-', 'd', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-'},
            {'-', 'd', '-', '-', 'b', '-'},
            {'-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-'}
    };
    private static final char[][] matrix2 = { //7 max, 3 min, (5+3+7+5)/4= 5,0 ave
            {'-', '-', '-', '-', '-', '-'},
            {'-', 'd', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-'},
            {'-', 'd', '-', '-', 'b', '-'},
            {'-', '-', '-', '-', '-', 'b'},
            {'-', '-', '-', '-', '-', '-'}
    };
    private static final char[][] matrix3 = { //6 max, 1 min, (1+1+4+5+3+6)/6 = 3,33 ave
            {'-', '-', '-', '-', '-', '-'},
            {'-', 'd', '-', '-', '-', '-'},
            {'-', 'b', '-', '-', '-', '-'},
            {'-', 'd', '-', '-', 'b', '-'},
            {'-', '-', '-', '-', '-', '-'},
            {'d', '-', '-', '-', '-', '-'}
    };
    private static final char[][] matrix4 = { //2 max, 1 min, (1+1+2+2)/4 = 1,5 ave
            {'-', '-', '-', '-', '-', '-'},
            {'-', 'd', '-', '-', '-', '-'},
            {'-', 'b', 'b', '-', '-', '-'},
            {'-', 'd', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-'}
    };
}
