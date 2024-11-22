package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.domain.User;
import be.bds.bdsbes.entities.HoaDon;
import be.bds.bdsbes.entities.KhachHang;
import be.bds.bdsbes.entities.Voucher;
import be.bds.bdsbes.payload.HangResponse;
import be.bds.bdsbes.payload.HoaDonResponse;
import be.bds.bdsbes.repository.HoaDonRepository;
import be.bds.bdsbes.repository.KhachHangRepository;
import be.bds.bdsbes.service.dto.HoaDonDTO;
import be.bds.bdsbes.service.iService.IHoaDonService;
import be.bds.bdsbes.utils.dto.PagedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service("hoaDonService")
public class HoaDonServiceImpl implements IHoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public PagedResponse<HoaDonResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<HoaDonResponse> entities = hoaDonRepository.getAll(pageable);
        List<HoaDonResponse> dtos = entities.toList();
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
    public PagedResponse<HoaDonResponse> getAllByLoaiHoaDon(int page, int size, Integer loaiHoaDon) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<HoaDonResponse> entities = hoaDonRepository.getAllByLoaiHD(pageable, loaiHoaDon);
        List<HoaDonResponse> dtos = entities.toList();
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
    public HoaDon createTaiQuay(HoaDonDTO hoaDonDTO) {
        LocalDateTime time = LocalDateTime.now();
        String maHd = "HD" + String.valueOf(time.getYear()).substring(2) +
                time.getMonthValue() + time.getDayOfMonth() + time.getHour() +
                time.getMinute() + time.getSecond();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa(maHd);
        hoaDon.setNgayTao(LocalDateTime.now());
        hoaDon.setUser(User.builder().id(hoaDonDTO.getIdUser()).build());
        hoaDon.setTrangThai(0);
        hoaDon.setLoaiHoaDon(0);
        hoaDon.setTrangThaiGiaoHang(0);
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon datHangOnline(HoaDonDTO hoaDonDTO) {
        LocalDateTime time = LocalDateTime.now();
        String maHd = "HD" + String.valueOf(time.getYear()).substring(2) +
                time.getMonthValue() + time.getDayOfMonth() + time.getHour() +
                time.getMinute() + time.getSecond();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa(maHd);
        hoaDon.setNgayTao(LocalDateTime.now());
        hoaDon.setUser(User.builder().id(hoaDonDTO.getIdUser()).build());
        hoaDon.setKhachHang(KhachHang.builder().id(hoaDonDTO.getIdKhachHang()).build());
        hoaDon.setNgayThanhToan(hoaDonDTO.getNgayThanhToan());
        hoaDon.setHinhThucThanhToan(hoaDonDTO.getHinhThucThanhToan());
        hoaDon.setHinhThucGiaoHang(1);
        hoaDon.setTenNguoiNhan(hoaDonDTO.getTenNguoiNhan());
        hoaDon.setSdtNguoiNhan(hoaDonDTO.getSdtNguoiNhan());
        hoaDon.setDiaChi(hoaDonDTO.getDiaChi());
        hoaDon.setTongTien(hoaDonDTO.getTongTien());
        hoaDon.setTienShip(hoaDonDTO.getTienShip());
        hoaDon.setTienGiamGia(hoaDonDTO.getTienGiamGia());
        hoaDon.setTongThanhToan(hoaDonDTO.getTongThanhToan());
        hoaDon.setGhiChu(hoaDonDTO.getGhiChu());
        hoaDon.setTienMat(BigDecimal.valueOf(0));
        hoaDon.setChuyenKhoan(BigDecimal.valueOf(0));
        hoaDon.setTrangThai(0);
        hoaDon.setTrangThaiGiaoHang(0);
        hoaDon.setLoaiHoaDon(1);
        if(hoaDonDTO.getIdVoucher() != null){
            hoaDon.setVoucher(Voucher.builder().id(hoaDonDTO.getIdVoucher()).build());
        }
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDonResponse thanhToanHoaDon(Long id, HoaDonDTO hoaDonDTO) {
        KhachHang khachHang = khachHangRepository.findById(hoaDonDTO.getIdKhachHang()).get();
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        hoaDon.setNgayThanhToan(LocalDateTime.now());
        hoaDon.setHinhThucGiaoHang(hoaDonDTO.getHinhThucGiaoHang());
        hoaDon.setHinhThucThanhToan(hoaDonDTO.getHinhThucThanhToan());
        hoaDon.setTenNguoiNhan(hoaDonDTO.getTenNguoiNhan());
        hoaDon.setSdtNguoiNhan(hoaDonDTO.getSdtNguoiNhan());
        hoaDon.setTenNguoiShip(hoaDonDTO.getTenNguoiShip());
        hoaDon.setSdtNguoiShip(hoaDonDTO.getSdtNguoiShip());
        hoaDon.setTienGiamGia(hoaDonDTO.getTienGiamGia());
        hoaDon.setTienShip(hoaDon.getTienShip());
        hoaDon.setTongThanhToan(hoaDonDTO.getTongThanhToan());
        hoaDon.setTienMat(hoaDonDTO.getTienMat());
        hoaDon.setChuyenKhoan(hoaDonDTO.getChuyenKhoan());
        hoaDon.setDiaChi(hoaDonDTO.getDiaChi());
        hoaDon.setKhachHang(khachHang);
        hoaDon.setTrangThai(hoaDonDTO.getTrangThai());
        hoaDon.setGhiChu("");
        this.hoaDonRepository.save(hoaDon);
        return hoaDonRepository.getOneById(id);
    }

    @Override
    public HoaDonResponse get(Long id) {
        return hoaDonRepository.getOneById(id);
    }

    @Override
    public HoaDonResponse getOneByMa(String ma) {
        return hoaDonRepository.getOneByMa(ma);
    }

    @Override
    public Integer updateTrangThaiThanhToan(Long id) {
        Optional<HoaDon> hoaDonOptional = hoaDonRepository.findById(id);
        if(hoaDonOptional.isPresent()){
            HoaDon hoaDon = hoaDonOptional.get();
            if(hoaDon.getTrangThai() == 0){
                hoaDon.setTrangThai(1);
                hoaDon.setChuyenKhoan(hoaDon.getTongThanhToan());
                hoaDon.setNgayThanhToan(LocalDateTime.now());
                hoaDonRepository.save(hoaDon);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public Integer updateTrangThaiGiaoHang(Long id, HoaDonDTO hoaDonDTO) {
        Optional<HoaDon> hoaDonOptional = hoaDonRepository.findById(id);
        if(hoaDonOptional.isPresent()){
            HoaDon hoaDon = hoaDonOptional.get();
            hoaDon.setTrangThaiGiaoHang(hoaDonDTO.getTrangThaiGiaoHang());
            hoaDon.setTenNguoiShip(hoaDonDTO.getTenNguoiShip());
            hoaDon.setSdtNguoiShip(hoaDonDTO.getSdtNguoiShip());
            hoaDonRepository.save(hoaDon);
            return 1;
        }
        return 0;
    }
}
