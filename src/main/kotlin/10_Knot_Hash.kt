import java.util.*

class Hash {
    fun hash(range: List<Int>, lengths: IntArray): Int {


        var currentPos = 0
        var skipSize = 0
        val iterator = lengths.iterator()

        while (iterator.hasNext()) {
            val nextLength = iterator.nextInt()
            reverseRange(range, currentPos, nextLength)
            println("Range $range c=$currentPos")
            currentPos += nextLength + skipSize
            if (currentPos >= range.size) currentPos -= range.size

            skipSize++
            println()
        }

        return range[0] * range[1]
    }

    private fun reverseRange(array: List<Int>, startPos: Int, len: Int) {
        var i = 0
        while (i < len / 2) {
            var start = startPos + i
            if (start >= array.size) start -= array.size

            var end = startPos + len - 1 - i
            if (end >= array.size) end -= array.size
            println("Swap pos $start and $end")
            Collections.swap(array, start, end)
            i++
        }
    }
}