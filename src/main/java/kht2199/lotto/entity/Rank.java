package kht2199.lotto.entity;

import java.util.Arrays;

import kht2199.lotto.exception.rank.OutOfRangeOfMatchedCountException;

/**
 *
 * @author heetaek.kim
 */
public enum Rank {
	FIRST(6, 2_000_000_000),
	SECOND(5, 30_000_000),
	THIRD(5, 1_500_000),
	FOURTH(4, 50_000),
	FIFTH(3, 5_000),
	MISS(0, 0);

	private final int countOfMatch;

	private final int winningMoney;

	Rank(int countOfMatch, int winningMoney) {
		this.countOfMatch = countOfMatch;
		this.winningMoney = winningMoney;
	}

	public static Rank[] range(int min, int max, boolean asc) {
		return Arrays.stream(Rank.values())
			.filter(r -> r.countOfMatch >= min && r.countOfMatch <= max)
			.sorted((a, b) -> (asc ? 1 : -1) * a.compareTo(b))
			.toArray(Rank[]::new);
	}

	public int getCountOfMatch() {
		return countOfMatch;
	}

	public int getWinningMoney() {
		return winningMoney;
	}

	public static Rank valueOf(int countOfMatch, boolean matchBonus)
			throws OutOfRangeOfMatchedCountException {
		if (countOfMatch > 6 || countOfMatch < 0) {
			throw new OutOfRangeOfMatchedCountException();
		}
		if (countOfMatch == 5 && matchBonus) {
			return SECOND;
		}
		if (countOfMatch == 5) {
			return THIRD;
		}
		return Arrays.stream(values())
			.filter(r -> r.countOfMatch == countOfMatch)
			.findFirst()
			.orElse(MISS);
	}

}