package techguardian.api.dto;

public class DadosEntrada {

    private String dataEntrada;
    private String horaEntrada;
    private int quantEntrada;
    private String obsEntrada;

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public int getQuantEntrada() {
        return quantEntrada;
    }

    public void setQuantEntrada(int quantEntrada) {
        this.quantEntrada = quantEntrada;
    }

    public String getObsEntrada() {
        return obsEntrada;
    }

    public void setObsEntrada(String obsEntrada) {
        this.obsEntrada = obsEntrada;
    }

}
