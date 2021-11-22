package serviceDesk.bceTradicionalFaq;

import java.time.LocalDate;

public class Faq {

    private long id = 0;
    private String categoria = " ";
    private String descricao = " ";
    private String status = " ";
    private String resposta = " ";
    private LocalDate dataCriacao = LocalDate.now();

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        if(resposta == null){
            this.resposta = "FAQ sem resposta";
        }else {
            this.resposta = resposta;
        }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return categoria;
    }

    public void setTitulo(String titulo) {
        this.categoria = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString(){
        return this.getTitulo();
    }
}
