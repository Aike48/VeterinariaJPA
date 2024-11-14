package entidades;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "nacimiento")
public class Nacimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nacimiento", nullable = false)
    private Long idNacimiento;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "ciudad_nacimiento")
    private String ciudadNacimiento;

    @Column(name = "departamento_nacimiento")
    private String departamentoNacimiento;

    @Column(name = "pais_nacimiento")
    private String paisNacimiento;

    public Nacimiento() {
    }

    public Nacimiento(LocalDate fechaNacimiento, String ciudadNacimiento, String departamentoNacimiento, String paisNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        this.ciudadNacimiento = ciudadNacimiento;
        this.departamentoNacimiento = departamentoNacimiento;
        this.paisNacimiento = paisNacimiento;
    }

    public Long getIdNacimiento() {
        return idNacimiento;
    }

    public void setIdNacimiento(Long idNacimiento) {
        this.idNacimiento = idNacimiento;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getDepartamentoNacimiento() {
        return departamentoNacimiento;
    }

    public void setDepartamentoNacimiento(String departamentoNacimiento) {
        this.departamentoNacimiento = departamentoNacimiento;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNacimiento [idNacimiento=").append(idNacimiento)
                .append(", fechaNacimiento=").append(fechaNacimiento)
                .append(", ciudadNacimiento=").append(ciudadNacimiento)
                .append(", departamentoNacimiento=").append(departamentoNacimiento)
                .append(", paisNacimiento=").append(paisNacimiento)
                .append("]");
        return sb.toString();
    }
}
