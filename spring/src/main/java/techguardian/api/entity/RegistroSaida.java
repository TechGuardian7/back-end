package techguardian.api.entity;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registro_saida")
public class RegistroSaida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sai_id")
    private Long id;

    @Column(name = "data_saida")
    private Date dataSaida;

    @Column(name = "hora_saida")
    private Time horaSaida;
    
    @Column(name = "obs_saida")
    private String obsSaida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Time getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Time horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getObsSaida() {
        return obsSaida;
    }

    public void setObsSaida(String obsSaida) {
        this.obsSaida = obsSaida;
    }
}
