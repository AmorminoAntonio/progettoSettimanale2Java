package catalogoBibliotecario;

public class Manga extends CatalogoBibliotecario {
    private String disegnatore;
    private PeriodicitàPubblicazione periodicità;

    public Manga(int ISBN, String titolo, int annoPubblicazione, int numeroPagine, String disegnatore, PeriodicitàPubblicazione periodicità) {
        super(ISBN, titolo, annoPubblicazione, numeroPagine);
        this.disegnatore = disegnatore;
        this.periodicità = periodicità;
    }

    public PeriodicitàPubblicazione getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(PeriodicitàPubblicazione periodicità) {
        this.periodicità = periodicità;
    }

    public String getDisegnatore() {
        return disegnatore;
    }

    public void setDisegnatore(String disegnatore) {
        this.disegnatore = disegnatore;
    }


    @Override
    public String toString() {
        return "Manga ==> " +
                "disegnatore: " + disegnatore +
                ", periodicità: " + periodicità +
                ", ISBN: " + ISBN +
                ", Titolo: " + Titolo +
                ", annoPubblicazione: " + annoPubblicazione +
                ", numeroPagine: " + numeroPagine;
    }
}
