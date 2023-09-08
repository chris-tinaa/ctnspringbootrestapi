package com.juaracoding.cspringbootrestapi.controller;

import com.juaracoding.cspringbootrestapi.dto.SupplierDTO;
import com.juaracoding.cspringbootrestapi.model.Supplier;
import com.juaracoding.cspringbootrestapi.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    private ModelMapper modelMapper;

    private SupplierService supplierService;

    @Autowired
    public SupplierController(ModelMapper modelMapper, SupplierService supplierService) {
        this.modelMapper = modelMapper;
        this.supplierService = supplierService;
    }

    @GetMapping("/v1/findall")
    public ResponseEntity<Object> findAll(HttpServletRequest request)
    {
        return supplierService.findAll(request);
    }

    @PostMapping("/v1/sv")
    public ResponseEntity<Object> save(@Valid @RequestBody SupplierDTO supplierDTO, HttpServletRequest request)
    {
        Supplier supplier = modelMapper.map(supplierDTO, new TypeToken<Supplier>() {}.getType());
        return supplierService.save(supplier,request);
    }

    @DeleteMapping("/v1/del/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return supplierService.delete(id,request);
    }

    @PostMapping("/v1/svb")
    public ResponseEntity<Object> saveBatch(@Valid @RequestBody List<SupplierDTO> listSupplierDTO,
                                            HttpServletRequest request)
    {
        List<Supplier> listSupplier = modelMapper.map(listSupplierDTO, new TypeToken<List<Supplier>>() {}.getType());
        return supplierService.saveBatch(listSupplier,request);
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return supplierService.findById(id,request);
    }

    @GetMapping("/v1/fbp/{page}/{size}")
    public ResponseEntity<Object> findByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestParam(value = "col") String columFirst,
            @RequestParam(value = "val") String valueFirst,
            HttpServletRequest request)
    {
        return supplierService.findByPage(page,size,columFirst,valueFirst,request);
    }

    @PutMapping("/v1/upd/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody SupplierDTO supplierDTO, HttpServletRequest request)
            throws Exception
    {
        Supplier supplier = modelMapper.map(supplierDTO, new TypeToken<Supplier>() {}.getType());
        return supplierService.update(id,supplier,request);
    }

    @GetMapping("/v1/fabp/{page}/{size}")
    public ResponseEntity<Object> findAllByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            HttpServletRequest request)
    {
        return supplierService.findAllByPage(page,size,request);
    }

}