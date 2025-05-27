package com.bach.springboot_jpa_relationship;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bach.springboot_jpa_relationship.entities.Client;
import com.bach.springboot_jpa_relationship.entities.Invoice;
import com.bach.springboot_jpa_relationship.repositories.ClientRepository;
import com.bach.springboot_jpa_relationship.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//manyToOneCreateClient();
		manyToOneFindById();
	}

	public void manyToOneCreateClient(){
		Client client = new Client("jhon", "Connor");
		clientRepository.save(client);

		Invoice invoice = new Invoice("compras para setUp", 1000L );
		invoice.setClient(client);

		//Invoice invoiceDB = invoiceRepository.save(invoice);
		//System.out.println(invoiceDB);

	}
	public void manyToOneFindById(){
		//buscamos al cliente y asignamos la factura
		Optional<Client> optionalClient = clientRepository.findById(1L);

		if(optionalClient.isPresent()){
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("compras para setUp", 1000L );
			invoice.setClient(client);

			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}
	}


}
