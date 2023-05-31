package simpleTable.swing.pagination;

/**
 * Those who want to be posted about {@link PaginationPanel} events should
 * implement this interface
 * 
 * @author mgg
 *
 */
public interface PaginationListener {

	/**
	 * @param ev
	 */
	public void paginationPerformed(PaginationEvent ev);
}