package com.juaracoding.cspringbootrestapi.controller;


import com.juaracoding.cspringbootrestapi.dto.BarangDTO;
import com.juaracoding.cspringbootrestapi.model.Barang;
import com.juaracoding.cspringbootrestapi.service.BarangService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/barang")
public class BarangController {
    private BarangService barangService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public BarangController(BarangService barangService) {
        this.barangService = barangService;
    }

    @GetMapping("/v1/findall")
    public ResponseEntity<Object> findAll(HttpServletRequest request)
    {
        return barangService.findAll(request);
    }

    @PostMapping("/v1/sv")
    public ResponseEntity<Object> save(@RequestBody BarangDTO barangDTO, HttpServletRequest request)
    {
        Barang barang = modelMapper.map(barangDTO, new TypeToken<Barang>() {}.getType());
        return barangService.save(barang,request);
    }
    @PutMapping("/v1/upd/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody BarangDTO barangDTO, HttpServletRequest request)
            throws Exception
    {
        Barang barang = modelMapper.map(barangDTO, new TypeToken<Barang>() {}.getType());
        return barangService.update(id,barang,request);
    }

    @DeleteMapping("/v1/del/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return barangService.delete(id,request);
    }

    @PostMapping("/v1/svb")
    public ResponseEntity<Object> saveBatch(@Valid @RequestBody List<BarangDTO> listBarangDTO,
                                            HttpServletRequest request)
    {
        List<Barang> listBarang = modelMapper.map(listBarangDTO, new TypeToken<List<Barang>>() {}.getType());
        return barangService.saveBatch(listBarang,request);
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return barangService.findById(id,request);
    }

    @GetMapping("/v1/fbp/{page}/{size}")
    public ResponseEntity<Object> findByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestParam(value = "col") String columFirst,
            @RequestParam(value = "val") String valueFirst,
            HttpServletRequest request)
    {
        return barangService.findByPage(page,size,columFirst,valueFirst,request);
    }

    @GetMapping("/v1/fabp/{page}/{size}")
    public ResponseEntity<Object> findAllByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            HttpServletRequest request)
    {
        return barangService.findAllByPage(page,size,request);
    }


}