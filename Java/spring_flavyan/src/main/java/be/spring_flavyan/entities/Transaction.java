package be.spring_flavyan.entities;

import be.spring_flavyan.entities.fiscal.Fiscal;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Fiscal buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Fiscal seller;
}