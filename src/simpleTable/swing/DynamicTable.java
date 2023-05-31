package simpleTable.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import javax.swing.JTable;
//import ColorHighlighter
//import CompoundHighlighter
import javax.swing.text.Highlighter;
//import HighlighterFactory
import simpleTable.swing.table.DynamicTableColumn;
import simpleTable.swing.table.DynamicTableModel;

/**
 * @author mgg
 *
 */

//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/swing/src/main/java/org/viewaframework/swing/DynamicTable.java#L23
public class DynamicTable<T> extends JTable{

	private static final long serialVersionUID = -8747703761099581354L;
	
	/**
	 * @param dynamicTableModel
	 */
	public DynamicTable(DynamicTableModel<T> dynamicTableModel) {
		super(dynamicTableModel);
		dynamicTableModel.setTable(this);
		//this.setHighlighters(HighlighterFactory.createAlternateStriping());
		this.initDynamicTableColumns(dynamicTableModel);
	//	this.setShowGrid(false, false);
		this.getSelectionModel().addListSelectionListener(dynamicTableModel);
	}
	
	/**
	 * This method initializes column settings set to the model
	 * 
	 * @param dynamicTableModel
	 */
	private void initDynamicTableColumns(DynamicTableModel<T> dynamicTableModel) {
		TableColumnModel columnModel = this.getColumnModel();		
		for (DynamicTableColumn column : dynamicTableModel.getColumns()){			
			Integer columnIndex = columnModel.getColumnIndex(column.getPropertyName());
			TableColumn tableColumn = columnModel.getColumn(columnIndex);
			String headerValue = column.getTitle() != null ? column.getTitle() : column.getPropertyName();
			tableColumn.setHeaderValue(headerValue);
			tableColumn.setPreferredWidth(column.getWidth());
			if (column.getRenderer() != null){
				tableColumn.setCellRenderer(column.getRenderer());
			}
		}
	}

	/**
	 * Use this constructor if you want to use this kind of table just because of the 
	 * row highlights
	 */
	public DynamicTable(){
		super();
		//this.setHighlighters(HighlighterFactory.createAlternateStriping());
		//this.setShowGrid(false, false);
	}

	/**
     * Paints empty rows too, after letting the UI delegate do
     * its painting.
     */
    public void paint(Graphics g) {
        super.paint(g);
        paintEmptyRows(g);
    }

    /**
     * Paints the backgrounds of the implied empty rows when the
     * table model is insufficient to fill all the visible area
     * available to us. We don't involve cell renderers, because
     * we have no data.
     */
    protected void paintEmptyRows(Graphics g) {
    	/*Highlighter[] highlighters = this.getCompoundHighlighter().getHighlighters();
    	if (highlighters.length == 1){
    		final int rowCount = getRowCount();
    		final Rectangle clip = g.getClipBounds();
    		if (rowCount * rowHeight < clip.height) {
    			for (int i = rowCount; i <= clip.height/rowHeight; ++i) {
    				g.setColor(colorForRow(i));
    				g.fillRect(clip.x, i * rowHeight, clip.width, rowHeight);
    			}
    		}
    	}*/
    }
    
    /**
     * Returns the appropriate background color for the given row.
     */
    protected Color colorForRow(int row) {
    	/*CompoundHighlighter ch = null;
    	int rowx = 1;
        if(row % 2 == 0){
        	rowx = 0;
        }      
        ch = CompoundHighlighter.class.cast(this.getCompoundHighlighter().getHighlighters()[0]);
        */
        return Color.gray;
    }

	/* (non-Javadoc)
	 * @see org.jdesktop.swingx.JXTable#setModel(javax.swing.table.TableModel)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void setModel(TableModel dataModel) {
	 /* If we're using a DynamicTableModel, the model needs to know about the
	  * JTable internally */
		if (dataModel instanceof DynamicTableModel){
			((DynamicTableModel) dataModel).setTable(this);
		}
	 /* Finally the model is set */
		super.setModel(dataModel);
	}
}