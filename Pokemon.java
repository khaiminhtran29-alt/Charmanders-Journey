/**
 * 
 */

public class Pokemon {
    public static final int MAX_LVL = 100;
    private static final int MAX_START_LVL = 70;
    private static final int MIN_START_LVL = 60;

    private PokemonType pokemonType;
    private int level;

    public Pokemon(PokemonType pokemonType, int level) {
        this.pokemonType = pokemonType;
        this.level = level;
    }

    /**
     * Adds given number of levels, capping at MAX_LVL
     * @param levels the number of levels to add
     * @return the number of levels that were actually added
     */
    public int addLevels(int levels) {
        int oldLevel = level;
        level = Math.min(MAX_LVL, levels + level);

        return level - oldLevel;
    }

    public String getImageFilename() {
        return pokemonType.getImageFileName();
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return pokemonType.getPokemonName();
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
        PokemonType[] pokemonTypes = PokemonType.values();

        int pokeTypeIndex = (int) (Math.random() * pokemonTypes.length);
        int pokeLevel = (int) (Math.random() * (maxLevel - minLevel + 1) + minLevel);

        Pokemon genRandPokemon = new Pokemon(pokemonTypes[pokeTypeIndex], pokeLevel);
        return genRandPokemon;
    }
}
