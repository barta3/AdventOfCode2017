val no = Node("")

class Tower {

    fun findBottom(list: ArrayList<Node>): Node {
        val root = list.find { n -> n.parent == no }!!
        return root
    }

    fun createNodes(filename: String): ArrayList<Node> {
        val list = ArrayList<Node>()
        val reader = Tower::class.java.getResource(filename).openStream().bufferedReader()
        reader.forEachLine { line ->
            list.add(createNode(line))
        }

        // create real children Nodes
        list.forEach { n ->
            n.children.forEach { c ->
                val childNode = list.find { n -> n.name == c }!!
                childNode.parent = n
                n.ch.add(childNode)
            }
        }

        list.forEach { n -> println("Name: ${n.name}  ${n.weight} Weight ") }
        return list
    }

    private fun createNode(line: String): Node {
        val name = line.split(" ")[0]
        val weight = Integer.parseInt(line.split(" ")[1].trim('(', ')'))
        if (line.contains("-> ")) {
            val children = line.split("-> ")[1].split(", ")
            return Node(name = name, children = children, weight = weight, accWeight =   weight)
        }
        return Node(name, weight, accWeight = weight)
    }
}

data class Node(val name: String,
                var weight: Int = 0,
                var accWeight: Int = 0,
                val children: List<String> = arrayListOf(),
                val ch: ArrayList<Node> = ArrayList<Node>(),
                var level : Int = 0) {
    var parent: Node = no
}

fun findWrong(root: Node) {

    val list = ArrayList<Node>()
    calcWeightOfSubTree(root, 0, list)
    println(root)
    val byParent = list.groupBy { n -> n.parent }

    println(byParent)
    for (entry in byParent.entries) {
        val nodes = entry.value
        val partition = nodes.partition { n -> n.accWeight == nodes.get(0).accWeight}
        if(partition.first.isEmpty() || partition.second.isEmpty()) continue

        var wrong: Node
        var shouldBe: Node
        if(partition.first.size > partition.second.size) {
            wrong = partition.second.get(0)
            shouldBe = partition.first.get(0)
        } else {
            wrong = partition.first.get(0)
            shouldBe = partition.second.get(0)
        }

        println()
        println("Error on level ${wrong.level}")
        nodes.forEach { print("  " + it.weight) }
        println()

        val diff = wrong.accWeight - shouldBe.accWeight
        println("Wrong Is ${wrong.name} ${wrong.weight}, should be ${wrong.weight -diff}")
    }
}

fun calcWeightOfSubTree(n: Node, level: Int, list: ArrayList<Node>): Int {

    if (n.ch.isEmpty()) {
        n.level = level
        list.add(n)
        return n.weight
    }
    var sum = n.accWeight
    n.ch.forEach { sum += calcWeightOfSubTree(it, level +1, list) }
    println("Weight of ${n.name}: $sum Level $level")
    n.level = level
    n.accWeight = sum
    list.add(n)
    return sum
}

fun main(args: Array<String>) {

//    val nodes = Tower().createNodes("07_input_example.txt")
    val nodes = Tower().createNodes("07_input_part1.txt")
    val root = Tower().findBottom(nodes)
    println("Root is ${root}")

    findWrong(root)
}