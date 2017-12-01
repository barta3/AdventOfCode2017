class InverseCaptcha {


    fun inverseCaptcha(input: String): Int {


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
}