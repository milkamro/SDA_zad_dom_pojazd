import org.hibernate.Session;

import java.util.Scanner;

public class MainSzukaj {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            System.out.println("Podaj id pojazdu: ");
            String podaneId = sc.nextLine();
            Long pojazdId = Long.parseLong(podaneId);

            Pojazd pojazd = session.get(Pojazd.class, pojazdId);

            System.out.println("Znaleziono pojazd: " + pojazd);

        } catch (Exception ioe) {
            System.err.println("Błąd bazy: " + ioe);
        }
    }
}

