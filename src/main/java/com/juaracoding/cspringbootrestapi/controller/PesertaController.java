package com.juaracoding.cspringbootrestapi.controller;

import com.juaracoding.cspringbootrestapi.model.Peserta;
import com.juaracoding.cspringbootrestapi.repo.PesertaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peserta")
public class PesertaController {
    @Autowired
    PesertaRepo pesertaRepo;

    @GetMapping("/")
    public List<Peserta> getAllData() {
        List<Peserta> daftarPeserta = pesertaRepo.findAll();
        return daftarPeserta;
    }

    @PostMapping("/")
    public String postData(@RequestBody Peserta peserta) {
        pesertaRepo.save(peserta);
        return peserta.getNama() + " berhasil ditambahkan";
    }


}
