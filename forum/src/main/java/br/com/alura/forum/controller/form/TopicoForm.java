package br.com.alura.forum.controller.form;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TopicoForm {

    @NotNull @NotEmpty @Length(min = 5)
    String titulo;

    @NotNull @NotEmpty @Length(min = 10)
    String mensagem;

    @NotNull @NotEmpty
    String nomeCurso;

    public Topico converter(CursoRepository cursoRepository) {
        return new Topico(titulo, mensagem, cursoRepository.findByNome(nomeCurso));
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
}