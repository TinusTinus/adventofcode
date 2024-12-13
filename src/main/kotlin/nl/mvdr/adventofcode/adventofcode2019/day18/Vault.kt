package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point

data class Vault(val openPassages: Set<Point>, val doors: Map<Point, Door>, val keys: Map<Key, Point>)
