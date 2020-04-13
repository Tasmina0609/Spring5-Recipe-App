package guru.springframework.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne // no cascade - Recipe owns this ( if Notes object deleted do not want the Recipe object be deleted )
    private Recipe recipe;

    @Lob // character large object storage
    private String recipeNotes;

}
