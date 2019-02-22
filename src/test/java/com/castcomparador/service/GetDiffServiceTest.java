package com.castcomparador.service;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.castcomparador.entity.BinaryData;
import com.castcomparador.exception.MissingBinaryDataException;
import com.castcomparador.exception.ResourceNotFoundException;
import com.castcomparador.repository.BinaryDataRepository;
import com.castcomparador.service.DiffService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GetDiffServiceTest {


    @Autowired
    public BinaryDataRepository binaryDataRepository;

    @Autowired
    public DiffService diffService;


    @Autowired
    private WebApplicationContext webApplicationContext;

    private String testData1;
    private String testData2;
    private String testData3;


    @Before
    public void setup() throws Exception {
        this.binaryDataRepository.deleteAll();

        testData1 = "AbtMVA==";
        testData2 = "ActxVA==";
        testData3 = "aaActxVA==";
    }


    @Test(expected = ResourceNotFoundException.class)
    public void ResourceNotFoundExceptionTest() {
        diffService.getDiff(1L);
    }


    @Test(expected = MissingBinaryDataException.class)
    public void MissingBinaryDataExceptionTest() {

        binaryDataRepository.save(new BinaryData(1l, testData1, ""));
        diffService.getDiff(1L);
    }

    @Test
    public void getDiffEqualTest() throws Exception {

        binaryDataRepository.save(new BinaryData(1L, testData1, testData1));
        String message = diffService.getDiff(1L);
        Assert.assertThat(message, Matchers.is("Data is equal"));
    }

    @Test
    public void getDiffOffSetTest() throws Exception {

        binaryDataRepository.save(new BinaryData(1L, testData1, testData2));
        String message = diffService.getDiff(1L);

        String offsets = "1,3";

        String dataIsEqualNotSame = String.format("Data is same size but offsets are different. Offsets:%s", offsets);

        Assert.assertThat(message, Matchers.is(dataIsEqualNotSame));
    }

    @Test
    public void getDiffSizeTest() throws Exception {

        binaryDataRepository.save(new BinaryData(1L, testData1, testData3));
        String message = diffService.getDiff(1L);
        Assert.assertThat(message, Matchers.is("Data is not equal"));
    }

}