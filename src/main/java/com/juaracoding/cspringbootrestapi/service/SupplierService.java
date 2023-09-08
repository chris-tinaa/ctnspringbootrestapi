package com.juaracoding.cspringbootrestapi.service;

import com.juaracoding.cspringbootrestapi.configuration.OtherConfiguration;
import com.juaracoding.cspringbootrestapi.core.IService;
import com.juaracoding.cspringbootrestapi.dto.SupplierDTO;
import com.juaracoding.cspringbootrestapi.handler.RequestCapture;
import com.juaracoding.cspringbootrestapi.handler.ResponseHandler;
import com.juaracoding.cspringbootrestapi.model.Supplier;
import com.juaracoding.cspringbootrestapi.repo.LogRequestRepo;
import com.juaracoding.cspringbootrestapi.repo.SupplierRepo;
import com.juaracoding.cspringbootrestapi.util.CsvReader;
import com.juaracoding.cspringbootrestapi.util.LogTable;
import com.juaracoding.cspringbootrestapi.util.LoggingFile;
import com.juaracoding.cspringbootrestapi.util.TransformDataPaging;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class SupplierService  implements IService<Supplier> {
    private SupplierRepo supplierRepo;

    private String [] strExceptionArr = new String[2];
    private TransformDataPaging transformDataPaging = new TransformDataPaging();
    private Map<String,Object> mapz = new HashMap<>();
    @Autowired
    private LogRequestRepo logRequestRepo;
    private ModelMapper modelMapper;

    @Autowired
    public SupplierService(SupplierRepo supplierRepo, ModelMapper modelMapper) {
        strExceptionArr[0] = "SupplierService";
        this.supplierRepo = supplierRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<Object> save(Supplier supplier, HttpServletRequest request) {
        if(supplier==null)
        {
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV002001",//errorCode Fail Validation modul-code 001 sequence 001 range 001 - 010
                    request
            );
        }

        try{
            supplierRepo.save(supplier);
        }catch (Exception e)
        {
            strExceptionArr[1] = "save(Supplier supplier, HttpServletRequest request)  --- LINE 59 \n"+ RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data Gagal Disimpan",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002001",//errorCode Fail Error modul-code 001 sequence 001 range 001 - 010
                    request
            );
        }

        return new ResponseHandler().generateResponse(
                "Data Berhasil Disimpan",//message
                HttpStatus.CREATED,//httpstatus created
                null,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> update(Long id, Supplier supplier, HttpServletRequest request) {
        Optional<Supplier> optionalSupplier;
        Supplier supplierTrans;
        Boolean isValid = true;

        try{
            optionalSupplier =  supplierRepo.findById(id);

            if(optionalSupplier.isEmpty())
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Valid",//message
                        HttpStatus.BAD_REQUEST,//httpstatus
                        null,//object
                        "FV002011",//errorCode Fail Validation modul-code 001 sequence 001 range 011 - 020
                        request
                );
            }

            supplierTrans = optionalSupplier.get();
            //UNTUK METHOD PATCH
            supplierTrans.setAlamatSupplier(supplier.getAlamatSupplier());
            supplierTrans.setNamaSupplier(supplier.getNamaSupplier());
            supplierTrans.setListBarang(supplier.getListBarang());
            supplierTrans.setModifiedBy(1L);
            supplierTrans.setModifiedDate(new Date());

        }
        catch (Exception e)
        {
            isValid = false;
            strExceptionArr[1] = "update(Long id, Barang barang, HttpServletRequest request) --- LINE 119 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV002011",//errorCode Fail Validation modul-code 001 sequence 001 range 011 - 020
                    request
            );
        }

        return new ResponseHandler().generateResponse(
                "Data Berhasil Diubah",//message
                HttpStatus.CREATED,//httpstatus seharusnya no content 204 (permintaan berhasil tapi tidak ada content untuk dikirim dalam response)
                null,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        Optional<Supplier> supplier =  supplierRepo.findById(id);

        if(supplier.isEmpty())
        {
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV002021",//errorCode Fail Validation modul-code 001 sequence 001 range 021 - 030
                    request
            );
        }

        try{
            supplierRepo.deleteById(id);
        }catch (Exception e)
        {
            strExceptionArr[1] = "delete(Long id, HttpServletRequest request) --- LINE 164 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data Gagal Dihapus",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002021",//errorCode Fail Error modul-code 001 sequence 001 range 021 - 030
                    request
            );
        }
        return new ResponseHandler().generateResponse(
                "Data Berhasil Dihapus",//message
                HttpStatus.CREATED,//httpstatus seharusnya no content 204 (permintaan berhasil tapi tidak ada content untuk dikirim dalam response)
                null,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );    }

    @Override
    public ResponseEntity<Object> saveBatch(List<Supplier> lt, HttpServletRequest request) {
        if(lt.isEmpty())
        {
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV002031",//errorCode Fail Validation modul-code 001 sequence 001 range 031 - 040
                    request
            );
        }

        try{
            supplierRepo.saveAll(lt);
        }catch (Exception e)
        {
            strExceptionArr[1] = "saveBatch(List<Barang> lt, HttpServletRequest request) --- LINE 207 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data Gagal Disimpan",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002031",//errorCode Fail Error modul-code 001 sequence 001 range 031 - 040
                    request
            );
        }

        return new ResponseHandler().generateResponse(
                "Data Berhasil Disimpan",//message
                HttpStatus.CREATED,//httpstatus created
                null,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Supplier> supplier ;
        SupplierDTO supplierDTO;
        try{
            supplier  = supplierRepo.findById(id);//select barang dari db
            if(supplier==null)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV002041",//errorCode Fail Validation modul-code 001 sequence 001 range 041 - 050
                        request
                );
            }

            supplierDTO = modelMapper.map(supplier, new TypeToken<SupplierDTO>() {}.getType());

        }catch (Exception e)
        {
            strExceptionArr[1] = "findById(Long id, HttpServletRequest request) --- LINE 241 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002041",//errorCode Fail Validation modul-code 001 sequence 001 range 041 - 050
                    request
            );
        }


        return new ResponseHandler().generateResponse(
                "Data Ditemukan",//message
                HttpStatus.OK,//httpstatus OK
                supplierDTO,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );    }

    @Override
    public ResponseEntity<Object> findByPage(Integer page,
                                             Integer size, String columFirst, String valueFirst, HttpServletRequest request) {
        Page<Supplier> pageSupplier;
        List<SupplierDTO> listSupplierDTO;
        try
        {
            Pageable pageable = PageRequest.of(page,size, Sort.by("idSupplier"));
            if(columFirst.equals("nama"))
            {
                pageSupplier = supplierRepo.findByNamaSupplierContains(pageable,valueFirst);
            } else {
                pageSupplier = supplierRepo.findAll(pageable);
            }

            listSupplierDTO = modelMapper.map(pageSupplier.getContent(), new TypeToken<List<SupplierDTO>>() {}.getType());
            int dataSize = pageSupplier.getContent().size();

            if(dataSize==0)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV002051",//errorCode Fail Validation modul-code 001 sequence 001 range 051 - 060
                        request
                );
            }

        }catch (Exception e)
        {
            strExceptionArr[1] = "findByPage(Integer page, Integer size, String columFirst, String valueFirst, HttpServletRequest request) --- LINE 304 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002051",//errorCode Fail Validation modul-code 001 sequence 001 range 051 - 060
                    request
            );
        }
        return new ResponseHandler().generateResponse(
                "Data Ditemukan",//message
                HttpStatus.OK,//httpstatus OK
                transformDataPaging.mapDataPaging(mapz,pageSupplier,listSupplierDTO),//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );    }

    @Override
    public ResponseEntity<Object> findAllByPage(Integer page,
                                                Integer size,
                                                HttpServletRequest request) {
        Page<Supplier> pageSupplier;
        List<SupplierDTO> listSupplierDTO;
        try
        {
            Pageable pageable = PageRequest.of(page,size, Sort.by("idSupplier"));
            pageSupplier = supplierRepo.findAll(pageable);
            int dataSize = pageSupplier.getContent().size();
            if(dataSize==0)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV002061",//errorCode Fail Validation modul-code 001 sequence 001 range 061 - 070
                        request
                );
            }
            listSupplierDTO = modelMapper.map(pageSupplier.getContent(), new TypeToken<List<SupplierDTO>>() {}.getType());


        }catch (Exception e)
        {
            strExceptionArr[1] = "findAllByPage(Integer page, Integer size, HttpServletRequest request) --- LINE 346 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002061",//errorCode Fail Validation modul-code 001 sequence 001 range 061 - 070
                    request
            );
        }
        return new ResponseHandler().generateResponse(
                "Data Ditemukan",//message
                HttpStatus.OK,//httpstatus OK
                transformDataPaging.mapDataPaging(mapz,pageSupplier,listSupplierDTO),//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        List<Supplier> listSupplier;
        try{
            listSupplier = supplierRepo.findAll();
            if(listSupplier.size()==0)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV002071",//errorCode Fail Validation modul-code 001 sequence 001 range 071 - 080
                        request
                );
            }
        }catch (Exception e)
        {
            strExceptionArr[1] = " findAll(HttpServletRequest request) --- LINE 382 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002071",//errorCode Fail Validation modul-code 001 sequence 001 range 071 - 080
                    request
            );
        }

        return new ResponseHandler().generateResponse(
                "Data Ditemukan",//message
                HttpStatus.OK,//httpstatus OK
                listSupplier,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );    }

    @Override
    public ResponseEntity<Object> dataToExport(MultipartFile multipartFile, HttpServletRequest request) {
        try{

            if(!CsvReader.isCsv(multipartFile))
            {
                return new ResponseHandler().generateResponse(
                        "Tipe File tidak Valid",//message
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE,//httpstatus
                        null,//object
                        "FV002081",//errorCode Fail Validation modul-code 001 sequence 001 range 081 - 090
                        request
                );
            }

            List<Supplier> listSupplier = CsvReader.csvToSupplier(multipartFile.getInputStream());
            if(listSupplier.size()==0)
            {
                return new ResponseHandler().generateResponse(
                        "Content tidak valid",//message
                        HttpStatus.BAD_REQUEST,//httpstatus
                        null,//object
                        "FV002082",//errorCode Fail Validation modul-code 001 sequence 001 range 081 - 090
                        request
                );
            }

            supplierRepo.saveAll(listSupplier);

        }catch (Exception e)
        {
            strExceptionArr[1] = "dataToExport(MultipartFile multipartFile, HttpServletRequest request) --- LINE 433 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002081",//errorCode Fail Validation modul-code 001 sequence 001 range 081 - 090
                    request
            );
        }

        return new ResponseHandler().generateResponse(
                "Data Berhasil di Unggah",//message
                HttpStatus.CREATED,//httpstatus OK
                null,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }
}
