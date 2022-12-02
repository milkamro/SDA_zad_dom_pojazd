import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class MainDodaj {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println("Podaj marke:");
            String marka = sc.nextLine();

            System.out.println("Podaj moc:");
            double moc = sc.nextDouble();
            sc.nextLine();

            System.out.println("Podaj kolor:");
            String kolor = sc.nextLine();

            int rok = 0;
            do {
                System.out.println("Podaj rok produkcji (między 1990-2020:");
                rok = sc.nextInt();
                sc.nextLine();
            } while (rok < 1990 || rok > 2020);

            System.out.println("Podaj czy jest elektryczny (true/false):");
            boolean czyElektryczny = sc.nextBoolean();
            sc.nextLine();
            Pojazd pojazd = Pojazd.builder()
                    .marka(marka)
                    .moc(moc)
                    .kolor(kolor)
                    .rokProdukcji(rok)
                    .czyElektryczny(czyElektryczny)
                    .build();
            session.persist(pojazd);

            transaction.commit();
        } catch (Exception ioe) {
            System.err.println("Błąd bazy: " + ioe);
        }
    }
}
