package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        //get categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1. Cut the avocados:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl."
        + "2. Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
        + "3. Add the remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste. " + "" +
                "4. Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days." );
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados\n" +
    "Feel free to experiment with variations by adding strawberries, peaches, pineapple, mangoes, or even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with your homemade guacamole!\n"+
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It still tastes great\n" +
               "\n" +
               "Read more: https://www.simplyrecipes.com/how-to-ripen-avocados-3-methods-6825988");
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("fresh lime or lemon juice", new BigDecimal(1), tableSpoonUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("serrano (or jalapeño) chilis, stems and seeds removed, minced", new BigDecimal(1), eachUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tableSpoonUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("freshly ground black pepper", new BigDecimal(2), dashUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("ripe tomato, chopped (optional)", new BigDecimal(".5"), eachUom, guacRecipe));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        //add to return list
        recipes.add(guacRecipe);

        //Yummi Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setCookTime(15);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirections("1.Prepare the grill:\n" +
                "Prepare either a gas or charcoal grill for medium-high, direct heat."
        + "2. Make the marinade and coat the chicken:\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings."
        + "3.Grill the chicken:\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165°F. Transfer to a plate and rest for 5 minutes."
        + "4. Thin the sour cream with milk:\n" +
                "Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle."
        + "5. Assemble the tacos:\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges."
        + "6.Warm the tortillas:\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n"
        + "\n" + "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today's tacos are more purposeful a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n"
        + "\n" + "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        tacosNotes.setRecipe(tacosRecipe);
        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.getIngredients().add(new Ingredient("ancho chili powder", new BigDecimal(2), tableSpoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("dried oregano", new BigDecimal(1), teaSpoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("dried cumin", new BigDecimal(1),teaSpoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("sugar", new BigDecimal(1), teaSpoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("kosher salt", new BigDecimal(".5"), teaSpoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("finely grated orange zest", new BigDecimal(1), tableSpoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3),tableSpoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("olive oil", new BigDecimal(2), tableSpoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("skinless, boneless chicken thighs",new BigDecimal(4),eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("small corn tortillas",new BigDecimal(7),eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("packed baby arugula",new BigDecimal(3),cupsUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("medium ripe avocados, sliced",new BigDecimal(2),eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("radishes, thinly sliced",new BigDecimal(4),eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("pint cherry tomatoes, halved",new BigDecimal(".5"),eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("red onion, thinly sliced",new BigDecimal(".25"),eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("sour cream",new BigDecimal(".5"),cupsUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("milk",new BigDecimal(".25"),cupsUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("lime, cut into wedges",new BigDecimal(1),eachUom, tacosRecipe));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);
        return recipes;
    }
}
