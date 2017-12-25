fun main(args: Array<String>) {
    val steps = 369
    val buffer = mutableListOf(0)
    var pos = 0
    for (v in 1..2017) {
        var next = (pos + steps) % buffer.size
        next += 1
        buffer.add(next, v)
        pos = next
        println("$v $buffer")
    }
    println("Part 1: ${buffer[pos + 1]}")

}

