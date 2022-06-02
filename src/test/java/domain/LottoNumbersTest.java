package domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumbersTest {
    @Test
    void create() {
        //given
        List<LottoNumber> lottoNumbers = TestObjectSupport.convertToLottoNumberList(Arrays.asList(1, 2, 3, 4, 5, 6));

        //when
        LottoNumbers result = new LottoNumbers(lottoNumbers);

        //then
        assertThat(result).isEqualTo(new LottoNumbers(lottoNumbers));
    }

    @Test
    void createByStaticFactoryMethod() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        //when
        LottoNumbers result = LottoNumbers.create(lottoNumbers);

        //then
        assertThat(result).isEqualTo(new LottoNumbers(TestObjectSupport.convertToLottoNumberList(lottoNumbers)));
    }

    @Test
    void numberCount() {
        //given
        List<LottoNumber> fiveLottoNumbers = TestObjectSupport.convertToLottoNumberList(Arrays.asList(1, 2, 3, 4, 5));

        //then
        assertThatThrownBy(() -> new LottoNumbers(fiveLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void duplicateNumber() {
        //given
        List<LottoNumber> duplicateNumbers = TestObjectSupport.convertToLottoNumberList(Arrays.asList(1, 2, 3, 4, 5, 1));

        //then
        assertThatThrownBy(() -> new LottoNumbers(duplicateNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void toList() {
        //given
        LottoNumbers lottoNumbers = TestObjectSupport.createLottoNumbers();
        List<LottoNumber> expectedLottoNumbers = TestObjectSupport.convertToLottoNumberList(Arrays.asList(1, 2, 3, 4, 5, 6));

        //when
        List<LottoNumber> result = lottoNumbers.toList();

        //then
        assertThat(result).isEqualTo(expectedLottoNumbers);
    }

    @Test
    void match() {
        //given
        LottoNumbers comparedLottoNumbers = TestObjectSupport.createLottoNumbers();
        LottoNumbers comparingLottoNumbers = TestObjectSupport.createLottoNumbers();

        //when
        int result = comparedLottoNumbers.match(comparingLottoNumbers);

        //then
        assertThat(result).isEqualTo(6);
    }

    @Test
    void matchBonusNumber() {
        //given
        LottoNumbers lottoNumbers = TestObjectSupport.createLottoNumbers();
        LottoNumber lottoNumber1 = new LottoNumber(1);
        LottoNumber lottoNumber2 = new LottoNumber(7);

        //when
        boolean result1 = lottoNumbers.match(lottoNumber1);
        boolean result2 = lottoNumbers.match(lottoNumber2);

        //then
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
    }
}
