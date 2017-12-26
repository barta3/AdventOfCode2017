var soundFrq = 0L
var regs = mutableListOf<Register>()

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
        var currReg = regs.find { register -> register.label == regLabel }
        if (currReg == null) {
            currReg = Register(regLabel)
            regs.add(currReg)
        }
        val other = regs.find { r -> r.label == value[0] }
        val otherValue = other?.value ?: if (value == "_") null else value.toLong()

        when (instr) {
            "snd" -> {
                soundFrq = currReg.value
                println("Play $soundFrq")
            }
            "set" -> currReg.value = otherValue!!
            "add" -> currReg.value += otherValue!!
            "mul" -> currReg.value *= otherValue!!
            "mod" -> currReg.value = currReg.value.rem(otherValue!!)
            "rcv" -> if (currReg.value != 0L) return -1
            "jgz" -> if (currReg.value > 0) return ix + value.toInt()
        }
        return ix + 1
    }
}

data class Register(val label: Char, var value: Long = 0) {

}