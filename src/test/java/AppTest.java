package test.java;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();


  @BeforeEach
  public void init(){
    DatabaseRule database = new DatabaseRule();
  }

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @BeforeAll
  public void beforeEach(){
    ServerRule server = new ServerRule();
  }

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Pokedex");
  }
  
  //This test checks if the page displayed contains "Pokedex" - I think it checks in the HTML source code
  //This is an acceptance test

  @Test
  public void allPokemonPageIsDisplayed() {
    goTo("http://localhost:4567/");
    click("#viewDex");
    assertThat(pageSource().contains("Ivysaur"));
    assertThat(pageSource().contains("Charizard"));
  }
  
  //This test checks if both "Ivysaur" and "Charizard" are displayed on the page - same as test1
  //This is an acceptance test

  @Test
  public void individualPokemonPageIsDisplayed() {
    goTo("http://localhost:4567/pokepage/6");
    assertThat(pageSource().contains("Charizard"));
  }
  
  //This test asserts that the precise page "http://localhost:4567/pokepage/6" contains "Chrizard"
  //same as test 1 and 2
  //This is another acceptance test


  @Test
  public void arrowsCycleThroughPokedexCorrectly() {
    goTo("http://localhost:4567/pokepage/6");
    click(".glyphicon-triangle-right");
    assertThat(pageSource().contains("Squirtle"));
  }
  //This test asserts that, when on page 6, if we click arrow, we are on Squirtle page (as it contains the keyword
  //"Squirtle")
  //This is an integration test

  @Test
  public void searchResultsReturnMatches() {
    goTo("http://localhost:4567/pokedex");
    fill("#name").with("char");
    assertThat(pageSource().contains("Charizard"));
  }
  //This test asserts that the page found when typing "char" in the search bar is the one containing "Charizard"
  //This is an integration test

  @Test
  public void searchResultsReturnNoMatches() {
    goTo("http://localhost:4567/pokedex");
    fill("#name").with("x");
    assertThat(pageSource().contains("No matches for your search results"));
  }
  
  //This test asserts that when the user searches "x", the page it gets redirected to is the "no matches" one
  //This is an integration test

}
