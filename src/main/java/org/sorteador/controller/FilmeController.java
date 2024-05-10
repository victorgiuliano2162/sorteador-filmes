package org.sorteador.controller;

import org.sorteador.entity.Filme;
import org.sorteador.service.FilmeDto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FilmeController extends FilmeDto {


    static FilmeDto filmeDto;

    //Pronto
    public String cadastrarFilme(Filme f) {
        if(filmeDto.cadastrar(f)) {
            return "O filme " + f.getNome() + " foi cadastrado com sucesso.";
        } else if(!filmeDto.cadastrar(f)) {
            return "Não foi possível cadastrar o filme";
        } else {
            return null;
        }
    }

    //Pronto
    public String consultarFilme(int id) {
        if(filmeDto.consultarPorId(id) != null) {
            return "O filme " + consultarPorId(id).getNome() + " está cadastrado no sistema.";
        } else if(filmeDto.consultarPorId(id) == null) {
            return "Filme não cadastrado";
        } else {
            return null;
        }
    }

    //Feito
    public List<Filme> consultarVariosFilmesPorNome(String nome){
        List<Filme> filmes = filmeDto.consultarVariosPorNome(nome);
        if(filmes != null) {
            return filmes;
        } else{
            return null;
        }
    }

    //Feito
    public String alterarFilme(int id, String nome, String quemEscolheu) {
        boolean alterar = filmeDto.alterar(id, nome, quemEscolheu);
        if(alterar) {
            return "O filme " + consultarPorId(id).getNome() + " foi alterado com sucesso.";
        } else {
            return "Alteração não realizada";
        }
    }

    //Feito
    public String excluirFilme(int id) {
        boolean excluido = filmeDto.excluir(id);
        if(excluido) {
            return "Filme excluído com sucesso";
        } else {
            return "Impossível excluir filme";
        }
    }

    //Feito
    public List<Filme> consultarTodosFilmes() {
        if(!consultarTodos().isEmpty()) {
            return consultarTodos();
        } else {
            return null;
        }
    }

    //Feito
    public List<Filme> consultarFilmePorNome(String nome) {
        if(!consultarVariosPorNome(nome).isEmpty()){
            return consultarVariosPorNome(nome);
        } else {
            return null;
        }
    }


}
