
import javax.persistence.*;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
    	 EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("biblioteca");
    	    EntityManager entityManager = entityManagerFactory.createEntityManager();

    	    try {
    	        entityManager.getTransaction().begin();

    	        LibroDAO libroDAO = new LibroDAO(entityManager);

    	        Libro libro1 = new Libro("978-1244567891", "Titolo libro 1", 2023, 200, "Autore libro 1", "Genere libro 1");
    	        libroDAO.aggiungiLibro(libro1);

    	        Libro libro2 = new Libro("978-9476543310", "Titolo libro 2", 2023, 300, "Autore libro 2", "Genere libro 2");
    	        libroDAO.aggiungiLibro(libro2);

    	        Libro libro3 = new Libro("978-5554555555", "Titolo libro 3", 2026, 250, "Autore libro 3", "Genere libro 3");
    	        libroDAO.aggiungiLibro(libro3);

    	        Libro libro4 = new Libro("978-9994999999", "Titolo libro 4", 2020, 180, "Autore libro 4", "Genere libro 4");
    	        libroDAO.aggiungiLibro(libro4);

            

             //Rimozione di un elemento del catalogo dato un codice ISBN
            Libro libroDaRimuovere = entityManager.find(Libro.class, "978-1244567891");
            entityManager.remove(libroDaRimuovere);
           
            

           // Ricerca per ISBN
            Libro libroPerISBN = entityManager.find(Libro.class, "978-0987654321");
            System.out.println("Libro trovato per ISBN: " + libroPerISBN);

            // Ricerca per anno pubblicazione
            TypedQuery<Libro> queryPerAnno = entityManager.createQuery("SELECT l FROM Libro l WHERE l.annoPubblicazione = :anno", Libro.class);
            queryPerAnno.setParameter("anno", 2023);
            List<Libro> libriPerAnno = queryPerAnno.getResultList();
            System.out.println("Libri trovati per anno pubblicazione: " + libriPerAnno);

            // Ricerca per autore
            TypedQuery<Libro> queryPerAutore = entityManager.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class);
            queryPerAutore.setParameter("autore", "Autore libro");
            List<Libro> libriPerAutore = queryPerAutore.getResultList();
            System.out.println("Libri trovati per autore: " + libriPerAutore);

            // Ricerca per titolo o parte di esso
            TypedQuery<Libro> queryPerTitolo = entityManager.createQuery("SELECT l FROM Libro l WHERE l.titolo LIKE :titolo", Libro.class);
            queryPerTitolo.setParameter("titolo", "%parte del titolo%");
            List<Libro> libriPerTitolo = queryPerTitolo.getResultList();
            System.out.println("Libri trovati per titolo: " + libriPerTitolo);

            // Ricerca degli elementi attualmente in prestito dato un numero di tessera utente
            TypedQuery<Prestito> queryPrestitiPerUtente = entityManager.createQuery("SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera", Prestito.class);
            queryPrestitiPerUtente.setParameter("numeroTessera", "Numero tessera utente");
            List<Prestito> prestitiPerUtente = queryPrestitiPerUtente.getResultList();
            System.out.println("Prestiti trovati per utente: " + prestitiPerUtente);

            // Ricerca di tutti i prestiti scaduti e non ancora restituiti
            TypedQuery<Prestito> queryPrestitiScaduti = entityManager.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE AND p.dataRestituzioneEffettiva IS NULL", Prestito.class);
            List<Prestito> prestitiScaduti = queryPrestitiScaduti.getResultList();
            System.out.println("Prestiti scaduti e non ancora restituiti: " + prestitiScaduti);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
    }


