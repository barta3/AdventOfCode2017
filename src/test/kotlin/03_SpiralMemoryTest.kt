import org.junit.Assert.assertEquals
import org.junit.Test

class `03_SpiralMemoryTest` {
    @Test
    fun calcDistance() {
        assertEquals(0, SpiralMemory().calcDistance(1))
        assertEquals(3, SpiralMemory().calcDistance(12))
        assertEquals(2, SpiralMemory().calcDistance(23))
        assertEquals(31, SpiralMemory().calcDistance(1024))
        assertEquals(480, SpiralMemory().calcDistance(347991))
    }

    @Test
    fun testPart2() {
        assertEquals(10, SpiralMemory().part2(8))
        assertEquals(54, SpiralMemory().part2(50))
        assertEquals(349975, SpiralMemory().part2(347991))
    }

}