package com.dvnguyen.daddysouvenir.service;

import com.dvnguyen.daddysouvenir.domain.Sale;
import com.dvnguyen.daddysouvenir.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

	private final SaleRepository saleRepository;

	public SaleService(SaleRepository saleRepository) {
		this.saleRepository = saleRepository;
	}

	public List<Sale> list() {
		return saleRepository.findAll();
	}

	public Sale getSaleById(Long id) throws Exception {
		Optional<Sale> sale = saleRepository.findById(id);

		if (sale.isPresent()) {
			return sale.get();
		} else {
			throw new Exception("No Sale record exist for given id");
		}
	}

	public Sale createOrUpdateEmployee(Sale entity) throws Exception
	{
		Optional<Sale> sale = saleRepository.findById(entity.getId());

		if(sale.isPresent())
		{
			Sale newEntity = sale.get();
			newEntity.setItem(entity.getItem());
			newEntity.setQuantity(entity.getQuantity());
			newEntity.setAmount(entity.getAmount());

			newEntity = saleRepository.save(newEntity);

			return newEntity;
		} else {
			entity = saleRepository.save(entity);

			return entity;
		}
	}

	public void deleteEmployeeById(Long id) throws Exception
	{
		Optional<Sale> employee = saleRepository.findById(id);

		if(employee.isPresent())
		{
			saleRepository.deleteById(id);
		} else {
			throw new Exception("No sale record exist for given id");
		}
	}
}
