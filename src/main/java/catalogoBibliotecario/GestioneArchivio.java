package catalogoBibliotecario;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;


public class GestioneArchivio {


    private static List<CatalogoBibliotecario> catalogoProdotti = new ArrayList<>();
    private static List<Riviste> listaRiviste = new ArrayList<>();
    private static List<Libri> listaLibri = new ArrayList<>();
    private static Faker fakeData = new Faker(new Locale("it-IT"));
    private static Scanner sc = new Scanner(System.in);
    private static Logger log = LoggerFactory.getLogger(GestioneArchivio.class);
    private static Random randomIndex;

    public static void main(String[] args) {


        System.out.println("lista dei libri: ");
        generaBooks();

        System.out.println("lista delle riviste: ");
        generaRiviste();

       /* System.out.println("Aggiungi un nuovo libro:");
        aggiungiLibroSenzaDuplicati();*/


    }

    public static void genera

    public static void generaBooks() {
        for (int i = 0; i < 11; i++) {
            Libri libro = new Libri(fakeData.random().nextInt(1001, 5001), fakeData.book().title(),
                    fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(30, 600),
                    fakeData.book().author(), fakeData.book().genre());
            listaLibri.add(libro);
        }
        listaLibri.forEach(System.out::println);
    }


    public static void generaRiviste() {
        for (int i = 0; i < 11; i++) {
            Riviste rivista = new Riviste(fakeData.random().nextInt(1001, 5001), fakeData.book().title(),
                    fakeData.number().numberBetween(2020, 2025), fakeData.number().numberBetween(15, 30),PeriodicitàPubblicazione.Mensile
            );
            listaRiviste.add(rivista);
        }
        listaRiviste.forEach(System.out::println);
    }


    public static void aggiungiLibroSenzaDuplicati() {

        System.out.print("Inserisci ISBN: ");
        int isbn = sc.nextInt();
        sc.nextLine();  // Per consumare la newline rimasta nel buffer

        System.out.print("Inserisci titolo: ");
        String titolo = sc.nextLine();

        System.out.print("Inserisci anno di pubblicazione: ");
        int annoPubblicazione = sc.nextInt();

        System.out.print("Inserisci numero di pagine: ");
        int numeroPagine = sc.nextInt();
        sc.nextLine();  // Per consumare la newline rimasta nel buffer

        System.out.print("Inserisci autore: ");
        String autore = sc.nextLine();

        System.out.print("Inserisci genere: ");
        String genere = sc.nextLine();

        Libri libro = new Libri(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);

        if (listaLibri.contains(libro)) {
            System.out.println("Libro con ISBN " + isbn + " già presente.");
        } else {
            listaLibri.add(libro);
            System.out.println("Libro aggiunto: " + libro);
        }
    }

    public static void ricercaPerISBN(int isbn) {
        Optional<Libri> libroTrovato = listaLibri.stream()
                .filter(libro -> libro.getISBN() == isbn)
                .findFirst();

        libroTrovato.ifPresentOrElse(
                libro -> System.out.println("Libro trovato: " + libro),
                () -> System.out.println("Nessun libro trovato con ISBN " + isbn)
        );
    }

    public static void ricercaPerAutore(String autore) {
        List<Libri> libriAutore = listaLibri.stream()
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());

        if (libriAutore.isEmpty()) {
            System.out.println("Nessun libro trovato per l'autore: " + autore);
        } else {
            libriAutore.forEach(libro -> System.out.println("Libro trovato: " + libro));
        }
    }


}
