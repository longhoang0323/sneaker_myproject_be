package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.Hang;
import be.bds.bdsbes.payload.HangResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HangRepository extends JpaRepository<Hang, Long> {

    @Query("select new be.bds.bdsbes.payload.HangResponse(h.id, h.ma, h.ten, h.trangThai) from Hang h")
    Page<HangResponse> getAll(Pageable pageable);
}
