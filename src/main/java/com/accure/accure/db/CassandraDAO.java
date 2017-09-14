/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accure.accure.db;

import com.accure.db.in.DAO;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFSDBFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;

/**
 *
 * @author sansari
 */
public class CassandraDAO implements DAO {

    public CassandraDAO(String dbURL, String port, String schema) {
    }

    public CassandraDAO(String dbURL, String schema) {
    }

    public boolean insert(String table, String primarykey, Map<String, String> columns) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean insertJson(String table, String json) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Map<String, String> fetch(String table, String primaryKey, String[] columns) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchJson(String strTable, String strPrimaryKey) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<HashMap<String, String>> fetchMultipleRows(String table, String[] primaryKeys) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchRowsByConditions(String table, Map<String, Map<String, String>> columns) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchRowsByConditions(String table, Map<String, Map<String, String>> columns, int limit) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String insert(String table, Map<String, String> columns) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String insert(String table, String primarykey, Map<String, String> columns, String dummy) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String insert(String table, String primarykey, String primaryvalue, Map<String, String> columns) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean update(String table, String primarykey, Map<String, String> columns) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean createlist(String table, String primarykey, String listname, ArrayList list) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String[] getlist(String table, String primarykey, String listname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean addtolist(String table, String primarykey, String listname, String listvalue) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deletefromlist(String table, String primarykey, String listname, String listvalue) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Map<String, String> fetch(String table, String fieldname, String fieldValue, String[] columns) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchAllRowsByConditions(String table, Map<String, String> conditions) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchAllRowsLike(String table, Map<String, String> conditions, String strLogic) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchRowsByConditions(String table, Map<String, Map<String, String>> columns, String strLogic) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean createMap(String strTable, String strPrimaryKey, String strMapName, Map keyValues) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Map getMap(String strTable, String strPrimaryKey, String strMapName) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteFromMap(String strTable, String strPrimaryKey, String strMapName, String strKeyName) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean updateToMap(String strTable, String strPrimaryKey, String strMapName, String strKeyName, String KeyValue) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String storeBinaryFile(String strFilePath, String strDocType, String strOwnerID) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchRowsByConditions(String table, String strInFieldName, String[] matchValues, String strSortKey, String strSortOrder) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String insert(String table, String json) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetch(String strTable, String strPrimaryKey) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean update(String table, String primarykey, String json) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCount(String strTable, Map<String, String> conditions) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchRowsLike(String table, Map<String, String> conditions, String strLogic, int fromIndex, int toIndex) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String storeBinaryFile(String strFilePath, String strDocType, String strOwnerID, String bucketName) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public GridFSDBFile getDbFile(String fileID, String bucketName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean getToFileSystem(String fileID, String bucketName, String outFilePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String listAllFiles(String OwnerId, String bucketName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public InputStream getDbFileInputStream(String fileID, String bucketName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MongoFile getGridFsFile(String fileID, String bucketName) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean removeFileFromDb(String fileID, String bucketName) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String storeBinaryFile(MongoFile mf) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchRowsByConditions(String table, List<Query> qlist) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String fetchDistinctList(String table, String columnname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchRowsByConditions(String table, List<ObjectId> matchValues, String strSortKey, String strSortOrder) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String listAllFilesByCondition(String folderName, HashMap<String, String> conditions) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchRowsByDatePeriods(String strTable, String strDateColName, String strStartDate, String strEndDate, Map<String, Map<String, String>> columns) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchRowsByConditions(String strTable, String columnName, List<String> matchValues, String strSortKey, String strSortOrder) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchAll(String strTable) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long countAll(String strTable) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteDocument(String strTable, String strPrimaryKey) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String searchFromMultiColumn(String table, Map<String, Object> conditions, String strLogic, int fromIndex, int limit) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String searchFromMultiColumnMore(String table, Map<String, Object> conditions, String strLogic, int fromIndex, int limit) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String aggregateWithMatchGroupAndSort(String strTable, Map<String, Object> cond, String matchCond) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String fetchRowsByConditions(String strTable, Map<String, String> conditions, int fromIndex, int limit) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean delete(String strTable, String strPrimaryKey) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DB getDB() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public boolean insertMultiple(String strTable, String json) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
}