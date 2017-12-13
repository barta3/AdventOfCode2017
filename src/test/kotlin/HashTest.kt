import org.junit.Assert.assertEquals
import org.junit.Test

class HashTest {

    @Test
    fun example() {
        val res = Hash().hash(intArrayOf(0,1,2,3,4).toMutableList(), intArrayOf(3,4,1,5))
        assertEquals(12, res)
    }

    @Test
    fun real() {
        val range = (0..255).toMutableList()
        val lengths = "14,58,0,116,179,16,1,104,2,254,167,86,255,55,122,244"
                .split(",")
                .map { s -> Integer.valueOf(s) }
                .toList()
        val res = Hash().hash(range, lengths.toIntArray())
        assertEquals(1935, res)
    }
}