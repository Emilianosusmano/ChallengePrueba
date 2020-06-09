//package com.emilianosusmano.springboot.tests;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import com.emilianosusmano.springboot.service.impl.VerificadorMutanteServiceImpl;
//
//public class MutantTest {
//	VerificadorMutanteServiceImpl vm = new VerificadorMutanteServiceImpl();
//
//	@Test
//	public void cadenaValidaCharsTest() {
//		String[] mutantDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
//
//		Assert.assertTrue(vm.cadenaValida(mutantDna));
//	}
//
//	@Test
//	public void cadenaNoValidaCharsTest() {
//		String[] notMutantDna = { "RLMXVA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
//
//		Assert.assertFalse(vm.cadenaValida(notMutantDna));
//	}
//
//	@Test
//	public void cadenaValidaLengthTest() {
//		String[] mutantDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
//
//		Assert.assertTrue(vm.cadenaValida(mutantDna));
//	}
//
//	@Test
//	public void cadenaNoValidaLenghtTest() {
//		String[] notMutantDna = { "AARTGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
//
//		Assert.assertFalse(vm.cadenaValida(notMutantDna));
//	}
//
//	@Test
//	public void mutantTest() {
//		String[] mutantDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
//		Assert.assertTrue(vm.isMutant(mutantDna));
//	}
//
//	@Test
//	public void notMutantTest() {
//		String[] notMutantDna = { "ATGCGA", "CAGTGC", "TTCTGT", "AGAAGG", "CCCGTA", "TCACTG" };
//		Assert.assertFalse(vm.isMutant(notMutantDna));
//	}
//
//}
