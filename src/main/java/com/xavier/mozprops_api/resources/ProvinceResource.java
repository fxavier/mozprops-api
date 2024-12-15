package com.xavier.mozprops_api.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xavier.mozprops_api.dto.ProvinceRequest;
import com.xavier.mozprops_api.dto.ProvinceResponse;
import com.xavier.mozprops_api.service.ProvinceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/provinces")
public class ProvinceResource {

    private final ProvinceService provinceService;

    @PostMapping
    public ResponseEntity<ProvinceRequest> createProvince(@Valid @RequestBody ProvinceRequest provinceRequest) {
        ProvinceRequest createdProvince = provinceService.createProvince(provinceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProvince);
    }

    @GetMapping
    public ResponseEntity<List<ProvinceResponse>> getAllProvinces() {
        return ResponseEntity.ok(provinceService.getAllProvinces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinceResponse> getProvinceById(@PathVariable Long id) {
        return ResponseEntity.ok(provinceService.getProvinceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProvinceResponse> updateProvince(@PathVariable Long id, @Valid @RequestBody ProvinceRequest provinceRequest) {
        return ResponseEntity.ok(provinceService.updateProvince(id, provinceRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvince(@PathVariable Long id) {
        provinceService.deleteProvince(id);
        return ResponseEntity.noContent().build();
    }
}
