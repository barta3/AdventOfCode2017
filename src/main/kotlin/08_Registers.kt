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

data class Condition(val reg: String, val comp: Comp, val value: Int)

enum class Comp(val s: String) {
    GR(">"), GREQ(">="), EQ("=="), SMEQ("<="), SM("<");

    companion object {
        fun from(findBy: String): Comp = Comp.values().first { it.s == findBy }
    }

}

fun main(args: Array<String>) {
    val instructions = Parser().parse("08_input_example.txt")
    instructions.forEach { l -> println(l) }

    val max = run(instructions)

}

fun run(instructions: ArrayList<Instruction>): Int {

    val regs = HashMap<String, Int>()
    instructions.forEach { i ->
        //regs.computeIfAbsent(i.reg, 0)
    }

    return 0
}
