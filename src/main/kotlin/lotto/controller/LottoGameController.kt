package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoMachine
import lotto.model.PurchaseAmount
import lotto.model.WinningLotto
import lotto.model.WinningStatus
import lotto.util.LottoNumbersGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val lottoCount = getLottoCount(purchaseAmount)
        val totalLottoTickets = generateLottoTickets(lottoCount)

        OutputView.printNumberOfTicket(lottoCount.numberOfManualLotto, lottoCount.getNumberOfAutoTickets())
        OutputView.printLottoTickets(totalLottoTickets)

        val winningStatus = makeResult(totalLottoTickets)
        OutputView.printWinningStatus(winningStatus)
        OutputView.printEarningRate(winningStatus.getEarningRate(purchaseAmount.money))
    }

    private fun getPurchaseAmount(): PurchaseAmount {
        val purchaseAmount = InputView.getPurchaseAmount()
        return PurchaseAmount.from(purchaseAmount) ?: getPurchaseAmount()
    }

    private fun getLottoCount(purchaseAmount: PurchaseAmount): LottoCount {
        val totalLottoCount = purchaseAmount.getTotalNumberOfLotto()
        val manualLottoCount = InputView.getNumberOfManualLotto()
        return LottoCount.from(totalLottoCount, manualLottoCount) ?: getLottoCount(purchaseAmount)
    }

    private fun generateLottoTickets(lottoCount: LottoCount): List<Lotto> {
        val manualLottoTickets =
            if (lottoCount.numberOfManualLotto > 0) generateManualLotto(lottoCount.numberOfManualLotto) else emptyList()
        val autoLottoTickets = generateAutoLotto(lottoCount.getNumberOfAutoTickets())
        return manualLottoTickets.plus(autoLottoTickets)
    }

    private fun generateManualLotto(numberOfManualTickets: Int): List<Lotto> {
        val manualLottoNumbers = InputView.getManualLottoNumbers(numberOfManualTickets)
        return LottoMachine.issueManualTickets(manualLottoNumbers)
    }

    private fun generateAutoLotto(numberOfAutoTickets: Int): List<Lotto> {
        return LottoMachine.issueAutoTickets(numberOfAutoTickets, LottoNumbersGenerator)
    }

    private fun makeResult(lottoTickets: List<Lotto>): WinningStatus {
        val lotteryResult =
            WinningLotto(Lotto(InputView.getWinningNumbers()), InputView.getBonusNumber())
        return WinningStatus(lotteryResult.generateWinningStatus(lottoTickets))
    }
}
