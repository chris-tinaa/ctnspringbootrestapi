package com.juaracoding.cspringbootrestapi.controller;

import com.juaracoding.cspringbootrestapi.model.Peserta;
import com.juaracoding.cspringbootrestapi.repo.PesertaRepo;
import com.juaracoding.cspringbootrestapi.service.PesertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/peserta")
public class PesertaController {
    @Autowired
    PesertaRepo pesertaRepo;

    @Autowired
    PesertaService pesertaService;

    @GetMapping("/")
    public List<Peserta> getAllData() {
        List<Peserta> daftarPeserta = pesertaRepo.findAll();
        return daftarPeserta;
    }

    @PostMapping("/")
    public String postData(@RequestBody Peserta peserta) {
//        pesertaRepo.save(peserta);
        pesertaService.save(peserta);
        return peserta.getNama() + " berhasil ditambahkan";
    }

    @GetMapping("/search-peserta-by-id")
    public Map<String,Object> searchPesertaById(@RequestParam(value = "id") Integer id) {
//        Optional<Peserta> hasil = pesertaRepo.findById(id);
//        return hasil.get();
        Optional<Peserta> hasil = pesertaRepo.findById(id);
        Map<String,Object> m = new HashMap<>();
        if(hasil.isEmpty())
        {
            m.put("message","Data tidak ada");
            return m;
        }
        m.put("data",hasil);
        m.put("message","Data ditemukan");
        return m;
    }

    @GetMapping("/search-peserta-by-nama")
    public Map<String,Object> searchPesertaByName(@RequestParam(value = "nama") String nama) {
        List<Peserta> hasil = pesertaRepo.findByNama(nama);
        Map<String,Object> m = new HashMap<>();
        if(hasil.isEmpty())
        {
            m.put("message","Data tidak ada");
            return m;
        }
        m.put("data",hasil);
        m.put("message","Data ditemukan");
        return m;
    }

    @GetMapping("/search-peserta-by-nama-contains-batch")
    public Map<String,Object> findByNamaContainsAndBatch(
            @RequestParam(value = "nama") String nama,
            @RequestParam(value = "batch") String batch
    ) {
        List<Peserta> hasil = pesertaRepo.findByNamaContainsAndBatch(nama,batch);
        Map<String,Object> m = new HashMap<>();
        if(hasil.isEmpty())
        {
            m.put("message","Data tidak ada");
            return m;
        }
        m.put("data",hasil);
        m.put("message","Data ditemukan");
        return m;
    }


    @GetMapping("/search-peserta-by-nama-sw")
    public Map<String,Object> searchPesertaByNameSW(
            @RequestParam(value = "nama") String nama
    ) {
        List<Peserta> hasil = pesertaRepo.findByNamaStartsWith(nama);
        Map<String,Object> m = new HashMap<>();
        if(hasil.isEmpty())
        {
            m.put("message","Data tidak ada");
            return m;
        }
        m.put("data",hasil);
        m.put("message","Data ditemukan");
        return m;
    }

    @GetMapping("/search-peserta-by-nama-ew")
    public Map<String,Object> searchPesertaByNameEW(
            @RequestParam(value = "nama") String nama
    ) {
        List<Peserta> hasil = pesertaRepo.findByNamaEndsWith(nama);
        Map<String,Object> m = new HashMap<>();
        if(hasil.isEmpty())
        {
            m.put("message","Data tidak ada");
            return m;
        }
        m.put("data",hasil);
        m.put("message","Data ditemukan");
        return m;
    }


    @GetMapping("/search-peserta-by-nama-contains")
    public Map<String,Object> searchPesertaByNameContains(
            @RequestParam(value = "nama") String nama
    ) {
        List<Peserta> hasil = pesertaRepo.findByNamaContains(nama);
        Map<String,Object> m = new HashMap<>();
        if(hasil.isEmpty())
        {
            m.put("message","Data tidak ada");
            return m;
        }
        m.put("data",hasil);
        m.put("message","Data ditemukan");
        return m;
    }


}
