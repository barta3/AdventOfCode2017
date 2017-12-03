class SpiralMemory {
    fun calcDistance(input: Int): Int {

        val dimension = (Math.sqrt(input.toDouble()) + 0.5).toInt() + 1
        println("Input: $input, create $dimension x $dimension grid")

/*
17  16  15  14  13
18   5   4   3  12
19   6   1   2  11
20   7   8   9  10
21  22  23---> ...

5   4   3
6   1   2
7   8   9

 */
        // s(2, 2) i(4, 1)
        // d = 2+1 = 3

        // 1 234 56789
        // 44 43 42 41 40 30 20 10 00 01 02 03 04 14 24 34 - 33 32 31 21 11 12 13

        // Create Empty
        val grid = arrayListOf<IntArray>()
        (1..dimension).forEach({ grid.add(IntArray(dimension)) })



        var oneX = 0;
        var oneY = 0;
        if (dimension % 2 == 1) {
            oneX = dimension / 2
            oneY = dimension / 2
        } else {
            oneX = (dimension / 2) - 1
            oneY = dimension / 2
        }
        println("1 is at $oneX $oneY")
        grid[oneY][oneX] = 1

        printGrid(grid)


        // Find coordinate for input
        var r = 1
        var u = 1
        var l = 2
        var d = 2
        var resX = oneX
        var resY = oneY

        var c = 1
        var found = false;
        while (c < input) {
            println("Step $r right")
            for (i in 1..r) {
                resX++
                if (++c == input) {
                    println("FOUND $resX $resY")
                    found = true;
                    break
                }
            }
            r += 2
            if (found) break


            println("Step $u up")
            for (i in 1..u) {
                resY--
                if (++c == input) {
                    println("FOUND $resX $resY")
                    found = true;
                    break
                }
            }
            u += 2

            if (found) break

            println("Step $l left")
            for (i in 1..l) {
                resX--
                if (++c == input) {
                    println("FOUND $resX $resY")
                    found = true;
                    break
                }
            }
            l += 2
            if (found) break

            println("Step $d down")
            for (i in 1..d) {
                resY++
                if (++c == input) {
                    println("FOUND $resX $resY")
                    found = true;
                    break
                }
            }
            d += 2

        }

        println("$input is at: $resX $resY")
        grid[resY][resX] = input
        printGrid(grid)


        // https://en.wikipedia.org/wiki/Taxicab_geometry
        val distance = Math.abs(oneX - resX) + Math.abs(oneY - resY)


        return distance
    }

    private fun printGrid(grid: ArrayList<IntArray>) {

        println("Grid:")
        grid.forEach { row ->
            row.forEach { print("$it ") }
            println()
        }
        println()
    }

}

fun main(args: Array<String>) {
    SpiralMemory().calcDistance(8)
}
