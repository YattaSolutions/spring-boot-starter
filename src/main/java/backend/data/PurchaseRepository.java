package backend.data;

import backend.domain.Purchase;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Long>
{
   List<Purchase> findByPriceGreaterThan (@Param("price") double price);
}
