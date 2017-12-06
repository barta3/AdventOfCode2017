import org.junit.Assert.assertEquals
import org.junit.Test

class `06_MemoryTest` {
    @Test
    fun part1() {
        assertEquals(5, Memory().reallocatePart1(intArrayOf(0, 2, 7, 0)))
        assertEquals(5042, Memory().reallocatePart1(intArrayOf(5, 1, 10, 0, 1, 7, 13, 14, 3, 12, 8, 10, 7, 12, 0, 6)))
    }

    @Test
    fun part2() {
        assertEquals(4, Memory().reallocatePart2(intArrayOf(0, 2, 7, 0)))
        assertEquals(1086, Memory().reallocatePart2(intArrayOf(5, 1, 10, 0, 1, 7, 13, 14, 3, 12, 8, 10, 7, 12, 0, 6)))
    }

}