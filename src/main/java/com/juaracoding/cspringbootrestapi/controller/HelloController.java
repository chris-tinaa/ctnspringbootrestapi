package com.juaracoding.cspringbootrestapi.controller;

import com.juaracoding.cspringbootrestapi.configuration.OtherConfiguration;
import com.juaracoding.cspringbootrestapi.model.CalonPeserta;
import com.juaracoding.cspringbootrestapi.util.FileUtility;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping("/permisi")
    public String firstPage() {
        System.out.println("Value Flag Loging : "+ OtherConfiguration.getFlagLoging());
        return OtherConfiguration.getFlagLoging();
    }

    @PostMapping("/seleksi")
    public String seleksiCalonPeserta(@Valid @RequestBody CalonPeserta calon) {
        if (calon.getNilai() > 80 && calon.getUmur() >= 18 && calon.getUmur() <= 60) {
            return calon.getNama() + " lolos seleksi";
        } else {
            return calon.getNama() + " tidak lolos seleksi";
        }

//        calon.setNilai(100);
//        calon.setUmur(79);
//
//        Map<String,Object> mapz = new HashMap<>();
//        mapz.put("message","OK BOSS !!");
//        mapz.put("data",calon);
//        mapz.put("status","single");
//        mapz.put("waktu",new Date());
////        if (calon.getNilai() > 80
////                && calon.getUmur() >= 18
////                && calon.getUmur() <= 60) {
////            return calon.getNama() + " lolos seleksi";
////        } else {
////            return calon.getNama() + " tidak lolos seleksi";
////        }
//        return mapz;
    }

    @PostMapping("/seleksi2")
    public String seleksiCalonPeserta2(
            @RequestParam(value = "nama") String nama,
            @RequestParam(value = "umur") Integer umur,
            @RequestParam(value = "nilai") Integer nilai
    ) {

        if (nilai > 80 && umur >= 18 && umur <= 60) {
            return nama + " lolos seleksi";
        } else {
            return nama + " tidak lolos seleksi";
        }
    }

    @PostMapping("/seleksi3/{nama}/{umur}/{nilai}")
    public String seleksiCalonPeserta3(
            @PathVariable(value = "nama") String nama,
            @PathVariable(value = "umur") Integer umur,
            @PathVariable(value = "nilai") Double nilai
    ) {
        if (nilai > 80 && umur >= 18 && umur <= 60) {
            return nama + " lolos seleksi";
        } else {
            return nama + " tidak lolos seleksi";
        }
    }

    @PostMapping("/kirim-file")
    public String submitFile(@RequestParam(value = "kiriman") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "user-files/";
        FileUtility.saveFile(uploadDir, fileName, file);
        return "Berhasil mengunggah file " + fileName;
    }

}
