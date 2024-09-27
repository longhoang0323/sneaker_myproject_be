package be.bds.bdsbes.service.dto;

import be.bds.bdsbes.entities.ChatLieu;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatLieuDTO {

    private String ma;

    private String ten;

    private int trangThai;

    public ChatLieu dto(ChatLieu chatLieu){
        chatLieu.setTen(this.getTen());
        chatLieu.setTrangThai(0);
        return chatLieu;
    }
}
