package catalogoBibliotecario;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GestioneArchivio {


    private static List<CatalogoBibliotecario> catalogoProdotti = new ArrayList<>();
    private static List<Riviste> listaRiviste = new ArrayList<>();
    private static List<Libri> listaLibri = new ArrayList<>();
    private static Faker fakeData = new Faker(new Locale("it-IT"));
    private static Scanner sc = new Scanner(System.in);
    private static Logger log = LoggerFactory.getLogger(GestioneArchivio.class);


    public static void main(String[] args) {


        System.out.println("catalogo bibliotecario: ");
        generaBooks();
        generaRiviste();
        stampaCatalogo();

        while (true) {
            System.out.println("\nseleziona la funziona da svolgere: ");
            System.out.println("1: cerca con ISBN,2: cerca tramite AUTORE,3: cerca tramite ANNO di PUBBLICAZIONE,4: aggiungi un elemento,5: rimuovi un elemento,6: vedi STATISTICHE");
            System.out.println("7: vedi totale pagine libri e riviste, 8: vedi catalogo");
            int scelta = sc.nextInt();

            if (scelta == 0) {
                System.out.println("programma terminato.");
                sc.close();
                break;
            } else {
                switch (scelta) {
                    case 1:
                        System.out.print("inserisci cod.ISBN: ");
                        ricercaPerISBN(sc.nextInt());
                        break;
                    case 2:
                        System.out.print("inserisci l'autore: ");
                        sc.nextLine();
                        ricercaPerAutore(sc.nextLine());
                        break;
                    case 3:
                        System.out.print("inserisci l'anno di pubblicazione: ");
                        ricercaPerPubblicazione(sc.nextInt());
                        break;
                    case 4:
                        System.out.println("Vamos, crea l'elemento...");
                        sc.nextLine();
                        aggiungiElementoSenzaDuplicati();
                        break;
                    case 5:
                        System.out.println("inserisci il cod.ISBN: ");
                        rimuoviTramiteIsbn(sc.nextInt());
                        aggiornaCatalogo();
                        break;
                    case 6:
                        sc.nextLine();
                        break;
                    case 7:
                        stampaNumeroPagine();
                        break;
                    case 8:
                        stampaCatalogo();
                        break;
                    default:
                        break;
                }
            }
        }



/*
        System.out.println("inerisci l'autore per ricercare: ");
        ricercaPerAutore(sc.nextLine().toLowerCase().trim());


        System.out.println("inserisci l' ID per ricercare: ");
        ricercaPerISBN(sc.nextInt());


        System.out.println("inserisci l'anno di pubblicazione per ricercare: ");
        ricercaPerPubblicazione(sc.nextInt());*/


        /*System.out.println("inserisci il cod ISBN per rimuovere l'elemento: ");
        rimuoviTramiteIsbn(sc.nextInt());
        stampaCatalogo();*/


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

    public static void stampaCatalogo() {
        catalogoProdotti.addAll(listaLibri);
        catalogoProdotti.addAll(listaRiviste);
        if (catalogoProdotti.isEmpty()) {
            System.out.println("Mi dispiace, il tuo catalogo è vuoto !");
        } else {
            catalogoProdotti.forEach(System.out::println);
        }
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


            boolean giàEsistente = listaLibri.stream()
                    .anyMatch(book -> book.getISBN() == libro.getISBN());

            if (giàEsistente) {
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
            String periodicita = sc.nextLine();
            PeriodicitàPubblicazione periodicità = PeriodicitàPubblicazione.valueOf(periodicita);

            Riviste rivista = new Riviste(isbn, titolo, annoPubblicazione, numeroPagine, periodicità);


            boolean giàEsistente = listaRiviste.stream()
                    .anyMatch(riviste -> riviste.getISBN() == rivista.getISBN());

            if (giàEsistente) {
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
                .peek(elemCatalogo -> System.out.println("Prodotto trovato: " + elemCatalogo))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Nessun prodotto trovato con anno di pubblicazione " + annoDiPubblicazione);
                    return null;
                });
    }


    public static void ricercaPerISBN(int isbn) {
        catalogoProdotti.stream()
                .filter(catalogoBibliotecario -> catalogoBibliotecario.getISBN() == isbn)
                .findFirst()
                .ifPresentOrElse(
                        catalogoBibliotecario -> System.out.println("prodotto trovato: " + catalogoBibliotecario),
                        () -> System.out.println("Nessun libro trovato con ISBN " + isbn)
                );
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

    //DA RIVEDERE PERCHE' MI DUPLICA LA LISTA CATALOGO PRODOTTI
    public static void rimuoviTramiteIsbn(int codiceProd) {
        catalogoProdotti.stream()
                .filter(catalogoBibliotecario -> catalogoBibliotecario.getISBN() == codiceProd)
                .findFirst()
                .ifPresentOrElse(
                        elem -> {
                            catalogoProdotti.removeIf(p -> elem.getISBN() == codiceProd);
                            System.out.println("Elemento con ISBN " + codiceProd + " rimosso con successo.");
                        },
                        () -> System.out.println("Rimozione dell'elemento con codice ISBN " + codiceProd + " non avvenuta correttamente !")
                );
        aggiornaCatalogo();
    }

    public static void aggiornaCatalogo() {
        catalogoProdotti = Stream.concat(listaLibri.stream(), listaRiviste.stream())
                .collect(Collectors.toList());
        catalogoProdotti.forEach(System.out::println);
    }

    public static void sommaPagineLibri() {
        int sum = listaLibri.stream()
                .mapToInt(Libri::getNumeroPagine)
                .sum();
        System.out.println("n° pagine totali dei libri in catalogo: " + sum + " " + "pagine");
    }

    public static void sommaPagineRiviste() {
        int sum = listaRiviste.stream()
                .mapToInt(Riviste::getNumeroPagine)
                .sum();
        System.out.println("n° pagine totali delle riviste in catalogo: " + sum + " " + "pagine");
    }

    public static void stampaNumeroPagine() {
        sommaPagineLibri();
        sommaPagineRiviste();
    }


}
