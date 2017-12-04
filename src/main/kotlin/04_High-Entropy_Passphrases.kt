import java.io.InputStream

class HighEntropyPassphrase {
    fun isValid(phrase: String): Boolean {
        val elements = phrase.split(" ");
        val numberOfElements = elements.size

        return elements
                .groupBy { it }
                .count() == numberOfElements
    }

    fun anagramCheck(phrase: String): Boolean {
        val words = phrase.split(" ")
        val sortedWords = ArrayList<String>()
        words.forEach { word ->
            val sortedWord = word.split("")
                    .filter { !it.isEmpty() }
                    .sorted()
                    .joinToString("")
            sortedWords.add(sortedWord)
        }

        println(sortedWords)

        return sortedWords.distinct().size == words.size
    }
}

fun main(args: Array<String>) {
    val inputStream: InputStream = HighEntropyPassphrase::class.java.getResource("04_input.txt").openStream()
    var valid = 0;
    var inValid = 0;
    inputStream.bufferedReader().useLines { lines ->
        lines.forEach {
            //            if (HighEntropyPassphrase().isValid(it)) valid++ else inValid++
            if (HighEntropyPassphrase().anagramCheck(it)) valid++ else inValid++
        }
    }

    println("Valid: $valid - Invalid: $inValid")

}