package com.rod.log.model;

import java.util.List;
import java.util.function.Function;

import javax.swing.table.AbstractTableModel;

public class TagsFixasModel extends AbstractTableModel
	{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private final List<Tag> tarefasJira;
	     
	    private final String[] columnNames = new String[] {
	    		"Tag", "Descrição"
	    };
	    	    
	    @SuppressWarnings("rawtypes")
		private final Class[] columnClass = new Class[] {
	        String.class, String.class, Integer.class
	    };
	 
	    public TagsFixasModel(List<Tag> tarefas)
	    {
	        this.tarefasJira = tarefas;
	    }
	     
	    @Override
	    public String getColumnName(int column)
	    {
	        return columnNames[column];
	    }
	 
	    @Override
	    public Class<?> getColumnClass(int columnIndex)
	    {
	        return columnClass[columnIndex];
	    }
	 
	    @Override
	    public int getColumnCount()
	    {
	        return columnNames.length;
	    }
	 
	    @Override
	    public int getRowCount()
	    {
	        return tarefasJira.size();
	    }
	 
	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex)
	    {
	        Tag row = tarefasJira.get(rowIndex);
	        if(0 == columnIndex) {
	            return row.getTag();
	        }
	        else if(1 == columnIndex) {
	            return row.getDescricao();
	        }
	        
	        return null;
	    }
	    
	    @Override
	    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	    {
	        Tag row = tarefasJira.get(rowIndex);
	        if(0 == columnIndex) {
	            row.setTag((String) aValue);
	        }
	        else if(1 == columnIndex) {
	            row.setDescricao((String) aValue);
	        }
	    }
	    
	    @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex)
	    {
	        return true;
	    }	    
	}
