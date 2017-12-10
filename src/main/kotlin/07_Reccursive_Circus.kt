val no = Node("")

class Tower {


    fun findBottom(filename: String): String {

        val list = ArrayList<Node>()
        val reader = Tower::class.java.getResource(filename).openStream().bufferedReader()
        reader.forEachLine { line ->
            list.add(createNode(line))
        }

        list.forEach { n -> println(n) }

        // create real children Nodes
        list.forEach { n ->
            n.children.forEach { c ->
                val childNode = list.find { n -> n.name == c }!!
                childNode.parent = n
                n.ch.add(childNode)
            }
        }

        list.forEach { n -> println("Name: ${n.name}  ${n.ch.size} Children ") }



        val root = list.find {n -> n.parent == no}!!
        return root.name
    }

    private fun createNode(line: String): Node {
        val name = line.split(" ")[0]
        if (line.contains("-> ")) {
            val children = line.split("-> ")[1].split(", ")
            return Node(name = name, children = children)
        }
        return Node(name)
    }
}

data class Node(val name: String,
                val children: List<String> = arrayListOf(),
                val ch: ArrayList<Node> = ArrayList<Node>()) {
    var parent: Node = no
}

fun main(args: Array<String>) {

    val root1 = Tower().findBottom("07_input_example.txt")
    println("Root1 is $root1")
    val root2 = Tower().findBottom("07_input_part1.txt")
    println("Root2 is $root2")

}