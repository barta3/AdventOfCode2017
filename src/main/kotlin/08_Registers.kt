class Parser {
    fun parse(filename: String): ArrayList<Instruction> {
        val reader = Parser::class.java.getResource(filename).openStream().bufferedReader()

        val instructions = ArrayList<Instruction>()
        reader.forEachLine { l ->
            val parts = l.split(" ")

            if (parts.size != 7) throw IllegalArgumentException("invalid line: $l")

            val reg = parts[0]
            val op = Operation.valueOf(parts[1].toUpperCase())
            val amount = Integer.parseInt(parts[2])

            val condReg = parts[4]
            val condCom = Comp.from(parts[5])
            val condValue = Integer.parseInt(parts[6])

            val condition = Condition(condReg, condCom, condValue)

            val instruction = Instruction(reg, op, amount, condition)
            instructions.add(instruction)
        }

        return instructions

    }
}

data class Instruction(val reg: String,
                       val operation: Operation,
                       val amount: Int,
                       val condition: Condition)

enum class Operation { INC, DEC }

data class Condition(val reg: String, val comp: Comp, val value: Int) {
    fun eval(regs: HashMap<String, Int>): Boolean {
        if (!regs.containsKey(reg)) {
            regs.put(reg, 0)
        }

        val regValue = regs.get(reg)!!

        val contidtion = when (comp) {
            Comp.GR -> regValue > value
            Comp.GREQ -> regValue >= value
            Comp.EQ -> regValue == value
            Comp.SMEQ -> regValue <= value
            Comp.SM -> regValue < value
            Comp.NEQ -> regValue != value
        }

        return contidtion

    }
}

enum class Comp(val s: String) {
    GR(">"), GREQ(">="), EQ("=="), SMEQ("<="), SM("<"), NEQ("!=");

    companion object {
        fun from(findBy: String): Comp = Comp.values().first { it.s == findBy }
    }
}

fun run(instructions: ArrayList<Instruction>): HashMap<String, Int> {

    var max = 0
    val regs = HashMap<String, Int>()

    instructions.forEach { i ->
        if (i.condition.eval(regs)) {
            val amountAbs = if(Operation.DEC == i.operation) (-1) * i.amount else i.amount
            regs.merge(i.reg, amountAbs, Math::addExact)
            println("Match $i newVals: $regs")
            max = Math.max(max, regs.get(i.reg)!!)
        } else {
            println("No match $i")
        }
    }

    println("Overall Max: $max")

    return regs
}

fun main(args: Array<String>) {
//    val instructions = Parser().parse("08_input_example.txt")
    val instructions = Parser().parse("08_input_part1.txt")
    instructions.forEach { l -> println(l) }

    val registers = run(instructions)
    println(registers)

    val maxValue = registers.values.max()
    println("Final Max is $maxValue")

}

