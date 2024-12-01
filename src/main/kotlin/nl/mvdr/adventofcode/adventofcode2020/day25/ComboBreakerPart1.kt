package nl.mvdr.adventofcode.adventofcode2020.day25

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Long {
    val (cardPublicKey, doorPublicKey) = lines.map(String::toLong).toList()
    val cardEncryptionKey = findEncryptionKey(cardPublicKey, doorPublicKey)

    // validate the result
    val doorEncryptionKey = findEncryptionKey(doorPublicKey, cardPublicKey)
    require(cardEncryptionKey == doorEncryptionKey) { "Encryption keys do not match: $cardEncryptionKey, $doorEncryptionKey" }

    return cardEncryptionKey
}

private fun findEncryptionKey(ownPublicKey: Long, otherPublicKey: Long) = transform(otherPublicKey, findLoopSize(ownPublicKey))

private fun findLoopSize(publicKey: Long): Int {
    var value = 1L
    var result = 0

    while (value != publicKey) {
        value = step(value, 7L)
        result++
    }

    logger.debug { "Loop size for public key $publicKey: $result" }
    return result
}

private fun transform(subjectNumber: Long, loopSize: Int): Long {
    var value = 1L
    for (i in 0 until loopSize) {
        value = step(value, subjectNumber)
    }
    return value
}

private fun step(value: Long, subjectNumber: Long) = (value * subjectNumber % 20201227)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day25-2020.txt")
    logger.info { result }
}
