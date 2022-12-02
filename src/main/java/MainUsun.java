import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class MainUsun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Podaj id pojazdu do usuniecia: ");
            String podaneId = sc.nextLine();
            Long pojazdId = Long.parseLong(podaneId);

            Pojazd pojazd = session.get(Pojazd.class, pojazdId);

            if (pojazd == null) {
                System.err.println("Pojazd nie istnieje.");
            } else {
                session.remove(pojazd);
                System.out.println("Pojazd o id: " + pojazdId + " usuniety");
            }

            transaction.commit();

        } catch (Exception ioe) {
            System.err.println("Błąd bazy: " + ioe);
        }
    }
}

