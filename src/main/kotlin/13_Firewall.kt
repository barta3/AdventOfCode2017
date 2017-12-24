import java.io.BufferedReader

class Firewall(val layers: MutableList<Layer> = mutableListOf<Layer>()) {

    var ps = 0
    var paketPos = -1
    var severity = 0


    fun print() {
        println("Picosecond: $ps")
        layers.forEach(Layer::print)
        println()
    }

    fun stepScanner() {
        println("Step scanners")
        layers.forEach { layer -> layer.stepScanner() }
        ps++
    }

    fun movePacket() {
        paketPos++
        println("Move to $paketPos")
        val layer = layers.find { l -> l.depth == paketPos } ?: return
        val hit = layer.scannerPos == 0
        if (hit) {
            println("Hit ${layer.depth} * ${layer.range}")
            severity += layer.depth * layer.range

        }
    }

}

class Layer(val depth: Int, val range: Int = 0) {
    var scannerPos = 0
    var moveUp = true


    fun stepScanner() {
        if (moveUp) {
            scannerPos++
        } else {
            scannerPos--
        }
        if (scannerPos <= 0 || scannerPos >= range - 1) {
            moveUp = !moveUp
        }
    }

    fun print() {
        print("$depth ")
        for (i in 0 until range) {
            print(if (scannerPos == i) "[S]" else "[ ]")

        }
        println()
    }
}

fun main(args: Array<String>) {

    val reader = Firewall::class.java
            .getResource("13_input.txt")
            .openStream()
            .bufferedReader()

    val layers = getLayersFromFile(reader)
    //val layers = getDemoLayers() // Severity = 24


    val firewall = Firewall(layers)
    println("Initial State: ")
    firewall.layers.forEach(Layer::print)
    println()

    val max = layers.maxBy(Layer::depth)
    for (i in 1..max!!.depth + 1) {
        firewall.movePacket()
        firewall.stepScanner()
        firewall.print()
    }

    print("Severity: ${firewall.severity}")

}

fun getLayersFromFile(reader: BufferedReader): MutableList<Layer> {
    val layers = mutableListOf<Layer>()
    reader.forEachLine {
        val d = it.split(": ")[0]
        val r = it.split(": ")[1]
        layers.add(Layer(Integer.valueOf(d), Integer.valueOf(r)))
    }


    return layers
}

private fun getDemoLayers(): MutableList<Layer> {
    val demoLayers = mutableListOf<Layer>()

    demoLayers.add(Layer(0, 3))
    demoLayers.add(Layer(1, 2))
    demoLayers.add(Layer(4, 4))
    demoLayers.add(Layer(6, 4))
    return demoLayers
}