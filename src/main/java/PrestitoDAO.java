import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PrestitoDAO {
    private EntityManager entityManager;

    public PrestitoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Prestito> ricercaPrestitiPerUtente(String numeroTessera) {
        TypedQuery<Prestito> query = entityManager.createQuery("SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera", Prestito.class);
        query.setParameter("numeroTessera", numeroTessera);
        return query.getResultList();
    }

    public List<Prestito> ricercaPrestitiScadutiNonRestituiti() {
        TypedQuery<Prestito> query = entityManager.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE AND p.dataRestituzioneEffettiva IS NULL", Prestito.class);
        return query.getResultList();
    }
}
