package catalogoBibliotecario;

import java.util.Objects;

public abstract class CatalogoBibliotecario {
    protected int ISBN;
    protected String Titolo;
    protected int annoPubblicazione;
    protected int numeroPagine;



    public CatalogoBibliotecario(int ISBN, String titolo, int annoPubblicazione, int numeroPagine) {
        this.ISBN = ISBN;
        this.Titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitolo() {
        return Titolo;
    }

    public void setTitolo(String titolo) {
        this.Titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CatalogoBibliotecario that = (CatalogoBibliotecario) o;
        return ISBN == that.ISBN && annoPubblicazione == that.annoPubblicazione && numeroPagine == that.numeroPagine && Objects.equals(Titolo, that.Titolo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, Titolo, annoPubblicazione, numeroPagine);
    }

    @Override
    public String toString() {
        return "CatalogoBibliotecario{ " +
                "cod.ISBN: " + ISBN +
                ", Titolo:' " + Titolo + '\'' +
                ", anno di Pubblicazione: " + annoPubblicazione +
                ", numero di Pagine: " + numeroPagine +
                '}';
    }
}
