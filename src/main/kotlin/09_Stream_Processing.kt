class Stream {
    fun removeGarbage(input: String): String {
        val round1 = input.replace(Regex("!."), "")
        return round1.replace(Regex("<.*?>,?"), "")
    }

    fun calcScore(cleaned: String): Int {
        var level = 0
        var score = 0
        cleaned.split("").forEach { c ->
            when (c) {
                "{" -> level++
                "}" -> {
                    score += level
                    level--
                }
            }
        }
        return score
    }

    fun countGarbagePayload(input: String): Int {
        val cleaned = input.replace(Regex("!."), "")
        val regex = Regex("<.*?>")

        return regex.findAll(cleaned).sumBy { it.value.length - 2 }
    }
}

fun main(args: Array<String>) {
    val filename = "09_input.txt"
    val reader = Stream::class.java.getResource(filename).openStream().bufferedReader()

    val input = reader.readLine()
    val cleaned = Stream().removeGarbage(input)
    println(cleaned)
    val score = Stream().calcScore(cleaned)
    println("Score $score")

    val part2 = Stream().countGarbagePayload(input)
    println("Part 2: $part2")
}

