package nl.mvdr.adventofcode.adventofcode2020.day25

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val (cardPublicKey, doorPublicKey) = lines.map(String::toInt).toList()

    val cardLoopSize = findLoopSize(cardPublicKey)
    logger.info { "Card loop size: $cardLoopSize" } // TODO reduce log level
    val cardEncryptionKey = transform(doorPublicKey, cardLoopSize)
    logger.info { "Encryption key: $cardEncryptionKey" } // TODO reduce log level

    // validate the result
    val doorLoopSize = findLoopSize(doorPublicKey)
    logger.info { "Door loop size: $doorLoopSize" } // TODO reduce log level
    val doorEncryptionKey = transform(cardPublicKey, doorLoopSize)
    require(cardEncryptionKey == doorEncryptionKey) { "Encryption keys do not match: $cardEncryptionKey, $doorEncryptionKey" }

    return cardEncryptionKey
}

private fun findLoopSize(publicKey: Int): Int {
    var value = 1
    var result = 0

    while (value != publicKey) {
        value = step(value)
        result++
    }

    return result
}

private fun transform(subjectNumber: Int, loopSize: Int): Int {
    var value = 1

    for (i in 0 until loopSize) {
        value = step(value, subjectNumber)
    }

    return value
}

private fun step(value: Int, subjectNumber: Int = 7) = (value * subjectNumber % 20201227)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day25-2020.txt")
    logger.info { result }
}
