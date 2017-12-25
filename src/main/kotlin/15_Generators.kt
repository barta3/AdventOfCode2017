class Generator(val factor: Long, start: Long) {

    var prev = start
    fun generateNext(): Long {

        val m = prev * factor
        val r = m % 2147483647
        prev = r
        return r
    }
}

fun main(args: Array<String>) {

    // Example
    // val a = Generator(16807, 65)
    // val b = Generator(48271, 8921)

    val a = Generator(16807, 873)
    val b = Generator(48271, 583)


    var matches = 0
    for (i in 0..40_000_000) {
        val ga = a.generateNext()
        val gb = b.generateNext()

        //println("$ga, $gb")

        if (match(ga.toInt(), gb.toInt())) {
            matches++
        }
        if (i % 1000 == 0) println(i)
    }
    println("Number of Matches: $matches")
}

fun match(a: Int, b: Int): Boolean {

    val la = a.shl(16)
    val lb = b.shl(16)
    return la == lb
}