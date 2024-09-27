package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.ChatLieu;
import be.bds.bdsbes.payload.ChatLieuResponse;
import be.bds.bdsbes.service.dto.ChatLieuDTO;
import be.bds.bdsbes.utils.dto.PagedResponse;

public interface IChatLieuService {

    PagedResponse<ChatLieuResponse> getAll(int page, int size);

    ChatLieu create(ChatLieuDTO chatLieuDTO);

    ChatLieu update(Long id, ChatLieuDTO chatLieuDTO);

    ChatLieu get(Long id);

    ChatLieu updateTrangThai(Long id, ChatLieuDTO chatLieuDTO);
}
