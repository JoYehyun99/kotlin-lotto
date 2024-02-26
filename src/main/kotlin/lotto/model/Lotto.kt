package lotto.model

data class Lotto(private val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == NUMBER_COUNT) { "로또 번호는 중복되지 않는 6개의 수로 구성되어야 합니다." }
    }

    fun countMatchingNumbers(winningNumbers: Lotto): Int = numbers.intersect(winningNumbers.numbers).size

    fun hasMatchingBonusNumbers(bonusNumber: LottoNumber): Boolean = bonusNumber in numbers

    override fun toString(): String = numbers.toList().sortedBy { it.number }.toString()

    companion object {
        const val NUMBER_COUNT = 6

        fun of(vararg numbers: Int): Lotto = Lotto(numbers.map { LottoNumber.valueOf(it) }.toSet())
    }
}
