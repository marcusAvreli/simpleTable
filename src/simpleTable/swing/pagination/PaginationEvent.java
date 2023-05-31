package simpleTable.swing.pagination;

import simpleTable.swing.PaginationPanel;
import simpleTable.swing.PaginationPanel.Names;
import simpleTable.swing.PaginationPanel.PaginationStatus;

/**
 * This event will be thrown by a {@link PaginationPanel}
 * 
 * @author mgg
 *
 */
public class PaginationEvent{
	
	private PaginationPanel paginationPanel;
	private Object source;
	private PaginationStatus status;
	private Names trigger;
	
	/**
	 * @return
	 */
	public PaginationPanel getPaginationPanel() {
		return paginationPanel;
	}
	/**
	 * @return
	 */
	public PaginationStatus getPaginationStatus() {
		return status;
	}
	/**
	 * @return
	 */
	public Object getSource() {
		return source;
	}
	/**
	 * @return
	 */
	public Names getTrigger() {
		return trigger;
	}
	/**
	 * @param paginationPanel
	 */
	public void setPaginationPanel(PaginationPanel paginationPanel) {
		this.paginationPanel = paginationPanel;
	}
	/**
	 * @param status
	 */
	public void setPaginationStatus(PaginationStatus status) {
		this.status = status;
	}
	/**
	 * @param source
	 */
	public void setSource(Object source) {
		this.source = source;
	}
	/**
	 * @param trigger
	 */
	public void setTrigger(Names trigger) {
		this.trigger = trigger;
	}
}