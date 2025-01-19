package catalogoBibliotecario;


import java.util.Objects;


public class Riviste extends CatalogoBibliotecario {
    private PeriodicitàPubblicazione periodicità;


    public Riviste(int ISBN, String titolo, int annoPubblicazione, int numeroPagine, Enum<PeriodicitàPubblicazione> periodicitàPubblicazione) {
        super(ISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicità = (PeriodicitàPubblicazione) periodicitàPubblicazione;
    }


    public PeriodicitàPubblicazione getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(PeriodicitàPubblicazione periodicità) {
        this.periodicità = periodicità;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Riviste riviste = (Riviste) o;
        return Objects.equals(periodicità, riviste.periodicità);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), periodicità);
    }

    @Override
    public String toString() {
        return "RIVISTA ==> " +
                "periodicità di Pubblicazione: " + periodicità +
                ", cod.ISBN: " + ISBN +
                ", Titolo: " + Titolo +
                ", anno di Pubblicazione: " + annoPubblicazione +
                ", numero di Pagine: " + numeroPagine;
    }
}
