package br.com.alura.forum.config.validations;

public class ErroFormularioDTO {
    String campo;
    String erro;

    public ErroFormularioDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
