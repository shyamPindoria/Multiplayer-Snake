package tests;

import org.junit.runner.RunWith; 
import org.junit.runners.Suite;

@RunWith(Suite.class) 
@Suite.SuiteClasses({
	CellTest.class,
	SnakeTest.class,
	PlayerTest.class,
	GameBoardTest.class
})

public class TestRunner {

}
