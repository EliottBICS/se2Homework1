package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import main.java.Move;
import main.java.Pokemon;

@RunWith(Parameterized.class)
public class newTest {
	
	  @Rule
	  public DatabaseRule database = new DatabaseRule();
	  
	  @Parameterized.Parameters
	  public static Collection<Object[]> data() {
	      List<Object[]> args = new ArrayList<>();
	      args.add(new Object[] { new Pokemon("Squirtle", "Water", "Normal", "A cute turtle", 50.0, 12, 16, false) });
	      return args;
	  }
	  
	  private final Pokemon myPokemon;
	  public newTest(Pokemon myPokemon) {
	    this.myPokemon = myPokemon;
	  }
	  
	  @Test
	  
	  public void fighting_damagesDefender() {
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

}
