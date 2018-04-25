
package backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Purchase implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue
   private Long id;

   @Column
   private Date date;

   @Column
   private String item;

   @Column
   private int amount;

   @Column
   private double price;

   @JsonIgnore
   @ManyToOne
   private Customer customer;

   public Date getDate ()
   {
      return date;
   }

   public void setDate (Date date)
   {
      this.date = date;
   }

   public String getItem ()
   {
      return item;
   }

   public void setItem (String item)
   {
      this.item = item;
   }

   public int getAmount ()
   {
      return amount;
   }

   public void setAmount (int amount)
   {
      this.amount = amount;
   }

   public double getPrice ()
   {
      return price;
   }

   public void setPrice (double price)
   {
      this.price = price;
   }

   public Customer getCustomer ()
   {
      return customer;
   }

   public void setCustomer (Customer customer)
   {
      this.customer = customer;
   }
}
