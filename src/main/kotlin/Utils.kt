fun getLines(filename: String): List<String> {
    val input = Register::class.java
            .getResource(filename)
            .readText()

    return input.split("\n")
}