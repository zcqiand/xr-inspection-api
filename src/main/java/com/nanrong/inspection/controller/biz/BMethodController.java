package com.nanrong.inspection.controller.biz;

import com.nanrong.inspection.domain.biz.BMethod;
import com.nanrong.inspection.dto.biz.BaseMethodDTO;
import com.nanrong.inspection.service.biz.BMethodService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biz/b-method")
@Tag(name = "基础方法管理", description = "基础方法管理接口")
public class BMethodController {

    private final BMethodService methodService;

    @Autowired
    public BMethodController(BMethodService methodService) {
        this.methodService = methodService;
    }

    @PostMapping
    public ResponseEntity<BMethod> createMethod(@RequestBody BaseMethodDTO methodDTO) {
        BMethod method = methodService.createMethod(methodDTO);
        return ResponseEntity.ok(method);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BMethod> updateMethod(@PathVariable Long id, @RequestBody BaseMethodDTO methodDTO) {
        BMethod method = methodService.updateMethod(id, methodDTO);
        return ResponseEntity.ok(method);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMethod(@PathVariable Long id) {
        methodService.deleteMethod(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BMethod>> getAllMethods() {
        List<BMethod> methods = methodService.findAllMethods();
        return ResponseEntity.ok(methods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BMethod> getMethodById(@PathVariable Long id) {
        BMethod method = methodService.findMethodById(id);
        return ResponseEntity.ok(method);
    }
}