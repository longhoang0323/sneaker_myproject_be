package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.domain.User;
import be.bds.bdsbes.entities.KhachHang;
import be.bds.bdsbes.entities.TheThanhVien;
import be.bds.bdsbes.exception.ServiceException;
import be.bds.bdsbes.payload.KhachHangResponse1;
import be.bds.bdsbes.repository.KhachHangRepository;
import be.bds.bdsbes.repository.UserRepository;
import be.bds.bdsbes.service.dto.KhachHangDTO;
import be.bds.bdsbes.service.dto.TheThanhVienDTO;
import be.bds.bdsbes.service.iService.IKhachHangService;
import be.bds.bdsbes.service.mapper.KhachHangMapper;
import be.bds.bdsbes.utils.AppConstantsUtil;
import be.bds.bdsbes.utils.ServiceExceptionBuilderUtil;
import be.bds.bdsbes.utils.ValidationErrorUtil;
import be.bds.bdsbes.utils.dto.KeyValue;
import be.bds.bdsbes.utils.dto.PagedResponse;
import be.bds.bdsbes.utils.dto.ValidationErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service("khachHangServiceImpl")
@EnableScheduling
public class KhachHangServiceImpl implements IKhachHangService {

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private KhachHangMapper khachHangMapper;

    @Autowired
    private EmailService emailService;

    TheThanhVienDTO theThanhVienDTO;

    @Override
    public List<KhachHang> getList() {
        return khachHangRepository.findAll();
    }

    @Override
    public Page<KhachHang> getPage(Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        return khachHangRepository.findAll(pageable);
    }

    @Override
    public KhachHangResponse1 getOne(Long id) {
        return khachHangRepository.get(id);
    }

    @Override
    public KhachHang create(KhachHangDTO khachHangDTO) {
        Random random = new Random();
        int min = 1;
        int max = Integer.MAX_VALUE;
        int ma = random.nextInt(max - min + 1) + min;
        KhachHang khachHang = khachHangDTO.dto(new KhachHang());
        khachHang.setMa("KH" + ma);
        khachHang.setHoTen(khachHangDTO.getHoTen());
        khachHang.setCccd(khachHangDTO.getCccd());
        khachHang.setSdt(khachHangDTO.getSdt());
        khachHang.setDiaChi(khachHangDTO.getDiaChi());
        khachHang.setGhiChu("0");
        khachHang.setTheThanhVien(TheThanhVien.builder().id(Long.parseLong("1")).build());
        return khachHangRepository.save(khachHang);
    }

    @Override
    public KhachHang update(KhachHangDTO khachHangDTO, Long id) {
        Optional<KhachHang> khachHangOptional = khachHangRepository.findById(id);
        if(khachHangOptional.isPresent()){
            KhachHang khachHang = khachHangDTO.dto(khachHangOptional.get());
            khachHang.setDiaChi(khachHangDTO.getDiaChi());
            User user = userRepository.getUserByKhachHang(khachHang.getId());
            if(user != null){
                user.setSdt(khachHangDTO.getSdt());
                user.setName(khachHangDTO.getHoTen());
                this.userRepository.save(user);
            }
            return khachHangRepository.save(khachHang);
        }
        return null;
    }

    @Override
    public PagedResponse<KhachHangResponse1> getKhachHang(int page, int size) throws ServiceException {
        if (page <= 0) {
            throw ServiceExceptionBuilderUtil.newBuilder()
                    .addError(new ValidationErrorResponse("page", ValidationErrorUtil.Invalid))
                    .build();
        }

        if (size > AppConstantsUtil.MAX_PAGE_SIZE) {
            List<KeyValue> params = new ArrayList<>();
            params.add(new KeyValue("max", String.valueOf(AppConstantsUtil.MAX_PAGE_SIZE)));

            throw ServiceExceptionBuilderUtil.newBuilder()
                    .addError(new ValidationErrorResponse("pageSize", ValidationErrorUtil.Invalid, params))
                    .build();
        }

        // Retrieve all entities
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.ASC, "id");
        Page<KhachHang> entities = khachHangRepository.findAll(pageable);
        List<KhachHangResponse1> dtos = this.khachHangMapper.toDtoList(entities.getContent());

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
    public Boolean createOrUpdate(KhachHangDTO khachHangDTO) throws ServiceException{
        for(KhachHang kh: khachHangRepository.findAll()){
            if(kh.getCccd() != null){
                if(kh.getCccd().trim().equals(khachHangDTO.getCccd().trim())){
                    kh.setSdt(khachHangDTO.getSdt());
                    kh.setHoTen(khachHangDTO.getHoTen());
                    kh.setCccd(khachHangDTO.getCccd());
                    kh.setNgaySinh(khachHangDTO.getNgaySinh());
                    kh.setDiaChi(khachHangDTO.getDiaChi());
                    this.khachHangRepository.save(kh);
                    return true;
                }
            }
        }
        Random random = new Random();
        int min = 1;
        int max = Integer.MAX_VALUE;
        int ma = random.nextInt(max - min + 1) + min;
        KhachHang khachHang = khachHangDTO.dto(new KhachHang());
        khachHang.setMa("KH" + ma);
        khachHang.setHoTen(khachHangDTO.getHoTen());
        khachHang.setCccd(khachHangDTO.getCccd());
        khachHang.setSdt(khachHangDTO.getSdt());
        khachHang.setGhiChu("0");
        khachHang.setNgaySinh(khachHangDTO.getNgaySinh());
        khachHang.setTheThanhVien(TheThanhVien.builder().id(Long.parseLong("1")).build());
        this.khachHangRepository.save(khachHang);
        return true;
    }

    @Override
    public Long findIdByCCCD(String cccd) {
        return khachHangRepository.findIdByCccd(cccd);
    }

    @Override
    public KhachHangResponse1 getKhachHangbyUser(Long id){
        return khachHangRepository.getKhachHangByUser(id);
    }

    @Override
    public Boolean updateKH(KhachHangDTO khachHangDTO, Long id) {
        Long idKH = khachHangRepository.findByI(id);
        Optional<KhachHang> khachHangOptional = khachHangRepository.findById(idKH);
        KhachHang khachHang = khachHangDTO.dto(khachHangOptional.get());
        khachHang.setHoTen(khachHangDTO.getHoTen());
        khachHang.setCccd(khachHangDTO.getCccd());
        khachHang.setSdt(khachHangDTO.getSdt());
        khachHang.setGioiTinh(khachHangDTO.getGioiTinh());
        khachHang.setNgaySinh(khachHangDTO.getNgaySinh());
        khachHang.setDiaChi(khachHangDTO.getDiaChi());
        if(khachHang.getGhiChu() == null){
            khachHang.setGhiChu("0");
        }
        khachHangRepository.save(khachHang);
        return true;
    }

    @Override
    public KhachHangResponse1 getKHbyCccd(String cccd) {
        KhachHangResponse1 khachHangResponse1 = khachHangRepository.getKhachHangByCCCD(cccd);
        if(khachHangResponse1 == null){
            return null;
        } else {
            return khachHangResponse1;
        }
    }

    @Override
    public Integer updateGhiChu(String ghiChu, Long id) throws MessagingException {
        KhachHang khachHang = khachHangRepository.findById(id).get();
        if(khachHang.getGhiChu() != null && (!khachHang.getGhiChu().isEmpty() || Integer.parseInt(khachHang.getGhiChu()) > 0)){
            this.khachHangRepository.updateGhiChu(String.valueOf((Integer.parseInt(khachHang.getGhiChu()) + Integer.parseInt(ghiChu))), id);
            // send email
            // Thay địa chỉ bằng trg email hoặc địa chỉ save mail vào
            if(khachHang.getDiaChi() != null && !khachHang.getDiaChi().isEmpty()) {
                emailService.sendEmailWithPoints(khachHang.getDiaChi(), khachHang.getHoTen(), Integer.parseInt(String.valueOf((Integer.parseInt(khachHang.getGhiChu()) + Integer.parseInt(ghiChu)))));
            }
            return 1;
        }
        this.khachHangRepository.updateGhiChu(ghiChu, id);
        if(khachHang.getDiaChi() != null && !khachHang.getDiaChi().isEmpty()){
            emailService.sendEmailWithPoints(khachHang.getDiaChi(), khachHang.getHoTen(), Integer.parseInt(ghiChu));
        }
        return 1;
    }

    @Override
    public Integer updateGhiChu2(String ghiChu, Long id) throws MessagingException {
        KhachHang khachHang = khachHangRepository.findById(id).get();
        this.khachHangRepository.updateGhiChu(ghiChu, id);
        if(khachHang.getDiaChi() != null && !khachHang.getDiaChi().isEmpty()){
            emailService.sendEmailWithPoints(khachHang.getDiaChi(), khachHang.getHoTen(), Integer.parseInt(ghiChu));
        }
        return 1;
    }

    @Override
    public PagedResponse<KhachHangResponse1> getListBySearch(int page, int size, String inputSearch) throws ServiceException {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<KhachHangResponse1> entities = khachHangRepository.getListBySearch(pageable, inputSearch);
        List<KhachHangResponse1> dtos = entities.toList();
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
    public Integer sendPointstoCustomer(Long id) throws MessagingException {
        KhachHang khachHang = khachHangRepository.findById(id).get();
        if(khachHang.getDiaChi() != null && !khachHang.getDiaChi().isEmpty()){
            this.emailService.sendEmailWithPoints(khachHang.getDiaChi(), khachHang.getHoTen(), Integer.parseInt(khachHang.getGhiChu()));
        }
        return 1;
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void expireVouchers() {
          List<KhachHang> list = khachHangRepository.findAll();
        for (KhachHang khachHang : list) {
            if(khachHang.getGhiChu() != null){
                khachHang.setGhiChu(String.valueOf(Integer.parseInt(khachHang.getGhiChu())*9/10));
            }
        }
        khachHangRepository.saveAll(list);
    }
}
