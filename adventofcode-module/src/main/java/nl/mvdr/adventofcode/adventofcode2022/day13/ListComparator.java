package nl.mvdr.adventofcode.adventofcode2022.day13;

import java.util.Comparator;
import java.util.List;

/**
 * Comparator for lists of packet values.
 *
 * @author Martijn van de Rijdt
 */
class ListComparator implements Comparator<List<PacketValue>> {
    @Override
    public int compare(List<PacketValue> firstList, List<PacketValue> secondList) {
        int result = 0;
        int index = 0;
        while (result == 0 && index < firstList.size() && index < secondList.size()) {
            result = firstList.get(index).compareTo(secondList.get(index));
            index++;
        }
        if (result == 0 && firstList.size() != secondList.size()) {
            if (index == firstList.size()) {
                result = -1;
            } else if (index == secondList.size()) {
                result = 1;
            } else {
                throw new IllegalStateException("Failed to compare lists: " + firstList + " vs " + secondList);
            }
        }
        return result;
    }
}
