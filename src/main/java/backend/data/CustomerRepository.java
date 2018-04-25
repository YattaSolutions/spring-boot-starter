package backend.data;

import backend.domain.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>
{
   Customer findByCustomerId(@Param("customerId") Long customerId);

   List<Customer> findByNameContainingIgnoreCase (@Param("name") String name);
}
