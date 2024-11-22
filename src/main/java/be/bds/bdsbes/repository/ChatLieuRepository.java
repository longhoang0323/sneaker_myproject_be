package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.ChatLieu;
import be.bds.bdsbes.payload.ChatLieuResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu, Long> {

    @Query("select new be.bds.bdsbes.payload.ChatLieuResponse(c.id, c.ma, c.ten, c.trangThai) from ChatLieu c")
    Page<ChatLieuResponse> getAll(Pageable pageable);
}
