import java.io.InputStream

class CorruptionChecksum {
    fun calcPart1(sheet: ArrayList<IntArray>): Number {

        var res = 0;
        sheet.forEach { ints ->
            res += ints.max()!! - ints.min()!!
        }
        return res
    }

    fun calcPart2(sheet: ArrayList<IntArray>): Number {
        var res = 0;

        sheet.forEach { row ->
            row.forEach { n ->
                val r = row.filter { e -> e != n }
                        .find { e -> e % n == 0 }
                res += r?.div(n) ?: 0
            }

        }

        return res;
    }

}

fun main(args: Array<String>) {
    val inputStream: InputStream = CorruptionChecksum::class.java.getResource("02_input.txt").openStream()

    val sheet = arrayListOf<IntArray>();
    inputStream.bufferedReader().useLines { lines ->
        lines.forEach {
            val row = it.split("\t").map { v -> Integer.parseInt(v) }.toIntArray()
            sheet.add(row)
        }
    }
    println("${sheet.size} Rows")

    val res1 = CorruptionChecksum().calcPart1(sheet)
    println("Result Part 1: $res1")


    val res2 = CorruptionChecksum().calcPart2(sheet)
    println("Result Part 2: $res2")
}
