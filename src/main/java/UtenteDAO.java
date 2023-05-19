
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UtenteDAO {
    private EntityManager entityManager;

    public UtenteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void aggiungiUtente(Utente utente) {
        entityManager.persist(utente);
    }

    public void rimuoviUtente(String numeroTessera) {
        Utente utente = entityManager.find(Utente.class, numeroTessera);
        if (utente != null) {
            entityManager.remove(utente);
        }
    }

    public Utente ricercaPerNumeroTessera(String numeroTessera) {
        return entityManager.find(Utente.class, numeroTessera);
    }
}
