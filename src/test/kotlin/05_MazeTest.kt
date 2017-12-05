import org.junit.Assert.assertEquals
import org.junit.Test

class `05_MazeTest` {
    @Test
    fun getSteps() {
        val input = arrayOf(0, 3, 0, 1, -3)
        assertEquals(5, Maze().getSteps(input, false))
        assertEquals(10, Maze().getSteps(input, true))
    }

}