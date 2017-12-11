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
}

fun main(args: Array<String>) {
    val filename = "09_input.txt"
    val reader = Stream::class.java.getResource(filename).openStream().bufferedReader()

    val input = reader.readLine()
    val cleaned = Stream().removeGarbage(input)
    println(cleaned)
    val score = Stream().calcScore(cleaned)
    println("Score $score")
}

