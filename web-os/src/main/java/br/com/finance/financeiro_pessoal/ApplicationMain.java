package br.com.finance.financeiro_pessoal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;
import br.com.finance.financeiro_pessoal.domain.fin.MovimentoCaixa;
import br.com.finance.financeiro_pessoal.service.fin.ContaCaixaService;
import br.com.finance.financeiro_pessoal.service.fin.MovimentoCaixaService;

@SpringBootApplication
public class ApplicationMain implements CommandLineRunner{

	@Autowired
	private MovimentoCaixaService movimentoCaixaService;
	
	@Autowired
	private ContaCaixaService contaCaixaService;
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//teste();
	}
	
	private void teste(){
		ContaCaixa c = contaCaixaService.procurarPeloID(2L);
		MovimentoCaixa movCaixa = movimentoCaixaService.findByContaCaixaEndingWith(c);
		System.out.println(movCaixa.getContaCaixa());
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
