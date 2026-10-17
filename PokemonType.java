/**
 * 
 */

public enum PokemonType
{
   BELLSPROUT("bellsprout.png", "Bellsprout"),
   BULBASAUR("bulbasaur.png", "Bulbasaur"),
   CATERPIE("caterpie.png", "Caterpie"),
   CHARMANDER("charmander.png", "Charmander"),
   CLEFAIRY("clefairy.png", "Clefairy"),
   DIGLETT("diglett.png", "Diglett"),
   DITTO("ditto.png", "Ditto"),
   EEVEE("eevee.png", "Eevee"),
   EKANS("ekans.png", "Ekans"),
   FARFETCHD("farfetchd.png", "Farfetch'd"),
   GEODUDE("geodude.png", "Geodude"),
   GHASTLY("ghastly.png", "Ghastly"),
   GOLDEEN("goldeen.png", "Goldeen"),
   HORSEA("horsea.png", "Horsea"),
   JIGGLYPUFF("jigglypuff.png", "Jigglypuff"),
   KOFFING("koffing.png", "Koffing"),
   MACHOP("machop.png", "Machop"),
   MAGIKARP("magikarp.png", "Magikarp"),
   MAGNEMITE("magnemite.png", "Magnemite"),
   MRMIME("mrmime.png", "Mr. Mime"),
   ODDISH("oddish.png", "Oddish"),
   ONIX("onix.png", "Onix"),
   PIDGEY("pidgey.png", "Pidgey"),
   PIKACHU("pikachu.png", "Pikachu"),
   POLIWAG("poliwag.png", "Poliwag"),
   PONYTA("ponyta.png", "Ponyta"),
   PSYDUCK("psyduck.png", "Psyduck"),
   RATTATA("rattata.png", "Rattata"),
   SANDSHREW("sandshrew.png", "Sandshrew"),
   SLOWPOKE("slowpoke.png", "Slowpoke"),
   SNORLAX("snorlax.png", "Snorlax"),
   SQUIRTLE("squirtle.png", "Squirtle"),
   STARYU("staryu.png", "Staryu"),
   TENTACOOL("tentacool.png", "Tentacool"),
   VOLTORB("voltorb.png", "Voltorb"),
   VULPIX("vulpix.png", "Vulpix"),
   ZUBAT("zubat.png", "Zubat");

   private String imageFileName;
   private String pokemonName;
   
   private PokemonType(String img, String pokeName)
   {
      imageFileName = "images/" + img;
      pokemonName = pokeName;
   }

   public String getImageFileName()
   {
      return imageFileName; 
   }

   public String getPokemonName()
   {
      return pokemonName;
   }
}
