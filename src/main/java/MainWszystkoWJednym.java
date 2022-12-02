import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class MainWszystkoWJednym {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println("Co chcesz zrobic (dodaj/lista/szukaj/usun/aktualizuj)?");
            String odpowiedz = sc.nextLine();
            if(odpowiedz.equals("dodaj")){

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
            } else if(odpowiedz.equals("lista")){
                TypedQuery<Pojazd> zapytanie = session.createQuery("FROM Pojazd", Pojazd.class);

                List<Pojazd> listaWszystkichPojazdow = zapytanie.getResultList();

                for (Pojazd pojazd : listaWszystkichPojazdow) {
                    System.out.println(pojazd);
                }
            } else if(odpowiedz.equals("szukaj")){
                System.out.println("Podaj id pojazdu: ");
                String podaneId = sc.nextLine();
                Long pojazdId = Long.parseLong(podaneId);

                Pojazd pojazd = session.get(Pojazd.class, pojazdId);

                System.out.println("Znaleziono pojazd: " + pojazd);
            } else if(odpowiedz.equals("usun")){
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
            } else if(odpowiedz.equals("aktualizuj")) {
                System.out.println("Podaj id pojazdu do aktualizacji: ");
                String podaneId = sc.nextLine();
                Long pojazdId = Long.parseLong(podaneId);

                Pojazd pojazd = session.get(Pojazd.class, pojazdId);
                if (pojazd == null) {
                    System.err.println("Pojazd nie istnieje.");
                } else {
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
                    Pojazd pojazdAktualizowany = Pojazd.builder()
                            .marka(marka)
                            .moc(moc)
                            .kolor(kolor)
                            .rokProdukcji(rok)
                            .czyElektryczny(czyElektryczny)
                            .build();
                    session.merge(pojazdAktualizowany);
                }
            }
                transaction.commit();
            } catch (Exception ioe) {
                System.err.println("Błąd bazy: " + ioe);
            }
        }
    }

