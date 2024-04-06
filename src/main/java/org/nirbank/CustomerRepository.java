package org.nirbank;

import java.util.List;

import org.nirbank.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	public List<Customer> findByEmail(String email);

}
