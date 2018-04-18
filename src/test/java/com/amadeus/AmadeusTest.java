package com.amadeus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;


public class AmadeusTest {
  @Test public void testInitialize() {
    Amadeus amadeus = new Amadeus(new Configuration("id", "secret"));
    assertTrue("should require a Configuration object",
               amadeus instanceof Amadeus);
  }

  @Test public void testBuilder() {
    assertTrue("should return a Configuration",
               Amadeus.builder("id", "secret") instanceof Configuration);
  }

  @Test public void testBuilderWithEnvironment() {
    Map<String,String> environment = new HashMap<String,String>() {
      {
        put("AMADEUS_CLIENT_ID", "123");
        put("AMADEUS_CLIENT_SECRET", "234");
      }
    };
    assertTrue("should return a Configuration",
            Amadeus.builder(environment) instanceof Configuration);
  }

  @Test(expected = NullPointerException.class)
  public void testBuilderWithInvalidEnvironment() {
    Map<String,String> environment = System.getenv();
    assertTrue("should return a Configuration",
            Amadeus.builder(environment).build() instanceof Amadeus);
  }

  @Test public void testVersion() {
    assertEquals("should have a version number", Amadeus.VERSION, "1.0.0");
  }
}