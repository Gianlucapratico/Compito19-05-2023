import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class LibroDAO {
    private EntityManager entityManager;

    public LibroDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void aggiungiLibro(Libro libro) {
        if (ricercaPerISBN(libro.getIsbn()) == null) {
            entityManager.persist(libro);
        } else {
            System.out.println("Il libro con ISBN " + libro.getIsbn() + " esiste già nel database.");
        }
    }

    public void rimuoviLibro(String isbn) {
        Libro libro = ricercaPerISBN(isbn);
        if (libro != null) {
            entityManager.remove(libro);
        }
    }

    public Libro ricercaPerISBN(String isbn) {
        return entityManager.find(Libro.class, isbn);
    }

    public List<Libro> ricercaPerAutore(String autore) {
        TypedQuery<Libro> query = entityManager.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    public List<Libro> ricercaPerTitolo(String titolo) {
        TypedQuery<Libro> query = entityManager.createQuery("SELECT l FROM Libro l WHERE l.titolo LIKE :titolo", Libro.class);
        query.setParameter("titolo", "%" + titolo + "%");
        return query.getResultList();
    }
}


