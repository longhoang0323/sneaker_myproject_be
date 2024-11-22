package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.MauSac;
import be.bds.bdsbes.payload.MauSacResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Long> {

    @Query("select new be.bds.bdsbes.payload.MauSacResponse(m.id, m.ma, m.ten, m.trangThai) from MauSac m")
    Page<MauSacResponse> getAll(Pageable pageable);
}
