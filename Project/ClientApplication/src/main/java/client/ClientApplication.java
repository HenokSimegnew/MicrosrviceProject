package client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
public class ClientApplication implements CommandLineRunner {
	@Autowired
	private RestOperations  restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("-----Create Product 1 TV -------");
		System.out.println("-----Create Product 2 MP3 Player -------");
		//create products
		restTemplate.postForLocation("http://localhost:8080/products", new ProductDTO("A33","TV",700.0,"samsung TV",100.0));
		restTemplate.postForLocation("http://localhost:8080/products", new ProductDTO("A34","MP3 Player",30.0,"samsung CD player",40));

		//get the products before update
		ProductDTO product00 = restTemplate.getForObject("http://localhost:8080/products/A33", ProductDTO.class);
		System.out.println("-----Product 1 before update -------");
		System.out.println(product00);

		System.out.println("-----Product 1 change from Tv to Iphone 12 -------");
		//change a product
		restTemplate.put("http://localhost:8090/products/A33", new ProductDTO("A33","IPhone 12",450.0,"Latest Apple mobile",1400));

		//get the products from the Product service after update
		ProductDTO product0 = restTemplate.getForObject("http://localhost:8090/products/A33", ProductDTO.class);
		System.out.println("-----Product 1 after update product from Tv to Iphone 12 -------");
		System.out.println(product0);

		ProductDTO product1 = restTemplate.getForObject("http://localhost:8090/products/A34", ProductDTO.class);
		System.out.println("-----Product 2 after update-------");
		System.out.println(product1);


		restTemplate.postForLocation("http://localhost:8089/customers", new CustomerDTO("111","Henok","Simegnew","hsimegnew",new Address("1000","fairfild","52537"),"6418"));
		restTemplate.postForLocation("http://localhost:8089/customers", new CustomerDTO("222","Abel","Simegnew","hsimegnew",new Address("1000","fairfild","52537"),"6418"));


//get the customer before update
		CustomerDTO customer = restTemplate.getForObject("http://localhost:8089/customers/111", CustomerDTO.class);
		System.out.println("-----Customer 1 before update -------");
		System.out.println(customer);

		//change a customer
		//restTemplate.put("http://localhost:8089/customers/111", new CustomerDTO("111","Henok S","Chekol","hsimegnew","6418"));


//print customer after update
		CustomerDTO customer1 = restTemplate.getForObject("http://localhost:8089/customers/111", CustomerDTO.class);
		System.out.println("-----get customer 1 -------");
		System.out.println(customer1);

		CustomerDTO customer2 = restTemplate.getForObject("http://localhost:8089/customers/222", CustomerDTO.class);
		System.out.println("-----get customer 2 -------");
		System.out.println(customer2);

		//before add new product or change quality
		ShoppingCartDTO cartbeforaddnew = restTemplate.getForObject("http://localhost:8080/CartQueries/61133c61e221e13ccc0f53b4", ShoppingCartDTO.class);
		System.out.println("\n-----before add new product or change quality   -------");
		if (cartbeforaddnew != null) cartbeforaddnew.print();


		//Add product to shoping cart
		//if you add the same product increase the quantity
		ProductDTO product4 = restTemplate.getForObject("http://localhost:8080/products/A34", ProductDTO.class);
		restTemplate.postForLocation("http://localhost:8080/CartCommands/61133c61e221e13ccc0f53b4/5", product4);

		System.out.println("\n-----Add  product >>if the product not exist prformed   -------");
		System.out.println("\n-----Change quantity >>of the product if the product exit performed     -------");

		Thread.sleep(5000);
		//get the shoppingcart by id
		ShoppingCartDTO cart = restTemplate.getForObject("http://localhost:8080/CartQueries/61133c61e221e13ccc0f53b4", ShoppingCartDTO.class);
		System.out.println("\n-----Shoppingcart after add new product or add quntity of the product-------");
		if (cart != null) cart.print();


		// Remove product from shopping cart
		restTemplate.put("http://localhost:8080/CartCommands/61133c61e221e13ccc0f53b4/product/",product4);

		System.out.println("\n-----Remove  product >>from the Shoppingcart prformed   -------");

		Thread.sleep(5000);
		//Shoppingcart After Product is Removed
		ShoppingCartDTO cartAfterRemoval = restTemplate.getForObject("http://localhost:8080/CartQueries/61133c61e221e13ccc0f53b4", ShoppingCartDTO.class);
		System.out.println("\n-----Shoppingcart After Product is Removed-------");
		if (cartAfterRemoval != null) cartAfterRemoval.print();

		System.out.println("\n-----Order Is created -------");
		OrderDTO orderDTO = restTemplate.postForObject("http://localhost:3030/CartCommands/61133c61e221e13ccc0f53b4/order/",null,OrderDTO.class);
		System.out.println(orderDTO);

		System.out.println("\n-----Add customer to order -------");
		restTemplate.put("http://localhost:3033/orders/"+orderDTO.getId()+"/customer/"+customer1.getId(),null);

		System.out.println("\n-----Order Is placed -------");
		restTemplate.put("http://localhost:3033/orders/"+orderDTO.getId()+"/placeOrder",null);

		OrderDTO placedOrderDTO = restTemplate.getForObject("http://localhost:3033/orders/"+orderDTO.getId(),OrderDTO.class);
		System.out.println(placedOrderDTO);

	}

}
