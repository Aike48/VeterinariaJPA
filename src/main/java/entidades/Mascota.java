package entidades;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mascotas")
public class Mascota implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Integer idMascota;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(length = 45)
    private String raza;

    @Column(name = "color", length = 45)
    private String colorMascota;

    @Column(length = 45)
    private String sexo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", referencedColumnName = "id_persona")
    private Persona duenio;

    public Mascota() {

    }

    public Mascota(String nombre, String raza, String colorMascota, String sexo, Persona duenio) {
        super();
        this.nombre = nombre;
        this.raza = raza;
        this.colorMascota = colorMascota;
        this.sexo = sexo;
        this.duenio = duenio; // Correcta asignación del dueño
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColorMascota() {
        return colorMascota;
    }

    public void setColorMascota(String colorMascota) {
        this.colorMascota = colorMascota;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Persona getDuenio() {
        return duenio;
    }

    public void setDuenio(Persona duenio) {
        this.duenio = duenio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mascota [idMascota=").append(idMascota)
                .append(", nombre=").append(nombre)
                .append(", raza=").append(raza)
                .append(", colorMascota=").append(colorMascota)
                .append(", sexo=").append(sexo)
                .append("] Dueño: ").append(duenio != null ? duenio.toString() : "No asignado");
        return sb.toString();
    }
}
