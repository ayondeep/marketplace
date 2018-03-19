package com.assessment.marketplace.service;

import com.assessment.marketplace.entities.Seller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class SellerServiceImplTest {

    @TestConfiguration
    static class SellerServiceImplTestContextConfiguration {

        @Bean
        public SellerService sellerService() {
            return new SellerServiceImpl();
        }
    }

    @Resource
    private SellerService sellerService;

    @MockBean
    private JpaRepository<Seller, Long> jpaRepository;

    @Before
    public void setUp() {
        Seller seller = new Seller();
        seller.setName("seller");
        seller.setId(0);

        Mockito.when(jpaRepository.findById(seller.getId()))
                .thenReturn(Optional.of(seller));

    }

    @Test
    public void testSeller_findById_shouldFindSeller() {
        long id = 0;
        Optional<Seller> found = sellerService.findById(id);

        Assert.assertEquals(found.get().getId(), id);
    }

    @Test
    public void testSeller_findById_shouldNotFindSeller() {
        long id = 1;
        Optional<Seller> found = sellerService.findById(id);

        Assert.assertEquals(found.isPresent(), false);
    }

    @Test
    public void testSeller_addSeller_shouldAdd() {
        Seller seller = new Seller();

        Mockito.when(jpaRepository.save(seller))
                .thenReturn(seller);

        Seller found = sellerService.add(seller);

        Assert.assertNotNull(found);
    }
}
