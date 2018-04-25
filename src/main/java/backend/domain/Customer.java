package backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Customer implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue
   private Long id;

   @Column
   private Long customerId;

   @Column
   private String name;

   @OneToMany(
         mappedBy = "customer",
         //fetch = FetchType.EAGER,
         fetch = FetchType.LAZY,
         cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
   private Set<Purchase> purchases;

   public Long getCustomerId ()
   {
      return customerId;
   }

   public void setCustomerId (Long customerId)
   {
      this.customerId = customerId;
   }

   public String getName ()
   {
      return name;
   }

   public void setName (String name)
   {
      this.name = name;
   }

   public Set<Purchase> getPurchases ()
   {
      return purchases;
   }

   public void setPurchases (Set<Purchase> purchases)
   {
      this.purchases = purchases;
   }
}
