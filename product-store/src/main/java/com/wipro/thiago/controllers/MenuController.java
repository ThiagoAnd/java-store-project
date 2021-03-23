package com.wipro.thiago.controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.wipro.thiago.models.Product;

public class MenuController {

	public void startMenu(Scanner scan) {
		List<Product> products = new ArrayList<Product>();
		int option = -1;

		do {

			option = getMenuOption(scan);
			executeOption(option, products, scan);

		} while (option != 0);
		scan.close();
		System.out.println("Program terminated.");

	}

	public int getMenuOption(Scanner scan) {
		int number = -1;

		do {
			try {
				System.out.println("+++++++++++THIAGO STORE+++++++++++++++++++");
				System.out.println("[1] Add product");
				System.out.println("[2] List all the products");
				System.out.println("[3] Search for product");
				System.out.println("[0] Exit program");
				number = scan.nextInt();
				if (number < 0 || number > 3) {
					System.out.println("\n!!!Choose 0 to 3!!!\n");
				}
			} catch (NoSuchElementException e) {
				scan.nextLine();
				System.out.println("\n!!!Incorrect input.Only numbers allowed!!!\n");

			}
		} while (number < 0 || number > 3);

		return number;

	}

	public void executeOption(int option, List<Product> products, Scanner scan) {

		switch (option) {

		case 1:
			addProduct(products, scan);
			break;
		case 2:
			listProducts(products, scan);
			break;
		case 3:
			searchProduct(products, scan);
			break;
		case 0:
			break;
		default:
			System.out.println("Invalid option");
		}
	}

	public void addProduct(List<Product> products, Scanner scan) {
		String tempName;
		double tempPrice;
		String tempDescription;
		int tempQuantity;
		String tempCategory;

		System.out.println("\n");

		while (true) {
			try {
				System.out.println("\n+++++++Provide product data+++++++\n");
				scan.nextLine();
				System.out.print("Product name: ");
				tempName = scan.nextLine();

				System.out.print("Product description: ");
				tempDescription = scan.nextLine();

				System.out.print("Product category: ");
				tempCategory = scan.nextLine();

				System.out.print("Product price: ");
				tempPrice = scan.nextDouble();

				System.out.print("Product quantity: ");
				tempQuantity = scan.nextInt();

			} catch (InputMismatchException e) {

				System.out.println("\n!!!Incorrect input.(DOUBLE for price and INT for quantity)!!!");
				continue;
			}
			break;
		}
		products.add(new Product(tempName, tempPrice, tempDescription, tempQuantity, tempCategory));

		System.out.println("\nProduct added.\nPress ENTER to continue");
		scan.nextLine();
		scan.nextLine();

	}

	public void listProducts(List<Product> products, Scanner scan) {

		if (verifyList(products) != null) {
			products.stream().forEach(System.out::println);
			System.out.println("\n");
		}
		scan.nextLine();
		System.out.println("Press ENTER to continue");
		scan.nextLine();
	}

	

	public void searchProduct(List<Product> products, Scanner scan) {
		if (verifyList(products) == null) {
			scan.nextLine();
		} else {
			String tempName;
			boolean exist;

			scan.nextLine();
			System.out.print("\nEnter the product name: ");
			tempName = scan.nextLine();

			exist = products.stream().anyMatch(product -> product.getName().equalsIgnoreCase(tempName));

			if (exist) {
				System.out.println("\nFound product " + tempName);
				products.stream().filter(product -> product.getName().equalsIgnoreCase(tempName))
						.forEach(this::showProductPriceAndDescription);
				System.out.println("\n");
			} else {
				System.out.println("\nThere is no product with that name, or you forgot the accents.");
			}

		}

		System.out.println("Press ENTER to continue");
		scan.nextLine();

	}
	
	public List<Product> verifyList(List<Product> products) {
		if (products.isEmpty()) {
			System.out.println("\nThere is no product in stock");
			return null;
		}
		return products;

	}

	public void showProductPriceAndDescription(Product product) {
		System.out.print("\n\nPrice= " + product.getPrice());
		System.out.print("\nDescription= " + product.getDescription());
	}

}
