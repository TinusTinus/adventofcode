package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point

data class Vault(private val openPassages: Set<Point>, private val doors: Map<Point, Door>, val keys: Map<Key, Point>)
