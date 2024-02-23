package lotto.view

import lotto.model.Lotto
import lotto.model.WinningStatus
import lotto.util.WinningRank

object OutputView {
    fun printNumberOfTicket(numberOfTicket: Int) {
        println("${numberOfTicket}개를 구매했습니다.")
    }

    fun printLottoTickets(lottoTickets: List<Lotto>) {
        lottoTickets.forEach { println(it) }
        println()
    }

    fun printWinningStatus(winningStatus: WinningStatus) {
        println("\n당첨 통계")
        println("---------")
        for (rank in WinningRank.entries) {
            when (rank) {
                WinningRank.MISS -> continue
                WinningRank.SECOND -> print("${rank.countOfMatch}개 일치, 보너스 볼 일치(${rank.winningMoney}원) - ")
                else -> print("${rank.countOfMatch}개 일치 (${rank.winningMoney}원) - ")
            }
            println("${winningStatus.resultStatus.getOrDefault(rank, 0)}개")
        }
    }

    fun printEarningRate(earningRate: Double) {
        println("총 수익률은 %.2f입니다.".format(earningRate))
    }
}
