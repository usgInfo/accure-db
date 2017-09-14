/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accure.accure.db;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author user1
 */
public class Query {
    private String tableName;
    private String columnName;
    private String columnValue;
    private String logicOperator;
    private String[] matchValues;
    private List<ObjectId> pkmatchValues=null;
    private String condition;

    public List<ObjectId> getPkmatchValues() {
        return pkmatchValues;
    }

    public void setPkmatchValues(List<ObjectId> pkmatchValues) {
        this.pkmatchValues = pkmatchValues;
    }

    

    public String getColumnValue() {
        return columnValue;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getLogicOperator() {
        return logicOperator;
    }

    public void setLogicOperator(String logicOperator) {
        this.logicOperator = logicOperator;
    }

    public String[] getMatchValues() {
        return matchValues;
    }

    public void setMatchValues(String[] matchValues) {
        this.matchValues = matchValues;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
}
