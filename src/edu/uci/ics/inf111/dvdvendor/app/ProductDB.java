/*
 * Creator: Rosalva Gallardo-Valencia
 * Based on code created by Susan Elliott Sim
 * Course: Inf111, Winter 2009
 * 
 * Created on January 8, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 */
package edu.uci.ics.inf111.dvdvendor.app;
/*
 * Based on code created by Susan Elliott Sim
 * Course: Inf111, Winter 2009
 * 
 * Created on January 7, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 * The ProductDB class maintains the items in the database. The items are stored in a hash table.
 */

import java.util.Hashtable;

import edu.uci.ics.inf111.dvdvendor.exceptions.ProductDBException;

/**
 * The ProductDB class encapsulates the list of all products sold in the store. 
 * In a real system, this would likely be a wrapper around a database of products
 * which would be managed elsewhere.  In our sample system, we have a method which 
 * can provide a sample DB, and the capability to add items to the DB using an
 * addItem() method. The ProductDB is instantiated only once and it uses the 
 * singleton pattern to guarantee this characteristic.
 *
 */
public class ProductDB {
	
	/**
	 * Private instance of this class
	 */
	private static ProductDB instance = null;
	
	/**
	 * This Hashtable is the core of our sample DB.  In a real implementation, the
	 * actual data would likely be in a separate database, which we would access
	 * using database queries.
	 */
	private Hashtable<String, Product> productsHT;

	/**
	 * Constructs an empty database. This method should not be used by outside classes
	 */
	private ProductDB() {
		
		productsHT = new Hashtable<String, Product>();
	}
	/**
	 * It returns a new class if the instance is null, else it returns the instance
	 * already created and initialized
	 * @throws Exception 
	 */	
	public static ProductDB getInstance() throws Exception {
	  if(instance == null) {
	         instance = new ProductDB();
	         instance.initializeTestDB();
	      }
	   return instance;
	}
	
	/**
	 * This test method constructs a sample database which is useful for
	 * testing purposes.  Products may also be added individually using the
	 * addItem() method.
	 * @throws Exception
	 */
	public void initializeTestDB() throws Exception {
		/* 
		 * This try block is used to capture the various code- and item-creation exceptions
		 * which are thrown by BarCode()
		 */ 
		try {
			// Hardcoding the items in the database.
			
			// For details on how to create legal bar codes, see the BarCode class.
			BarCode bc1 = new BarCode("786936224306");
			BarCode bc2 = new BarCode("717951000842");
			BarCode bc3 = new BarCode("024543213710");
			BarCode bc4 = new BarCode("085392132225");
			BarCode bc5 = new BarCode("013803086706");
			BarCode bc6 = new BarCode("012300000642");
			BarCode bc7 = new BarCode("071009003507");
			BarCode bc8 = new BarCode("639382000393");
			BarCode bc9 = new BarCode("012345678912");
			BarCode bc10 = new BarCode("074470034629");
						
			// DVDs
			DVD dvd1 = new DVD(bc1, 
					"The Chronicles of Narnia: Prince Caspian",
					1.00,
					"The magical world of C.S. Lewis' beloved fantasy comes to life once again in PRINCE CASPIAN, the second installment of THE CHRONICLES OF NARNIA series. Join Peter, Susan, Edmund Lucy, the mighty and majestic Aslan, friendly new Narnian creatures and Prince Caspian as they lead the Narnians on a remarkable journey to restore peace and glory to their enchanted land. Rated PG by the Motion Picture Association of America for epic battle action and violence. Widescreen. Closed Captioned. Spanish dubbed and subtitled available.",
					new Utils.genre[]{Utils.genre.ACTION_ADVENTURE, Utils.genre.FAMILY, Utils.genre.HITMOVIES},
					"2:35", "Ben Barnes,George Henley");
			
			DVD dvd2 = new DVD(bc2, 
					"Wall-E",
					1.00,
					"After hundreds of lonely years of doing what he was built for, the curious and lovable WALL-E discovers a new purpose in life when he meets a sleek search robot named EVE. Join them and a hilarious cast of characters on a fantastic journey across the universe. WALL-E is a film your family will want to enjoy over and over again.",
					new Utils.genre[]{Utils.genre.ANIMATION, Utils.genre.FAMILY, Utils.genre.KIDS, Utils.genre.HITMOVIES},
					"1:38", "Jeff Garlin (V),Sigourney Weaver (V)");
			
			DVD dvd3 = new DVD(bc3, 
					"Mamma Mia!",
					1.00,
					"MAMMA MIA became a Broadway smash when it hit Broadway back in 2001. With a story framed around the music of the Swedish pop band Abba, crowds loved its raucous, dance party vibe. Now it comes to DVD, with some truly delightful performances from the likes of Meryl Streep and Pierce Brosnan. It is the story of Sophie, a young woman living on a picturesque Greek island with her mother, Donna. Together, Donna and Sophie run a ramshackle island inn, and they are in the midst of preparing for Sophie's wedding.",
					new Utils.genre[]{Utils.genre.COMEDY, Utils.genre.MUSICAL, Utils.genre.HITMOVIES},
					"1:48", "Colin Firth,Meryl Streep,Pierce Brosnan");	
			
			DVD dvd4 = new DVD(bc4, 
					"All Roads Lead Home",
					1.00,
					"In this inspirational film based on a true story, a young girl has difficulty adjusting after the death of her mother. When her father is unable to help her, he sends her to live with her grandfather on a farm.",
					new Utils.genre[]{Utils.genre.DRAMA, Utils.genre.FAMILY},
					"1:48", "Jason London,Patton Oswalt,Peter Coyote");				
			
			DVD dvd5 = new DVD(bc5, 
					"The Sisterhood of the Traveling Pants 2",
					1.00,
					"Lifelong friends embark on separate paths for their first year of college and the summer beyond, but remain in touch by sharing their experiences with each other as they always have--with honesty and humor. Discovering their individual strengths, fears, talents and capacity for love through the choices they make, they come to value more than ever the bond they share and the immeasurable power of their friendship.",
					new Utils.genre[]{Utils.genre.DRAMA, Utils.genre.FAMILY, Utils.genre.HITMOVIES},
					"1:57", "Amber Tamblyn,America Ferrera");	

			DVD dvd6 = new DVD(bc6, 
					"August Evening",
					1.00,
					"The lives of an aging, undocumented farm worker named Jaime and his young, widowed daughter-in-law, Lupe, are thrown into upheaval. ",
					new Utils.genre[]{Utils.genre.DRAMA, Utils.genre.FOREIGN},
					"2:07", "Pedro Castaneda");	
			
			DVD dvd7 = new DVD(bc7, 
					"The Dark Knight",
					1.00,
					"The follow-up to Batman Begins, The Dark Knight reunites director Christopher Nolan and star Christian Bale, who reprises the role of Batman/Bruce Wayne in his continuing war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to destroy organized crime in Gotham for good. The triumvirate proves effective, but soon find themselves prey to a rising criminal mastermind known as The Joker, who thrusts Gotham into anarchy and forces Batman closer to crossing the fine line between hero and vigilante.",
					new Utils.genre[]{Utils.genre.ACTION_ADVENTURE, Utils.genre.SCIFI_FANTASY, Utils.genre.SUSPENSE, Utils.genre.HITMOVIES},
					"2:32", "Christian Bale,Gary Oldman,Heath Ledger");
			
			DVD dvd8 = new DVD(bc8, 
					"Burn After Reading",
					1.00,
					"An ousted CIA official's memoir accidentally falls into the hands of two unwise gym employees intent on exploiting their find.",
					new Utils.genre[]{Utils.genre.COMEDY, Utils.genre.HITMOVIES},
					"1:35", "Brad Pitt,George Clooney");
			
			DVD dvd9 = new DVD(bc9, 
					"Made of Honor",
					1.00,
					"Platonic friends since college have never entertained the prospect of romance -- he's a womanizer who never wanted to commit; she wants marriage but has never found the right man. Just as he begins to think he's ready to settle, she gets engaged to a handsome Scotsman -- and asks her longtime male pal to be her 'maid' of honor. He agrees, but only so he can sabotage the wedding and woo her before it's too late.",
					new Utils.genre[]{Utils.genre.COMEDY, Utils.genre.ROMANCE, Utils.genre.HITMOVIES},
					"1:41", "Patrick Dempsey, Michelle Monaghan, Kevin McKidd");
			
			DVD dvd10 = new DVD(bc10, 
					"All Hat",
					1.00,
					"Based on a novel by top selling author Brad Smith, All Hat is an exciting and breathtaking western tale. When Ray is let out of jail, his worst troubles are just starting. Full of thoroughbred racing, guns and romance, All Hat is a powerful film you don't want to miss.",
					new Utils.genre[]{Utils.genre.WESTERN},
					"1:31", "Keith Carradine, Rachael Leigh Cook, Graham Greene");
			
			// adding items to hash table
			addItem(dvd1);
			addItem(dvd2);
			addItem(dvd3);
			addItem(dvd4);
			addItem(dvd5);
			addItem(dvd6);
			addItem(dvd7);
			addItem(dvd8);
			addItem(dvd9);
			addItem(dvd10);

		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * This method returns a copy of the ProductDB Hashtable.  If we
	 * provided the original, external code could modify the DB directly.
	 */
	public Hashtable<String, Product> listAll() {
		// make a copy of productsHT before returning
		Hashtable<String, Product> copyHT = new Hashtable<String, Product>(
				productsHT);
		return copyHT;
	}
	
	/**
	 * This method looks up a product in the database.  
	 * @param code	The bar code of the product.
	 * @return	The Product class of the corresponding product, or null if no such product.
	 */
	public Product lookUpItem(BarCode barCode) {
		return productsHT.get(barCode.getBarCode());
	}

	/**
	 * This method is called to add items directly to the database in our example.   
	 * This method checks if the product already exist in the DB. If the product exists, 
	 * a ProductDBException will be thrown, else the product will be added to the database. 
	 * In a real implementation, this would likely be done directly to the product 
	 * database using a separate piece of software.
	 * @param item	The product to be added.
	 * @throws ProductDBException If the product already exist in the DB.
	 */
	public void addItem(Product item) throws ProductDBException {
		//Look up the item in the DB
		//If the item does not exist, add it to the hashtable
		if (lookUpItem(item.getBarCode()) == null)
		{
			productsHT.put(item.getBarCode().getBarCode(), item);
		}
		//If the item already exist in the DB, throws an exception
		else 
		{
			throw new ProductDBException("Product " + item.getBarCode().getBarCode() +
					" - " + ((DVD) item).getTitle() +"  is already in ProductDB.");
		}
		
	}
}
