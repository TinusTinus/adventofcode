package nl.mvdr.adventofcode.adventofcode2025.day10

interface State<S : State<S>> {
    fun update(button: Button): S
    fun getInitialState(): S
    fun getPossibleStates(): Set<S>
}

fun <S: State<S>> computeFewestButtonPressesToReach(targetState: S, buttons: Set<Button>): Int {
    val visited = mutableSetOf<S>()
    var current = setOf(targetState.getInitialState())
    var buttonPresses = 0

    while(!current.contains(targetState)) {
        val next = current.flatMap { state -> buttons.map { state.update(it) } }
            .filter { !visited.contains(it) }
            .toSet()

        visited.addAll(current)
        current = next
        buttonPresses++
    }

    return buttonPresses
}
