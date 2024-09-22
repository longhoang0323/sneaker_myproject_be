package be.bds.bdsbes.service.mapper;

import be.bds.bdsbes.entities.DatPhong;
import be.bds.bdsbes.payload.DatPhongMapping;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DatPhongMapper2 extends EntityMapper<DatPhongMapping, DatPhong>{

    @Mapping(target = "maKhachHang", source = "khachHang.ma")
    @Mapping(target = "hoTen", source = "khachHang.hoTen")
    @Mapping(target = "sdt", source = "khachHang.sdt")
    @Mapping(target = "tenPhong", source = "phong.ma")
    @Mapping(target = "tenLoaiPhong", source = "phong.loaiPhong.tenLoaiPhong")
    @Mapping(target = "giaPhong", source = "phong.loaiPhong.giaTheoNgay")
    @Mapping(target = "idHoaDon", source = "hoaDon.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DatPhongMapping toDto(DatPhong datPhong);
}
