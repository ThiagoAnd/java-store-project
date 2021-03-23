package com.wipro.thiago.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.wipro.thiago.models.Product;

class MenuControllerTest {

	private MenuController menuController;

	@BeforeEach
	void setup() {
		this.menuController = new MenuController();
	}
	
	


	@Nested
	class GetMenuOptionTests {

		@Test
		void should_ReturnTrue_When_ChooseFromZeroToThree() {

			// given

			String actual = "3";
			Scanner scan = new Scanner(new ByteArrayInputStream(actual.getBytes()));

			// when

			int number = menuController.getMenuOption(scan);

			// then

			assertTrue(number >= 0 || number <= 3);

		}

		@Test
		void should_ThrowNoSuchElementException_When_MenuOptionNotNumber() {

			// given

			String actual = "a";
			Scanner scan = new Scanner(new ByteArrayInputStream(actual.getBytes()));

			// when

			Executable executable = () -> menuController.getMenuOption(scan);

			// Then
			assertThrows(NoSuchElementException.class, executable);
		}

	}
	
	


	@Nested
	class SearchProductTests {

		@Test
		void should_ValidateEquality_When_ProductNotEmpty() {

			// given

			String expected = "\nPrice= 450.5\nDescription= Microsof O.S. system";

			Product product = new Product("Windows 10", 450.5, "Microsof O.S. system", 3, "Software");

			ByteArrayOutputStream content = new ByteArrayOutputStream();
			System.setOut(new PrintStream(content));

			// when

			menuController.showProductPriceAndDescription(product);

			// then
			assertEquals(expected, content.toString());

		}
	}
	
	


	@Nested
	class AddProductTests {

		@Test
		void should_ThrowNoSuchElementException_When_ProductInputNotNumber() {

			// given

			String actual = "\nWindows 10\nMicrosoft S.O.\nSoftware\nNOTNUMBER\n5\n\n";
			Scanner scan = new Scanner(new ByteArrayInputStream(actual.getBytes()));

			List<Product> products = new ArrayList<Product>();
			products.add(new Product("Windows 10", 450.5, "Microsof O.S. system", 3, "Software"));

			// when

			Executable executable = () -> menuController.addProduct(products, scan);

			// Then

			assertThrows(NoSuchElementException.class, executable);
		}

	}
	
	


	@Nested
	class VerifyListTests {

		@Test
		void should_ReturnNullProductList_When_ProductListIsEmpty() {
			// given

			List<Product> products = new ArrayList<Product>();

			// when

			List<Product> productList = menuController.verifyList(products);

			// Then

			assertNull(productList);
		}

		@Test
		void should_ReturnProductList_When_ProductListNotEmpty() {
			// given

			List<Product> products = new ArrayList<Product>();
			products.add(new Product("Windows 10", 450.5, "Microsof O.S. system", 3, "Software"));

			// when

			List<Product> productList = menuController.verifyList(products);

			// Then

			assertNotNull(productList);
		}
	}

}
