package dog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DogAndBonePathTest {
    @ParameterizedTest
    @MethodSource("provideMaxPathMatrix")
    public void longestPath_givenMatrix_shouldGiveLongestPath(char[][] incomingMatrix, int expectedLongestPath){
        DogAndBonePath dogAndBonePath = new DogAndBonePath(incomingMatrix);

        int result = dogAndBonePath.longestPath();

        Assertions.assertEquals(expectedLongestPath, result);
    }

    @ParameterizedTest
    @MethodSource("provideMinPathMatrix")
    public void shortestPath_givenMatrix_shouldGiveShortestPath(char[][] incomingMatrix, int expectedShortestPath){
        DogAndBonePath dogAndBonePath = new DogAndBonePath(incomingMatrix);

        int result = dogAndBonePath.shortestPath();

        Assertions.assertEquals(expectedShortestPath, result);
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
    private static final char[][] matrix1 = { // 5 max, 3 min
            {'-', '-','-', '-','-', '-'},
            {'-', 'd','-', '-','-', '-'},
            {'-', '-','-', '-','-', '-'},
            {'-', 'd','-', '-','b', '-'},
            {'-', '-','-', '-','-', '-'},
            {'-', '-','-', '-','-', '-'}
    };
    private static final char[][] matrix2 = { //7 max, 3 min
            {'-', '-','-', '-','-', '-'},
            {'-', 'd','-', '-','-', '-'},
            {'-', '-','-', '-','-', '-'},
            {'-', 'd','-', '-','b', '-'},
            {'-', '-','-', '-','-', 'b'},
            {'-', '-','-', '-','-', '-'}
    };
    private static final char[][] matrix3 = { //6 max, 1 min
            {'-', '-','-', '-','-', '-'},
            {'-', 'd','-', '-','-', '-'},
            {'-', 'b','-', '-','-', '-'},
            {'-', 'd','-', '-','b', '-'},
            {'-', '-','-', '-','-', '-'},
            {'d', '-','-', '-','-', '-'}
    };
    private static final char[][] matrix4 = { //2 max, 1 min
            {'-', '-','-', '-','-', '-'},
            {'-', 'd','-', '-','-', '-'},
            {'-', 'b','b', '-','-', '-'},
            {'-', 'd','-', '-','-', '-'},
            {'-', '-','-', '-','-', '-'},
            {'-', '-','-', '-','-', '-'}
    };
}
