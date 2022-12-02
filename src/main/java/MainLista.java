import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;

public class MainLista {
    public static void main(String[] args) {

        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            TypedQuery<Pojazd> zapytanie = session.createQuery("FROM Pojazd", Pojazd.class);

            List<Pojazd> listaWszystkichPojazdow = zapytanie.getResultList();

            for (Pojazd pojazd : listaWszystkichPojazdow) {
                System.out.println(pojazd);
            }

        } catch (Exception ioe) {
            System.err.println("Błąd bazy: " + ioe);
        }
    }
}
