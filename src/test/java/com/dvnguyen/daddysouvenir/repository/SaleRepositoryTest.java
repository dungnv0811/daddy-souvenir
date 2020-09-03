package com.dvnguyen.daddysouvenir.repository;

import com.dvnguyen.daddysouvenir.domain.Sale;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
class SaleRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private SaleRepository saleRepository;

	@Test
	public void whenFindById_thenReturnSale() {
		// given
		Sale sale = new Sale(1, "Hat", 1, 20000);
		entityManager.persist(sale);
		entityManager.flush();

		// when
		Optional<Sale> found = saleRepository.findById(sale.getId());

		// then
		assertTrue(() -> found.isPresent()
				&& found.get().getItem().equals(sale.getItem()));
	}

}