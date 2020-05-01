package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipeShow/{id}")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe",recipeService.findById(Long.valueOf(id)));
        return "recipeShow";
    }

    @RequestMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe",new RecipeCommand());
        return "recipeForm";
    }

    @RequestMapping("/update/{id}")
    public String updateRecipe(@PathVariable String id,Model model) {
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipeForm";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand=recipeService.saveRecipeCommand(command);
        return "redirect:/recipeShow/"+savedCommand.getId();
    }

    @RequestMapping("/recipe/delete/{id}")
    public String deleteById(@PathVariable String id) {

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
