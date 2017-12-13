import java.util.*
import kotlin.collections.ArrayList

class Hash {

    fun prepare(lengthString: String): IntArray {
        val standard = intArrayOf(17, 31, 73, 47, 23).asList()
        val chars = lengthString.chars().toArray()
        val combined = chars.toMutableList()
        combined.addAll(standard)
        return combined.toIntArray()
    }

    fun calcSparseHash(range: MutableList<Int>, lengths: String): Int {
        val l = prepare(lengths)
        for (i in 1..64) {
            hash(range, l)
            println("Round $i: $range")
            println("C: $currentPos - SK: $skipSize")
        }

        return range[0] * range[1]
    }

    fun calcDense(sparse: IntArray): IntArray {
        if (sparse.size != 256) throw IllegalArgumentException("Must be 256 elements")

        val hashes = ArrayList<Int>()
        for (i in 0..255 step 16) {
            var blockHash = 0
//            println(i)
            sparse.toList().subList(i, i + 16)
                    .forEach { blockHash = it.xor(blockHash) }
            println("Block $i Hash: $blockHash")
            hashes.add(blockHash)
        }
        return hashes.toIntArray()

    }

    private var currentPos: Int = 0
    private var skipSize: Int = 0

    fun hash(range: List<Int>, lengths: IntArray): Int {

        val iterator = lengths.iterator()

        while (iterator.hasNext()) {
            val nextLength = iterator.nextInt()
            reverseRange(range, currentPos, nextLength)
//            println("Range $range c=$currentPos")
            currentPos += nextLength + skipSize
            while (currentPos >= range.size) currentPos -= range.size

            skipSize++
//            println()
        }

        return range[0] * range[1]
    }

    private fun reverseRange(array: List<Int>, startPos: Int, len: Int) {
        var i = 0
        while (i < len / 2) {
            var start = startPos + i
            while (start >= array.size) start -= array.size

            var end = startPos + len - 1 - i
            while (end >= array.size) end -= array.size
//            println("Swap pos $start and $end")
            Collections.swap(array, start, end)
            i++
        }
    }

    fun toHexString(input: IntArray): String {
        return input.map {
            String.format("%02x", it)
        }.joinToString("")
    }

    fun part2Full(lengts: String): String {
        val range = (0..255).toMutableList()
        println("Orig: $range")
        println()
        val sparse = calcSparseHash(range, lengts)
        println("Sparse: $range")
        println()
        val dense = calcDense(range.toIntArray())
        println("Dense: $dense")
        println()
        return toHexString(dense)
    }
}