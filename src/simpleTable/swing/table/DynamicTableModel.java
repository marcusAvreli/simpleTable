package simpleTable.swing.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





/**
 * @author mgg
 *
 * @param <T>
 */
//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/swing/src/main/java/org/viewaframework/swing/table/DynamicTableModel.java#L22
public class DynamicTableModel <T> extends AbstractTableModel 
	implements ListSelectionListener{
	private static final Logger logger = LoggerFactory.getLogger(DynamicTableModel.class);
	
	private static final long serialVersionUID = 1L;
	private List<DynamicTableColumn> columns;
	private T selectedObject;
	private List<T> selectedObjects;
	private List<T> sourceList;
	private JTable table;
	private void debugJustInCase(String message) {
		if (logger.isDebugEnabled()) {
			logger.info(message);
		}
	}
	public DynamicTableModel(List<DynamicTableColumn> columns) {
		super();
		this.sourceList = new ArrayList<T>();
		this.columns = columns;
		this.selectedObjects = new ArrayList<T>();
	}
	
	public DynamicTableModel(){
		super();
		this.sourceList = new ArrayList<T>();
		this.columns = new ArrayList<DynamicTableColumn>();
		this.selectedObjects = new ArrayList<T>();
	}
	
	/**
	 * @param column
	 */
	public void addColumn(DynamicTableColumn column){
		this.columns.add(column);
	}

	/**
	 * @param srcList
	 */
	public synchronized void addAll(Collection<T> srcList){
		this.sourceList.clear();
		this.sourceList.addAll(srcList);
		debugJustInCase("add_all_called");
		debugJustInCase("add_all_called:"+this.getRowCount() );
		this.fireTableDataChanged();
		if (this.getRowCount() > 0){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					table.setRowSelectionInterval(0,0);					
				}
			});
		}
	}

	/**
	 * @param e
	 */
	public synchronized void addRow(T e){
		this.sourceList.add(e);
		this.fireTableDataChanged();
		int modelIndex = this.sourceList.indexOf(e);
		int visualIndex = this.table.convertRowIndexToView(modelIndex);		
		this.table.setRowSelectionInterval(visualIndex,visualIndex);
	}
	/**
	 * 
	 */
	public synchronized void clear(){
		this.sourceList.clear();
		this.fireTableDataChanged();
	}

	/**
	 * @param e
	 * @return
	 */
	public boolean contains(T e){
		return this.sourceList.contains(e);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Comparable.class;
	}	
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columns.size();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		return columns.get(column).getPropertyName();
	}
	
	/**
	 * @return
	 */
	public List<DynamicTableColumn> getColumns() {
		return columns;
	}
	
	/**
	 * @param rowIndex
	 * @return
	 */
	public T getRow(Integer rowIndex){
		return this.sourceList.get(rowIndex);
	}
		
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return sourceList!= null ? sourceList.size() : 0;
	}
	
	/**
	 * @return
	 */
	public T getSelectedObject() {
		return selectedObject;
	}
	
	/**
	 * @return
	 */
	public List<T> getSelectedObjects() {
		return selectedObjects;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		debugJustInCase("get_value_at_called");
		T currentObject = this.sourceList.get(rowIndex);
		Object valueObject = null;
		try {
			valueObject = new PropertyUtilsBean().
					getProperty(
						currentObject,
						columns.get(columnIndex).getPropertyName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		debugJustInCase("valueObject:"+valueObject);
		debugJustInCase("get_value_at_finished");
		return valueObject;
	}

	/**
	 * @param e
	 */
	public synchronized void removeRow(T e){
		int modelIndex = this.sourceList.indexOf(e);
		this.sourceList.remove(e);
		int visualIndex = this.table.convertRowIndexToView(modelIndex);
		this.fireTableRowsDeleted(visualIndex, visualIndex);		
		this.fireTableDataChanged();
	}
	
	/**
	 * @param e
	 */
	public void setSelectedObject(T e) {
		this.selectedObject = e;
	}

	/**
	 * @param selectedObjects
	 */
	public void setSelectedObjects(List<T> selectedObjects) {
		this.selectedObjects = selectedObjects;
	}

	/**
	 * @param table
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()){				
			int minIndex = table.getSelectionModel().getMinSelectionIndex();
			int maxIndex = table.getSelectionModel().getMaxSelectionIndex();			
			try {
				if (minIndex >= 0){					
					this.selectedObjects.clear();
					for (int i = minIndex ; i<=maxIndex;i++){
						int realIndex = table.convertRowIndexToModel(i);
						T value = getRow(realIndex);
                        if (table.getSelectionModel().isSelectedIndex(i)) {
						    this.selectedObjects.add(value);
                         /* Always the first occurrence is the result of calling getSelectedObject() */
                            if (i == minIndex){
                                this.selectedObject = value;
                            }
                            if (logger.isDebugEnabled()){
                                logger.debug(
                                    new StringBuilder().
                                        append("[M:").append(realIndex).
                                        append(" | V:").append(minIndex ++).
                                        append("] ").append(value).toString());
                            }
                        }
					}
				}
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			} 
		}
	}
}