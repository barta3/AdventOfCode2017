import kotlin.math.absoluteValue

class HexGrid {

    val cells = mutableListOf<Cell>()
    val dim = 10

    fun initGrid() {
        for (i in -1 * dim..dim) {
            for (j in -1 * dim..dim)
                cells.add(Cell(i, j))
        }
        println(cells)
    }

    fun goSteps(steps: String) {
        var x = 0
        var y = 0
        steps.split(",").forEach { step ->
            when (step) {
                "n" -> x++
                "ne" -> {
                    x++; y--
                }
                "se" -> y--
                "s" -> x--
                "sw" -> {
                    x--; y++
                }
                "nw" -> y++

            }
            println("X: $x  Y:$y")
        }
        println(Math.max(x.absoluteValue, x.absoluteValue))
        println()
    }
}

data class Cell(val x: Int, val y: Int)

fun main(args: Array<String>) {
    val hexGrid = HexGrid()
    hexGrid.initGrid()
    hexGrid.goSteps("ne,ne,ne") // 3
    hexGrid.goSteps("ne,ne,sw,sw") // 0
    hexGrid.goSteps("ne,ne,s,s") // 2
    hexGrid.goSteps("se,sw,se,sw,sw") // 3


    val reader = HexGrid::class.java
            .getResource("11_input.txt")
            .openStream()
            .bufferedReader()

    val input = reader.readLine()
    println("Size: ${input.length}")
    hexGrid.goSteps(input) // 824
}

