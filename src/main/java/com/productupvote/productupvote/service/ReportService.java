package com.productupvote.productupvote.service;

import com.opencsv.CSVWriter;
import com.productupvote.productupvote.domain.OfferRepository;
import com.productupvote.productupvote.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * OfferService
 * Service class for offers.
 * Methods:
 * save.
 *
 * @author U1554969 Jakub Chruslicki
 */
@Service
public class ReportService {

    @Autowired
    private ProductService productService;

    /**
     * This method gets the list of products
     *
     * @return
     */
    public List<List<String>> getTopProducts() {
        List<List<String>> topList = new ArrayList<>();
        List<String> headings = new ArrayList<>();
        headings.add("Id");
        headings.add("Name");
        headings.add("Description");
        headings.add("Username");
        headings.add("UpVotes");
        headings.add("Owner Approved");
        headings.add("Admin Approved");
        headings.add("Date Approved");
        headings.add("Date Submitted");
        headings.add("Get Image");
        topList.add(headings);
        List<Product> products = productService.approvedProducts("yes", true, "", "top", "desc");
        for (Product product : products) {
            List<String> productList = new ArrayList<>();
            productList.add(String.valueOf(product.getId()));
            productList.add(product.getName());
            productList.add(product.getDescription());
            productList.add(product.getUser().getUsername());
            productList.add(String.valueOf(product.getUpVotes()));
            productList.add(String.valueOf(product.getUserApproved()));
            productList.add(product.getApproved());
            productList.add(String.valueOf(product.getDateApproved()));
            productList.add(String.valueOf(product.getDateSubmitted()));
            productList.add(product.getImage());
            topList.add(productList);
        }
        return topList;
    }

    /**
     * This method saves file to local server storage
     *
     * @param list list to save in the CSV file.
     * @return directory URL.
     */
    public String saveFile(List<List<String>> list) {
        Date date = new Date();
        File file = new File("./getTopProducts" + java.time.LocalDate.now()
                + ".csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter toDownloadFile = new FileWriter(file);

            // create CSVWriter with ',' as separator
            CSVWriter writer = new CSVWriter(toDownloadFile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            // create a List which contains String array
            List<String[]> data = new ArrayList<String[]>();
            for (List<String> l : list) {
                data.add(new String[]{l.get(0), l.get(1), l.get(2), l.get(3), l.get(4), l.get(5), l.get(6), l.get(7), l.get(8)});
            }
            writer.writeAll(data);
            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return file.getPath();
    }

    /**
     * This method starts up download feature.
     *
     * @param filePath path to the download file.
     * @return link to requesting file.
     * @throws IOException
     */
    public ResponseEntity<InputStreamResource> downloadCSV(String filePath) throws IOException {
        File file = new File(filePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
                .body(resource);
    }
}
