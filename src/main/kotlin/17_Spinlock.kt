fun main(args: Array<String>) {

    part1()
    part2()

}

private fun part1() {
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

private fun part2() {
    // Zero is always at pos 0 -> only remember inserts after zero
    val steps = 369
    var target = 0
    var next = 0
    for (i in 1..50_000_000) {
        next = ((steps + next) % i) + 1

        if(next == 1) {
            target = i
        }
    }

    println("Part 2: $target")
}

