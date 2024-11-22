package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.domain.User;
import be.bds.bdsbes.entities.KhachHang;
import be.bds.bdsbes.exception.ServiceException;
import be.bds.bdsbes.payload.KhachHangResponse1;
import be.bds.bdsbes.repository.KhachHangRepository;
import be.bds.bdsbes.repository.UserRepository;
import be.bds.bdsbes.service.dto.KhachHangDTO;
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


    @Override
    public List<KhachHang> getList() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHangResponse1 getOne(Long id) {
        return khachHangRepository.get(id);
    }

    @Override
    public KhachHangResponse1 getKhachBanLe(int trangThai) {
        return khachHangRepository.getKhachBanLe(trangThai);
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
        khachHang.setSdt(khachHangDTO.getSdt());
        khachHang.setDiaChi(khachHangDTO.getDiaChi());
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
        Page<KhachHangResponse1> entities = khachHangRepository.getAll(pageable);
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
    public KhachHangResponse1 getKhachHangbyUser(Long id){
        return khachHangRepository.getKhachHangByUser(id);
    }

    @Override
    public Boolean updateKH(KhachHangDTO khachHangDTO, Long id) {
        Long idKH = khachHangRepository.findByI(id);
        Optional<KhachHang> khachHangOptional = khachHangRepository.findById(idKH);
        KhachHang khachHang = khachHangDTO.dto(khachHangOptional.get());
        khachHang.setHoTen(khachHangDTO.getHoTen());
        khachHang.setSdt(khachHangDTO.getSdt());
        khachHang.setGioiTinh(khachHangDTO.getGioiTinh());
        khachHang.setNgaySinh(khachHangDTO.getNgaySinh());
        khachHang.setDiaChi(khachHangDTO.getDiaChi());
        khachHangRepository.save(khachHang);
        return true;
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

    @Scheduled(cron = "0 0 0 1 * ?")
    public void expireVouchers() {

    }
}
