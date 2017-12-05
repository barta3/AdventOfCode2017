import java.io.InputStream

class Maze() {
    fun getSteps(input: Array<Int>, step2: Boolean): Int {

        var pos = 0
        var steps = 0
        println("${input.joinToString()}, pos = $pos")

        while (pos < input.size) {
            val current = input[pos]
            if (step2 && current >= 3) {
                input[pos]--
            } else {
                input[pos]++
            }
            pos += current
//            println("${input.joinToString()}, pos = $pos")
            steps++
//            println("$steps - $pos")
        }
        return steps
    }

}

fun main(args: Array<String>) {
    val inputStream: InputStream = Maze::class.java.getResource("05_input.txt").openStream()
    val list = arrayListOf<Int>()
    inputStream.bufferedReader().forEachLine { l -> list.add(Integer.parseInt(l)) }

    println("${list.size} Elements")

    val steps1 = Maze().getSteps(list.toTypedArray(), false)
    println("Part 1 Finished in $steps1 steps")

    val steps2 = Maze().getSteps(list.toTypedArray(), true)
    println("Part 2 Finished in $steps2 steps")

}