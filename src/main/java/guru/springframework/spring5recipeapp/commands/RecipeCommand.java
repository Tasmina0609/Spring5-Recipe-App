package guru.springframework.spring5recipeapp.commands;

import guru.springframework.spring5recipeapp.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private String directions;
    private String source;
    private String url;
    private Integer cookTime;
    private Integer prepTime;
    private Integer servings;
//    private Byte[] image;
    private NotesCommand notes;
    private Difficulty difficulty;
    private Set<CategoryCommand> categories=new HashSet<>();
    private Set<IngredientCommand> ingredients=new HashSet<>();
}
