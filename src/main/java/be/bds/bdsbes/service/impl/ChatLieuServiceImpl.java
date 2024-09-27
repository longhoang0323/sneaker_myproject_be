package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.entities.ChatLieu;
import be.bds.bdsbes.payload.ChatLieuResponse;
import be.bds.bdsbes.repository.ChatLieuRepository;
import be.bds.bdsbes.service.dto.ChatLieuDTO;
import be.bds.bdsbes.service.iService.IChatLieuService;
import be.bds.bdsbes.utils.dto.PagedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("chatLieuService")
public class ChatLieuServiceImpl implements IChatLieuService {

    @Autowired
    ChatLieuRepository chatLieuRepository;

    public int getNumberOfRecords() {
        Long count = chatLieuRepository.count();
        return count.intValue();
    }


    private String generateAutoCode() {
        int numberOfDigits = 2;

        int numberOfExistingRecords = getNumberOfRecords();

        String autoCode = "C" + String.format("%0" + numberOfDigits + "d", numberOfExistingRecords + 1);

        return autoCode;
    }

    @Override
    public PagedResponse<ChatLieuResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<ChatLieuResponse> entities = chatLieuRepository.getAll(pageable);
        List<ChatLieuResponse> dtos = entities.toList();
        return new PagedResponse<>(
                dtos,
                page,
                size,
                entities.getTotalElements(),
                entities.getTotalPages(),
                entities.isLast(),
                entities.getSort().toString()
        );
    }

    @Override
    public ChatLieu create(ChatLieuDTO chatLieuDTO) {
        ChatLieu chatLieu = chatLieuDTO.dto(new ChatLieu());
        chatLieu.setMa(generateAutoCode());
        return chatLieuRepository.save(chatLieu);
    }

    @Override
    public ChatLieu update(Long id,ChatLieuDTO chatLieuDTO) {
        if(chatLieuRepository.findById(id).isPresent()){
            ChatLieu chatLieu = chatLieuRepository.findById(id).get();
            chatLieu.setTen(chatLieuDTO.getTen());
            return chatLieuRepository.save(chatLieu);
        }
        return null;
    }

    @Override
    public ChatLieu get(Long id) {
        if(chatLieuRepository.findById(id).isPresent()){
            return chatLieuRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public ChatLieu updateTrangThai(Long id, ChatLieuDTO chatLieuDTO) {
        if(chatLieuRepository.findById(id).isPresent()){
            ChatLieu chatLieu = chatLieuRepository.findById(id).get();
            chatLieu.setTrangThai(chatLieuDTO.getTrangThai());
            return chatLieuRepository.save(chatLieu);
        }
        return null;
    }
}
