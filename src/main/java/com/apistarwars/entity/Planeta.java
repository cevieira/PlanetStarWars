package com.apistarwars.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Document(collection = "planeta")
public class Planeta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @NotBlank(message = "O nome é obrigatório")
    @NotNull
    @Size(message = "", min = 3, max = 60)
    private String nome;
    @NotBlank(message = "O clima é obrigatório")
    @NotNull
    @Size(message = "", min = 3, max = 60)
    private String clima;
    @NotBlank(message = "O terreno é obrigatório")
    @NotNull
    @Size(message = "", min = 3, max = 60)
    private String terreno;

    private Integer quantidadeFilmes;


    public Planeta(String nome, String clima, String terreno) {
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }
}
