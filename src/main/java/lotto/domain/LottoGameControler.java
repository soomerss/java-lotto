package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LottoGameControler {

    public List<Lotto> lottos;

    private static final String RESULT_INFO_MESSAGE = "당첨 통계\n---";

    private static final Integer[] winningPrizes = {2000000000, 30000000, 1500000, 50000, 5000};
    public LottoGameControler(){}


    public List<Lotto> buyLotto() {
        int lottoNum = LottoGenerator.inputMoney();
        return LottoGenerator.finalGenerator(lottoNum);
    }

    public Lotto winningNumbers = WinningNumberInput.winningNumberInput();
    public int bonusNumber = WinningNumberInput.BonusNumberInput();

    public void printResult(LottoSet lottoSet) {
        List<Integer> result = lottoSet.checkWinning(this.winningNumbers.getNumbers(), this.bonusNumber);
        int inputMoney = lottoSet.getInputMoney();

        System.out.println(RESULT_INFO_MESSAGE);
        for (int index = result.size() - 1; index >= 0; index--) {
            int ranking = index + 1;
            int count = result.get(index);

            System.out.println(makeRankingMessage(ranking, count));
        }
        int totalWinngingPrize = getTotalPrize(result);
        System.out.println(makeProfitMessage((double)inputMoney, (double)totalWinngingPrize));
    }

    private int getTotalPrize(List<Integer> result) {
        int totalWinngingPrize = 0;
        for (int index = 0; index < result.size(); index++) {
            int count = result.get(index);
            int winningPrize = winningPrizes[index];
            totalWinngingPrize += count * winningPrize;
        }
        return totalWinngingPrize;
    }

    private String makeProfitMessage(Double inputMoney, Double totalWinngingPrize) {
        Double profit = totalWinngingPrize / inputMoney * 100;
        DecimalFormat formatter = new DecimalFormat("###,###.0");
        return "총 수익률은 " + formatter.format(profit) + "%입니다.";
    }

    private String makeRankingMessage(int ranking, int count) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        if (ranking == 1) {
            return "6개 일치 (" + formatter.format(winningPrizes[ranking - 1]) + "원) - " + count + "개";
        }
        if (ranking == 2) {
            return "5개 일치, 보너스 볼 일치 (" + formatter.format(winningPrizes[ranking - 1]) + "원) - " + count + "개";
        }
        return 7 - ranking + 1 + "개 일치 (" + formatter.format(winningPrizes[ranking - 1]) + "원) - " + count + "개";
    }






}
