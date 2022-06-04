package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, false, new Cash(2_000_000_000)),
    SECOND(5, true, true, new Cash(30_000_000)),
    THIRD(5, true, false, new Cash(1_500_000)),
    FOURTH(4, false, false, new Cash(50_000)),
    FIFTH(3, false, false, new Cash(5_000)),
    LOST(0, false, false, new Cash(0));

    private final int matchCount;
    private final boolean needBonusCheck;
    private final boolean bonusMatch;
    private final Cash winningMoney;

    Rank(int matchCount, boolean needBonusCheck, boolean bonusMatch, Cash winningMoney) {
        this.matchCount = matchCount;
        this.needBonusCheck = needBonusCheck;
        this.bonusMatch = bonusMatch;
        this.winningMoney = winningMoney;
    }

    public static Rank of(int matchCount, boolean bonusMatch) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isSameMatchCount(matchCount))
                .filter(rank -> rank.checkBonus(bonusMatch))
                .findAny()
                .orElse(LOST);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public Cash getWinningMoney() {
        return winningMoney;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    private boolean isSameMatchCount(int matchCount) {
        return this.matchCount == matchCount;
    }

    private boolean checkBonus(boolean bonusMatch) {
        if (!this.needBonusCheck) {
            return true;
        }
        return this.bonusMatch == bonusMatch;
    }
}
