package backend;

import backend.data.CustomerRepository;
import backend.domain.Customer;
import backend.domain.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

/**
 * By implementing ApplicationRunner, this component will be run
 * as soon as the Spring application has been initialized.
 */
@Component
public class SampleData implements ApplicationRunner
{
   private static final int MAX_PRICE = 1000;
   private static final int MAX_AMOUNT = 10;

   private final CustomerRepository customerRepository;
   private final Random random = new Random ();

   /**
    * A few random things, generated via randomlists.com.
    */
   private final String[] items = new String[] {
         "blanket", "candy wrapper", "cell phone", "cinder block", "clay pot", "couch", "drill press", "fake flowers",
         "glasses", "helmet", "keyboard", "lamp", "newspaper", "paper", "photo album", "piano", "rubber band", "sand paper",
         "scotch tape", "shovel", "sketch pad", "soy sauce packet", "stop sign", "sun glasses", "tire swing", "tomatoes", "washing machine"
   };

   /**
    * A few random sample names, generated via fakenamegenerator.com.
    */
   private final String[] names = new String[] {
         "Kaja de Munnik", "Felisardo Blanco", "Alijja Arko", "Doriana Teraž", "Lowell David", "Karl Drago Predalic", "Garland Descoteaux", "Filippa Gunnarsson", "Assunta Piazza", "Catriona Fleming", "Selma Eriksen", "Yonas Mustafa", "Chovka Bataev",
         "Jorja Hamilton", "Christine Vadnais", "Klaudie Hrachovcová", "Luce Fallaci", "Celestyna Czerwinska", "Kenneth Jackson", "Rebeca Melo", "Deti Sultanovich", "Vicuska Bakó", "Philipp Egger", "Olivier Boutot", "Michael Meier", "Milo Ferguson",
         "Ariën Butt", "Maurelle Laframboise", "Cher Auberjonois", "Ada Alvær", "Reinout Markerink", "Fayzul Melikov", "Karolina Bala", "Hyiab Mewael", "Corey Allan", "Jano Savicic", "Patricia Perez", "Yegor Mishin", "Villads Mortensen",
         "Iven Bordeleau", "Giusto Enríquez", "Kaj Heikkinen", "Václav Pokorný", "Laura Costa", "Mason Walker", "Kakeru Yamamura", "Petr Bacovský", "Alicia Walton", "Petr Svoboda", "Sedanur den Drijver", "Marcus Lindholm", "Hrvoja Kovacevic",
         "Omar Robertson", "Caroline Lauritsen", "Jonne Määttä", "Edil Timayev", "Maslin Bler", "Susan Higgins", "Szczeosny Majewski", "Cencio Miranda", "Vedran Bosanac", "Magomed Sultygov", "Valentina Bilic", "John Carr", "Geppetto Girón",
         "Ian Bezrukov", "Hedda Johnsson", "Marjon van Beveren", "Machiko Izumi", "Leon Winter", "Jasmine Pugh", "Suljo Musoski", "Fideli Johansson", "Nurbika Sheripov", "Cornelius Izmaylov", "Charles Ahmed", "Siiri Jussilainen", "Claudia Eichel",
         "Timmo Postma", "Boguslawa Sobczak", "Kinuka Numata", "Anna Petersen", "Sauli Laukkanen", "Jonny Ekström", "Cino Napolitano", "Adrienne Forest", "Rosamonde Artois", "Ursula Möller", "Gjurgjica Fuhrmann", "Karla Kristiansen", "Makka Musliyevich",
         "Evelyn Zubareva", "Shkurta Škerl", "Daisy Parkes", "Zágon Hoffman", "Abdul Moore", "Oskar Nowak", "Fujiko Tashima", "Kaitlyn Laing", "Line Bak", "Tearlach Bizier", "Musa Batukayev", "Millard Lessard", "Zuzana Sladká", "Bradley Nicholson",
         "Ida Drange", "Cyrus Koltsova", "Naim Maldonado", "Paula Richards", "Lantos Berki", "Ruvayda Kadyrov", "Khoza Batukayev", "Emma Malde", "Anne Halvorsen", "Doris Milic", "Khasbulat Korgay", "Georg Skoglund", "Malou Månsson", "Evelyn Cavalcanti",
         "Xenia Carrillo", "Natan Bruce", "Anna Yáñez", "Mohammad Rushisvili", "David Frandsen", "Jens Larsen", "Linette Thibault", "Ensio Jansson", "Gracie Brooks", "Fabijan Marinovic", "Malin Bakketun", "Kai Carnarvon", "Karin Schiffer", "Phoebe Little",
         "Patrick Wright", "Hannah Bruton", "Pascal Boncoeur", "Rayan Blom", "Susumu Ikegami", "Huba Hegyi", "Taija Lindholm-Jansson", "Quinzio Marino", "Gilda Napolitani", "Vilma Halland", "Rachel Casanova", "Taylor Hartley", "Ljubica Ceh", "Logan Vassilieff",
         "Karolien Meere", "Sjon Heijmans", "Marina Azevedo", "Aranka Lôrinc", "Antica Vozelj", "Mirel Taseski", "Nikola Duffková", "Kan Kumagai", "Shayla Drenth", "Ilya Blokhin", "Linka Kállay", "Corbin Koerts", "Emma Holmberg", "Caspian Stokland",
         "Ivan Antonín", "Monique Dumont", "Seppo Koho", "Nicholas Šetoric", "Oto Osovniker", "Miroslav Stoklásek", "Maya Gaarder", "Yoeri Nieman", "Ingeborg Olsen", "Nancy Guerrero", "Maryam Masaev", "Niamh Wall", "Miniya Nasih", "Moricz Bencik",
         "Alessandro Nyman", "Robert Blake", "Motoko Baba", "Rikard Illés", "Sara Nasih", "Erno Kunstelj", "Panu Savolainen", "Tim Theissen", "Mike Kristiansen", "Annie Bell", "Ruby Oliver", "Murrin Craig", "Brodie Broadbent", "Stefania Pinto", "Lisa Lockhart",
         "Orígenes Franco", "Jesse Kettunen", "Victor Bårdsen", "Remie Blankestijn", "Abbas Arsanukayev", "Khadimat Aslakhanov", "Kristian Mathisen", "Klimek Król", "Viliam Dobre", "Belle Hill", "Gayane Ignatyeva", "Sepiyat Vedzizhev", "Yudit Loera",
         "Lukáš Cervinka", "Mariangela Casárez", "Emma Andresen", "Alcia Zelaya", "Vivien Lukáts", "Sienna Morton", "Charli Atherton", "Katarzyna Zielinska", "Heike Lehmann", "Ali Sultygov", "Kyle Hobbs", "David Hofmann", "Amalie Frandsen", "Melker Mattsson",
         "Hanna Hana", "Lysbeth Coumans", "Frank Biermann", "Arthur Andreasson", "Kimiaki Agano", "Radames Valladares", "Felix Hafstad", "Lelio Mancini", "Tiana Jamieson", "Sointu Helminen", "Gazmen Obersnel", "Laetitia Labrosse", "Fedot Zuyev", "Alaor Santiago",
         "Zelimkhan Batukayev", "Tabaré Atencio", "Natanael Orozco", "Julia Rodrigues", "Katinka Kántor", "Nicoline Gulbrandsen", "Asya Zaitseva", "Holly Patel", "Nadica Lukic", "Tiago Costa", "Lauri Yli-Sirniö", "Isabela Dias", "Amancay Arana", "Michelle Müller",
         "Vilde Øwre", "Daniella McKay", "Sieuwerd Lit", "Laura Muecke", "Mario Kovacic", "Angus McGowen", "Kusama Kadiev", "Zorica Ivancic", "Faye Léveillé", "Andrew Fraire", "Bakar Ryzaev", "Robel Efrem", "Hortensio Cervantes", "Irena Vašícková", "Jens Jakobsen",
         "Tena Klobucar", "Hidaka Senda", "Karolin Maier", "Jenni Nikkola", "Zak Simpson", "Medhanie Berhane", "Brahim Lau", "Busana Bataev", "Varg Vigre", "Dawid Paterson", "Karolin Engel", "Ayaan Kennedy", "Nikita Glazkov", "Bent Larsen", "Adalrico Piazza",
         "Hayden Allen", "Milan Krecek", "Katerina Kuglerová", "Albracca Chandonnet", "Geraldina Trentini", "Mikita Hirata", "Letícia Rocha", "Kian Sutherland", "Cajsa Håkansson", "Aleksander Berntsen", "Rosa Bradshaw", "Ratimir Kralj", "Nicolas Santos",
         "Orlando Calabrese", "Kimmo Kalliomäki", "Sesuna Hamid", "Tristán Varela", "Amalio Marchesi", "Robert Hahn", "Molly Hill", "Sobieslaw Kwiatkowski", "Szilárd Szigethy", "Rosaria Giordano", "Niklas Egge", "Lucas Wechsler", "Miroš Stolar", "Vigor Björk"
   };

   @Autowired
   public SampleData (CustomerRepository customerRepository)
   {
      this.customerRepository = customerRepository;
   }

   @Override
   public void run (ApplicationArguments args)
   {
      final List<String> namesList = Arrays.asList (names);
      Collections.shuffle (namesList);
      for (String name : namesList) {
         customerRepository.save (newCustomer (name));
      }
   }

   private Customer newCustomer (String name)
   {
      Customer customer = new Customer ();
      customer.setName (name);
      customer.setCustomerId ((long) random.nextInt (1000));
      customer.setPurchases (new HashSet<> ());

      for (int i = 0; i < random.nextInt (5); i++) {
         final Purchase purchase = newPurchase (random.nextInt (10000));
         purchase.setCustomer (customer);
         customer.getPurchases ().add (purchase);
      }
      return customer;
   }

   private Purchase newPurchase (int id)
   {
      Purchase purchase = new Purchase ();
      final double price = random.nextDouble () * MAX_PRICE;
      purchase.setPrice ((int) (price * 100) / 100.);
      purchase.setAmount (random.nextInt (MAX_AMOUNT));
      purchase.setItem (items[random.nextInt (items.length - 1)]);
      purchase.setDate (randomDate ());
      return purchase;
   }

   private final long earliestDate = Timestamp.valueOf ("2014-01-01 00:00:00").getTime ();
   private final long latestDate = Timestamp.valueOf ("2018-03-31 23:59:00").getTime ();

   private Date randomDate ()
   {
      long difference = latestDate - earliestDate + 1;
      return new Date (earliestDate + (long) (random.nextDouble () * difference));
   }
}
