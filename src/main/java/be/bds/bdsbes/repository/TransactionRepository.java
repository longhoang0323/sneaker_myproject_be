package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findTopByQrCodeOrderByCreatedAtDesc(String qrCode);
}
