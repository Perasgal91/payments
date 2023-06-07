package dog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DogAndBonePathTest {
    @ParameterizedTest
    @MethodSource("provideMatrix")
    public void longestPath_givenMatrix_shouldGiveLongestPath(char[][] incomingMatrix, int expectedLongestPath){
        DogAndBonePath dogAndBonePath = new DogAndBonePath(incomingMatrix);

        int result = dogAndBonePath.longestPath();

        Assertions.assertEquals(expectedLongestPath, result);
    }

    private static Stream<Arguments> provideMatrix() {
        return Stream.of(
                Arguments.of(matrix1, 5),
                Arguments.of(matrix2, 7),
                Arguments.of(matrix3, 6),
                Arguments.of(matrix4, 2)
        );
    }
    private static final char[][] matrix1 = {
            {'-', '-','-', '-','-', '-'},
            {'-', 'd','-', '-','-', '-'},
            {'-', '-','-', '-','-', '-'},
            {'-', 'd','-', '-','b', '-'},
            {'-', '-','-', '-','-', '-'},
            {'-', '-','-', '-','-', '-'}
    };
    private static final char[][] matrix2 = { //7
            {'-', '-','-', '-','-', '-'},
            {'-', 'd','-', '-','-', '-'},
            {'-', '-','-', '-','-', '-'},
            {'-', 'd','-', '-','b', '-'},
            {'-', '-','-', '-','-', 'b'},
            {'-', '-','-', '-','-', '-'}
    };
    private static final char[][] matrix3 = { //6
            {'-', '-','-', '-','-', '-'},
            {'-', 'd','-', '-','-', '-'},
            {'-', 'b','-', '-','-', '-'},
            {'-', 'd','-', '-','b', '-'},
            {'-', '-','-', '-','-', '-'},
            {'d', '-','-', '-','-', '-'}
    };
    private static final char[][] matrix4 = { //2
            {'-', '-','-', '-','-', '-'},
            {'-', 'd','-', '-','-', '-'},
            {'-', 'b','b', '-','-', '-'},
            {'-', 'd','-', '-','-', '-'},
            {'-', '-','-', '-','-', '-'},
            {'-', '-','-', '-','-', '-'}
    };
}
