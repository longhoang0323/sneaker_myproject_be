package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.KichThuoc;
import be.bds.bdsbes.payload.KichThuocResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KichThuocRepository extends JpaRepository<KichThuoc, Long> {

    @Query("select new be.bds.bdsbes.payload.KichThuocResponse(k.id, k.ma, k.ten, k.trangThai) from KichThuoc k")
    Page<KichThuocResponse> getAll(Pageable pageable);
}
