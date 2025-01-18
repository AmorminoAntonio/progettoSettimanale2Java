package catalogoBibliotecario;

import java.util.Objects;

public class Libri extends CatalogoBibliotecario {
    private String autore, genere;


    public Libri(int ISBN, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(ISBN, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }


    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Libri libri = (Libri) o;
        return Objects.equals(autore, libri.autore) && Objects.equals(genere, libri.genere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), autore, genere);
    }

    @Override
    public String toString() {
        return "LIBRO: " +
                "autore:' " + autore + '\'' +
                ", genere:' " + genere + '\'' +
                ", cod.ISBN: " + ISBN +
                ", Titolo:' " + Titolo + '\'' +
                ", anno di pubblicazione: " + annoPubblicazione +
                ", numero di Pagine: " + numeroPagine;
    }
}
