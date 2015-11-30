public class Store //kurwa
{
	Register registers[];

	//h�r initieras Kassa-arrayen Store. Man st�ller in med quantity_of_registers hur m�nga kassor Store ska ha
	public Store(int quantity_of_registers)
	{
		registers = new Register[quantity_of_registers];
		for(int i = 0; i<quantity_of_registers; i++)
		{
			registers[i] = new Register();
		}
		registers[0].open();
	}
	
	
	public Register[] getRegisters() {
		return registers;
	}


	public void printOpenRegisters()
	{
		int i = 0;
		for(Register r : registers)
		{
			if(r.isOpen())
			{
				System.out.println(i);
				i++;
			}
		}
	}
	
	
	//L�gger ihop antalet Customers i varje kassa-k� i Kassa-arrayen(Store) och delar det p� antalet kassor som �r �ppna.
	public float getAverageQueueLength()
	{
		float customers_in_store = 0;
		float number_of_registers = 0;
		for(Register r : this.registers)
		{
			if(r.isOpen())
			{
			customers_in_store = customers_in_store + r.getQueueLength(); 
			number_of_registers++;
			}
		}
		
		return customers_in_store/number_of_registers;
	}
	
	
	//L�gger en Customer i den kassa-k� som �r kortast. Den g�r allts� igenom Kassa-arrayen(Store)
	public void newCustomer(Customer c)
	{
		int min = registers[0].getQueueLength();
		int index_of_shortest_queue = 0;
		for(int i = 1; i < this.registers.length; i++)
		{
			if(registers[i].isOpen())
			{
				if(registers[i].getQueueLength() < min)
				{
				min = registers[i].getQueueLength();
				index_of_shortest_queue = i;
				}
			}
		}
		registers[index_of_shortest_queue].addToQueue(c);
	}
	
	
	//G�r igenom Kassa-Arrayen(store) och l�gger alla Customers som har 0 groceries i en array och returnerar dom
	public Customer[] getDoneCustomers()
	{
		Customer[] done_customers = new Customer[registers.length];
		int i = 0;
		for(Register r : this.registers)
		{
			if(r.currentCustomerIsDone())
			{
				done_customers[i]= r.removeCurrentCustomer();
				i++;
			}
		}
		return done_customers;
	}
	
	//G�r igenom Kassa-arrayen(Store) och �ppnar f�rsta b�sta kassa som �r st�ngd.
	//Jag funderar p� att om n�r man skapar en Store s� kanske man borde Skapa kassor och ha dom som closed alla f�rutom den f�rsta(index 0)
	
	public boolean allRegistersOpen()
	{
		int i = 0;
		for(Register r : this.registers)
		{
			if(r.isOpen())
				{
					i++;
				}
		}
		if(i == this.registers.length)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void openNewRegister()
	{
		if(!allRegistersOpen())
		{
			int i = 0;
			while(this.registers[i].isOpen() == true)
			{
				i++;
			}
			registers[i].open();
		}
			else
			{
			System.out.println("All registers alreadya opened");
			
			}
	}
	
	//utf�r step fr�n klassen Register p� alla �ppna kassor.
	public void step()
	{
		for(Register r : this.registers)
		{
			if(r.isOpen())
			{
			r.step();
			}
		}
	}
	
	//ska lista alla kassor med index-nummer och printa hur m�nga som �r i k�n och om den �r �ppen. Den g�r de tv� sista grejerna.
	public void print_registers_in_store()
	{
		int kassanummer = 0;
		for(Register r : this.registers)
		{
			if(r != null)
			{
				if(r.isOpen())
				{
				System.out.println("______________");
				System.out.println("\nKassanummer");
				System.out.println(kassanummer);
				System.out.println(r.getFirstPointer());
				//h�r vill jag printa ut vilket index i Kassa-arrayen(Store) kassan jag printar ut har.
				r.printRegister();
				kassanummer++;
				}
			}
		}
	}
	
	
	public void fill_Store(Customer[] test_customers)
	{
		for(Customer c : test_customers)
		{
			this.newCustomer(c);
		}
	}
	
}
