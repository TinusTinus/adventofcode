package nl.mvdr.adventofcode.adventofcode2018.day24;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A group of units.
 *
 * @author Martijn van de Rijdt
 */
class Group {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Group.class);
    
    /** The army to which this group belongs. */
    private final Army army;
    /** Unique identification of this group within its army. */
    private final int id;
    /** The number of units within this group. */
    private final int units;
    /** The amount of damage a unit can take before it is destroyed. */
    private final int hitPoints;
    /** The amount of damage each unit deals. */
    private final int attackDamage;
    /** Type of damage this unit deals. */
    private final String attackType;
    /** Higher initiative units attack first and win ties. */
    private final int initiative;
    /** Damage type weaknesses. */
    private final Set<String> weaknesses;
    /** Damage type immunities. */
    private final Set<String> immunities;

    /**
     * Parses a textual representation of a group.
     * 
     * @param id unique identification of this group within its army
     * @param text line from the puzzle input representing a group, for example:
     *     "18 units each with 729 hit points (weak to fire; immune to cold, slashing) with an attack that does 8 radiation damage at initiative 10"
     * @return group
     */
    static Group parse(Army army, int id, String text) {
        Pattern pattern = Pattern.compile("(?<units>\\d*) units each with (?<hitPoints>\\d*) hit points"
                + "( \\((?<weaknessesAndImmunities>[^\\)]*)\\))?"
                + " with an attack that does (?<attackDamage>\\d*) (?<attackType>[a-z]*) damage"
                + " at initiative (?<initiative>\\d*)");
        
        Matcher matcher = pattern.matcher(text);
        matcher.matches();
        
        int units = Integer.parseInt(matcher.group("units"));
        int hitPoints = Integer.parseInt(matcher.group("hitPoints"));
        int attackDamage = Integer.parseInt(matcher.group("attackDamage"));
        String attackType = matcher.group("attackType");
        int initiative = Integer.parseInt(matcher.group("initiative"));
        
        Set<String> weaknesses = new HashSet<>();
        Set<String> immunities = new HashSet<>();
        String weaknessesAndImmunities = matcher.group("weaknessesAndImmunities");
        if (weaknessesAndImmunities != null) {
            String[] parts = weaknessesAndImmunities.split("; ");
            for (String part : parts) {
                if (part.startsWith("weak to ")) {
                    Stream.of(part.substring("weak to ".length()).split(", "))
                            .forEach(weaknesses::add);
                } else if (part.startsWith("immune to ")) {
                    Stream.of(part.substring("immune to ".length()).split(", "))
                            .forEach(immunities::add);
                } else {
                    throw new IllegalStateException("Unexpected input part: " + part);
                }
            }
        }
        weaknesses = Set.copyOf(weaknesses);
        immunities = Set.copyOf(immunities);
        
        return new Group(army, id, units, hitPoints, attackDamage, attackType, initiative, weaknesses, immunities);
    }
    
    /**
     * Constructor.
     * 
     * @param army the army to which this group belongs
     * @param id unique identification of this group within its army
     * @param units number of units
     * @param hitPoints amount of damage a unit can take before it is destroyed
     * @param attackDamage the amount of damage each unit deals
     * @param attackType attack type
     * @param initiative initiative: higher initiative units attack first and win ties
     * @param weaknesses damage type weaknesses
     * @param immunities damage type immunities
     */
    private Group(Army army, int id, int units, int hitPoints, int attackDamage, String attackType, int initiative,
            Set<String> weaknesses, Set<String> immunities) {
        super();
        this.army = army;
        this.id = id;
        this.units = units;
        this.hitPoints = hitPoints;
        this.attackDamage = attackDamage;
        this.attackType = attackType;
        this.initiative = initiative;
        this.weaknesses = weaknesses;
        this.immunities = immunities;
    }
    
    Army getArmy() {
        return army;
    }

    int getId() {
        return id;
    }
    
    int getUnits() {
        return units;
    }

    int getHitPoints() {
        return hitPoints;
    }

    int getAttackDamage() {
        return attackDamage;
    }

    String getAttackType() {
        return attackType;
    }

    int getInitiative() {
        return initiative;
    }

    Set<String> getWeaknesses() {
        return weaknesses;
    }

    Set<String> getImmunities() {
        return immunities;
    }
    
    /** @return the number of units in that group multiplied by their attack damage */
    int effectivePower() {
        return units * attackDamage;
    }
    
    /**
     * Selects a target from the given potential targets.
     * 
     * @param potentialTargets potential targets
     * @return target, or empty if no valid target could be found
     */
    Optional<Group> selectTarget(Set<Group> potentialTargets) {
        return potentialTargets.stream()
                .filter(target -> target.army != this.army)
                .max(Comparator.comparing(this::calculateDamage)
                        .thenComparing(Group::effectivePower)
                        .thenComparing(Group::getInitiative))
                .filter(target -> 0 < this.calculateDamage(target));
    }
    
    /**
     * Determines how much damage this group would do to the given other group, based on damage types, weaknesses and immunities.
     * 
     * @param target target group
     * @return damage
     */
    private int calculateDamage(Group target) {
        int result;
        if (target.getImmunities().contains(this.attackType)) {
            result = 0;
        } else if (target.getWeaknesses().contains(this.attackType)) {
            result = 2 * this.effectivePower();
        } else {
            result = effectivePower();
        }
        return result;
    }
    
    /**
     * Performs an attack on the given target.
     * 
     * @param target target group
     * @return target with updated number of remaining units, or empty if the target was defeated
     */
    Optional<Group> attack(Group target) {
        int damage = calculateDamage(target);
        LOGGER.debug("{} attacks {} for {} damage", this.name(), target.name(), damage);
        return target.takeDamage(damage);
    }
    
    /**
     * Takes the given damage.
     * 
     * @param damage total damage; immunities and weaknesses must have already been accounted for in this number
     * @return group with updated number of remaining units, or empty if this group was defeated
     */
    Optional<Group> takeDamage(int damage) {
        int defeatedUnits = damage / this.hitPoints;
        int remainingUnits = units - defeatedUnits;
        
        Optional<Group> result;
        if (remainingUnits <= 0) {
            LOGGER.debug("{} has been defeated", name());
            result = Optional.empty();
        } else {
            LOGGER.debug("{} has lost {} units", name(), defeatedUnits);
            result = Optional.of(new Group(army, id, remainingUnits, hitPoints, attackDamage, attackType, initiative, weaknesses, immunities));
        }
        return result;
    }
    
    /** @return shorter String representation than {@link #toString()} */
    private String name() {
        return army + " group " + id;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(army);
        builder.append(" group ");
        builder.append(id);
        builder.append(": ");
        builder.append(units);
        builder.append(" units each with ");
        builder.append(hitPoints);
        builder.append(" hit points");
        
        if (!weaknesses.isEmpty() || !immunities.isEmpty()) {
            builder.append(" (");
            
            if (!weaknesses.isEmpty()) {
                builder.append("weak to ");
                builder.append(weaknesses.stream().sorted().collect(Collectors.joining(", ")));
                if (!immunities.isEmpty()) {
                    builder.append("; ");
                }
            }
            if (!immunities.isEmpty()) {
                builder.append("immune to ");
                builder.append(immunities.stream().sorted().collect(Collectors.joining(", ")));
            }
            
            builder.append(")");
        }
        
        builder.append(" with an attack that does ");
        builder.append(attackDamage);
        builder.append(" ");
        builder.append(attackType);
        builder.append(" damage at initiative ");
        builder.append(initiative);
        
        return builder.toString();
    }
}
