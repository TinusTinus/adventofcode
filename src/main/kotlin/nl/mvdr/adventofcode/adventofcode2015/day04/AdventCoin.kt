package nl.mvdr.adventofcode.adventofcode2015.day04

import org.apache.commons.codec.digest.DigestUtils

/**
 * Mines a coin. Searches for an MD5 hash with the given [prefix], based on the puzzle [input].
 */
fun mine(input: List<String>, prefix: String): Int {
    val secretKey = input.first()
    var result = 0
    while (!DigestUtils.md5Hex(secretKey + result).startsWith(prefix)) {
        result++
    }
    return result
}