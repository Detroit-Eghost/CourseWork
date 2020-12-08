package ac.liv.csc.comp201.control;

import java.util.ArrayList;

import ac.liv.csc.comp201.model.IMachine;

public class CoinHandlerManager {
	// The coin codes are as follows
	// 1p  code = ab
	// 5p  code = ac
	// 10p code = ba
	// 20p code = bc
	// 50p code = bd
	// 100p code = ef

	public void handleCoinsIn(IMachine machine) 
	{
	
		String code=machine.getCoinMechanism().getCoinKeyCode();
		
		// TO DO add in coin handling logic here..
		// You have methods such as
		//  machine.setBalance(int   set balance in pence
		// machine.getBalance(       return balance in pence 
		if(code==null)
		{
			return;
		}
		System.out.println("Coin code is "+code);
		
		int balance = 0;
		balance = machine.getBalance();
		
		switch (code)
		{
		case "ab":
			balance += 1;
			break;
		case "ac":
			balance += 5;
			break;
		case "ba":
			balance += 10;
			break;
		case "bc":
			balance += 20;
			break;
		case "bd":
			balance += 50;
			break;
		case "ef":
			balance += 100;
		}
		
		machine.setBalance(balance);
		
	}
	
	/**
	 * Pay's the current balance in coins if it can be paid, this is a best try function
	 * if not enough coins then it pays the best it can do
	 */
	public void returnChange(IMachine machine) {
		machine.getDisplay().setTextString("Trying to pay change back");
		
		System.out.println("PAYING CHANGE!!!! balance is "+machine.getBalance());
		// These methods will be useful
		// gives coin back to customer
		//  machine.getCoinMechanism().dispenseCoin(coinCode)
		// get level of certain coin
	    //  machine.getCoinMechanism().getCoinHopperLevel(coinCode)   
		
		int balance = machine.getBalance();
		ArrayList<String> coinCode = new ArrayList<String>();
		coinCode.add("ab"); // 1p  code = ab 0
		coinCode.add("ac"); // 5p  code = ac 1
		coinCode.add("ba"); // 10p code = ba 2
		coinCode.add("bc"); // 20p code = bc 3
		coinCode.add("bd"); // 50p code = bd 4
		coinCode.add("ef"); // 100p code = ef 5
		
		int[] tmpValue = {1,5,10,20,50,100};
		
		int hl=0;
		int hopperLevel = machine.getCoinMechanism().getCoinHopperLevel(coinCode.get(hl));
		
//		System.out.println(hopperLevel);
		
		int i = 5;
		if (hopperLevel != 0 && balance>0)
		{
			if(balance>=100)
			{
				while(balance>=100)
				{
					machine.getCoinMechanism().dispenseCoin(coinCode.get(i));
					balance -= tmpValue[i];
				}
			}
			if(balance>=50&&balance<100)
			{
				i=4;
				while(balance>=50&&balance<100)
				{
					machine.getCoinMechanism().dispenseCoin(coinCode.get(i));
					balance -= tmpValue[i];
				}
			}
			if(balance>=20&&balance<50)
			{
				i=3;
				while(balance>=20&&balance<50)
				{
					machine.getCoinMechanism().dispenseCoin(coinCode.get(i));
					balance -= tmpValue[i];
				}
			}
			if(balance>=10&&balance<20)
			{
				i=2;
				while(balance>=10&&balance<20)
				{
					machine.getCoinMechanism().dispenseCoin(coinCode.get(i));
					balance -= tmpValue[i];
				}
			}
			if(balance>=5&&balance<10) 
			{
				i=1;
				while(balance>=5&&balance<10)
				{
					machine.getCoinMechanism().dispenseCoin(coinCode.get(i));
					balance -= tmpValue[i];
				}
			}
			if(balance>=1&&balance<5)
			{
				i=0;
				while(balance>=1&&balance<5)
				{
					machine.getCoinMechanism().dispenseCoin(coinCode.get(i));
					balance -= tmpValue[i];
				}
			}
		}
		else
		{
			System.out.println("the balance is 0");
		}
		
	}

}
