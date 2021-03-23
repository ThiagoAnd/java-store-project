package com.wipro.thiago;

import java.util.Scanner;

import com.wipro.thiago.controllers.MenuController;

public class Main {

	public static void main(String[] args) {
					
		Scanner scan = new Scanner(System.in);
		MenuController menu = new MenuController();

		menu.startMenu(scan);

	}

}
