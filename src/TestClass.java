import static java.lang.System.out;

//import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import handlers.*;
import windows.MainWindow;

class TestClass 
{
	@Test
	void test() 
	{
		//Instances
		FileHandler fh = new FileHandler();
		CharacterHandler ch = new CharacterHandler();
		ch.loadList(fh.getDb());
		//FileHandler test
		String line="Wizardous witness|1|30.0|200.0|MAGE|ARCANE WARRIOR";
		ch.addLine(line);
		//out.println(fh.getFilePath());
		out.println(ch.getCharactersList());
		//fh.addLine(line);
		line="Wizardous witness|1|40.0|420.0|MAGE|ARCANE WARRIOR";
		ch.editChar(line, 3);
		out.println(ch.getCharactersList());
		//out.println(fh.writeFile());
		//out.println(fh.getDb());
		//--------------- MainWindow test -----------------------------------
		MainWindow mw = new MainWindow();
		mw.show();
		out.println(mw.isShown());
		//
		while(mw.isShown())
			continue;
		
	}
}
