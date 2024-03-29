package nl.mvdr.adventofcode.adventofcode2015.day24

/**
 * Selects the first group, given the [lines] from the puzzle input containing package weights and the number of [groups].
 */
fun selectFirstGroup(lines: Sequence<String>, groups: Int): Long {
    val packages = lines.map(String::toLong).toList()

    val weightPerGroup = packages.sum() / groups

    return generateSequence(1, Int::inc)
        .map { createGroups(packages, weightPerGroup, it) }
        .first { it.isNotEmpty() }
        .minOf(::quantumEntanglement)
}

/**
 * Determines all possible groups out of the given [packages], with the given [groupSize]
 * where the group's total weight equals the given [targetWeight].
 * Note that the given packages must be sorted in increasing weight!
 */
private fun createGroups(packages: List<Long>, targetWeight: Long, groupSize: Int): Set<Set<Long>> = when {
    targetWeight < 0 -> throw IllegalArgumentException("Invalid target weight: $targetWeight")
    groupSize < 0 -> throw IllegalArgumentException("Invalid group size: $groupSize")
    targetWeight == 0L && groupSize == 0 -> setOf(emptySet()) // empty group has weight 0
    groupSize == 0 -> emptySet()
    packages.isEmpty() -> emptySet()
    targetWeight < packages.first() -> emptySet() // packages are sorted: if the first package is too heavy, so is the rest
    else -> {
        // Either include the first package in the group, or don't.
        val firstPackage = packages.first()
        val remainingPackages = packages.drop(1)

        val groupsWithFirstPackage =
            createGroups(remainingPackages, targetWeight - firstPackage, groupSize - 1).map { it union setOf(firstPackage) }.toSet()
        val groupsWithoutFirstPackage =
            createGroups(remainingPackages, targetWeight, groupSize)

        groupsWithFirstPackage union groupsWithoutFirstPackage
    }
}

private fun quantumEntanglement(group: Set<Long>) = group.reduce { package0, package1 -> package0 * package1 }