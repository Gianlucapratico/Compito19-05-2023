
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RivistaDAO {
    private EntityManager entityManager;

    public RivistaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void aggiungiRivista(Rivista rivista) {
        entityManager.persist(rivista);
    }

    public void rimuoviRivista(String isbn) {
        Rivista rivista = entityManager.find(Rivista.class, isbn);
        if (rivista != null) {
            entityManager.remove(rivista);
        }
    }

    public Rivista ricercaPerISBN(String isbn) {
        return entityManager.find(Rivista.class, isbn);
    }

    public List<Rivista> ricercaPerPeriodicita(Periodicita periodicita) {
        TypedQuery<Rivista> query = entityManager.createQuery("SELECT r FROM Rivista r WHERE r.periodicita = :periodicita", Rivista.class);
        query.setParameter("periodicita", periodicita);
        return query.getResultList();
    }
}
