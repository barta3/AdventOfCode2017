import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class HashTest {

    @Test
    fun example() {
        val res = Hash().hash(intArrayOf(0, 1, 2, 3, 4).toMutableList(), intArrayOf(3, 4, 1, 5))
        assertEquals(12, res)
    }

    @Test
    fun real() {
        val range = (0..255).toMutableList()
        val lengths = "14,58,0,116,179,16,1,104,2,254,167,86,255,55,122,244"
                .split(",")
                .map { s -> Integer.valueOf(s) }
                .toIntArray()


        val res = Hash().hash(range, lengths)
        assertEquals(1935, res)
    }

    @Test
    fun sparseHash() {
        val range = (0..255).toMutableList()
        val lengths = "14,58,0,116,179,16,1,104,2,254,167,86,255,55,122,244"
        assertEquals(6468, Hash().calcSparseHash(range, lengths))
    }

    @Test
    fun denseHash() {
        // After 64 rounds

        println(65.xor(0))

        val sparse = intArrayOf(132, 49, 125, 103, 113, 81, 78, 143, 72, 196, 214, 117, 4, 63, 24, 158, 200, 8, 47, 226, 169, 92, 211, 14, 175, 12, 86, 182, 48, 46, 251, 253, 185, 194, 1, 181, 44, 199, 184, 146, 110, 57, 148, 2, 247, 60, 149, 236, 193, 17, 154, 65, 150, 66, 26, 230, 9, 201, 107, 205, 94, 130, 64, 55, 96, 140, 192, 217, 28, 118, 240, 105, 56, 168, 15, 237, 83, 69, 111, 124, 222, 122, 97, 43, 209, 167, 53, 147, 71, 228, 238, 91, 229, 106, 186, 16, 98, 88, 197, 221, 27, 119, 190, 29, 84, 212, 52, 139, 34, 23, 249, 82, 183, 142, 3, 172, 202, 114, 104, 10, 233, 131, 87, 239, 73, 112, 136, 93, 254, 252, 220, 101, 50, 246, 99, 198, 25, 127, 6, 213, 11, 19, 100, 18, 59, 80, 108, 144, 206, 179, 225, 215, 115, 36, 95, 138, 191, 128, 79, 224, 164, 243, 32, 207, 33, 203, 5, 151, 22, 174, 134, 137, 42, 120, 68, 35, 152, 156, 45, 235, 250, 187, 165, 173, 75, 234, 232, 102, 244, 85, 171, 223, 166, 41, 61, 241, 255, 116, 210, 163, 133, 40, 178, 121, 180, 21, 13, 77, 126, 89, 153, 135, 141, 7, 188, 208, 31, 20, 231, 248, 76, 245, 67, 90, 62, 195, 227, 129, 161, 30, 176, 39, 70, 242, 160, 38, 123, 218, 157, 58, 145, 155, 177, 219, 109, 204, 162, 0, 74, 170, 51, 189, 216, 54, 37, 159)
        val hashes = Hash().calcDense(sparse)
        val expected = intArrayOf(220, 126, 125, 238, 113, 13, 76, 114, 1, 206, 66, 113, 62, 107, 131, 89)
        assertArrayEquals(expected, hashes)

    }

    @Test
    fun representHex() {
        val input = intArrayOf(64, 7, 255)
        assertEquals("4007ff", Hash().toHexString(input))
    }

    @Test
    fun part2Full() {
        runPart2("a2582a3a0e66e6e86e3812dcb672a272", "")
        runPart2("33efeb34ea91902bb2f59c9920caa6cd", "AoC 2017")
        runPart2("3efbe78a8d82f29979031a4aa0b16a9d", "1,2,3")
        runPart2("63960835bcdc130f0b66d7ff4f6a5a8e", "1,2,4")

        // Part2
        runPart2("dc7e7dee710d4c7201ce42713e6b8359", "14,58,0,116,179,16,1,104,2,254,167,86,255,55,122,244")


    }

    private fun runPart2(expected: String, input: String) {
        assertEquals(expected, Hash().part2Full(input))
    }


    @Test
    fun prepare() {
        val intArrayOf = intArrayOf(0, 1, 2, 3, 4)
        val lengths = intArrayOf(3, 4, 1, 5)
        val res = Hash().prepare("1,2,3")
        assertArrayEquals(intArrayOf(49,44,50,44,51,17,31,73,47,23), res)
    }
}