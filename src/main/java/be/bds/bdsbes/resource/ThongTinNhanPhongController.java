package be.bds.bdsbes.resource;

import be.bds.bdsbes.service.dto.ThongTinNhanPhongDTO;
import be.bds.bdsbes.service.iService.IThongTinNhanPhongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rpc/bds/thong-tin-nhan-phong")
public class ThongTinNhanPhongController {

    @Autowired
    IThongTinNhanPhongService iThongTinNhanPhongService;

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody @Valid ThongTinNhanPhongDTO thongTinNhanPhongDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<ObjectError> listError = bindingResult.getAllErrors();
            return ResponseEntity.badRequest().body(listError);
        }
        return ResponseEntity.ok(iThongTinNhanPhongService.create(thongTinNhanPhongDTO));
    }

    @GetMapping("detail")
    public ResponseEntity<?> get(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(this.iThongTinNhanPhongService.getTTById(id));
    }

    @GetMapping("list")
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(this.iThongTinNhanPhongService.getList());
    }
}
