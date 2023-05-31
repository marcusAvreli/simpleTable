package simpleTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import simpleTable.swing.DynamicTable;
import simpleTable.swing.MasterViewPanel;
import simpleTable.swing.PaginationPanel;
import simpleTable.swing.PaginationPanel.PaginationStatus;
import simpleTable.swing.pagination.PaginationEvent;
import simpleTable.swing.pagination.PaginationListener;
import simpleTable.swing.table.DynamicTableColumn;
import simpleTable.swing.table.DynamicTableModel;


public class MainFrame  extends JFrame{
	private List<Member> memberList;
	private List<DynamicTableColumn> columns;
	private DynamicTable<Member> dynamicTable;
	private DynamicTableModel<Member> dynamicTableModel;
	private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);
	public MainFrame() {
		initComponents();
	}

	public void initComponents() {
		Dimension dimension = new Dimension(700, 700);
		// setSize(dimension);
		
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//desktop.setOpaque(true);
		memberList = Arrays.asList(
				new Member("Joe","0392jr"),
				new Member("Janet","323rr"),
				new Member("Blackham","3r23r"),
				new Member("Erikka","234f2"),
				new Member("Moira","23d23d"),
				new Member("Ulrich","23f23f"),
				new Member("Blackham","3r23r"),
				new Member("Erikka","234f2"),
				new Member("Moira","23d23d"),
				new Member("Ulrich","23f23f"),
				new Member("Joe","0392jr"),
				new Member("Janet","323rr"),
				new Member("Blackham","3r23r"),
				new Member("Erikka","234f2"),
				new Member("Moira","23d23d"),
				new Member("Ulrich==","23f23f"),
				new Member("Blackham","3r23r"),
				new Member("Erikka","234f2"),
				new Member("Moira","23d23d"),
				new Member("Ulrich","23f23f"),
				new Member("Joe","0392jr"),
				new Member("Janet","323rr"),
				new Member("Blackham","3r23r"),
				new Member("Erikka","234f2"),
				new Member("Moira","23d23d"),
				new Member("Ulrich","23f23f"),
				new Member("Blackham===","3r23r"),
				new Member("Erikka","234f2"),
				new Member("Moira","23d23d"),
				new Member("Ulrich","23f23f"),
				new Member("Joe","0392jr"),
				new Member("Janet","323rr"),
				new Member("Blackham","3r23r"),
				new Member("Erikka","234f2"),
				new Member("Moira","23d23d"),
				new Member("Ulrich","23f23f"),
				new Member("Blackham","3r23r"),
				new Member("Erikka","234f2"),
				new Member("Moira","23d23d"),
				new Member("Ulrich","23f23f")
			);
			columns = Arrays.asList(
				new DynamicTableColumn("name",0,200),
				new DynamicTableColumn("password",1,50)
			);
			logger.info("size:"+memberList.size());
			dynamicTableModel = new DynamicTableModel<Member>(columns);
			dynamicTable = new DynamicTable<Member>(dynamicTableModel);
			dynamicTableModel.addAll(memberList.subList(0, 9));
			MasterViewPanel<Member> mvp = new MasterViewPanel<>(dynamicTable);
			
			
			PaginationPanel paginationPanel = mvp.getPaginationPanel();
			
			paginationPanel.getPaginationStatus().setTotalResults(memberList.size());
	 		paginationPanel.setEnabled(true);
	 		
	 		paginationPanel.setRecordsShownPattern("Showing records %1$d to %2$d");
	 		paginationPanel.addPaginationListener(new PaginationListener() {
	 			public void paginationPerformed(PaginationEvent ev) {
	 				//logger.info("ev:"+ev.getSource());
	 				logger.info("ev_source:"+ev.getSource());
	 				//logger.info("ev_source:"+ev.setPaginationStatus(PaginationStatus.this.setOffset(1)));
	 				PaginationStatus status = ev.getPaginationStatus();
	 				//status.setOffset(2);
	 				ev.setPaginationStatus(status);
	 				int start = status.getFirstRecordInPage();
	 				int end = status.getLastRecordInPage();
	 				logger.info("ev_source:"+ev.getSource());
	 				logger.info("start:"+start+ " end:"+end);
	 				//List<CustomApplication> subRows = rows.subList(startIndex, endIndex);
					
					
	 				dynamicTableModel.addAll(memberList.subList(start, end));
	 				
	 			}
	 		});
	 		mvp.setPreferredSize(dimension);
			//frame.getContentPane().add(new JScrollPane(dynamicTable));
		getContentPane().add(mvp);
	//	frame.add(desktop, BorderLayout.CENTER);
		setSize(dimension);
		setVisible(true);
		pack();

	}
}
