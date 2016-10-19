package br.com.finance.financeiro_pessoal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.finance.financeiro_pessoal.domain.fin.MovimentoCaixa;
import br.com.finance.financeiro_pessoal.service.fin.MovimentoCaixaService;

@SpringBootApplication
public class ApplicationMain implements CommandLineRunner{

	@Autowired
	private MovimentoCaixaService movimentoCaixaService;
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testPagination();
	}
	
	private void testPagination(){
		Page<MovimentoCaixa> pages = movimentoCaixaService.findAll(new PageRequest(0, 2));
		pages.getContent().forEach(System.out::println);
		
		pages = movimentoCaixaService.findAll(new PageRequest(1, 2));
		pages.getContent().forEach(System.out::println);
		
		pages = movimentoCaixaService.findAll(new PageRequest(2, 2));
		pages.getContent().forEach(System.out::println);
	}
}
