package com.juaracoding.cspringbootrestapi.util;


import com.juaracoding.cspringbootrestapi.model.Barang;
import com.juaracoding.cspringbootrestapi.model.KategoriBarang;
import com.juaracoding.cspringbootrestapi.model.Supplier;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static boolean isCsv(MultipartFile multipartFile)
    {
        if(!"text/csv".equals(multipartFile.getContentType()))
        {
            return false;
        }
        return true;
    }

    public static List<KategoriBarang> csvToKategoriBarangData(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().
                        withTrim()
        );
        List<KategoriBarang> listKategoriBarang = new ArrayList<KategoriBarang>();
        try {

            Iterable<CSVRecord> iterRecords = csvParser.getRecords();
            KategoriBarang kategoriBarang;
            for (CSVRecord record : iterRecords) {
                kategoriBarang = new KategoriBarang();
                kategoriBarang.setNamaKategoriBarang(record.get("NamaKategoriBarang"));
                listKategoriBarang.add(kategoriBarang);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {

            if (!csvParser.isClosed()) {
                csvParser.close();
            }
            return listKategoriBarang;
        }
    }

    public static List<Barang> csvToKategoriBarang(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().
                        withTrim()
        );
        List<Barang> listBarang = new ArrayList<Barang>();
        try {

            Iterable<CSVRecord> iterRecords = csvParser.getRecords();
            Barang barang;
            for (CSVRecord record : iterRecords) {
                barang = new Barang();
                barang.setNamaBarang(record.get("namaBarang"));
                barang.setDeskripsi(record.get("deskripsi"));
                listBarang.add(barang);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {

            if (!csvParser.isClosed()) {
                csvParser.close();
            }
            return listBarang;
        }
    }

    public static List<Supplier> csvToSupplier(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().
                        withTrim()
        );
        List<Supplier> listSupplier = new ArrayList<>();
        try {

            Iterable<CSVRecord> iterRecords = csvParser.getRecords();
            Supplier supplier;
            for (CSVRecord record : iterRecords) {
                supplier = new Supplier();
                supplier.setNamaSupplier(record.get("namaSupplier"));
//                supplier.setDeskripsi(record.get("deskripsi"));
                listSupplier.add(supplier);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {

            if (!csvParser.isClosed()) {
                csvParser.close();
            }
            return listSupplier;
        }
    }
}