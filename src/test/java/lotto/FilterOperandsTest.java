package lotto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterOperandsTest {
    private final FilterOperands dut = new FilterOperands();

    @ParameterizedTest(name = "{displayName} - {arguments}")
    @MethodSource("rawFormulaToOperands")
    void filterWithoutCustomDelimiter(String rawFormula, Collection<Operand> operands) {
        assertThat(dut.filter(rawFormula)).containsExactlyElementsOf(operands);
    }

    @ParameterizedTest(name = "{displayName} - {arguments}")
    @MethodSource("rawFormulaAndDelimiterToOperands")
    void filterWithCustomDelimiter(String rawFormula, Delimiter delimiter, Collection<Operand> operands) {
        assertThat(dut.filter(rawFormula, delimiter)).containsExactlyElementsOf(operands);
    }

    static Stream<Arguments> rawFormulaToOperands() {
        return Stream.of(
                Arguments.of(
                        "//;\n1,2:3",
                        Arrays.asList(new Operand(1), new Operand(2), new Operand(3))
                ),
                Arguments.of(
                        "1,2:3",
                        Arrays.asList(new Operand(1), new Operand(2), new Operand(3))
                )
        );
    }

    static Stream<Arguments> rawFormulaAndDelimiterToOperands() {
        return Stream.of(
                Arguments.of(
                        "//;\n1,2;3",
                        new Delimiter(";"),
                        Arrays.asList(new Operand(1), new Operand(2), new Operand(3))
                ),
                Arguments.of(
                        "//?\n1?2,3",
                        new Delimiter("?"),
                        Arrays.asList(new Operand(1), new Operand(2), new Operand(3))
                ),
                Arguments.of(
                        "//_\n1,2:3,4_1",
                        new Delimiter("_"),
                        Arrays.asList(new Operand(1), new Operand(2), new Operand(3), new Operand(4), new Operand(1))
                )
        );
    }
}
