import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;


public class App{
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";
        enableDebugScreen();

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //Post method to post animals in DataBase
        post("/sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = request.queryParams("rangerName");
            String sightingLocation = request.queryParams("sightingLocation");
            String animalName = request.queryParams("animalName");
            String animalAge = request.queryParams("animalAge");
            String animalHealth = request.queryParams("animalHealth");
            String animalSpecies = request.queryParams("animalSpecies");
            if(animalSpecies.equals("safe")){
                RegAnimal regularAnimal = new RegAnimal(animalName, animalAge, animalHealth, animalSpecies);
                regularAnimal.save();
                Sighting newSighting1 = new Sighting(rangerName, sightingLocation, regularAnimal.getId());
                newSighting1.save();
            } else if(animalSpecies.equals("endangered")){
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(animalName, animalAge, animalHealth, animalSpecies);
                endangeredAnimal.save();
                Sighting newSighting2 = new Sighting(rangerName, sightingLocation, endangeredAnimal.getId());
                newSighting2.save();
            }
            model.put("sightings", Sighting.all());
            model.put("template", "templates/View-Sightings.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //Get Method to view All sightings
        get("/View-Sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Sighting.all());
            model.put("template", "templates/View-Sightings.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}