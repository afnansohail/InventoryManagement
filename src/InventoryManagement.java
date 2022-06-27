/*
 * 
 *	@author Afnan Sohail
 *	Date Created: April 13th, 2022
 *	Project Name: Inventory Management
 *
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


public class InventoryManagement
{

	// This method displays all the elements currently in the inventory
	public static void displaylist(LinkedList<Product> list)
	{
		System.out.println("---------------------------------------------------------");
		System.out.println("ID\t\tName\t\tPrice\t\tQuantity");
		System.out.println("---------------------------------------------------------");

		if(!list.isEmpty())
		{
			for(Product item : list)
				System.out.println(item);
		}
		else
			System.out.println("                No Items in the Inventory                ");

		System.out.println("---------------------------------------------------------");

		System.out.println();
	}

	// This method adds an item to the end of the list
	public static LinkedList<Product> addItem(LinkedList<Product> list)
	{
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		System.out.print("Product ID: ");
		int id = in.nextInt();
		in.nextLine();
		System.out.print("Product Name: ");
		String name = in.nextLine();
		System.out.print("Product Price: ");
		int price = in.nextInt();
		System.out.print("Product Quantity: ");
		int qtty = in.nextInt();

		list.add(new Product(id, name, price, qtty));

		return list;
	}

	// This method takes an ID and deletes the item corresponding to that ID from
	// the list
	// If no match is found, the list remains unchanged
	public static LinkedList<Product> deleteItem(LinkedList<Product> list, int id)
	{
		int index = -1;
		boolean isFound = false;
		for(int i = 0; i < list.size(); i++)
		{
			if(id == list.get(i).getId())
			{
				index = i;
				isFound = true;
				break;
			}
		}
		
		if(isFound)
		{
			list.remove(index);
			System.out.println("\nInventory Updated Successfully!");
		}
		else
			System.out.println("\nID could not be matched. Inventory not updated.");
			
		return list;
	}

	// This method takes an ID and the further asks what information is to be
	// edited corresponding to that ID
	// If the ID isn't matched, the list remains unchanged
	private static LinkedList<Product> editItem(LinkedList<Product> list, int id)
	{
		int index = -1;
		boolean isFound = false;
		for(int i = 0; i < list.size(); i++)
		{
			if(id == list.get(i).getId())
			{
				index = i;
				isFound = true;
				break;
			}
		}

		if(isFound)
		{
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			System.out.println("What would you like to edit?: ");
			System.out.println("1- Product ID\n2- Product Name\n3- Product Price\n4- Product Quantity");
			System.out.print("Please enter your choice: ");
			int choice = in.nextInt();

			switch(choice)
			{
			case 1:
				System.out.print("Enter the new ID: ");
				int ID = in.nextInt();
				list.get(index).setId(ID);
				System.out.println("\nInventory Updated Successfully!");
				break;

			case 2:
				in.nextLine();
				System.out.print("Enter the new name: ");
				String name = in.nextLine();
				list.get(index).setName(name);
				System.out.println("\nInventory Updated Successfully!");
				break;

			case 3:
				System.out.print("Enter the new price: ");
				int price = in.nextInt();
				list.get(index).setPrice(price);
				System.out.println("\nInventory Updated Successfully!");
				break;

			case 4:
				System.out.print("Enter the new Quantity: ");
				int qtty = in.nextInt();
				list.get(index).setItemsCount(qtty);
				System.out.println("\nInventory Updated Successfully!");
				break;
				
			default:
				System.out.println("Invalid Choice!");
				break;
			}
		}
		else
			System.out.println("ID could not be matched. Inventory not updated.");

		return list;
	}

	// After each update (addition or deletion of any item), this method is called
	// and stores the updated list in a file
	// In this way, the list can be retrieved even after the program is terminated
	public static void writeToFile(LinkedList<Product> pList) throws IOException
	{
		String filePath = "ProductList.txt";
		FileWriter fw = new FileWriter(filePath);

		for(Product item : pList)
			fw.append(item.getId() + "-" + item.getName() + "-" + item.getPrice() + "-" + item.getItemsCount() + "\n");

		fw.close();
	}

	// This method read the list details from the text file
	public static LinkedList<Product> readFromFile() throws IOException
	{
		LinkedList<Product> tmp = new LinkedList<>();

		String filePath = "ProductList.txt";
		File file = new File(filePath);

		if(file.exists())
		{

			Scanner fileInput = new Scanner(file);
			while(fileInput.hasNext())
			{
				String fileLine = fileInput.nextLine();
				String[] pDescription = fileLine.split("-");

				int id = Integer.parseInt(pDescription[0]);
				String name = pDescription[1];
				int price = Integer.parseInt(pDescription[2]);
				int count = Integer.parseInt(pDescription[3]);

				tmp.add(new Product(id, name, price, count));
			}

			fileInput.close();
		}

		return tmp;
	}

	// This method is used to create a new User Account
	public static void signUp() throws IOException
	{
		System.out.println("=== Create a new Account ===");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.print("Enter your new username (without spaces): ");
		String userName = in.nextLine();
		System.out.print("Enter your new password: ");
		String password = in.nextLine();

		String filePath = "UserCredentials.txt";
		FileWriter fw = new FileWriter(filePath, true);

		fw.write(userName + "-" + password + "\n");

		System.out.println("=== " + userName + "\'s account successfully created! ===");
		fw.close();
	}

	// This method is used to log in to an existing account
	public static boolean login() throws FileNotFoundException
	{
		System.out.println("=== Log-in to your Account ===");
		boolean isLoggedIn = false;

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.print("Username: ");
		String userName = in.nextLine();
		System.out.print("Password: ");
		String password = in.nextLine();

		String filePath = "UserCredentials.txt";
		File file = new File(filePath);

		if(file.exists())
		{
			Scanner fileInput = new Scanner(file);
			while(fileInput.hasNext())
			{
				String fileLine = fileInput.nextLine();
				String[] credentials = fileLine.split("-");

				if(credentials[0].equals(userName))
				{
					if(credentials[1].equals(password))
					{
						isLoggedIn = true;
						break;
					}
				}
			}

			fileInput.close();
		}

		return isLoggedIn;
	}

	// Driver function
	public static void main(String[] args) throws IOException
	{
		System.out.println("===============================================");
		System.out.println("|>|>|>|>| INVENTORY MANAGEMENT SYSTEM |<|<|<|<|");
		System.out.println("===============================================");
		System.out.println();

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int choice = 0;

		do
		{
			System.out.println("Please log-in or create a new account to continue: ");
			System.out.println("1- Log-in\n2- Create Account");

			do
			{
				System.out.print("Please enter your choice (1 or 2): ");
				choice = in.nextInt();
			}
			while(choice != 1 && choice != 2);

			System.out.println();

			if(choice == 1)
			{
				boolean loggedIn = login();
				if(loggedIn == false)
					System.out.println("INCORRECT CREDENTIALS!");
				else
				{
					System.out.println("Logged in succesfully");
					break;
				}
			}
			else
				signUp();

			System.out.println();
		}
		while(true);

		System.out.println();
		System.out.println("===============================================");

		LinkedList<Product> pList = new LinkedList<>();

		pList = readFromFile();

		boolean toExit;
		do
		{
			toExit = false;

			System.out.println("------------------ Main Menu ------------------");
			System.out.println("1- View Inventory\n2- Add Item\n3- Delete Item\n4- Edit an Item\n5- Exit");

			System.out.print("Please enter your choice: ");
			choice = in.nextInt();

			System.out.println();

			switch(choice)
			{
			case 1:
				System.out.println(">>> VIEWING THE CURRENT INVENTORY LIST <<<");
				displaylist(pList);
				break;

			case 2:
				System.out.println(">>> ADDING A NEW ITEM <<<");
				pList = addItem(pList);
				writeToFile(pList);
				System.out.println("\nInventory Updated Successfully!");
				System.out.println();
				break;

			case 3:
				System.out.println(">>> DELETING AN ITEM <<<");
				System.out.print("Enter the ID of the item to be removed: ");
				int rID = in.nextInt();
				pList = deleteItem(pList, rID);
				writeToFile(pList);
				System.out.println();
				break;

			case 4:
				System.out.println(">>> EDITING AN ITEM <<<");
				System.out.print("Enter the ID of the item to be edited: ");
				int eID = in.nextInt();
				pList = editItem(pList, eID);
				writeToFile(pList);
				System.out.println();
				break;

			case 5:
				toExit = true;
				break;
			}
			if(!toExit)
				System.out.println("\nRETURNING TO THE MAIN MENU....\n\n");
		}
		while(!toExit);

		System.out.println("TERMINATED SUCCESSFULLY!");
	}
}
