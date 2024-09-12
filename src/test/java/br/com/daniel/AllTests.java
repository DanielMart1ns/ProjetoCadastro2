package br.com.daniel;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@Suite.SuiteClasses({ClientServiceTest.class, ClientDAOTest.class,
	ProdutoService.class, ProdutoDAOTest.class,
	VendaDAOTest.class})
public class AllTests {

}
