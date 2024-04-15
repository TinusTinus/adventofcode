package nl.mvdr.adventofcode.adventofcode2021.day19

import nl.mvdr.adventofcode.point.Point3D

data class Scanner(private val id: Int, private val beacons: Set<Point3D>)

private const val SCANNER_PREFIX = "--- scanner "
private const val SCANNER_SUFFIX = " ---"

fun parseScanners(lines: List<String>): Set<Scanner> {
    val result = mutableSetOf<Scanner>()

    var scannerId: Int? = null
    var beacons: MutableSet<Point3D>? = null

    for (line in lines) {
        if (line.startsWith(SCANNER_PREFIX)) {
            // Start of a new scanner
            scannerId = parseScannerId(line)
            beacons = mutableSetOf()
        } else if (line.isEmpty()) {
            result.add(Scanner(scannerId!!, beacons!!))
            scannerId = null
            beacons = null
        } else if (line.isNotEmpty()) {
            beacons!!.add(Point3D.parse(line))
        }
    }
    result.add(Scanner(scannerId!!, beacons!!))

    return result
}

/**
 * Gets the scanner id, based on the first [line] of its textual representation.
 * For example: "--- scanner 0 ---".
 */
private fun parseScannerId(line: String): Int {
    if (!line.startsWith(SCANNER_PREFIX) || !line.endsWith(SCANNER_SUFFIX)) {
        throw IllegalArgumentException("Unable to retrieve scanner id from line '$line'")
    }
    return line.substring(SCANNER_PREFIX.length until line.length - SCANNER_SUFFIX.length).toInt()
}