package backend.controller;

import backend.data.CustomerRepository;
import backend.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@CrossOrigin
@Controller
public class SearchController
{
  private final CustomerRepository customerRepository;

  @Autowired
   public SearchController (CustomerRepository customerRepository)
   {
      this.customerRepository = customerRepository;
   }

   @RequestMapping(value = "/search", method = RequestMethod.GET)
   @ResponseBody
   List<Customer> search (
        @RequestParam(name = "name", required = false) String name
   ) {
      final List<Customer> customers = new LinkedList<> ();

       // kein Suchbegriff angegeben - liefere alle Kunden zurück
      if (name == null || name.trim ().length () == 0) {
         customerRepository.findAll ().forEach (customers::add);
      } else {
         // Suchbegriff auf Namen der Kunden anwenden
         final String nameLowercase = name.toLowerCase ().trim ();
         final List<Customer> list = customerRepository.findByNameContainingIgnoreCase (nameLowercase);
         customers.addAll (list);
      }
      return customers;
   }
}
