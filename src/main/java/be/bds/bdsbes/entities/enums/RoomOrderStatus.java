package be.bds.bdsbes.entities.enums;


public enum RoomOrderStatus {

    STATUS0(0, "DH", "Đã hủy"),
    STATUS1(1, "DD", "Đã đặt"),
    STATUS2(2, "DCI", "Đã check-in"),
    STATUS3(3, "DCO", "Đã check-out"),
    STATUS4(4, "CXN", "Chờ xác nhận"),
    STATUS5(5, "COM", "Check-out muộn");

    private int id;

    private String code;

    private String name;

    RoomOrderStatus() {
    }

    RoomOrderStatus(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
