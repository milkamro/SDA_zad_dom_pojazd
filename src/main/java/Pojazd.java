import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Scanner;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Pojazd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marka;
    private double moc;
    private String kolor;
    private int rokProdukcji;
    private boolean czyElektryczny;

}
