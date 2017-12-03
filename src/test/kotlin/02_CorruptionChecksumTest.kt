import org.junit.Assert
import org.junit.Test

class CorruptionChecksumTest {
    @Test
    fun testCalcPart1() {
        val row1 = intArrayOf(5, 1, 9, 5)
        val row2 = intArrayOf(7, 5, 3)
        val row3 = intArrayOf(2, 4, 6, 8)
        val input = arrayListOf<IntArray>(row1, row2, row3)
        val result = CorruptionChecksum().calcPart1(input)
        Assert.assertEquals(18, result)
    }

    @Test
    fun testCalcPart2() {
        val row1 = intArrayOf(5, 9, 2, 8)
        val row2 = intArrayOf(9, 4, 7, 3)
        val row3 = intArrayOf(3, 8, 6, 5)
        val input = arrayListOf<IntArray>(row1, row2, row3)
        val result = CorruptionChecksum().calcPart2(input)
        Assert.assertEquals(9, result)
    }

}