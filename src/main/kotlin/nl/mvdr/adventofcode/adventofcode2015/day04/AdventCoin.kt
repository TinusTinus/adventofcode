package nl.mvdr.adventofcode.adventofcode2015.day04

import org.apache.commons.codec.digest.DigestUtils

/**
 * Mines a coin.
 *
 * @param input the puzzle input, where the first line is the secret key
 * @param prefix the prefix to search for (five or six zeroes)
 */
fun mine(input: List<String>, prefix: String): Int {
    val secretKey = input.first()
    var result = 0
    while (!DigestUtils.md5Hex(secretKey + result).startsWith(prefix)) {
        result++
    }
    return result
}