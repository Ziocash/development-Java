import static java.lang.System.out;

import org.junit.jupiter.api.Test;

import handlers.*;
import windows.CharacterCreationWindow;
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
		String line="1|Wizardous witness|1|30.0|200.0|0|0|0|MAGE|ARCANE WARRIOR";
		ch.addLine(line);
		out.println(ch.getCharactersList());
		line="1|Wizardous witness|1|30.0|440.0|0|0|0|MAGE|ARCANE WARRIOR";
		ch.editChar(line, 3);
		out.println(ch.getCharactersList());
		fh.setDb(ch.parseList());
		//--------------- MainWindow test -----------------------------------		
		MainWindow mw = new MainWindow();
		mw.show();
		out.println(mw.isShown());
		
		CharacterCreationWindow charWindow = new CharacterCreationWindow();
		charWindow.show();
		//
		while(charWindow.isShown())
		{
			//if(charWindow.isShown() == false)
				//ch.addCharacter(charWindow.getNewCharacter());
			continue;
		}
	}
}
