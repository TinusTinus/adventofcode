package nl.mvdr.adventofcode.adventofcode2024.day21;

record CacheKey<B extends KeypadButton<B>>(B button, B initialButton, int robots) {

}
