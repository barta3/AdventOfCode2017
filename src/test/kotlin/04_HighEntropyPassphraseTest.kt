import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class `04_HighEntropyPassphraseTest` {
    @Test
    fun isValid() {
        assertTrue(HighEntropyPassphrase().isValid("aa bb cc dd ee"))
        assertFalse(HighEntropyPassphrase().isValid("aa bb cc dd aa"))
        assertTrue(HighEntropyPassphrase().isValid("aa bb cc dd aaa"))
    }

    @Test
    fun anagram() {
        assertTrue(HighEntropyPassphrase().anagramCheck("abcde fghij"))
        assertFalse(HighEntropyPassphrase().anagramCheck("abcde xyz ecdab"))
        assertTrue(HighEntropyPassphrase().anagramCheck("a ab abc abd abf abj"))
        assertTrue(HighEntropyPassphrase().anagramCheck("iiii oiii ooii oooi oooo"))
        assertFalse(HighEntropyPassphrase().anagramCheck("oiii ioii iioi iiio"))

    }

}