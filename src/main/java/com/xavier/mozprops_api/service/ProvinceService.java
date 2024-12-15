package com.xavier.mozprops_api.service;

import java.util.List;

import com.xavier.mozprops_api.dto.ProvinceRequest;
import com.xavier.mozprops_api.dto.ProvinceResponse;

import jakarta.validation.Valid;

public interface ProvinceService {
    ProvinceRequest createProvince(@Valid ProvinceRequest provinceRequest);
    List<ProvinceResponse> getAllProvinces();
    ProvinceResponse getProvinceById(Long id);
    ProvinceResponse updateProvince(Long id, @Valid ProvinceRequest provinceRequest);
    void deleteProvince(Long id);
}
