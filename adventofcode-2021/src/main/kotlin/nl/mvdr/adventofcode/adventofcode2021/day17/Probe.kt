package nl.mvdr.adventofcode.adventofcode2021.day17

import nl.mvdr.adventofcode.point.Point
import nl.mvdr.adventofcode.point.plus
import kotlin.math.sign

data class Probe(val position: Point, val velocity: Point) {

    /**
     * Constructor for a probe at the initial position with the given [initialVelocity].
     */
    constructor(initialVelocity: Point) : this(Point.ORIGIN, initialVelocity)

    private fun step(): Probe {
        val newPosition = position + velocity
        val newVelocity = Point(velocity.x + (velocity.x.sign * -1), velocity.y - 1)
        return Probe(newPosition, newVelocity)
    }

    /**
     * Returns the highest y position which will be reached during this probe's trajectory.
     */
    fun maxY(): Int = when {
        velocity.y == 0 -> position.y
        0 < velocity.y -> step().maxY()
        position == Point.ORIGIN -> 0 // Probe was fired from 0,0 at a downward angle.
        else -> throw IllegalStateException("Probe is already on the way down!")
    }

    /**
     * Checks whether this probe will eventually be within the target area at the end of any step.
     */
    fun willReachTargetArea(targetArea: TargetArea): Boolean = when {
        targetArea.contains(position) -> true
        position.y < targetArea.y.first && velocity.y < 0 -> false // probe is below the target area and falling
        velocity.x == 0 && position.x !in targetArea.x -> false // probe is falling straight down, but not directly above the target area
        else -> step().willReachTargetArea(targetArea)
    }
}

/**
 * Returns all possible initial probes which will eventually reach the given [targetArea].
 */
fun getProbes(targetArea: TargetArea): Sequence<Probe> {
    val xVelocityRange = 1..targetArea.x.last
    val yVelocityRange = targetArea.y.first..1000 // Note that the max y velocity was pretty much pulled from thin air

    return xVelocityRange.asSequence()
        .flatMap { xVelocity -> yVelocityRange.map { yVelocity -> Point(xVelocity, yVelocity) } }
        .map(::Probe)
        .filter { it.willReachTargetArea(targetArea) }
}
