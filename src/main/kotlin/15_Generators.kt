class Generator(val factor: Long, start: Long,
                val multimpleOf: Int) {

    var prev = start
    fun generateNext(): Long {

        val m = prev * factor
        var r = m.rem(2147483647)

        prev = r
        while (r.rem(multimpleOf) != 0L) {
            r = generateNext()
        }

        return r
    }
}

fun main(args: Array<String>) {

    // Example
    //val a = Generator(16807, 65, 4)
    //val b = Generator(48271, 8921, 8)

    // Puzzle Input
    val a = Generator(16807, 873, 4)
    val b = Generator(48271, 583, 8)


    var matches = 0
    for (i in 1..5_000_000) {
        val ga = a.generateNext()
        val gb = b.generateNext()

        //println("$ga, $gb")

        if (match(ga.toInt(), gb.toInt())) {
            matches++
        }
        if (i % 100_000 == 0) println(i)
    }
    println("Number of Matches: $matches")
}

fun match(a: Int, b: Int): Boolean {

    val la = a.shl(16)
    val lb = b.shl(16)
    return la == lb
}