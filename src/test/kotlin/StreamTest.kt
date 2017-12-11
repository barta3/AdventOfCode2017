
import org.junit.Assert.assertEquals
import org.junit.Test

class StreamTest {
    @Test
    fun removeGarbage() {
        val expected = ""
        checkClean("<!!!>>", expected)
        checkClean("<abcd123*>", expected)
        checkClean("<<<<>", expected)
        checkClean("<{!>}>", expected)
        checkClean("<!!!>>", expected)
        checkClean("<{o\"i!a,<{i<a>", expected)

        checkClean("{}", "{}")
        checkClean("{{{}}}", "{{{}}}")
        checkClean("{{},{}}", "{{},{}}")
        checkClean("{{{},{},{{}}}}", "{{{},{},{{}}}}")
        checkClean("{<{},{},{{}}>}", "{}")
        checkClean("{<a>,<a>,<a>,<a>}", "{}")
        checkClean("{{<a>},{<a>},{<a>},{<a>}}", "{{},{},{},{}}")
        checkClean("{{<!>},{<!>},{<!>},{<a>}}", "{{}}")
    }

    private fun checkClean(input: String, expected: String) {
        assertEquals(expected, Stream().removeGarbage(input))
    }

    @Test
    fun testCalcScore() {
        assertEquals(1, Stream().calcScore("{}"))
        assertEquals(6, Stream().calcScore("{{{}}}"))
        assertEquals(5, Stream().calcScore("{{},{}}"))
        assertEquals(16, Stream().calcScore("{{{},{},{{}}}}"))
    }

    @Test
    fun testCountGarbagePayload() {
        assertEquals(0, Stream().countGarbagePayload("<>"))
        assertEquals(17, Stream().countGarbagePayload("<random characters>"))
        assertEquals(3, Stream().countGarbagePayload("<<<<>"))
        assertEquals(2, Stream().countGarbagePayload("<{!>}>"))
        assertEquals(0, Stream().countGarbagePayload("<!!>"))
        assertEquals(0, Stream().countGarbagePayload("<!!!>>"))
        assertEquals(10, Stream().countGarbagePayload("<{o\"i!a,<{i<a>"))
    }

}