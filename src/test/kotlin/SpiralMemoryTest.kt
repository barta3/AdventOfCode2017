import org.junit.Assert
import org.junit.Test

class SpiralMemoryTest {
    @Test
    fun calcDistance() {
        Assert.assertEquals(0, SpiralMemory().calcDistance(1))
        Assert.assertEquals(3, SpiralMemory().calcDistance(12))
        Assert.assertEquals(2, SpiralMemory().calcDistance(23))
        Assert.assertEquals(31, SpiralMemory().calcDistance(1024))
        Assert.assertEquals(480, SpiralMemory().calcDistance(347991))
    }

}