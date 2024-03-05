package nl.mvdr.adventofcode.adventofcode2015.day17

/**
 * Counts the number of ways in which the given [eggnog] can fit exactly in the containers with the given [containerVolumes].
 * All volumes are in liters.
 * The [containersToUse] parameter can be used to limit the number of containers to be used.
 */
fun countWaysToFitInContainers(containerVolumes: List<Int>, eggnog: Int, containersToUse: Int = containerVolumes.size): Int = when {
    eggnog < 0 -> 0 // does not fit
    eggnog == 0 -> 1 // fits exactly in the containers already used
    containersToUse == 0 -> 0 // out of containers
    containerVolumes.isEmpty() -> 0 // no more remaining containers
    else -> // either use the next container, or don't
        countWaysToFitInContainers(containerVolumes.subList(1, containerVolumes.size), eggnog - containerVolumes.first(), containersToUse - 1) +
                countWaysToFitInContainers(containerVolumes.subList(1, containerVolumes.size), eggnog, containersToUse)
}