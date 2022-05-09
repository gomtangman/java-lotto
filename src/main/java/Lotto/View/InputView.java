package Lotto.View;

import Lotto.Exception.CustomException;
import Lotto.Model.LottoCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
        throw new CustomException("유틸성클래스는 인스턴스 생성을 할 수 없습니다.");
    }

    public static int inputCash() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static int inputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static List<LottoCard> inputManualLotto(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<LottoCard> lottoCards = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottoCards.add(new LottoCard(Arrays.stream(SCANNER.nextLine()
                            .trim()
                            .split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList())));
        }

        return lottoCards;
    }

    public static List<Integer> inputLastWeekWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        return Arrays.stream(SCANNER.nextLine()
                        .trim()
                        .split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int inputBonus() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(SCANNER.nextLine());
    }
}