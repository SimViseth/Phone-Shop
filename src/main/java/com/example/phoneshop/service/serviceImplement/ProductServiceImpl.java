package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.dto.product.PriceDTO;
import com.example.phoneshop.dto.product.ProductDTO;
import com.example.phoneshop.dto.product.ProductImportDTO;
import com.example.phoneshop.entity.Product;
import com.example.phoneshop.entity.ProductImportHistory;
import com.example.phoneshop.exception.ApiException;
import com.example.phoneshop.exception.ResourceNotFound;
import com.example.phoneshop.mapper.ProductMapper;
import com.example.phoneshop.repository.ProductImportHistoryRepository;
import com.example.phoneshop.repository.ProductRepository;
import com.example.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product createProduct(Product product) {
        String name = "%s %s".formatted(product.getModel().getName(), product.getColor().getName());
        product.setProductName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Product", id));
    }

    @Override
    public Product getByModelIdAndColorId(Long modelId, Long colorId) {
        return productRepository.findByModelIdAndColorId(modelId, colorId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Product with model id = %s and color id = %d are not found"));
    }

    @Override
    public void importProduct(ProductImportDTO importDTO) {
        if (importDTO.getProductId() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Product id must not be null");
        }
        Product product = getById(importDTO.getProductId());

        Integer availableUnit = product.getAvailableUnit() != null ? product.getAvailableUnit() : 0;

        product.setAvailableUnit(availableUnit + importDTO.getImportUnit());

        productRepository.save(product);

        ProductImportHistory importHistory = productMapper.toProductImportHistory(importDTO, product);
        productImportHistoryRepository.save(importHistory);
    }

    @Override
    public void setSalePrice(Long productId, BigDecimal price) {
        Product product = getById(productId);
        product.setSalePrice(price);
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public void uploadProduct(MultipartFile file) {
        /*
            0. CUSTOM SAMPLE EXCEL FILE WITH COLUMN AND SOME DATA THEN VIEW AND FOLLOW IT STEP BY STEP
            1. GET SHEET FROM EXCEL
            2. GET ROWS
            3. GET CELL
        */
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet("sheet1"); // sheet name
            Iterator<Row> rowIterator = sheet.iterator();

            rowIterator.next(); // get first one row (to skip header row of table)

            while (rowIterator.hasNext()) {

                // get one row one by one
                Row row = rowIterator.next();

                // -------------- get cell -------------------
                Cell cellModelId = row.getCell(0);
                Long modelId = (long) cellModelId.getNumericCellValue();

                Cell cellColorId = row.getCell(1);
                Long colorId = (long) cellColorId.getNumericCellValue();

                Cell cellImportPrice = row.getCell(2);
                Double importPrice = cellImportPrice.getNumericCellValue();

                Cell cellImportUnit = row.getCell(3);
                Integer importUnit = (int) cellImportUnit.getNumericCellValue();

                Cell cellImportDate = row.getCell(4);
                LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();

                Product product = getByModelIdAndColorId(modelId, colorId);

               Integer availableUnit = 0;
               if (product.getAvailableUnit() != null) {
                   availableUnit = product.getAvailableUnit();
               }

                product.setAvailableUnit(availableUnit + importUnit);

                productRepository.save(product);

                ProductImportHistory importHistory = productMapper.toProductImportHistory(importDTO, product);
                productImportHistoryRepository.save(importHistory);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
