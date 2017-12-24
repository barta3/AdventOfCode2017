import java.util.LinkedList

val edges = mutableListOf<Edge>()

class Pipes() {

}

data class Edge(val id: Int, val vertices: MutableList<Int> = mutableListOf())

fun createEdges(line: String) {
    val id = Integer.valueOf(line.split(" <-> ")[0])
    edges.add(Edge(id))
}

fun createVertices(line: String) {

    val split = line.split(" <-> ")
    val id = Integer.valueOf(split[0])
    val exisiting = edges.find { e -> e.id == id }

    val vertIds = split[1].split(", ").map { Integer.valueOf(it) }
    exisiting?.vertices?.addAll(vertIds)
}

fun bfs(): MutableList<Int> {
    val visited = mutableListOf<Int>()
    val queue = LinkedList<Edge>()

    val zero = edges.find { e -> e.id == 0 }!!
    queue.push(zero)
    visited.add(zero.id)

    while (!queue.isEmpty()) {
        val current = queue.pop()

        for (child in current.vertices) {
            if (visited.none { v -> v == child }) {
                queue.push(edges.find { l -> l.id == child })
                visited.add(child)
            }
        }
    }
    return visited
}


fun main(args: Array<String>) {

    // Parse
    //val file = "12_example.txt"
    val file = "12_input.txt"
    var reader = Pipes::class.java
            .getResource(file)
            .openStream()
            .bufferedReader()

    reader.forEachLine { createEdges(it) }

    println(edges)

    reader = Pipes::class.java
            .getResource(file)
            .openStream()
            .bufferedReader()

    reader.forEachLine { createVertices(it) }

    println(edges)

    val visited = bfs()
    println("${visited.size} Elements:  $visited")
}


