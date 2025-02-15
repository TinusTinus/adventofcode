package nl.mvdr.adventofcode.adventofcode2015.day25

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Point

private val logger = KotlinLogging.logger{}

fun getCode(row: Int, column: Int): Long {
    var code = 20151125L
    var position = Point(1, 1)
    while (position.x != column || position.y != row) {
        code = (code * 252533L) % 33554393L
        logger.debug { "$position -> $code" }
        position = when(position.y) {
            1 -> Point(1, position.x + 1)
            else -> Point(position.x + 1, position.y - 1)
        }
    }
    return code
}