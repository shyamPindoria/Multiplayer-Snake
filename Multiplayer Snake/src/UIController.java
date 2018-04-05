import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class UIController extends JFrame {

	/**
	 *  Default Serial Version
	 */
	private static final long serialVersionUID = 1L;

	public UIController() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	public UIController(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public UIController(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public UIController(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

}
