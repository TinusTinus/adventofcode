package nl.mvdr.adventofcode.adventofcode2021.day17

import nl.mvdr.adventofcode.point.Point
import nl.mvdr.adventofcode.point.plus
import kotlin.math.sign

data class Probe(val position: Point, val velocity: Point) {
    private fun step(): Probe {
        val newPosition = position + velocity
        val newVelocity = Point(velocity.x + (velocity.x.sign * -1), velocity.y - 1)
        return Probe(newPosition, newVelocity)
    }

    /**
     * Returns the highest y position which will be reached by this probe.
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
        position.y < targetArea.y.min() && velocity.y < 0 -> false
        else -> step().willReachTargetArea(targetArea)
    }
}
