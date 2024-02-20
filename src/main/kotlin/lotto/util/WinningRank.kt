package lotto.util

enum class WinningRank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    companion object {
        fun convert(matchCount: Int, matchBonus: Boolean): WinningRank =
            when (matchCount) {
                6 -> FIRST
                5 -> if (matchBonus) SECOND else THIRD
                4 -> FOURTH
                3 -> FIFTH
                else -> MISS
            }
    }
}
