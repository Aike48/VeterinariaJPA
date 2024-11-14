package entidades;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "nombre_producto", nullable = false, length = 45)
    private String nombreProducto;

    @Column(name = "precio_producto")
    private Double precioProducto;

    @ManyToMany
    @JoinTable(
            name = "personas_productos",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "persona_id")
    )
    private List<Persona> listaPersonas;

    public Producto() {
    }

    public Producto(Long idProducto, String nombreProducto, Double precioProducto) {
        super();
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto{idProducto=").append(idProducto)
                .append(", nombreProducto='").append(nombreProducto)
                .append("', precioProducto=").append(precioProducto)
                .append('}');
        return sb.toString();
    }

}
