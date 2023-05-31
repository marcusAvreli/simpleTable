package simpleTable.swing.table;

import javax.swing.table.TableCellRenderer;

/**
 * @author mgg
 *
 */

//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/swing/src/main/java/org/viewaframework/swing/table/DynamicTableColumn.java
public class DynamicTableColumn implements Comparable<DynamicTableColumn>{

	private boolean grouping;
	private Integer order;
	private String propertyName;
	private TableCellRenderer renderer;
	private String title;
	private Integer width = 100;

	/**
	 * 
	 */
	public DynamicTableColumn(){
		super();
	}

	/**
	 * @param propertyName
	 * @param width
	 */
	public DynamicTableColumn(String propertyName,Integer order,Integer width) {
		super();
		this.propertyName = propertyName;
		this.width = width;
		this.order = order;
	}

	/**
	 * @param propertyName
	 * @param width
	 * @param renderer
	 */
	public DynamicTableColumn(String propertyName,Integer order,Integer width,TableCellRenderer renderer){
		this(propertyName,order,width);
		this.renderer = renderer;
	}

	/**
	 * @return
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * @return
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @return
	 */
	public TableCellRenderer getRenderer() {
		return renderer;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @return
	 */
	public boolean isGrouping() {
		return grouping;
	}

	/**
	 * @param grouping
	 */
	public void setGrouping(boolean grouping) {
		this.grouping = grouping;
	}
	
	/**
	 * @param order
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * @param propertyName
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	/**
	 * @param renderer
	 */
	public void setRenderer(TableCellRenderer renderer) {
		this.renderer = renderer;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param width
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(DynamicTableColumn o) {
		return this.getOrder().compareTo(o.getOrder());
	}
}