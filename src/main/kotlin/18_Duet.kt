var soundFrq = 0L
var regs = mutableMapOf<Char, Long>()

fun main(args: Array<String>) {
    //val lines = getLines("18_example.txt")
    val lines = getLines("18_input.txt")

    println(lines)
    val instructions = parse(lines)
    println(instructions)

    var nextIx = 0
    while (nextIx != -1) {
        val next = instructions.find { i -> i.ix == nextIx }!!
        println("exec $nextIx: $next")
        nextIx = next.exec()
    }
    println("rcv executed, res = $soundFrq")

}


fun parse(lines: List<String>): MutableList<Instr> {
    val instructions = mutableListOf<Instr>()
    lines.forEachIndexed { index, line ->
        val parts = line.split(" ")
        val instr = Instr(index, parts[0], parts[1][0], parts.getOrElse(2, { "_" }))
        instructions.add(instr)
    }
    return instructions
}

data class Instr(val ix: Int, val instr: String, val regLabel: Char, var value: String) {
    fun exec(): Int {
        regs.computeIfAbsent(regLabel, { 0 })
        val currentValue = regs.get(regLabel)!!
        val otherValue = regs[value[0]] ?: if (value == "_") null else value.toLong()

        when (instr) {
            "snd" -> soundFrq = currentValue
            "set" -> regs[regLabel] = otherValue!!
            "add" -> regs.compute(regLabel, { k, v -> v?.plus(otherValue!!) })
            "mul" -> regs.compute(regLabel, { k, v -> v?.times(otherValue!!) })
            "mod" -> regs.compute(regLabel, { k, v -> v?.rem(otherValue!!) })
            "rcv" -> if (currentValue != 0L) return -1
            "jgz" -> if (currentValue > 0) return ix + value.toInt()
        }
        return ix + 1
    }
}

data class Register(val label: Char, var value: Long = 0) {

}