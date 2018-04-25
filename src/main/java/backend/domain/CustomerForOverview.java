package backend.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "overview", types = Customer.class)
public interface CustomerForOverview
{
   Long getCustomerId ();

   String getName ();

   @Value("#{target.purchases.size()}")
   Integer getNumberOfPurchases ();
}
