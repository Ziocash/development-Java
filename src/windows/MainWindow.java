package windows;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controllers.MainMenuController;
import models.TableModel;

public class MainWindow
{
	private JFrame frame;
	private JScrollPane scrollPane = new JScrollPane();
	private JMenuBar menuBar = new JMenuBar();	
	private TableModel model = new TableModel();
	private JTable table = new JTable();

	public MainWindow()
	{
		initializeComponents();
	}
	
	/**
	 * Initialize window components
	 */
	private void initializeComponents()
	{
		frame = new JFrame();
		frame.setTitle("Characters Manager - Finestra principale");
		frame.setBounds(0, 0, 700, 450);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		//------------------------------------------------		
		scrollPane.setViewportView(table);
		
		
		initializeMenu();
		initializeTable();
		frame.setJMenuBar(menuBar);
		frame.add(scrollPane);
	}
	
	/**
	 * Initialize window menus
	 */
	private void initializeMenu()
	{
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		//
		JMenuItem mntmNewWindow = new JMenuItem("New window");
		mnNewMenu.add(mntmNewWindow);
		
		MainMenuController newWindowController = new MainMenuController(mntmNewWindow, frame);
		mntmNewWindow.addActionListener(newWindowController);
		
		//
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		//
		JMenuItem mntmNewMenuItem = new JMenuItem("Delete");
		mnNewMenu.add(mntmNewMenuItem);
		
		//
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnNewMenu.add(mntmExit);
		
		MainMenuController exitController = new MainMenuController(mntmExit, frame);
		mntmExit.addActionListener(exitController);
	}
	
	/**
	 * 
	 */
	private void initializeTable()
	{
		table.setModel(model);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		for (int i = 0; i < table.getColumnCount(); i++)
		{
			int dataWidth = getColumnDataWidth(i);
			int headerWidth = getColumnHeaderWidth(i);
			table.getColumnModel().getColumn(i).setPreferredWidth(Math.max(dataWidth, headerWidth));
		}
	}

	/**
	 *  Calculates the width based on the widest cell renderer for the
	 *  given column.
	 * @param column
	 * @return
	 */
	private int getColumnDataWidth(int column)
	{
		int preferredWidth = 0;
		int maxWidth = table.getColumnModel().getColumn(column).getMaxWidth();

		for (int row = 0; row < table.getRowCount(); row++)
		{
			preferredWidth = Math.max(preferredWidth, getCellDataWidth(row, column));

			//  We've exceeded the maximum width, no need to check other rows

			if (preferredWidth >= maxWidth)
				break;
		}

		return preferredWidth + 20;
	}
	
	/**
	 * Calculates the width based on the column name
	 * @param column
	 * @return
	 */
	private int getColumnHeaderWidth(int column)
	{
		TableColumn tableColumn = table.getColumnModel().getColumn(column);
		Object value = tableColumn.getHeaderValue();
		TableCellRenderer renderer = tableColumn.getHeaderRenderer();

		if (renderer == null)
		{
			renderer = table.getTableHeader().getDefaultRenderer();
		}

		Component c = renderer.getTableCellRendererComponent(table, value, false, false, -1, column);
		return c.getPreferredSize().width + 20;
	}
	
	/**
	 * Get the preferred width for the specified cell
	 * @param row
	 * @param column
	 * @return
	 */
	private int getCellDataWidth(int row, int column)
	{
		//  Invoke the renderer for the cell to calculate the preferred width

		TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
		Component c = table.prepareRenderer(cellRenderer, row, column);
		int width = c.getPreferredSize().width + table.getIntercellSpacing().width;

		return width + 20;
	}
	
	/**
	 * 
	 */
	public void show()
	{
		frame.setVisible(true);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isShown()
	{
		return frame.isVisible();
	}
	
	/**
	 * 
	 */
	public void close()
	{
		frame.setVisible(false);
		frame.dispose();
	}
}
