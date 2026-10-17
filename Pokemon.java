/**
 * 
 */

public class Pokemon {
    public static final int MAX_LVL = 30;
    private static final int MAX_START_LVL = 10;
    private static final int MIN_START_LVL = 1;

    private PokemonType pokemonType;
    private int level;

    public Pokemon(PokemonType pokemonType, int level) {
        // TODO: initialize instance variables with arguments
    }

    /**
     * Adds given number of levels, capping at MAX_LVL
     * @param levels the number of levels to add
     * @return the number of levels that were actually added
     */
    public int addLevels(int levels) {
        // TODO: add levels to this pokemon's levels, capping at MAX_LVL
        // return how many levels were actualyl added
    }

    public String getImageFilename() {
        // TODO; return Pokemon's image file name
    }

    public int getLevel() {
        // TODO: return pokemon's level
    }

    public String getName() {
        // TODO: return pokemon's name
    }

    /**
     * Generate a pokemon with a random type and level within the default
     *  start level range
     * @return a randomly generated Pokemon
     */
    public static Pokemon generateRandomPokemon() {
       return generateRandomPokemon(MIN_START_LVL, MAX_START_LVL); 
    }

    /**
     * Generate a pokemon with a random type, and level in the given range
     * @return a randomly generated Pokemon
     */
    public static Pokemon generateRandomPokemon(int minLevel, int maxLevel) {
        // TODO: generate a random pokemon type from PokemonType
        // Generate a random level in range [minLevel, maxLevel]
        // return the pokemon
        // hint: PokemonType.values() returns an array of all your Pokemon types
    }
}
