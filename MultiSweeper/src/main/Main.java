package main;

import view.Viewer;

public class Main
{
	private Main()
	{}
	
	public static void main(String[] args)
	{
		if (args.length == 0) new Viewer(5);
		else new Viewer(Integer.parseInt(args[0]));
	}
}
