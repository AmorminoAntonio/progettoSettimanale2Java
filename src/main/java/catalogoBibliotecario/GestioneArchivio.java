package catalogoBibliotecario;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class GestioneArchivio {


    private static List<CatalogoBibliotecario> catalogoProdotti = new ArrayList<>();
    private static List<Riviste> listaRiviste = new ArrayList<>();
    private static List<Libri> listaLibri = new ArrayList<>();
    private static Faker fakeData = new Faker(new Locale("it-IT"));
    private static Scanner sc = new Scanner(System.in);
    private static Logger log = LoggerFactory.getLogger(GestioneArchivio.class);


    public static void main(String[] args) {


        System.out.println("catalogo bibliotecario: ");
        generaRiviste();
        generaBooks();

        StampaCatalogo();


        System.out.println("inerisci l'autore per ricercare: ");
        ricercaPerAutore(sc.nextLine().toLowerCase().trim());


        System.out.println("inserisci l' ID per ricercare: ");
        ricercaPerISBN(sc.nextInt());


        System.out.println("inserisci l'anno di pubblicazione per ricercare: ");
        ricercaPerPubblicazione(sc.nextInt());


    }


    public static void generaBooks() {
        Libri libro1 = new Libri(51, fakeData.book().title(), 1800, fakeData.number().numberBetween(30, 600), "Pepina lofinca", fakeData.book().genre());
        Libri libro2 = new Libri(52, fakeData.book().title(), 1900, fakeData.number().numberBetween(30, 600), "Carciofo bredio", fakeData.book().genre());
        Libri libro3 = new Libri(53, fakeData.book().title(), 2000, fakeData.number().numberBetween(30, 600), "Lorico caverna", fakeData.book().genre());
        Libri libro4 = new Libri(54, fakeData.book().title(), 1500, fakeData.number().numberBetween(30, 600), "zio prepu", fakeData.book().genre());
        Libri libro5 = new Libri(55, fakeData.book().title(), 1600, fakeData.number().numberBetween(30, 600), "thomas turbato", fakeData.book().genre());
        Libri libro6 = new Libri(56, fakeData.book().title(), 1100, fakeData.number().numberBetween(30, 600), "nera pantera", fakeData.book().genre());
        Libri libro7 = new Libri(57, fakeData.book().title(), 1700, fakeData.number().numberBetween(30, 600), "viola rosa", fakeData.book().genre());
        Libri libro8 = new Libri(58, fakeData.book().title(), 1400, fakeData.number().numberBetween(30, 600), "chiara tenaglia", fakeData.book().genre());

        listaLibri.addAll(Arrays.asList(libro1, libro2, libro3, libro4, libro5, libro6, libro7, libro8));

    }


    public static void generaRiviste() {
        Riviste rivista1 = new Riviste(59, fakeData.book().title(), 2020, fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.Mensile);
        Riviste rivista2 = new Riviste(60, fakeData.book().title(), 2021, fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.Mensile);
        Riviste rivista3 = new Riviste(61, fakeData.book().title(), 2022, fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.Mensile);
        Riviste rivista4 = new Riviste(62, fakeData.book().title(), 2023, fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.Mensile);
        Riviste rivista5 = new Riviste(63, fakeData.book().title(), 2024, fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.Mensile);
        Riviste rivista6 = new Riviste(64, fakeData.book().title(), 2025, fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.Mensile);
        Riviste rivista7 = new Riviste(65, fakeData.book().title(), 2019, fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.Mensile);
        Riviste rivista8 = new Riviste(66, fakeData.book().title(), 2018, fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.Mensile);

        listaRiviste.addAll(Arrays.asList(rivista1, rivista2, rivista3, rivista4, rivista5, rivista6, rivista7, rivista8));
    }

    public static void StampaCatalogo() {
        catalogoProdotti.addAll(listaLibri);
        catalogoProdotti.addAll(listaRiviste);

        catalogoProdotti.forEach(System.out::println);
    }


    public static void aggiungiElementoSenzaDuplicati() {
        System.out.println("Cosa desideri aggiungere? (1) Libro (2) Rivista");
        int sceltaElementoDaCreare = sc.nextInt();
        sc.nextLine();

        if (sceltaElementoDaCreare == 1) {

            System.out.print("Inserisci ISBN: ");
            int isbn = sc.nextInt();
            sc.nextLine();

            System.out.print("Inserisci Titolo: ");
            String titolo = sc.nextLine();

            System.out.print("Inserisci anno di pubblicazione: ");
            int annoPubblicazione = sc.nextInt();

            System.out.print("Inserisci numero di pagine: ");
            int numeroPagine = sc.nextInt();
            sc.nextLine();

            System.out.print("Inserisci autore: ");
            String autore = sc.nextLine();

            System.out.print("Inserisci genere: ");
            String genere = sc.nextLine();

            Libri libro = new Libri(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);


            boolean isDuplicate = listaLibri.stream()
                    .anyMatch(l -> l.getISBN() == libro.getISBN());

            if (isDuplicate) {
                System.out.println("Libro con ISBN " + isbn + " già presente.");
            } else {
                listaLibri.add(libro);
                System.out.println("Libro aggiunto: " + libro);
            }

        } else if (sceltaElementoDaCreare == 2) {

            System.out.print("Inserisci ISBN: ");
            int isbn = sc.nextInt();
            sc.nextLine();

            System.out.print("Inserisci Titolo: ");
            String titolo = sc.nextLine();

            System.out.print("Inserisci anno di pubblicazione: ");
            int annoPubblicazione = sc.nextInt();

            System.out.print("Inserisci numero di pagine: ");
            int numeroPagine = sc.nextInt();
            sc.nextLine();

            System.out.print("Inserisci periodicità (Settimanale, Mensile, Semestrale): ");
            String periodicitaInput = sc.nextLine();
            PeriodicitàPubblicazione periodicità = PeriodicitàPubblicazione.valueOf(periodicitaInput);

            Riviste rivista = new Riviste(isbn, titolo, annoPubblicazione, numeroPagine, periodicità);


            boolean isDuplicate = listaRiviste.stream()
                    .anyMatch(r -> r.getISBN() == rivista.getISBN());

            if (isDuplicate) {
                System.out.println("Rivista con ISBN " + isbn + " già presente.");
            } else {
                listaRiviste.add(rivista);
                System.out.println("Rivista aggiunta: " + rivista);
            }

        } else {
            System.out.println("Scelta non valida.");
        }
    }

    public static void ricercaPerPubblicazione(int annoDiPubblicazione) {
        catalogoProdotti.stream()
                .filter(catalogoBibliotecario -> catalogoBibliotecario.getAnnoPubblicazione() == annoDiPubblicazione)
                .peek(catalogo -> System.out.println("Prodotto trovato: " + catalogo))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Nessun prodotto trovato con anno di pubblicazione " + annoDiPubblicazione);
                    return null;
                });
    }


    public static void ricercaPerISBN(int isbn) {
        catalogoProdotti.stream()
                .filter(catalogoBibliotecario -> catalogoBibliotecario.getISBN() == isbn)
                .peek(catalogo -> System.out.println("prodotto trovato: " + catalogo))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Nessun libro trovato con ISBN " + isbn);
                    return null;
                });
    }


    public static void ricercaPerAutore(String autore) {
        listaLibri.stream()
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .findFirst()
                .ifPresentOrElse(
                        libro -> System.out.println("Libro trovato: " + libro),
                        () -> System.out.println("Nessun libro trovato per l'autore: " + autore)
                );
    }


    public static void rimuoviTramiteIsbn() {

    }


}
