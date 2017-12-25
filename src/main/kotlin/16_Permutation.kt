import java.util.*

fun main(args: Array<String>) {
    example()
    part1()
    part2()
}

private fun part1() {
    val input = "abcdefghijklmnop".toMutableList()
    val moves = Permutation::class.java
            .getResource("16_input.txt")
            .readText()

    val res = Permutation(input).run(moves)
    println("Part 1: $res")
}

private fun part2() {
    var input = "abcdefghijklmnop".toMutableList()
    val moves = Permutation::class.java
            .getResource("16_input.txt")
            .readText()

    var res = "start"
    val states = mutableSetOf<String>()
    var runs = 0
    val billion = 1_000_000_000
    for (i in 0..billion) {
        res = Permutation(input).run(moves)

        if (states.contains(res)) {
            println("Repeats after $i")
            runs = i
            break
        }

        states.add(res)
    }

    input = "abcdefghijklmnop".toMutableList()
    repeat(billion % runs) {
        res = Permutation(input).run(moves)
    }

    println("Part 2: $res")
}

class Permutation(val list: MutableList<Char>) {

    fun run(moves: String): String {

        moves.split(",").forEach { m ->
            when (m[0]) {
                's' -> spin(m.drop(1))
                'x' -> {
                    val (a, b) = m.drop(1).split("/").map { it.toInt() }
                    Collections.swap(list, a, b)
                }
                'p' -> {
                    val (a, b) = m.drop(1).split("/").map { it[0] }
                    Collections.swap(list, list.indexOf(a), list.indexOf(b))
                }
            }

        }
        return list.joinToString("")
    }

    private fun spin(move: String) {
        val n = Integer.valueOf(move)
        for (i in 1..n) {
            val r = list.removeAt(list.size - 1)
            list.add(0, r)
        }
    }

}

fun example() {
    val input = "abcde"
    val moves = "s1,x3/4,pe/b"

    val list = input.toMutableList()
    val res = Permutation(list).run(moves)

    println("Example: $res")
}
