package org.sorteador.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "filmes")
public class Filme {


    @Id
    @GeneratedValue
    @Getter
    private int id;

    @Getter @Setter
    private String nome;

    @Getter @Setter
    @Column(name = "quem_escolheu")
    private String quemEscolheu;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return id == filme.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Filme: "+ nome + "\nEscolhido por: " + quemEscolheu;
    }
}
