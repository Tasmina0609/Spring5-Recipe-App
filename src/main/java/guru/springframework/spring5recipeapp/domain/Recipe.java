package guru.springframework.spring5recipeapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @Lob // binary large object field
    private Byte[] image;

    @Enumerated(value=EnumType.STRING)  // store the String value of the enum in the database.
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL) // defines relationship  ( cascade - make recipe the owner )
    private Notes notes;

    @ManyToMany  // using only this leads to different table
    @JoinTable(name="recipe_category",
               joinColumns = @JoinColumn(name="recipe_id"),
               inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories=new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")  // mappedBy - removing the parent should also remove the child entities
    private Set<Ingredient> ingredients=new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);  // build association
    }

    // to eliminate bi-directional statements- Recipe and Notes
    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
