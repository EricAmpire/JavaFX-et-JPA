package teste.jpa.model;

import java.io.*;
import javax.persistence.*;

/**
 *
 * @author Eric-A-
 */
@Entity(name = "eleve")
public class Eleve implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom ;
    private String prenom ;

    
    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Eleve))
        {
            return false;
        }
        Eleve other = (Eleve) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals (other.id)));
    }

    @Override
    public String toString()
    {
        return "teste.jpa.model.Eleve[ id=" + id + " ]";
    }
    
}
