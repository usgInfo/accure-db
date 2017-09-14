package com.accure.accure.db;

import com.accure.db.in.DAO;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.QueryBuilder;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.mongodb.util.JSON;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bson.types.ObjectId;
import org.omg.CORBA.DynAnyPackage.InvalidValue;

/**
 *
 * @author kalyank
 */
public class MongodbDAO implements DAO {

    private static MongodbDAO mngDao = null;
    private static MongodbDAO mngDefaultDao = null;
    private DB mngDb = null;
    private MongoClient mngClient = null;
    private String strMngPort = null;
    private String strMngUrl = null;
    private String strMngSchema = null;

    private MongoClientOptions getClientOptions() {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.cursorFinalizerEnabled(false);
        builder.connectionsPerHost(500);
        return builder.build();
    }

    @Deprecated
    private MongodbDAO(String strUrl) {
        try {
            //Connect to Database
//            mngClient = new MongoClient(strUrl);
            mngClient = new MongoClient(strUrl, getClientOptions());
            // initialize the members
            strMngUrl = strUrl;
        } catch (UnknownHostException ex) {
            Logger.getLogger(MongodbDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Deprecated
    private MongodbDAO(String strUrl, String strPort) {
        try {
            //Connect to Database
//            mngClient = new MongoClient(strUrl, Integer.parseInt(strPort));
            mngClient = new MongoClient(new ServerAddress(strUrl, Integer.parseInt(strPort)), getClientOptions());
            // initialize the members
            strMngUrl = strUrl;
            strMngPort = strPort;
        } catch (UnknownHostException ex) {
            Logger.getLogger(MongodbDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private MongodbDAO(String strUrl, String uname, String pwd, String strSchema) {
        try {
            //Connect to Database
            MongoCredential credential = MongoCredential.createMongoCRCredential(uname, strSchema, pwd.toCharArray());
//            mngClient = new MongoClient(new ServerAddress(strUrl), Arrays.asList(credential));
            mngClient = new MongoClient(new ServerAddress(strUrl), Arrays.asList(credential), getClientOptions());
            // initialize the members
            strMngUrl = strUrl;
        } catch (UnknownHostException ex) {
            Logger.getLogger(MongodbDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private MongodbDAO(String strUrl, String strPort, String uname, String pwd, String strSchema) {
        try {
            //Connect to Database
            MongoCredential credential = MongoCredential.createMongoCRCredential(uname, strSchema, pwd.toCharArray());
//            mngClient = new MongoClient(new ServerAddress(strUrl), Arrays.asList(credential));
            mngClient = new MongoClient(new ServerAddress(strUrl, Integer.parseInt(strPort)), Arrays.asList(credential), getClientOptions());
            // initialize the members
            strMngUrl = strUrl;
            strMngPort = strPort;
        } catch (UnknownHostException ex) {
            Logger.getLogger(MongodbDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param strUrl
     * @param strPort
     * @param strSchema
     * @return
     */
    @Deprecated
    public static synchronized MongodbDAO getInstance(String strUrl, String strPort, String strSchema) {
        //create new instance if it is null or host / port is different
        if (mngDao == null) {
            mngDao = new MongodbDAO(strUrl, strPort);
        } else if (mngDao.mngClient == null) {
            mngDao = new MongodbDAO(strUrl, strPort);
        } else if (!strUrl.equalsIgnoreCase(mngDao.strMngUrl)
                || !strPort.equalsIgnoreCase(mngDao.strMngPort)) {
            //first close the connection and recreate it
            mngDao.close();
            mngDao = new MongodbDAO(strUrl, strPort);
        }
        //update the schema
        if (!strSchema.equalsIgnoreCase(mngDao.strMngSchema)) {
            // if database doesn't exists, MongoDB will create it for you
            mngDao.mngDb = mngDao.mngClient.getDB(strSchema);
            mngDao.strMngSchema = strSchema;
        }

        //return instance for specified port
        return mngDao;
    }

    public static synchronized MongodbDAO getInstance(String strUrl, String strPort, String strSchema, String uname, String pwd) {
        //create new instance if it is null or host / port is different
        if (mngDao == null) {
            mngDao = new MongodbDAO(strUrl, strPort, uname, pwd, strSchema);
        } else if (mngDao.mngClient == null) {
            mngDao = new MongodbDAO(strUrl, strPort, uname, pwd, strSchema);
        } else if (!strUrl.equalsIgnoreCase(mngDao.strMngUrl)
                || !strPort.equalsIgnoreCase(mngDao.strMngPort)) {
            //first close the connection and recreate it
            mngDao.close();
            mngDao = new MongodbDAO(strUrl, strPort, uname, pwd, strSchema);
        }
        //update the schema
        if (!strSchema.equalsIgnoreCase(mngDao.strMngSchema)) {
            // if database doesn't exists, MongoDB will create it for you
            mngDao.mngDb = mngDao.mngClient.getDB(strSchema);
            mngDao.strMngSchema = strSchema;
        }

        //return instance for specified port
        return mngDao;
    }

    public static synchronized MongodbDAO getDefaultInstance(String strUrl, String strSchema, String uname, String pwd) {
        //create new instance if it is null or host is different
        if (mngDefaultDao == null) {
            mngDefaultDao = new MongodbDAO(strUrl, uname, pwd, strSchema);
        } else if (mngDefaultDao.mngClient == null) {
            mngDefaultDao = new MongodbDAO(strUrl, uname, pwd, strSchema);
        } else if (!strUrl.equalsIgnoreCase(mngDefaultDao.strMngUrl)) {
            //first close the connection and recreate it
            mngDefaultDao.close();
            mngDefaultDao = new MongodbDAO(strUrl, uname, pwd, strSchema);
        }
        //update the schema
        if (!strSchema.equalsIgnoreCase(mngDefaultDao.strMngSchema)) {
            // if database doesn't exists, MongoDB will create it for you
            mngDefaultDao.mngDb = mngDefaultDao.mngClient.getDB(strSchema);
            mngDefaultDao.strMngSchema = strSchema;
        }

        //return instance for specified port
        return mngDefaultDao;
    }

    /**
     *
     * @param strUrl
     * @param strSchema
     * @return
     */
    @Deprecated
    public static synchronized MongodbDAO getDefaultInstance(String strUrl, String strSchema) {
        //create new instance if it is null or host is different
        if (mngDefaultDao == null) {
            mngDefaultDao = new MongodbDAO(strUrl);
        } else if (mngDefaultDao.mngClient == null) {
            mngDefaultDao = new MongodbDAO(strUrl);
        } else if (!strUrl.equalsIgnoreCase(mngDefaultDao.strMngUrl)) {
            //first close the connection and recreate it
            mngDefaultDao.close();
            mngDefaultDao = new MongodbDAO(strUrl);
        }
        //update the schema
        if (!strSchema.equalsIgnoreCase(mngDefaultDao.strMngSchema)) {
            // if database doesn't exists, MongoDB will create it for you
            mngDefaultDao.mngDb = mngDefaultDao.mngClient.getDB(strSchema);
            mngDefaultDao.strMngSchema = strSchema;
        }

        //return instance for specified port
        return mngDefaultDao;
    }

    /**
     *
     * @return String
     */
    public String getDbName() {
        return strMngSchema;
    }

    /**
     *
     * @return DB
     */
    public DB getDB() {
        return mngDb;
    }

    public boolean insert(String strTable, String strPrimaryKey, Map<String, String> columns) throws Exception {
        //System.out.println(primaryKey + "---------" + strTable);
        //_id field is reserved for primary key in mongodb, and that should be an unique value.
        //If you don't set anything to _id it ill automatically fill it with "MongoDB Id Object".
        //But you can put any unique info into that field.
        boolean bStatus = false;

        // if collection doesn't exists, MongoDB will create it for you
        DBCollection dbTable = mngDb.getCollection(strTable);

        // create a document to store key and value
        BasicDBObject document = new BasicDBObject(columns);

        document.put("createddate", new Date());
        WriteResult wRes = dbTable.insert(document);

        if (wRes.getError() == null) {
            bStatus = true;
        }
        return bStatus;
    }

    public String insert(String strTable, String json) throws Exception {
        //System.out.println(primaryKey + "---------" + strTable);
        //_id field is reserved for primary key in mongodb, and that should be an unique value.
        //If you don't set anything to _id it ill automatically fill it with "MongoDB Id Object".
        //But you can put any unique info into that field.
        boolean bStatus = false;

        // if collection doesn't exists, MongoDB will create it for you
        DBCollection dbTable = mngDb.getCollection(strTable);

        // create a document to store key and value
        DBObject dbObject = (DBObject) JSON.parse(json);

        WriteResult wRes = dbTable.insert(dbObject);

        String result = ((ObjectId) dbObject.get("_id")).toString();
        if (result.length() > 0) {
            return result;
        } else {
            return null;
        }
    }

    public boolean insertMultiple(String strTable, String json) throws Exception {
        //System.out.println(primaryKey + "---------" + strTable);
        //_id field is reserved for primary key in mongodb, and that should be an unique value.
        //If you don't set anything to _id it ill automatically fill it with "MongoDB Id Object".
        //But you can put any unique info into that field.
        boolean bStatus = false;

        // if collection doesn't exists, MongoDB will create it for you
        DBCollection dbTable = mngDb.getCollection(strTable);

        // create a document to store key and value
        List<DBObject> dbObject = (List<DBObject>) JSON.parse(json);

        WriteResult wRes = dbTable.insert(dbObject);
        bStatus = wRes.getLastConcern().callGetLastError();
        return bStatus;
    }

    public String fetch(String strTable, String strPrimaryKey) throws Exception {
        DBCursor cursor = null;
        String row = null;
        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new ObjectId(strPrimaryKey));
        BasicDBObject fields = new BasicDBObject();

        cursor = dbTable.find(whereQuery);

        if (cursor.size() > 0) {
            JSON json = new JSON();
            row = json.serialize(cursor);
            cursor.close();
            return row;
        } else {
            cursor.close();
            return null;
        }
    }

    public Map<String, String> fetch(String strTable, String strPrimaryKey, String[] columns) throws Exception {
        DBCursor cursor = null;
        Map<String, String> row = null;
        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new ObjectId(strPrimaryKey));
        BasicDBObject fields = new BasicDBObject();

        if (columns != null) {
            for (String column : columns) {
                fields.put(column, 1);
            }

            cursor = dbTable.find(whereQuery, fields);

            //displayCursor(cursor);
            row = getSingleRowMap(cursor);
        } else {
            cursor = dbTable.find(whereQuery);
            //displayCursor(cursor);
            row = getSingleRowMap(cursor);
        }
        if (cursor.size() > 0) {
            cursor.close();
            return row;
        } else {
            cursor.close();
            return null;
        }

    }

    /**
     *
     * @param strTable
     * @param strFieldname
     * @param strFieldvalue
     * @param columns
     * @return
     * @throws Exception
     */
    public Map<String, String> fetch(String strTable, String strFieldname, String strFieldvalue, String[] columns) throws Exception {
        DBCursor cursor = null;
        Map<String, String> row = null;
        DBCollection dbTable = mngDb.getCollection(strTable);

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put(strFieldname, strFieldvalue);
        BasicDBObject fields = new BasicDBObject();
        if (columns != null) {
            for (String column : columns) {
                fields.put(column, 1);
            }

            cursor = dbTable.find(whereQuery, fields);
            //displayCursor(cursor);
            row = getSingleRowMap(cursor);
        } else {
            cursor = dbTable.find(whereQuery);
            //displayCursor(cursor);
            row = getSingleRowMap(cursor);
        }
        if (cursor.size() > 0) {
            cursor.close();
            return row;
        } else {
            cursor.close();
            return null;
        }
    }

    public ArrayList<HashMap<String, String>> fetchMultipleRows(String strTable, String[] primaryKeys) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
     * Map<String, Map<String, String>> ==> column: condition: value
     * map.put("col1", nul);
     * map.put("col2", map1.put("equal", "amit"));
     * dao.fetchRowsByConditions("person", map);
     */
    public String fetchRowsByConditions(String strTable, Map<String, Map<String, String>> columns) throws Exception {
        //  int limit = 100;
        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject searchObject = new BasicDBObject();
        List<BasicDBObject> lstSearchArgs = new ArrayList<BasicDBObject>();
        lstSearchArgs.clear();

        if (columns != null && !columns.isEmpty()) {

            Set keys = columns.keySet();
            Iterator it = keys.iterator();
            while (it.hasNext()) {
                String column = (String) it.next();

                if (column.equalsIgnoreCase("SORTINFOKEY") || column.equalsIgnoreCase("SORTORDERKEY")) {
                    //System.out.println(column);
                    continue;
                }

                Map<String, String> columnAttribute = columns.get(column);
                if (columnAttribute != null) {
                    String condition = columnAttribute.keySet().iterator().next();
                    String value = columnAttribute.get(condition);
                    if (condition.equalsIgnoreCase("equal")) {
                        lstSearchArgs.add(new BasicDBObject(column, value));

                    } else if (condition.equalsIgnoreCase("lt")) {

                        lstSearchArgs.add(new BasicDBObject(column, new BasicDBObject("$lt", value)));
                    } else if (condition.equalsIgnoreCase("lte")) {
                        lstSearchArgs.add(new BasicDBObject(column, new BasicDBObject("$lte", value)));
                    } else if (condition.equalsIgnoreCase("gt")) {
                        lstSearchArgs.add(new BasicDBObject(column, new BasicDBObject("$gt", value)));

                    } else if (condition.equalsIgnoreCase("gte")) {
                        lstSearchArgs.add(new BasicDBObject(column, new BasicDBObject("$gte", value)));
                    } else { // default
                        lstSearchArgs.add(new BasicDBObject(column, value));

                    }
                }
            }
            searchObject.put("$and", lstSearchArgs);
            DBCursor cursor = dbTable.find(searchObject);

            if (cursor.size() > 0) {
                //displayCursor(cursor);
                sortLogicalResults(cursor, columns);
                JSON json = new JSON();
                String lstRes = json.serialize(cursor);
                cursor.close();
                return lstRes;
            } else {
                cursor.close();
                return null;
            }
        } else {
            throw new Exception("Must specify a conditional column");
        }
    }

    public String fetchRowsByConditions(String strTable, Map<String, Map<String, String>> columns, int limit) throws Exception {
        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject searchObject = new BasicDBObject();
        List<BasicDBObject> lstSearchArgs = new ArrayList<BasicDBObject>();
        lstSearchArgs.clear();

        if (columns != null && !columns.isEmpty()) {

            Set keys = columns.keySet();
            Iterator it = keys.iterator();
            while (it.hasNext()) {
                String column = (String) it.next();
                if (column.equalsIgnoreCase("SORTINFOKEY") || column.equalsIgnoreCase("SORTORDERKEY")) {
                    //System.out.println(column);
                    continue;
                }
                Map<String, String> columnAttribute = columns.get(column);
                if (columnAttribute != null) {
                    String condition = columnAttribute.keySet().iterator().next();
                    String value = columnAttribute.get(condition);
                    if (condition.equalsIgnoreCase("equal")) {
                        lstSearchArgs.add(new BasicDBObject(column, value));

                    } else if (condition.equalsIgnoreCase("lt")) {

                        lstSearchArgs.add(new BasicDBObject(column, new BasicDBObject("$lt", value)));
                    } else if (condition.equalsIgnoreCase("lte")) {
                        lstSearchArgs.add(new BasicDBObject(column, new BasicDBObject("$lte", value)));

                    } else if (condition.equalsIgnoreCase("gt")) {
                        lstSearchArgs.add(new BasicDBObject(column, new BasicDBObject("$gt", value)));

                    } else if (condition.equalsIgnoreCase("gte")) {
                        lstSearchArgs.add(new BasicDBObject(column, new BasicDBObject("$gte", value)));

                    } else { // default
                        lstSearchArgs.add(new BasicDBObject(column, value));

                    }
                }
            }

            searchObject.put("$and", lstSearchArgs);
            DBCursor cursor = dbTable.find(searchObject).limit(limit);
//            System.out.println(cursor.explain());
            if (cursor.size() > 0) {
                //displayCursor(cursor);
                sortLogicalResults(cursor, columns);
                JSON json = new JSON();
                String lstRes = json.serialize(cursor);
                cursor.close();
                return lstRes;
            } else {
                cursor.close();
                return null;
            }
        } else {
            throw new Exception("Must specify a conditional column");
        }

    }

    /// fetches all rows matching multiple field values in an AND operation
    public String fetchAllRowsByConditions(String strTable, Map<String, String> conditions) throws Exception {
        //   int limit = 100;

        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> lstObj = new ArrayList<BasicDBObject>();

        Set<String> keys = conditions.keySet();
        for (String key : keys) {
            lstObj.add(new BasicDBObject(key, conditions.get(key)));

        }

        andQuery.put("$and", lstObj);
        //System.out.println(andQuery.toString());

        DBCursor cursor = dbTable.find(andQuery);
        //displayCursor(cursor);

        if (cursor.size() > 0) {
            //Sort cursor results
            sortResults(cursor, conditions);
            JSON json = new JSON();
            String lstRes = json.serialize(cursor);
            cursor.close();
            return lstRes;
        } else {
            cursor.close();
            return null;
        }

    }

    /// fetches all rows matching multiple field values in an AND operation
    /**
     *
     * @param strTable
     * @param conditions
     * @return returns the count of records that match the query condition
     * @throws Exception
     */
    public int getCount(String strTable, Map<String, String> conditions) throws Exception {
        //   int limit = 100;

        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> lstObj = new ArrayList<BasicDBObject>();

        Set<String> keys = conditions.keySet();
        for (String key : keys) {
            lstObj.add(new BasicDBObject(key, conditions.get(key)));

        }

        andQuery.put("$and", lstObj);
        //System.out.println(andQuery.toString());

        DBCursor cursor = dbTable.find(andQuery);
        int count = cursor.count();
        cursor.close();
        return count;

    }

    public boolean update(String strTable, String strPrimaryKey, String json) throws Exception {

        boolean status = false;

        DBCollection dbTable = mngDb.getCollection(strTable);

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new ObjectId(strPrimaryKey));

        DBObject dbObject = (DBObject) JSON.parse(json);

        WriteResult result = dbTable.update(whereQuery, dbObject);

        return result.isUpdateOfExisting();

    }

    //called only at the logout
    public void close() {
        if (mngClient != null) {
            mngClient.close();
            mngClient = null;
        }
    }

    private Map<String, String> getSingleRowMap(DBCursor result) {
        Map<String, String> resMap = new HashMap<String, String>();
        //System.out.println("resultset count= " + result.count());
        for (DBObject dbObject : result) {

            Set<String> keys = dbObject.keySet();
            for (String key : keys) {
                //String value=(String)dbObject.get(key);
                //System.out.println("Value of " + key + " is: " + dbObject.get(key));
                resMap.put(key, dbObject.get(key).toString());
            }

        }

        return resMap;
    }

    private ArrayList<HashMap<String, String>> getMultiRowMap(DBCursor result) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        for (DBObject dbObject : result) {
            HashMap<String, String> resMap = new HashMap<String, String>();
            Set<String> keys = dbObject.keySet();
            for (String key : keys) {
                //String value=(String)dbObject.get(key);
                //System.out.println("Value of " + key + " is: " + dbObject.get(key));
                resMap.put(key, dbObject.get(key).toString());

            }
            list.add(resMap);
        }

        return list;
    }

    public boolean deletefromlist(String strTable, String strPrimaryKey, String strListname, String strListvalue) throws Exception {
        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject match = new BasicDBObject("_id", new ObjectId(strPrimaryKey));
        BasicDBObject update = new BasicDBObject(strListname, strListvalue);
        WriteResult result = dbTable.update(match, new BasicDBObject("$pull", update));

        BasicDBObject document = new BasicDBObject();
        document.append("updateddate", new Date());
        DBObject updates = new BasicDBObject("$set", document);
        WriteResult result1 = dbTable.update(match, updates);

        return result.isUpdateOfExisting();

    }

    public boolean addtolist(String strTable, String strPrimaryKey, String strListname, String strListvalue) throws Exception {
        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject match = new BasicDBObject("_id", new ObjectId(strPrimaryKey));
        BasicDBObject update = new BasicDBObject(strListname, strListvalue);
        WriteResult result = dbTable.update(match, new BasicDBObject("$push", update));

        BasicDBObject document = new BasicDBObject();
        document.append("updateddate", new Date());
        DBObject updates = new BasicDBObject("$set", document);
        WriteResult result1 = dbTable.update(match, updates);

        return result.isUpdateOfExisting();

    }

    public String[] getlist(String strTable, String strPrimaryKey, String strListname) throws Exception {
        String[] list = null;
        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new ObjectId(strPrimaryKey));

        DBCursor cursor = dbTable.find(whereQuery);
        //displayCursor(cursor);
        BasicDBList objlist = null;
        while (cursor.hasNext()) {
            //System.out.println(cursor.next());
            objlist = (BasicDBList) cursor.next().get(strListname);
        }
        if (objlist != null) {

            Object[] array = objlist.toArray();

            list = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                //System.out.println(array[i]);
                list[i] = array[i].toString();
            }
            return list;

        } else {
            return new String[0];
        }
    }

    public boolean createlist(String strTable, String strPrimaryKey, String strListname, ArrayList arList) throws Exception {
        boolean status = false;

        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new ObjectId(strPrimaryKey));
        BasicDBObject document = new BasicDBObject();
        document.append(strListname, arList);
        document.append("updateddate", new Date());
        DBObject updates = new BasicDBObject("$set", document);
        WriteResult result = dbTable.update(whereQuery, updates);
        return result.isUpdateOfExisting();
    }

    public String insert(String strTable, Map<String, String> columns) throws Exception {
        //_id field is reserved for primary key in mongodb, and that should be an unique value.
        //If you don't set anything to _id it ill automatically fill it with "MongoDB Id Object".
        //But you can put any unique info into that field.
        String objid = "";

        // if collection doesn't exists, MongoDB will create it for you
        DBCollection dbTable = mngDb.getCollection(strTable);

        // create a document to store key and value
        BasicDBObject document = new BasicDBObject(columns);

        document.put("createddate", new Date());
        WriteResult result = dbTable.insert(document);
        return ((ObjectId) document.get("_id")).toString();

    }

    public String insert(String strTable, String strPrimaryKey, Map<String, String> columns, String dummy) throws Exception {
        //_id field is reserved for primary key in mongodb, and that should be an unique value.
        //If you don't set anything to _id it ill automatically fill it with "MongoDB Id Object".
        //But you can put any unique info into that field.
        String objid = "";

        // if collection doesn't exists, MongoDB will create it for you
        DBCollection dbTable = mngDb.getCollection(strTable);

        // create a document to store key and value
        BasicDBObject document = new BasicDBObject(columns);
        document.put("createddate", new Date());
        WriteResult result = dbTable.insert(document);
        return ((ObjectId) document.get("_id")).toString();
    }

    public String insert(String strTable, String strPrimaryKey, String strPrimaryValue, Map<String, String> columns) throws Exception {
        //_id field is reserved for primary key in mongodb, and that should be an unique value.
        //If you don't set anything to _id it ill automatically fill it with "MongoDB Id Object".
        //But you can put any unique info into that field.
        String objid = "";

        // if collection doesn't exists, MongoDB will create it for you
        DBCollection dbTable = mngDb.getCollection(strTable);

        columns.put(strPrimaryKey, strPrimaryValue);
        // create a document to store key and value
        BasicDBObject document = new BasicDBObject(columns);

        document.put("createddate", new Date());
        WriteResult result = dbTable.insert(document);
        return ((ObjectId) document.get("_id")).toString();
    }

    public String fetchAllRowsLike(String strTable, Map<String, String> conditions, String strLogic) throws Exception {

        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject regexQuery = new BasicDBObject();
        List<BasicDBObject> lstObj = new ArrayList<BasicDBObject>();

        Set<String> keys = conditions.keySet();
        for (String key : keys) {
            if (key.equalsIgnoreCase("SORTKEY") || key.equalsIgnoreCase("SORTORDER")) {
                //System.out.println(column);
                continue;
            }
            lstObj.add(new BasicDBObject(key, java.util.regex.Pattern.compile(conditions.get(key), Pattern.CASE_INSENSITIVE)));

        }
        if (strLogic != null && !strLogic.isEmpty()) {

            if (strLogic.equalsIgnoreCase("and")) {
                regexQuery.put("$and", lstObj);

            } else if (strLogic.equalsIgnoreCase("or")) {

                regexQuery.put("$or", lstObj);
            } else if (strLogic.equalsIgnoreCase("nor")) {
                regexQuery.put("$nor", lstObj);

            } else { // default AND logic
                regexQuery.put("and", lstObj);

            }

            //System.out.println(regexQuery.toString());
            DBCursor cursor = dbTable.find(regexQuery);

            //displayCursor(cursor);
            if (cursor.size() > 0) {

                //Sort cursor results
                sortResults(cursor, conditions);

                JSON json = new JSON();
                String lstRes = json.serialize(cursor);

                cursor.close();
                return lstRes;
            } else {
                cursor.close();
                return null;
            }
        } else {
            throw new Exception("Must specify a logic type to be applied eg : and , or ");
        }

    }

    public String fetchRowsByConditions(String strTable, Map<String, Map<String, String>> columns, String strLogic) throws Exception {

        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject searchObject = new BasicDBObject();
        List<BasicDBObject> lstSearchArgs = new ArrayList<BasicDBObject>();
        lstSearchArgs.clear();

        if (columns != null && !columns.isEmpty()) {

            Set keys = columns.keySet();
            Iterator it = keys.iterator();
            while (it.hasNext()) {
                String column = (String) it.next();
                if (column.equalsIgnoreCase("SORTINFOKEY") || column.equalsIgnoreCase("SORTORDERKEY")) {
                    //System.out.println(column);
                    continue;
                }
                Map<String, String> columnAttribute = columns.get(column);
                if (columnAttribute != null) {
                    String condition = columnAttribute.keySet().iterator().next();
                    String value = columnAttribute.get(condition);
                    if (condition.equalsIgnoreCase("equal")) {
                        lstSearchArgs.add(new BasicDBObject(column, value));

                    } else if (condition.equalsIgnoreCase("lt")) {

                        lstSearchArgs.add(new BasicDBObject(column, new BasicDBObject("$lt", value)));
                    } else if (condition.equalsIgnoreCase("lte")) {
                        lstSearchArgs.add(new BasicDBObject(column, new BasicDBObject("$lte", value)));

                    } else if (condition.equalsIgnoreCase("gt")) {
                        lstSearchArgs.add(new BasicDBObject(column, new BasicDBObject("$gt", value)));

                    } else if (condition.equalsIgnoreCase("gte")) {
                        lstSearchArgs.add(new BasicDBObject(column, new BasicDBObject("$gte", value)));

                    } else { // default equal
                        lstSearchArgs.add(new BasicDBObject(column, value));
                    }
                }
            }

            if (strLogic.equalsIgnoreCase("and")) {
                searchObject.put("$and", lstSearchArgs);

            } else if (strLogic.equalsIgnoreCase("or")) {

                searchObject.put("$or", lstSearchArgs);
            } else if (strLogic.equalsIgnoreCase("nor")) {
                searchObject.put("$nor", lstSearchArgs);

            } else { // default AND logic
                searchObject.put("$and", lstSearchArgs);
            }

            DBCursor cursor = dbTable.find(searchObject);
            if (cursor.size() > 0) {
                sortLogicalResults(cursor, columns);
                JSON json = new JSON();
                String lstRes = json.serialize(cursor);
                cursor.close();
                return lstRes;
            } else {
                cursor.close();
                return null;
            }
        } else {
            throw new Exception("Must specify a conditional column");
        }
    }

    public boolean createMap(String strTable, String strPrimaryKey, String strMapName, Map keyValues) throws Exception {

        boolean status = false;

        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new ObjectId(strPrimaryKey));
        BasicDBObject document = new BasicDBObject();
        document.append(strMapName, keyValues);
        document.append("updateddate", new Date());
        DBObject updates = new BasicDBObject("$set", document);
        WriteResult result = dbTable.update(whereQuery, updates);
        return result.isUpdateOfExisting();
    }

    public Map getMap(String strTable, String strPrimaryKey, String strMapName) throws Exception {
        String[] list = null;
        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new ObjectId(strPrimaryKey));
        Map resultMap = null;
        DBCursor cursor = dbTable.find(whereQuery);
        //displayCursor(cursor);
        while (cursor.hasNext()) {
            //System.out.println(cursor.next());
            resultMap = (Map) cursor.next().get(strMapName);
        }
        return resultMap;
    }

    public boolean deleteFromMap(String strTable, String strPrimaryKey, String strMapName, String strKeyName) throws Exception {
        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject whereQuery = new BasicDBObject("_id", new ObjectId(strPrimaryKey));
        //BasicDBObject update = new BasicDBObject(strMapName, strKeyName);
        Map resultMap = null;
        DBCursor cursor = dbTable.find(whereQuery);
        BasicDBObject document = new BasicDBObject();
        //displayCursor(cursor);
        while (cursor.hasNext()) {
            //System.out.println(cursor.next());
            resultMap = (Map) cursor.next().get(strMapName);
        }
        resultMap.remove(strKeyName);
        document.append(strMapName, resultMap);
        document.append("updateddate", new Date());
        DBObject updates = new BasicDBObject("$set", document);
        WriteResult result = dbTable.update(whereQuery, updates);
        return result.isUpdateOfExisting();
    }

    public boolean updateToMap(String strTable, String strPrimaryKey, String strMapName, String strKeyName, String KeyValue) throws Exception {
        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject whereQuery = new BasicDBObject("_id", new ObjectId(strPrimaryKey));
        //BasicDBObject update = new BasicDBObject(strMapName, strKeyName);
        Map resultMap = null;
        DBCursor cursor = dbTable.find(whereQuery);
        BasicDBObject document = new BasicDBObject();
        //displayCursor(cursor);
        while (cursor.hasNext()) {
            //System.out.println(cursor.next());
            resultMap = (Map) cursor.next().get(strMapName);
        }
        resultMap.put(strKeyName, KeyValue);
        document.append(strMapName, resultMap);
        document.append("updateddate", new Date());
        DBObject updates = new BasicDBObject("$set", document);
        WriteResult result = dbTable.update(whereQuery, updates);

        return result.isUpdateOfExisting();
    }

    private void sortResults(DBCursor cursor, Map<String, String> conditions) {
        if (cursor.size() > 1) {

            try {

                if ((Integer.parseInt(conditions.get("SORTORDER"))) == -1) {
                    cursor.sort(new BasicDBObject(conditions.get("SORTKEY"), -1));
                } else {
                    cursor.sort(new BasicDBObject(conditions.get("SORTKEY"), 1));
                }
            } catch (Exception ex) {
                //ignore and continue since there is no sort key and order specified.
            }
        }

    }

    private void sortLogicalResults(DBCursor cursor, Map<String, Map<String, String>> conditions) {
        if (cursor.size() > 1) {

            try {
                Map<String, String> sortkeyinfo = conditions.get("SORTINFOKEY");
                Map<String, String> sortorderinfo = conditions.get("SORTORDERKEY");

                if ((Integer.parseInt(sortorderinfo.get("SORTORDER"))) == -1) {
                    cursor.sort(new BasicDBObject(sortkeyinfo.get("SORTKEY"), -1));
                } else {
                    cursor.sort(new BasicDBObject(sortkeyinfo.get("SORTKEY"), 1));
                }
            } catch (Exception ex) {
                //ignore and continue since there is no sort key and order specified.
            }
        }

    }

    /**
     * @param strFilePath
     * @param strDocType
     * @param strOwnerID
     * @return
     * @throws IOException
     */
    public String storeBinaryFile(String strFilePath, String strDocType, String strOwnerID, String folderName) throws IOException {

        File binaryFile = new File(strFilePath);

        GridFS gridFs = new GridFS(mngDb, folderName);

        GridFSInputFile gridFSInputFile;
        gridFSInputFile = gridFs.createFile(binaryFile);
        gridFSInputFile.setFilename(binaryFile.getName());

        String ext = FilenameUtils.getExtension(strFilePath);
        gridFSInputFile.setContentType(ext);
        gridFSInputFile.put("ownerID", strOwnerID);
        gridFSInputFile.put("docType", strDocType);
        gridFSInputFile.save();
        ObjectId id = (ObjectId) gridFSInputFile.get("_id");
        //System.out.println("record doc " + gridFSInputFile.toString());
        return id.toString();
        //return id;

    }

    public String fetchRowsByConditions(String strTable, String strInFieldName, String[] matchValues, String strSortKey, String strSortOrder) throws Exception {
        DBCollection dbTable = mngDb.getCollection(strTable);

        BasicDBObject query = new BasicDBObject();
        query.put(strInFieldName, new BasicDBObject("$in", matchValues));
        DBCursor cursor = dbTable.find(query);

        if (cursor.size() > 0) {
            //Apply sort key and sort order conditions
            if ((cursor.size() > 1) && (strSortKey != null) && (strSortOrder != null)) {
                try {

                    if ((Integer.parseInt(strSortOrder)) == -1) {
                        cursor.sort(new BasicDBObject(strSortKey, -1));
                    } else {
                        cursor.sort(new BasicDBObject(strSortKey, 1));
                    }
                } catch (Exception ex) {
                    //ignore and continue 
                }
            }

            JSON json = new JSON();
            String lstRes = json.serialize(cursor);
            cursor.close();
            return lstRes;

        } else {
            cursor.close();
            return null;
        }

    }

    public String fetchRowsLike(String strTable, Map<String, String> conditions, String strLogic, int fromIndex, int limit) throws Exception {

        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject regexQuery = new BasicDBObject();
        List<BasicDBObject> lstObj = new ArrayList<BasicDBObject>();

        Set<String> keys = conditions.keySet();
        for (String key : keys) {
            if (key.equalsIgnoreCase("SORTKEY") || key.equalsIgnoreCase("SORTORDER")) {
                //System.out.println(column);
                continue;
            }
            lstObj.add(new BasicDBObject(key, java.util.regex.Pattern.compile(conditions.get(key), Pattern.CASE_INSENSITIVE)));

        }
        if (strLogic != null && !strLogic.isEmpty()) {

            if (strLogic.equalsIgnoreCase("and")) {
                regexQuery.put("$and", lstObj);

            } else if (strLogic.equalsIgnoreCase("or")) {

                regexQuery.put("$or", lstObj);
            } else if (strLogic.equalsIgnoreCase("nor")) {
                regexQuery.put("$nor", lstObj);

            } else { // default AND logic
                regexQuery.put("and", lstObj);

            }

            //System.out.println(regexQuery.toString());
            DBCursor cursor = dbTable.find(regexQuery);

            //System.out.println(fromIndex + "<  > " + limit +  "< >" + cursor.size() );
            //displayCursor(cursor);
            if (cursor.size() > 0) {

                //Sort cursor results
                sortResults(cursor, conditions);
                if ((fromIndex > -1) && (limit > -1)) {
                    //System.out.println(fromIndex + "<  > " + limit +  "< >" + cursor.size() );
                    cursor.skip(fromIndex);
                    cursor.limit(limit);
                }
                //System.out.println(fromIndex + "<  > " + limit +  "< >" + cursor.size() +"\n\n" );
                JSON json = new JSON();
                String lstRes = json.serialize(cursor);

                cursor.close();
                return lstRes;
            } else {
                cursor.close();
                return null;
            }
        } else {
            throw new Exception("Must specify a logic type to be applied eg : and , or ");
        }
    }

    public GridFSDBFile getDbFile(String fileID, String folderName) {
        ObjectId oid = new ObjectId(fileID);
        GridFS gfs = new GridFS(mngDb, folderName);
        GridFSDBFile gfsdbfile;
        gfsdbfile = gfs.findOne(oid);
//        System.out.println(gfsdbfile);
        return gfsdbfile;
    }

    public InputStream getDbFileInputStream(String fileID, String folderName) {
        ObjectId oid = new ObjectId(fileID);
        GridFS gfs = new GridFS(mngDb, folderName);
        GridFSDBFile gfsdbfile;
        gfsdbfile = gfs.findOne(oid);
//        System.out.println(gfsdbfile);
        return gfsdbfile.getInputStream();

    }

    public String listAllFiles(String OwnerId, String folderName) {
        List<Object> filelist = new ArrayList<Object>();
        GridFS gfs = new GridFS(mngDb, folderName);
        BasicDBObject query = new BasicDBObject();
        query.put("ownerID", OwnerId);
        DBCursor cursor = gfs.getFileList(query);
//        while (cursor.hasNext()) {
//            System.out.println(cursor.next());
//        }
        if (cursor.size() > 0) {
            JSON json = new JSON();
            String lstRes = json.serialize(cursor);
            cursor.close();
            return lstRes;
        } else {
            cursor.close();
            return null;
        }
    }

    public boolean getToFileSystem(String fileID, String folderName, String outFilePath) throws IOException {

        ObjectId oid = new ObjectId(fileID);
        GridFS gfs = new GridFS(mngDb, folderName);
        GridFSDBFile gfsdbfile;
        gfsdbfile = gfs.findOne(oid);
        String filepath = outFilePath + gfsdbfile.getFilename();
//        System.out.println(filepath);
        gfsdbfile.writeTo(filepath);
        return true;
    }

    public MongoFile getGridFsFile(String fileID, String folderName) throws IOException {
        MongoFile mf = new MongoFile();
        ObjectId oid = new ObjectId(fileID);
        GridFS gfs = new GridFS(mngDb, folderName);
        GridFSDBFile gfsdbfile;
        gfsdbfile = gfs.findOne(oid);
        if (gfsdbfile != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream is = gfsdbfile.getInputStream();
            mf.setInputStream(is);
            baos.write(is);
            mf.setBaos(baos);
            mf.setOwnerID(fileID);
            mf.setFileName(gfsdbfile.getFilename());
            mf.setFileExt(gfsdbfile.getContentType());
            mf.setSize(String.valueOf(gfsdbfile.getLength()));
            mf.setDocType(gfsdbfile.getContentType());
//            mf.setStatus((String) gfsdbfile.get("status"));
//            mf.setCreatedate((String) gfsdbfile.get("createdate"));
            return mf;
        } else {
            return null;
        }
    }

    public String listAllFilesByCondition(String folderName, HashMap<String, String> conditions) {
        if (folderName == null || folderName.isEmpty() || conditions == null || conditions.isEmpty()) {
            return null;
        }
        GridFS gfs = new GridFS(mngDb, folderName);
        BasicDBObject query = new BasicDBObject();
        List<BasicDBObject> lstObj = new ArrayList<BasicDBObject>();

        Set<String> keys = conditions.keySet();
        for (String key : keys) {
            lstObj.add(new BasicDBObject(key, conditions.get(key)));

        }
        String operator = conditions.get("OPERATOR");
        if (operator == null || operator.isEmpty() || operator.equals("and")) {
            query.put("$and", lstObj);
        } else if (operator.equals("or")) {
            query.put("or", lstObj);
        }
        query.put("$and", lstObj);
//        System.out.println(query.toString());
        DBCursor cursor = gfs.getFileList(query);
        if (cursor.size() > 0) {
            JSON json = new JSON();
            String lstRes = json.serialize(cursor);
            cursor.close();
            return lstRes;
        } else {
            cursor.close();
            return null;
        }
    }

    public boolean removeFileFromDb(String fileID, String folderName) throws IOException {
        boolean status = false;
        ObjectId oid = new ObjectId(fileID);
        GridFS gfs = new GridFS(mngDb, folderName);
        GridFSDBFile gfsfile = gfs.findOne(oid);
        if (gfsfile != null) {
            gfs.remove(gfsfile);
            status = true;
        }
        return status;
    }

    public String storeBinaryFile(MongoFile mf) throws IOException {

        GridFS gridFs = new GridFS(mngDb, mf.getFolderName());
        GridFSInputFile gridFSInputFile;
        gridFSInputFile = gridFs.createFile(mf.getBaos().toByteArray());
        gridFSInputFile.setFilename(mf.getFileName());
        gridFSInputFile.setContentType(mf.getFileExt());
        gridFSInputFile.put("ownerID", mf.getOwnerID());
        gridFSInputFile.put("docType", mf.getDocType());
//        gridFSInputFile.put("status", mf.getStatus());
//        gridFSInputFile.put("createdate", mf.getCreatedate());
        gridFSInputFile.save();
        ObjectId id = (ObjectId) gridFSInputFile.get("_id");
        return id.toString();

    }

    public String fetchRowsByConditions(String strTable, List<Query> columns) throws Exception {
        //  int limit = 100;
        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject searchObject = new BasicDBObject();
        List<BasicDBObject> lstSearchArgs = new ArrayList<BasicDBObject>();
        lstSearchArgs.clear();
        Map<String, Map<String, String>> sortMap = new HashMap<String, Map<String, String>>();
        Iterator it = columns.iterator();
        while (it.hasNext()) {
            Query query = (Query) it.next();

            if (query.getColumnName().equalsIgnoreCase("SORTKEY") || query.getColumnName().equalsIgnoreCase("SORTORDER")) {
                Map<String, String> attrib = null;
                if (query.getColumnName().equalsIgnoreCase("SORTKEY")) {
                    attrib = new HashMap<String, String>();
                    attrib.put(query.getColumnName(), query.getColumnValue());
                    sortMap.put("SORTINFOKEY", attrib);
                }

                if (query.getColumnName().equalsIgnoreCase("SORTORDER")) {
                    attrib = new HashMap<String, String>();
                    attrib.put(query.getColumnName(), query.getColumnValue());
                    sortMap.put("SORTORDERKEY", attrib);
                }

//                System.out.println(column);
//                sortMap.put(query.getColumnName(), null);
                continue;
            }

            if (query.getCondition().equalsIgnoreCase("equal")) {
                lstSearchArgs.add(new BasicDBObject(query.getColumnName(), query.getColumnValue()));
            } else if (query.getCondition().equalsIgnoreCase("lt")) {
                lstSearchArgs.add(new BasicDBObject(query.getColumnName(), new BasicDBObject("$lt", query.getColumnValue())));
            } else if (query.getCondition().equalsIgnoreCase("lte")) {
                lstSearchArgs.add(new BasicDBObject(query.getColumnName(), new BasicDBObject("$lte", query.getColumnValue())));
            } else if (query.getCondition().equalsIgnoreCase("gt")) {
                lstSearchArgs.add(new BasicDBObject(query.getColumnName(), new BasicDBObject("$gt", query.getColumnValue())));
            } else if (query.getCondition().equalsIgnoreCase("gte")) {
                lstSearchArgs.add(new BasicDBObject(query.getColumnName(), new BasicDBObject("$gte", query.getColumnValue())));
            } else if (query.getCondition().equalsIgnoreCase("in")) {
                if (query.getPkmatchValues() != null) {
                    lstSearchArgs.add(new BasicDBObject(query.getColumnName(), new BasicDBObject("$in", query.getPkmatchValues())));
                } else {
                    lstSearchArgs.add(new BasicDBObject(query.getColumnName(), new BasicDBObject("$in", query.getMatchValues())));
                }
            } else { // default
                lstSearchArgs.add(new BasicDBObject(query.getColumnName(), query.getColumnValue()));

            }
//            query.put(strInFieldName, new BasicDBObject("$in", matchValues));
        }

        searchObject.put("$and", lstSearchArgs);
        DBCursor cursor = dbTable.find(searchObject);

        if (cursor.size() > 0) {
            if (sortMap.size() > 0) {
                sortLogicalResults(cursor, sortMap);
            }
//            displayCursor(cursor);            
//            sortLogicalResults(cursor, columns);
            JSON json = new JSON();
            String lstRes = json.serialize(cursor);
            cursor.close();
            return lstRes;
        } else {
            cursor.close();
            return null;
        }
    }

    public String fetchDistinctList(String table, String columnname) {
        DBCollection dbTable = mngDb.getCollection(table);
        List distinctlist = dbTable.distinct(columnname);
        //System.out.println(distinctlist);
        JSON json = new JSON();
        String lstRes = json.serialize(distinctlist);
        return lstRes;

    }

    public String fetchRowsByConditions(String strTable, List<ObjectId> matchValues, String strSortKey, String strSortOrder) throws Exception {
        DBCollection dbTable = mngDb.getCollection(strTable);

        BasicDBObject query = new BasicDBObject();
        query.put("_id", new BasicDBObject("$in", matchValues));
        DBCursor cursor = dbTable.find(query);

        if (cursor.size() > 0) {
            //Apply sort key and sort order conditions
            if ((cursor.size() > 1) && (strSortKey != null) && (strSortOrder != null)) {
                try {

                    if ((Integer.parseInt(strSortOrder)) == -1) {
                        cursor.sort(new BasicDBObject(strSortKey, -1));
                    } else {
                        cursor.sort(new BasicDBObject(strSortKey, 1));
                    }
                } catch (Exception ex) {
                    //ignore and continue 
                }
            }

            JSON json = new JSON();
            String lstRes = json.serialize(cursor);
            cursor.close();
            return lstRes;

        } else {
            cursor.close();
            return null;
        }

    }

    public String fetchRowsByConditions(String strTable, String columnName, List<String> matchValues, String strSortKey, String strSortOrder) throws Exception {
        DBCollection dbTable = mngDb.getCollection(strTable);

        BasicDBObject query = new BasicDBObject();
        query.put(columnName, new BasicDBObject("$in", matchValues));
        DBCursor cursor = dbTable.find(query);

        if (cursor.size() > 0) {
            //Apply sort key and sort order conditions
            if ((cursor.size() > 1) && (strSortKey != null) && (strSortOrder != null)) {
                try {

                    if ((Integer.parseInt(strSortOrder)) == -1) {
                        cursor.sort(new BasicDBObject(strSortKey, -1));
                    } else {
                        cursor.sort(new BasicDBObject(strSortKey, 1));
                    }
                } catch (Exception ex) {
                    //ignore and continue 
                }
            }

            JSON json = new JSON();
            String lstRes = json.serialize(cursor);
            cursor.close();
            return lstRes;

        } else {
            cursor.close();
            return null;
        }

    }

    public String fetchRowsByDatePeriods(String strTable, String strDateColName, String strStartDate, String strEndDate, Map<String, Map<String, String>> columns) {

        String lstRes = "";

        QueryBuilder qbObj = QueryBuilder.start();
        qbObj.put(strDateColName).greaterThanEquals(strStartDate);
        qbObj.put(strDateColName).lessThanEquals(strEndDate);

        if (columns != null && !columns.isEmpty()) {
            Set keys = columns.keySet();
            Iterator it = keys.iterator();
            while (it.hasNext()) {
                String strCol = (String) it.next();
                if (strCol.equalsIgnoreCase("SORTINFOKEY") || strCol.equalsIgnoreCase("SORTORDERKEY")) {
                    continue;
                }
                Map<String, String> columnAttribute = columns.get(strCol);
                if (columnAttribute != null) {
                    String condition = columnAttribute.keySet().iterator().next();
                    String value = columnAttribute.get(condition);
                    if (condition.equalsIgnoreCase("equal")) {
                        qbObj.and(new BasicDBObject(strCol, value));
                    } else if (condition.equalsIgnoreCase("lt")) {
                        qbObj.put(strCol).lessThan(value);
                    } else if (condition.equalsIgnoreCase("lte")) {
                        qbObj.put(strCol).lessThanEquals(value);
                    } else if (condition.equalsIgnoreCase("gt")) {
                        qbObj.put(strCol).greaterThan(value);
                    } else if (condition.equalsIgnoreCase("gte")) {
                        qbObj.put(strCol).greaterThanEquals(value);
                    }
                }
            }
        }

        DBObject query = qbObj.get();
        DBCollection dbTable = mngDb.getCollection(strTable);
        DBCursor cursor = dbTable.find(query);
        lstRes = JSON.serialize(cursor);
        if (lstRes.equals("[ ]")) {//for cheking empty result set.there should be a space inside bracket.
            lstRes = null;
        }
        cursor.close();
        return lstRes;
    }

    public String fetchAll(String strTable) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DBCollection dbTable = mngDb.getCollection(strTable);

        DBCursor cursor = dbTable.find();
        if (cursor.size() > 0) {
            JSON json = new JSON();
            String lstRes = json.serialize(cursor);
            cursor.close();
            return lstRes;
        } else {
            cursor.close();
            return null;
        }

    }

    public long countAll(String strTable) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DBCollection dbTable = mngDb.getCollection(strTable);
        DBCursor cursor = dbTable.find();
        return cursor.size();
    }

    public boolean deleteDocument(String strTable, String strPrimaryKey) throws Exception {
        boolean delete = false;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (strPrimaryKey.isEmpty() || strPrimaryKey == null || strPrimaryKey.length() == 0) {
            return delete;
        } else {
            DBCollection dbTable = mngDb.getCollection(strTable);
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("_id", new ObjectId(strPrimaryKey));
            WriteResult result = dbTable.remove(whereQuery);

            if ("1".equals(result.getField("n").toString())) {
                delete = true;
            } else if ("0".equals(result.getField("n").toString())) {
                delete = false;
            }

        }
        return delete;
    }

    public boolean delete(String strTable, String strPrimaryKey) throws Exception {
        boolean delete = false;
        if (strPrimaryKey.isEmpty() || strPrimaryKey == null || strPrimaryKey.length() == 0) {
            return delete;
        } else {
            DBCollection dbTable = mngDb.getCollection(strTable);
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("_id", new ObjectId(strPrimaryKey));
            WriteResult result = dbTable.remove(whereQuery);

            if (1 == result.getN()) {
                delete = true;
            } else if (0 == result.getN()) {
                delete = false;
            }
        }
        return delete;
    }

    // apply like query on multiple column
    public String searchFromMultiColumn(String strTable, Map<String, Object> conditions, String strLogic, int fromIndex, int limit) throws Exception {

        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject regexQuery = new BasicDBObject();
        List<BasicDBObject> lstObj = new ArrayList<BasicDBObject>();

        Set<String> keys = conditions.keySet();
        for (String key : keys) {
            if (key.equalsIgnoreCase("SORTKEY") || key.equalsIgnoreCase("SORTORDER")) {
                //System.out.println(column);
                continue;
            }
            if (conditions.get(key) instanceof String) {
                String val = (String) conditions.get(key);
                lstObj.add(new BasicDBObject(key, java.util.regex.Pattern.compile(val, Pattern.CASE_INSENSITIVE)));
            } else if (conditions.get(key) instanceof HashMap) {
                HashMap<String, String> map = (HashMap<String, String>) conditions.get(key);
                List<BasicDBObject> subListObj = new ArrayList<BasicDBObject>();
                for (Map.Entry<String, String> mapEntry : map.entrySet()) {
                    if (!mapEntry.getKey().equalsIgnoreCase("strLogic")) {
                        subListObj.add(new BasicDBObject(mapEntry.getKey(), java.util.regex.Pattern.compile(mapEntry.getValue(), Pattern.CASE_INSENSITIVE)));
                    }
                }
                if (map.get("strLogic").equalsIgnoreCase("and")) {
                    lstObj.add(new BasicDBObject("$and", subListObj));
                } else if (map.get("strLogic").equalsIgnoreCase("or")) {
                    lstObj.add(new BasicDBObject("$or", subListObj));
                } else if (map.get("strLogic").equalsIgnoreCase("nor")) {
                    lstObj.add(new BasicDBObject("$nor", subListObj));
                } else { // default AND logic
                    lstObj.add(new BasicDBObject("$and", subListObj));
                }
            }
        }
        //
        if (strLogic != null && !strLogic.isEmpty()) {
            if (strLogic.equalsIgnoreCase("and")) {
                regexQuery.put("$and", lstObj);
            } else if (strLogic.equalsIgnoreCase("or")) {
                regexQuery.put("$or", lstObj);
            } else if (strLogic.equalsIgnoreCase("nor")) {
                regexQuery.put("$nor", lstObj);
            } else { // default AND logic
                regexQuery.put("and", lstObj);
            }

            //System.out.println(regexQuery.toString());
            DBCursor cursor = dbTable.find(regexQuery);

            if (cursor.size() > 0) {
                //Sort cursor results
                if ((fromIndex > -1) && (limit > -1)) {
                    //System.out.println(fromIndex + "<  > " + limit +  "< >" + cursor.size() );
                    cursor.skip(fromIndex);
                    cursor.limit(limit);
                }
                //System.out.println(fromIndex + "<  > " + limit +  "< >" + cursor.size() +"\n\n" );
                JSON json = new JSON();
                String lstRes = json.serialize(cursor);

                cursor.close();
                return lstRes;
            } else {
                cursor.close();
                return null;
            }
        } else {
            throw new Exception("Must specify a logic type to be applied eg : and , or ");
        }
    }

    public String searchFromMultiColumnMore(String strTable, Map<String, Object> conditions, String strLogic, int fromIndex, int limit) throws Exception {

        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject regexQuery = new BasicDBObject();
        List<BasicDBObject> lstObj = new ArrayList<BasicDBObject>();

        Set<String> keys = conditions.keySet();
        for (String key : keys) {
            if (key.equalsIgnoreCase("SORTKEY") || key.equalsIgnoreCase("SORTORDER")) {
                //System.out.println(column);
                continue;
            }
            if (conditions.get(key) instanceof String) {
                String val = (String) conditions.get(key);
                lstObj.add(new BasicDBObject(key, java.util.regex.Pattern.compile(val, Pattern.CASE_INSENSITIVE)));
            } else if (conditions.get(key) instanceof HashMap) {
                HashMap<String, String> map = (HashMap<String, String>) conditions.get(key);
                List<BasicDBObject> subListObj = new ArrayList<BasicDBObject>();
                for (Map.Entry<String, String> mapEntry : map.entrySet()) {
                    if (!mapEntry.getKey().equalsIgnoreCase("strLogic")) {
                        subListObj.add(new BasicDBObject(mapEntry.getKey(), java.util.regex.Pattern.compile(mapEntry.getValue(), Pattern.CASE_INSENSITIVE)));
                    }
                }
                if (map.get("strLogic").equalsIgnoreCase("and")) {
                    lstObj.add(new BasicDBObject("$and", subListObj));
                } else if (map.get("strLogic").equalsIgnoreCase("or")) {
                    lstObj.add(new BasicDBObject("$or", subListObj));
                } else if (map.get("strLogic").equalsIgnoreCase("nor")) {
                    lstObj.add(new BasicDBObject("$nor", subListObj));
                } else { // default AND logic
                    lstObj.add(new BasicDBObject("$and", subListObj));
                }
            }
        }
        //
        if (strLogic != null && !strLogic.isEmpty()) {
            if (strLogic.equalsIgnoreCase("and")) {
                regexQuery.put("$and", lstObj);
            } else if (strLogic.equalsIgnoreCase("or")) {
                regexQuery.put("$or", lstObj);
            } else if (strLogic.equalsIgnoreCase("nor")) {
                regexQuery.put("$nor", lstObj);
            } else { // default AND logic
                regexQuery.put("and", lstObj);
            }

            //System.out.println(regexQuery.toString());
            DBCursor cursor = dbTable.find(regexQuery);
            if (limit == 100) {
                cursor.skip(fromIndex);
                JSON json = new JSON();
                String lstRes = json.serialize(cursor);

                cursor.close();
                return lstRes;
            } else {
                cursor.close();
                return null;
            }
        } else {
            throw new Exception("Must specify a logic type to be applied eg : and , or ");
        }
    }

    /**
     *
     * @param strTable
     * @param cond
     * @param matchCond
     * @return
     * @description :
     * @throws Exception
     */
    public String aggregateWithMatchGroupAndSort(String strTable, Map<String, Object> cond, String matchCond) throws Exception {
        if (strTable == null || strTable.isEmpty() || cond == null || cond.size() == 0) {
            return null;
        }
        // query list
        List<DBObject> qList = new ArrayList<DBObject>();
        // create match condition
        if (cond.containsKey("match") && cond.get("match") != null) {
            if (cond.get("match") instanceof Map) {
                DBObject match = (DBObject) JSON.parse(JSON.serialize(cond.get("match")));
                if (match.keySet().size() > 0) {
                    if (matchCond.equalsIgnoreCase("and")) {
                        List<DBObject> l = new ArrayList<DBObject>();
                        l.add(match);
                        qList.add(new BasicDBObject("$match", new BasicDBObject("$and", l)));
                    } else if (matchCond.equalsIgnoreCase("or")) {
                        List<DBObject> l = new ArrayList<DBObject>();
                        l.add(match);
                        qList.add(new BasicDBObject("$match", new BasicDBObject("$or", l)));
                    } else {
                        qList.add(new BasicDBObject("$match", match));
                    }
                } else {
                    throw new InvalidValue("match should not be empty");
                }
            } else {
                throw new InvalidValue("Invalid value of match \nValue of match key should be Map<String,Strin>");
            }
        }
        // create group condition
        if (cond.containsKey("group") && cond.get("group") != null) {
            if (cond.get("group") instanceof Set) {
                BasicDBObject groupCond = new BasicDBObject();
                Set<String> columns = (HashSet) cond.get("group");
                if (columns.size() > 0) {
                    for (String col : columns) {
                        groupCond.append(col, "$" + col);
                    }
                    qList.add(new BasicDBObject("$group", new BasicDBObject("_id", groupCond)
                            .append("count", new BasicDBObject("$sum", 1))));
                } else {
                    throw new InvalidValue("group should not be empty");
                }
            } else {
                throw new InvalidValue("Invalid value of group \nValue of group key should be Set<String>");
            }
        }
        // create sort condition
        if (cond.containsKey("sort") && cond.get("sort") != null && (cond.get("sort") instanceof Map)) {
            if (cond.get("sort") instanceof Map) {
                DBObject sort = (DBObject) JSON.parse(JSON.serialize(cond.get("sort")));
                if (sort.keySet().size() > 0) {
                    qList.add(new BasicDBObject("$sort", sort));
                } else {
                    throw new InvalidValue("sort should not be empty");
                }
            } else {
                throw new InvalidValue("Invalid value of sort \nValue of sort key should be Map<String,Strin>");
            }
        }
        // mongo aggregation
        AggregationOutput cursor = null;
        if (qList
                != null && qList.size()
                > 0) {
            DBCollection dbTable = mngDb.getCollection(strTable);
            cursor = dbTable.aggregate(qList);
            if (cursor.results().iterator().hasNext() == false
                    //                    || cursor.results().spliterator().estimateSize() == 0
                    || cursor.results().toString().equalsIgnoreCase("[ ]")) {
                return null;
            } else {
                return new JSON().serialize(cursor.results());
            }
        } else {
            return null;
        }
    }

    public String fetchRowsByConditions(String strTable, Map<String, String> conditions, int fromIndex, int limit) throws Exception {
        DBCollection dbTable = mngDb.getCollection(strTable);
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> lstObj = new ArrayList<BasicDBObject>();

        Set<String> keys = conditions.keySet();
        for (String key : keys) {
            if (key.equalsIgnoreCase("SORTKEY") || key.equalsIgnoreCase("SORTORDER")) {
                continue;
            }
            lstObj.add(new BasicDBObject(key, conditions.get(key)));
        }
        andQuery.put("$and", lstObj);

        DBCursor cursor = dbTable.find(andQuery);
        if (cursor.size() > 0) {
            //Sort cursor results
            sortResults(cursor, conditions);

            if ((fromIndex > -1) && (limit > -1)) {
//                System.out.println(fromIndex + "<  > " + limit + "< >" + cursor.size());
                cursor.skip(fromIndex);
                cursor.limit(limit);
            }
//            System.out.println(fromIndex + "<  > " + limit + "< >" + cursor.size() + "\n\n");

            JSON json = new JSON();
            String lstRes = json.serialize(cursor);
            cursor.close();
            return lstRes;
        } else {
            cursor.close();
            return null;
        }
    }

}
