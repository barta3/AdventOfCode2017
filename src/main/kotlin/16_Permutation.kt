import java.lang.Exception
import java.util.*

fun main(args: Array<String>) {
    example()
    puzzle()
}

fun puzzle() {
    val input = "abcdefghijklmnop".toMutableList()
    val moves = Permutation::class.java
            .getResource("16_input.txt")
            .readText()

    val res = Permutation(input).run(moves)
    println("End: $res")
}

fun example() {


    val demo = "12/0"
    println(demo.split("/")[1])

    val input = "abcde"
    val moves = "s1,x3/4,pe/b"

    val list = input.toMutableList()
    val res = Permutation(list).run(moves)

    println("End: $res")
}

class Permutation(val list: MutableList<Char>) {
    fun run(moves: String): String {
        println("Start $moves")

        moves.split(",").forEach { m ->
            when (m[0]) {
                's' -> spin(m.drop(1))
                'x' -> exchange(m.drop(1))
                'p' -> partner(m.drop(1))
            }

        }
        return list.joinToString("")
    }

    private fun spin(move: String) {
        //println(list)
        println("spin $move")
        val n = Integer.valueOf(move)
        for (i in 1..n) {
            val r = list.removeAt(list.size - 1)
            //println("Move $r to front")
            list.add(0, r)
        }
        //println(list)
    }

    private fun exchange(move: String) {

        println("exchange $move")
        val vals = move.split("/")
        val posA = Integer.valueOf(vals[0].trim())
        val posB = Integer.valueOf(vals[1].trim())

        Collections.swap(list, posA, posB)
        //println(list)

    }

    private fun partner(move: String) {
        println("partner $move")
        val a = move.split("/")[0]
        val b = move.split("/")[1]
        val posA = list.indexOf(a[0])
        val posB = list.indexOf(b[0])
        Collections.swap(list, posA, posB)

        //println(list)
    }
}