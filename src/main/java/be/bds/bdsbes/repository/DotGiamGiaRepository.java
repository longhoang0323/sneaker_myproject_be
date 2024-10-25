package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.DotGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("dotGiamGiaRepository")
public interface DotGiamGiaRepository extends JpaRepository<DotGiamGia, Long> {
}
