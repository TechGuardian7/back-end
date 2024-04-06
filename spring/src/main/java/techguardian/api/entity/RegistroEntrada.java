package techguardian.api.entity;

import java.sql.Time;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registro_entrada")
public class RegistroEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ent_id")
    private Long id;

    @Column(name = "data_entrada")
    private Date dataEntrada;

    @Column(name = "hora_entrada")
    private Time horaEntrada;
    
    @Column(name = "obs_entrada")
    private String obsEntrada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getObsEntrada() {
        return obsEntrada;
    }

    public void setObsEntrada(String obsEntrada) {
        this.obsEntrada = obsEntrada;
    }
}
