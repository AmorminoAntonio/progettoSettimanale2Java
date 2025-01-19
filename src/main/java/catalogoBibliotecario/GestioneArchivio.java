package catalogoBibliotecario;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


// Chiedo scusa per il disordine nel codice però mi sono concentrato più sul capire quali strade potessi percorrere
// per poter strutturare al meglio il codice e sul capire possibilmente piu concetti possibili, cosi
// da assimilare il più possibile per riuscire poi ad avere una mente più aperta sulla manutenibilità e suddivisione dei macro blocchi di codice.
// Ho capito che potevo gestire separando le due interfacce fra gestionale e ui dell' utente per snellire il Main, però non volevo rovinare il lavoro fatto prima della consegna finale.
// PS --> ovviamente continuerò a usare questo progetto per fare varie prove.
// Spero di aver fatto un minimo bene🤞 e le auguro una buona visione del mio codice...
// BUON DIVERTIMENTO.  █ 😁 █


public class GestioneArchivio {

    private static List<CatalogoBibliotecario> catalogoProdotti = new ArrayList<>();
    private static List<Riviste> listaRiviste = new ArrayList<>();
    private static List<Libri> listaLibri = new ArrayList<>();
    private static List<Manga> listaManga = new ArrayList<>();
    private static Faker fakeData = new Faker(new Locale("it-IT"));
    private static Scanner sc = new Scanner(System.in);
    private static Logger log = LoggerFactory.getLogger(GestioneArchivio.class);


    public static void main(String[] args) {

// qui genero il catalogo ma non lo stampo ancora a video.
        generaCatalogo();


        while (true) {

            System.out.println("\nSeleziona la funzione da svolgere: ");
            System.out.println("(1): Ricerca per ISBN");
            System.out.println("(2): Ricerca per Autore");
            System.out.println("(3): Ricerca per Pubblicazione");
            System.out.println("(4): Aggiungi elemento");
            System.out.println("(5): Rimuovi elemento tramite ISBN");
            System.out.println("(6): Modifica un elemento");
            System.out.println("(7): Stampa statistiche (INFO) del catalogo");
            System.out.println("(8): Stampa catalogo");
            System.out.println("(9): Ricerca per disegnatore(MANGA)");
            System.out.println("(0): Esci");
            System.out.println("\n");
            System.out.print("Selezione (inserisci di seguito) ▄ ▄ ▄ ");

            try {
                int scelta = sc.nextInt();
                sc.nextLine();

                // oppure potrei usare "\n" per consumare la riga residua.
                // Evitando un errore e quindi un salto improvviso a una riga di comando non direttamente successiva.
                // (gestisco l'errore cosi)
                if (scelta == 0) {
                    log.info("Programma Terminato con successo.");
                    sc.close();
                    break;
                } else {
                    switch (scelta) {
                        case 1:
                            ricercaPerISBN();
                            break;
                        case 2:
                            ricercaPerAutore();
                            break;
                        case 3:
                            ricercaPerPubblicazione();
                            break;
                        case 4:
                            aggiungiElementoSenzaDoppioni();
                            break;
                        case 5:
                            rimuoviTramiteIsbn();
                            break;
                        case 6:
                            modificaElementoCatalogo();
                            break;
                        case 7:
                            stampaStatistiche();
                            break;
                        case 8:
                            stampaCatalogo();
                            break;
                        case 9:
                            ricercaPerDisegnatore();
                            break;
                        default:
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                log.error(e.getMessage(), "ERRORE: mi hai inserito una stringa e non un numero.");
                sc.nextLine();
                // nel catch sto gestendo un valore immesso non corretto,
                // con messaggio di risposta dell'errore commesso,
                // poi successivamente un indicazione della condizione per una immissione del dato corretta.
            } catch (NumberFormatException numberFormatException) {
                log.error(numberFormatException.getMessage());
            }

        }
    }


    // PROMEMORIA DELLE IMPLEMENTAZIONI:
    // ALGO DELLE FUNZIONALITA':
    // CREAZIONE = libri, riviste.  █(OK)
    // AGGIUNTA IN CATALOGO = catalogo prodotti = (libri + riviste).  █(OK)
    // ALGO DI RICERCA = per ISBN, per AUTORE, per ANNO DI PUBBLICAZIONE.  █(OK)
    // ALGO DI AGGIUNTA PRODOTTO = suddiviso per tipo di prodotto fatta eccezione per prodotti già esistenti.  █(OK)
    // ALGO DI RIMOZIONE PRODOTTO = prima ricerco il cod. ISBN, poi rimuovo il prodotto e poi stampo il nuovo catalogo.  █(OK)
    // ALGO DI MODIFICA PRODOTTO = suddiviso per tipo di prodotto.  █(OK)
    // ALGO DI STAMPA CATALOGO = ciclo di stampa del catalogo aggiornato.  █(OK)
    // ALGO DI STAMPA STATISTICHE = totale libri, riviste + elem Max pagine + media pagine +

    public static void generaCatalogo() {
        catalogoProdotti.add(new Libri(51, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(30, 600), fakeData.book().author(), fakeData.book().genre()));
        catalogoProdotti.add(new Libri(52, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(30, 600), fakeData.book().author(), fakeData.book().genre()));
        catalogoProdotti.add(new Libri(53, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(30, 600), fakeData.book().author(), fakeData.book().genre()));
        catalogoProdotti.add(new Libri(54, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(30, 600), fakeData.book().author(), fakeData.book().genre()));
        catalogoProdotti.add(new Libri(55, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(30, 600), fakeData.book().author(), fakeData.book().genre()));
        catalogoProdotti.add(new Libri(56, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(30, 600), fakeData.book().author(), fakeData.book().genre()));
        catalogoProdotti.add(new Libri(57, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(30, 600), fakeData.book().author(), fakeData.book().genre()));
        catalogoProdotti.add(new Libri(58, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(30, 600), fakeData.book().author(), fakeData.book().genre()));
        catalogoProdotti.add(new Libri(59, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(30, 600), fakeData.book().author(), fakeData.book().genre()));
        catalogoProdotti.add(new Libri(60, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(30, 600), fakeData.book().author(), fakeData.book().genre()));
        catalogoProdotti.add(new Riviste(61, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Riviste(62, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Riviste(63, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Riviste(64, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Riviste(65, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Riviste(66, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Riviste(67, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Riviste(68, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Riviste(69, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Riviste(70, fakeData.book().title(), fakeData.number().numberBetween(1800, 2025), fakeData.number().numberBetween(15, 30), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Manga(81, fakeData.book().title(), fakeData.number().numberBetween(1958, 2025), fakeData.number().numberBetween(30, 50), fakeData.book().author(), PeriodicitàPubblicazione.settimanale));
        catalogoProdotti.add(new Manga(82, fakeData.book().title(), fakeData.number().numberBetween(1958, 2025), fakeData.number().numberBetween(30, 50), fakeData.book().author(), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Manga(83, fakeData.book().title(), fakeData.number().numberBetween(1958, 2025), fakeData.number().numberBetween(30, 50), fakeData.book().author(), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Manga(84, fakeData.book().title(), fakeData.number().numberBetween(1958, 2025), fakeData.number().numberBetween(30, 50), fakeData.book().author(), PeriodicitàPubblicazione.settimanale));
        catalogoProdotti.add(new Manga(85, fakeData.book().title(), fakeData.number().numberBetween(1958, 2025), fakeData.number().numberBetween(30, 50), fakeData.book().author(), PeriodicitàPubblicazione.semestrale));
        catalogoProdotti.add(new Manga(86, fakeData.book().title(), fakeData.number().numberBetween(1958, 2025), fakeData.number().numberBetween(30, 50), fakeData.book().author(), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Manga(86, fakeData.book().title(), fakeData.number().numberBetween(1958, 2025), fakeData.number().numberBetween(30, 50), fakeData.book().author(), PeriodicitàPubblicazione.semestrale));
        catalogoProdotti.add(new Manga(87, fakeData.book().title(), fakeData.number().numberBetween(1958, 2025), fakeData.number().numberBetween(30, 50), fakeData.book().author(), PeriodicitàPubblicazione.settimanale));
        catalogoProdotti.add(new Manga(88, fakeData.book().title(), fakeData.number().numberBetween(1958, 2025), fakeData.number().numberBetween(30, 50), fakeData.book().author(), PeriodicitàPubblicazione.settimanale));
        catalogoProdotti.add(new Manga(89, fakeData.book().title(), fakeData.number().numberBetween(1958, 2025), fakeData.number().numberBetween(30, 50), fakeData.book().author(), PeriodicitàPubblicazione.settimanale));
        catalogoProdotti.add(new Manga(90, fakeData.book().title(), fakeData.number().numberBetween(1958, 2025), fakeData.number().numberBetween(30, 50), fakeData.book().author(), PeriodicitàPubblicazione.semestrale));
        catalogoProdotti.add(new Manga(91, fakeData.book().title(), fakeData.number().numberBetween(1958, 2025), fakeData.number().numberBetween(30, 50), fakeData.book().author(), PeriodicitàPubblicazione.mensile));
        catalogoProdotti.add(new Manga(92, fakeData.book().title(), fakeData.number().numberBetween(1958, 2025), fakeData.number().numberBetween(30, 50), fakeData.book().author(), PeriodicitàPubblicazione.mensile));
    }


    static void stampaCatalogo() {
        if (catalogoProdotti.isEmpty()) {
            System.out.println("Mi dispiace, il tuo catalogo è vuoto !");
        } else {
            catalogoProdotti.forEach(System.out::println);
        }
    }


    static void aggiungiElementoSenzaDoppioni() {
        System.out.println("Cosa desideri aggiungere? (1)Libro  (2)Rivista  (3)Manga)");
        int scelta = sc.nextInt();
        sc.nextLine();

        switch (scelta) {
            case 1:
                System.out.print("Inserisci ISBN: ");
                int isbn = sc.nextInt();
                sc.nextLine();

                boolean libroGiàPresente = listaLibri.stream()
                        .anyMatch(book -> book.getISBN() == isbn);

                if (libroGiàPresente) {
                    try {
                        throw new IsbnDuplicatoException("Il libro con ISBN " + isbn + " è già presente.");
                    } catch (IsbnDuplicatoException e) {
                        log.error(e.getMessage());
                    }
                } else {
                    System.out.print("Inserisci Titolo: ");
                    String titolo = sc.nextLine().toLowerCase();

                    System.out.print("Inserisci anno di pubblicazione: ");
                    int annoPubblicazione = sc.nextInt();

                    System.out.print("Inserisci numero di pagine: ");
                    int numeroPagine = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Inserisci autore: ");
                    String autore = sc.nextLine().toLowerCase();

                    System.out.print("Inserisci genere: ");
                    String genere = sc.nextLine().toLowerCase();

                    Libri libro = new Libri(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);
                    listaLibri.add(libro);
                    System.out.println("Libro aggiunto: " + libro);
                }
                break;

            case 2:
                System.out.print("Inserisci ISBN: ");
                int isbnRivista = sc.nextInt();
                sc.nextLine();

                boolean rivistaGiàPresente = listaRiviste.stream()
                        .anyMatch(rivista -> rivista.getISBN() == isbnRivista);

                if (rivistaGiàPresente) {
                    try {
                        throw new IsbnDuplicatoException("La rivista con ISBN " + isbnRivista + " è già presente.");
                    } catch (IsbnDuplicatoException e) {
                        log.error(e.getMessage());
                    }
                } else {
                    System.out.print("Inserisci Titolo: ");
                    String titoloRivista = sc.nextLine().toLowerCase();

                    System.out.print("Inserisci anno di pubblicazione: ");
                    int annoPubblicazioneRivista = sc.nextInt();

                    System.out.print("Inserisci numero di pagine: ");
                    int numeroPagineRivista = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Inserisci periodicità (Settimanale, Mensile, Semestrale): ");
                    String periodicita = sc.nextLine().toLowerCase();

                    try {
                        PeriodicitàPubblicazione periodicitàEnum = PeriodicitàPubblicazione.valueOf(periodicita.toLowerCase());
                        Riviste rivista = new Riviste(isbnRivista, titoloRivista, annoPubblicazioneRivista, numeroPagineRivista, periodicitàEnum);
                        listaRiviste.add(rivista);
                        System.out.println("Rivista aggiunta: " + rivista);
                    } catch (IllegalArgumentException e) {
                        log.error("Periodicità di pubblicazione della rivista non valida. Le opzioni disponibili sono: Settimanale, Mensile, Semestrale.");
                    }
                }
                break;

            case 3:
                System.out.print("Inserisci ISBN: ");
                int isbnManga = sc.nextInt();
                sc.nextLine();

                boolean mangaGiàPresente = listaManga.stream()
                        .anyMatch(manga -> manga.getISBN() == isbnManga);

                if (mangaGiàPresente) {
                    try {
                        throw new IsbnDuplicatoException("La rivista con ISBN " + isbnManga + " è già presente.");
                    } catch (IsbnDuplicatoException e) {
                        log.error(e.getMessage());
                    }
                } else {
                    System.out.print("Inserisci Titolo: ");
                    String titoloManga = sc.nextLine().toLowerCase();

                    System.out.print("Inserisci anno di pubblicazione: ");
                    int annoPubblicazioneManga = sc.nextInt();

                    System.out.print("Inserisci numero di pagine: ");
                    int numeroPagineManga = sc.nextInt();
                    sc.nextLine();

                    System.out.print("inserisci il disegnatore: ");
                    String disegnatore = sc.nextLine().toLowerCase();

                    System.out.print("Inserisci periodicità (Settimanale, Mensile, Semestrale): ");
                    String periodicitaManga = sc.nextLine().toLowerCase();

                    try {
                        PeriodicitàPubblicazione periodicitàMangaEnum = PeriodicitàPubblicazione.valueOf(periodicitaManga.toLowerCase());
                        Manga manga = new Manga(isbnManga, titoloManga, annoPubblicazioneManga, numeroPagineManga, disegnatore, periodicitàMangaEnum);
                        listaManga.add(manga);
                        System.out.println("Manga aggiunto: " + manga);

                    } catch (IllegalArgumentException e) {
                        log.error("Periodicità di pubblicazione del manga non valida. Le opzioni disponibili sono: Settimanale, Mensile, Semestrale.");
                    }
                }
                break;

            default:
                log.error("Scelta non valida.");
                break;
        }
    }


    public static void modificaElementoCatalogo() {
        System.out.print("Inserisci l'ISBN dell'elemento da modificare: ");
        int IsbnCercato = sc.nextInt();
        sc.nextLine();

        try {
            CatalogoBibliotecario prodottoDaModificare = catalogoProdotti.stream()
                    .filter(catalogoBibliotecario -> catalogoBibliotecario.getISBN() == IsbnCercato)
                    .findFirst()
                    .orElseThrow(() -> new elementoNonEsistente("Nessun prodotto trovato con ISBN " + IsbnCercato));

            switch (prodottoDaModificare) {
                case Libri libro:
                    System.out.println("Elemento trovato: " + libro);
                    System.out.println("Modifica le proprietà del libro:");

                    System.out.print("Nuovo titolo (corrente: " + libro.getTitolo() + "): ");
                    String nuovoTitolo = sc.nextLine();
                    libro.setTitolo(nuovoTitolo);

                    System.out.print("Nuovo anno di pubblicazione (corrente: " + libro.getAnnoPubblicazione() + "): ");
                    int nuovoAnno = sc.nextInt();
                    libro.setAnnoPubblicazione(nuovoAnno);
                    sc.nextLine();

                    System.out.print("Nuovo numero di pagine (corrente: " + libro.getNumeroPagine() + "): ");
                    int nuovePagine = sc.nextInt();
                    libro.setNumeroPagine(nuovePagine);
                    sc.nextLine();

                    System.out.print("Nuovo autore (corrente: " + libro.getAutore() + "): ");
                    String nuovoAutore = sc.nextLine();
                    libro.setAutore(nuovoAutore);

                    System.out.print("Nuovo genere (corrente: " + libro.getGenere() + "): ");
                    String nuovoGenere = sc.nextLine();
                    libro.setGenere(nuovoGenere);

                    log.info("Modifica del libro avvenuta con successo!");
                    System.out.println("Libro modificato: " + libro);
                    break;

                case Riviste rivista:
                    System.out.println("Elemento trovato: " + rivista);
                    System.out.println("Modifica le proprietà della rivista:");

                    System.out.print("Nuovo titolo (corrente: " + rivista.getTitolo() + "): ");
                    String nuovoTitoloRivista = sc.nextLine();
                    rivista.setTitolo(nuovoTitoloRivista);

                    System.out.print("Nuovo anno di pubblicazione (corrente: " + rivista.getAnnoPubblicazione() + "): ");
                    int nuovoAnnoRivista = sc.nextInt();
                    rivista.setAnnoPubblicazione(nuovoAnnoRivista);
                    sc.nextLine();

                    System.out.print("Nuovo numero di pagine (corrente: " + rivista.getNumeroPagine() + "): ");
                    int nuovePagineRivista = sc.nextInt();
                    rivista.setNumeroPagine(nuovePagineRivista);
                    sc.nextLine();

                    System.out.print("Nuova periodicità (corrente: " + rivista.getPeriodicità() + "): ");
                    String nuovaPeriodicita = sc.nextLine();
                    try {
                        PeriodicitàPubblicazione nuovaPeriodicitaRivista = PeriodicitàPubblicazione.valueOf(nuovaPeriodicita);
                        rivista.setPeriodicità(nuovaPeriodicitaRivista);
                    } catch (IllegalArgumentException e) {
                        throw new periodicitàErrata("Periodicità non valida: " + nuovaPeriodicita);
                    }

                    log.info("Modifica della rivista avvenuta con successo!");
                    System.out.println("Rivista modificata: " + rivista);
                    break;


                case Manga manga:
                    System.out.println("Elemento trovato: " + manga);
                    System.out.println("Modifica le proprietà della manga:");

                    System.out.print("Nuovo titolo (corrente: " + manga.getTitolo() + "): ");
                    String nuovoTitoloManga = sc.nextLine();
                    manga.setTitolo(nuovoTitoloManga);

                    System.out.print("Nuovo anno di pubblicazione (corrente: " + manga.getAnnoPubblicazione() + "): ");
                    int nuovoAnnoManga = sc.nextInt();
                    manga.setAnnoPubblicazione(nuovoAnnoManga);
                    sc.nextLine();

                    System.out.print("Nuovo numero di pagine (corrente: " + manga.getNumeroPagine() + "): ");
                    int nuovePagineManga = sc.nextInt();
                    manga.setNumeroPagine(nuovePagineManga);
                    sc.nextLine();

                    System.out.print("Nuovo disegnatore (corrente: " + manga.getDisegnatore() + "): ");
                    String nuovoDisegnatoreManga = sc.nextLine();
                    manga.setDisegnatore(nuovoDisegnatoreManga);

                    System.out.print("Nuova periodicità (corrente: " + manga.getPeriodicità() + "): ");
                    String nuovaPeriodicità = sc.nextLine();
                    try {
                        PeriodicitàPubblicazione nuovaPeriodicitaEnum = PeriodicitàPubblicazione.valueOf(nuovaPeriodicità);
                        manga.setPeriodicità(nuovaPeriodicitaEnum);
                    } catch (IllegalArgumentException e) {
                        throw new periodicitàErrata("Periodicità non valida: " + nuovaPeriodicità);
                    }

                    log.info("Modifica del manga avvenuta con successo!");
                    System.out.println("Manga modificato: " + manga);
                    break;

                default:
                    throw new NoSuchElementException("Tipo di elemento non supportato per la modifica.");
            }
        } catch (elementoNonEsistente e) {
            log.error(e.getMessage(), "Elemento non presente all'interno del catalogo");
        } catch (periodicitàErrata e) {
            log.error(e.getMessage(), "Periodicità errata");
        } catch (Exception e) {
            log.error("Errore durante la modifica: " + e.getMessage());
        }

    }


    public static void ricercaPerPubblicazione() {
        System.out.print("inserisci l'anno di pubblicazione: ");
        int annoPubblicazione = sc.nextInt();
        catalogoProdotti.stream()
                .filter(catalogoBibliotecario -> catalogoBibliotecario.getAnnoPubblicazione() == annoPubblicazione)
                .findFirst()
                .ifPresentOrElse(
                        elemCatalogo -> System.out.println("Prodotto trovato: " + elemCatalogo),
                        () -> System.out.println("Nessun prodotto trovato con anno di pubblicazione " + annoPubblicazione)
                );
    }


    public static void ricercaPerISBN() {
        try {
            System.out.print("inserisci cod.ISBN: ");
            int isbn = sc.nextInt();
            catalogoProdotti.stream()
                    .filter(catalogoBibliotecario -> catalogoBibliotecario.getISBN() == isbn)
                    .findFirst()
                    .ifPresent(prodottoCercato -> System.out.println("prodotto trovato: " + prodottoCercato)
                    );
        } catch (IsbnNonValidoException incorrect) {
            log.error(incorrect.getMessage(), "il codice Isbn inserito non è CORRETTO.");
        } catch (NumberFormatException n) {
            log.error(n.getMessage(), "l' elemento non trovato. ");
        } catch (InputMismatchException e) {
            log.error("Erroe: hai inserito un formato sbagliato per il cod. Isbn");
        }
    }


    public static void ricercaPerAutore() {
        System.out.print("Inserisci il nome dell'autore: ");
        String autore = sc.nextLine().trim().toLowerCase();

        catalogoProdotti.stream()
                .filter(prodotto -> prodotto instanceof Libri)
                .map(prodotto -> (Libri) prodotto)
                .filter(libro -> libro.getAutore() != null && libro.getAutore().equalsIgnoreCase(autore))
                .findFirst()
                .ifPresentOrElse(
                        libro -> System.out.println("Libro trovato: " + libro),
                        () -> System.out.println("Nessun libro trovato per l'autore: " + autore)
                );
    }


    public static void ricercaPerDisegnatore() {
        System.out.print("Inserisci il nome del disegnatore: ");
        String disegnatore = sc.nextLine().trim().toLowerCase();

        catalogoProdotti.stream()
                .filter(prodotto -> prodotto instanceof Manga)
                .map(prodotto -> (Manga) prodotto)
                .filter(manga -> manga.getDisegnatore() != null && manga.getDisegnatore().equalsIgnoreCase(disegnatore))
                .findFirst()
                .ifPresentOrElse(
                        manga -> System.out.println("Manga trovato: " + manga),
                        () -> System.out.println("Nessun manga trovato per il disegnatore: " + disegnatore)
                );
    }


    //DA RIVEDERE PERCHE' MI DUPLICA LA LISTA CATALOGO PRODOTTI ---> █(OK)
    public static void rimuoviTramiteIsbn() {
        System.out.print("inserisci il cod.ISBN: ");
        int codiceProd = sc.nextInt();
        catalogoProdotti.stream()
                .filter(catalogoBibliotecario -> catalogoBibliotecario.getISBN() == codiceProd)
                .findFirst()
                .ifPresentOrElse(
                        catalogoBibliotecario -> {
                            catalogoProdotti.removeIf(prodotto -> prodotto.getISBN() == codiceProd);
                            System.out.println("il Prodotto con ISBN " + codiceProd + " è stato rimosso con successo.");
                            catalogoProdotti.forEach(System.out::println);
                        },
                        () -> System.out.println("Rimozione dell'elemento con codice ISBN " + codiceProd + " non avvenuta correttamente!")
                );
    }


    public static void stampaStatistiche() {

        // ALGO per stampare il totale di pagine dei libri
        int numPagLibri = catalogoProdotti.stream()
                .filter(Libri.class::isInstance)
                .mapToInt(CatalogoBibliotecario::getNumeroPagine)
                .sum();
        log.info("n° pagine totali dei libri in catalogo: " + numPagLibri + " " + "pagine");


        // ALGO per stampare il totale di pagine dei manga
        int numPagManga = catalogoProdotti.stream()
                .filter(Manga.class::isInstance)
                .mapToInt(CatalogoBibliotecario::getNumeroPagine)
                .sum();
        log.info("n° pagine totali dei manga in catalogo: " + numPagManga + " " + "pagine");


        // ALGO per stampare il totale di pagine delle riviste
        int numPagRiviste = catalogoProdotti.stream()
                .filter(Riviste.class::isInstance)
                .mapToInt(CatalogoBibliotecario::getNumeroPagine)
                .sum();
        log.info("n° pagine totali delle riviste in catalogo: " + numPagRiviste + " " + "pagine");


        // ALGO per stampare il numero medio di pagine per prodotto nel catalogo
        OptionalDouble mediaPagine = catalogoProdotti.stream()
                .mapToInt(CatalogoBibliotecario::getNumeroPagine)
                .average();
        log.info("media delle pagine per prodotto in catalogo; " + mediaPagine);


        // ALGO per stampare il prodotto con piu pagine.
        CatalogoBibliotecario prodottoConMaxPagine = catalogoProdotti.stream()
                .max(Comparator.comparingInt(CatalogoBibliotecario::getNumeroPagine))
                .orElseThrow(NoSuchElementException::new);
        log.info("Elemento con più pagine in catalogo: " + prodottoConMaxPagine);


        // ALGO per stampare il totale di libri nel catalogo
        long totaleLibri = catalogoProdotti.stream()
                .filter(Libri.class::isInstance)
                .count();
        if (listaLibri == null) {
            log.error("Errore! La lista libri è vuota");
        } else {
            log.info("Numero di libri nella lista: " + totaleLibri);
            listaLibri.forEach(System.out::println);
        }


        // ALGO per stampare il totale delle riviste nel catalogo
        long totaleRiviste = catalogoProdotti.stream()
                .filter(Riviste.class::isInstance)
                .count();
        if (listaRiviste == null) {
            log.error("errore ! - la tua lista riviste è vuota");
        } else {
            log.info("Numero di riviste nella lista: " + totaleRiviste);
            listaRiviste.forEach(System.out::println);
        }


        // ALGO per stampare il totale delle riviste nel catalogo
        long totaleManga = catalogoProdotti.stream()
                .filter(Manga.class::isInstance)
                .count();
        if (listaManga == null) {
            log.error("errore ! - la tua lista manga è vuota");
        } else {
            log.info("Numero di manga nella lista: " + totaleManga);
            listaManga.forEach(System.out::println);
        }
    }

}
