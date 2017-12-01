class InverseCaptcha {


    fun part1(input: String): Int {
        var res = 0
        val chars = input.toCharArray()

        for (i in chars.indices) {
            val firstChar = chars[i]
            val secondIndex = if (i < input.length - 1) i + 1 else 0
            if (firstChar == chars[secondIndex]) {
                res += Integer.parseInt(firstChar.toString())
            }

        }
        return res
    }

    fun part2(input: String): Int {
        var res = 0
        val chars = input.toCharArray()
        val halfSize = chars.size / 2

        for (i in chars.indices) {
            val secIdx = if(i + halfSize < chars.size) i + halfSize else i - halfSize

            val secChar = chars[secIdx]
            if (chars[i] == secChar) {
                res += Integer.parseInt(chars[i].toString())
            }
        }
        return res
    }
}