package com.roy.landcomparator.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

class ColorRenderer extends JLabel implements TableCellRenderer {

    private String columnName;
    List<Double> marker;

    public ColorRenderer(String column, List<Double> marker) {
        this.columnName = column;
        this.marker = marker;
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean selected, boolean hasFocus, int row, int column) {
        Object columnValue = table.getValueAt(row, table.getColumnModel()
                .getColumnIndex(columnName));
        if (value != null) {
            setText(value.toString());
        }
        setBackground(table.getBackground());
        setForeground(table.getForeground());

        if (marker.get(0) == 0.1) {
            for (int i = 1; i < marker.size(); i++) {
                if (columnValue.toString().equals(marker.get(i).toString())) {
                    setBackground(Color.red);
                }

            }
        }

        if (marker.get(0) == 0.2) {
            for (int i = 1; i < marker.size(); i++) {
                if (columnValue.toString().equals(marker.get(i).toString())) {
                    setBackground(Color.yellow);
                }

            }
        }

        return this;
    }
}
