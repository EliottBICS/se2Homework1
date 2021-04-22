package test.java;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.sql2o.*;

import main.java.Move;
import main.java.Pokemon;



public class PokemonTest {
	

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Pokemon_instantiatesCorrectly_true() {
    Pokemon myPokemon = new Pokemon("Squirtle", "Water", "None", "A cute turtle", 50.0, 12, 16, false);
    assertEquals(true, myPokemon instanceof Pokemon);
  }
  //certifies that a created pokemon is of the class "pokemon"
  //unit test

  @Test
  public void getName_pokemonInstantiatesWithName_String() {
    Pokemon myPokemon = new Pokemon("Squirtle", "Water", "None", "A cute turtle", 50.0, 12, 16, false);
    assertEquals("Squirtle", myPokemon.getName());
  }
  
  //certifies that a created pokemon is has the name its given
  //unit test
  

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Pokemon.all().size(), 0);
  }
  
  //certifies that there is no created pokemon at the beginning
  //unit test

  @Test
  public void equals_returnsTrueIfPokemonAreTheSame_true() {
    Pokemon firstPokemon = new Pokemon("Squirtle", "Water", "None", "A cute turtle", 50.0, 12, 16, false);
    Pokemon secondPokemon = new Pokemon("Squirtle", "Water", "None", "A cute turtle", 50.0, 12, 16, false);
    assertTrue(firstPokemon.equals(secondPokemon));
  }
  //certifies that if two pokemons are strictly identical, they have the same attributes
  //unit testing

  @Test
  public void save_savesPokemonCorrectly_1() {
    Pokemon newPokemon = new Pokemon("Squirtle", "Water", "None", "A cute turtle", 50.0, 12, 16, false);
    newPokemon.save();
    assertEquals(1, Pokemon.all().size());
  }
  //certifies that when a pokemon is saved, it is in the database
  //integration test

  @Test
  public void find_findsPokemonInDatabase_true() {
    Pokemon myPokemon = new Pokemon("Squirtle", "Water", "None", "A cute turtle", 50.0, 12, 16, false);
    myPokemon.save();
    Pokemon savedPokemon = Pokemon.find(myPokemon.getId());
    assertTrue(myPokemon.equals(savedPokemon));
  }
  //certifies that when a pokemon is created and saved in the database, we are able to find it
  //integration

  @Test
  public void addMove_addMoveToPokemon() {
    Move myMove = new Move("Punch", "Normal", 50.0, 100);
    myMove.save();
    Pokemon myPokemon = new Pokemon("Squirtle", "Water", "None", "A cute turtle", 50.0, 12, 16, false);
    myPokemon.save();
    myPokemon.addMove(myMove);
    Move savedMove = myPokemon.getMoves().get(0);
    assertTrue(myMove.equals(savedMove));
  }
  //certifies that when a certifies that the addMove() functions works on a pokemon in our database
  //integration unit

  @Test
  public void delete_deleteAllPokemonAndMovesAssociations() {
    Pokemon myPokemon = new Pokemon("Squirtle", "Water", "None", "A cute turtle", 50.0, 12, 16, false);
    myPokemon.save();
    Move myMove = new Move("Bubble", "Water", 50.0, 100);
    myMove.save();
    myPokemon.addMove(myMove);
    myPokemon.delete();
    assertEquals(0, Pokemon.all().size());
    assertEquals(0, myPokemon.getMoves().size());
  }
  //certifies that when we delete every pokemon in the database, it is empty afterwards
  //integration testing

  @Test
  public void searchByName_findAllPokemonWithSearchInputString_List() {
    Pokemon myPokemon = new Pokemon("Squirtle", "Water", "None", "A cute turtle", 50.0, 12, 16, false);
    myPokemon.save();
    assertEquals(myPokemon, Pokemon.searchByName("squir").get(0));
  }
//certifies that when a pokemon is searched in the database, it can be found by its name
  //integration testing

  @Test
  public void fighting_damagesDefender() {
    Pokemon myPokemon = new Pokemon("Squirtle", "Water", "Normal", "A cute turtle", 50.0, 12, 16, false);
    myPokemon.save();
    myPokemon.hp = 500;
    Move myMove = new Move("Bubble", "Water", 50.0, 100);
    myMove.attack(myPokemon);
    System.out.println(myPokemon.hp);
    myMove.attack(myPokemon);
        System.out.println(myPokemon.hp);
    myMove.attack(myPokemon);
        System.out.println(myPokemon.hp);
    myMove.attack(myPokemon);
    assertEquals(400, myPokemon.hp);
  }
  //certifies that when a move is used, it does damage
  //integration testing

}
