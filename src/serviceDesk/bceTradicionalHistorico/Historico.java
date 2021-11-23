package serviceDesk.bceTradicionalHistorico;

public class Historico {

    private long idHistorico = 0;
    private long idFormulario = 0;
    private String nomeAtendente = " ";
    private String status = "";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(long idHistorico) {
        this.idHistorico = idHistorico;
    }

    public long getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(long idFormulario) {
        this.idFormulario = idFormulario;
    }


    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }

    @Override
    public String toString(){
        return this.nomeAtendente;
    }
}
