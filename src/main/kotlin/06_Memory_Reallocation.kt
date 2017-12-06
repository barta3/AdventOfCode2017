class Memory {
    val states = mutableSetOf<String>()
    fun reallocatePart1(input: IntArray): Int {

        while (true) {
            val maxIdx = input.indexOf(input.max()!!)
            val maxVal = input[maxIdx]
            println("Max Id $maxIdx   Val $maxVal")
            input[maxIdx] = 0

            redistribute(input, maxVal, maxIdx + 1)
            println(input.joinToString())
            if (states.contains(input.joinToString())) break
            states.add(input.joinToString())
        }
        println("States:")
        for (state in states) {
            println(state)
        }
        return states.size + 1
    }

    fun reallocatePart2(input: IntArray): Int {

        val part1 = reallocatePart1(input)
        return part1 - 1 - states.indexOf(input.joinToString())
    }

    private fun redistribute(input: IntArray, dist: Int, startPos: Int) {
        var s = startPos
        var v = dist
        while (v-- > 0) {
            if (s >= input.size) {
                s = 0
            }
            input[s++]++
        }
    }

}