package Lotto.Model;

import Lotto.Exception.InvalidBonusForLottoException;
import Lotto.Exception.InvalidMoneyForLottoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoCard {
    private static final int MIN_VALID_NUMBER = 1;
    private static final int MAX_VALID_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;
    private static final List<Integer> LOTTO_POOL = IntStream.range(MIN_VALID_NUMBER, MAX_VALID_NUMBER + 1).boxed().collect(Collectors.toList());
    private final List<Integer> lotto;
    private Prize prize;

    public LottoCard() {
        Collections.shuffle(LOTTO_POOL);
        this.lotto = new ArrayList<>(LOTTO_POOL.subList(0, LOTTO_SIZE));
    }

    public LottoCard(List<Integer> lotto) {
        validateLotto(lotto);
        this.lotto = lotto;
    }

    private void validateLotto(List<Integer> lotto) {
        int validNumberCount = lotto.stream()
                .filter(n -> MIN_VALID_NUMBER <= n && n <= MAX_VALID_NUMBER)
                .collect(Collectors.toSet())
                .size();

        if (validNumberCount != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 숫자범위가 맞지 않습니다.");
        }
    }

    public List<Integer> getLotto() {
        return lotto;
    }

    public void matchPrize(LottoCard winningCard, int bonus){
        validateBonus(bonus);

        int matchCount = (int)lotto.stream()
                .filter(winningCard::contains)
                .count();

        prize = Prize.valueOf(matchCount, lotto.contains(bonus));
    }

    private void validateBonus(int bonus){
        if(bonus < MIN_VALID_NUMBER || bonus > MAX_VALID_NUMBER){
            throw new InvalidBonusForLottoException();
        }
    }


    public Prize getPrize() {
        return prize;
    }


    private boolean contains(int number) {
        return lotto.contains(number);
    }
}