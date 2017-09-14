package com.accure.db.in;

import com.accure.accure.db.CassandraDAO;
import com.accure.accure.db.MongodbDAO;

/**
 *
 * @author sansari
 */
public final class DAOFactory {

    @Deprecated
    public static DAO getDAO(String daoName, String dbURL, String port, String schema) {
        if (daoName.equalsIgnoreCase("cassandra")) {
            return new CassandraDAO(dbURL, port, schema);
        } else if (daoName.equalsIgnoreCase("mongodb")) {
            return MongodbDAO.getInstance(dbURL, port, schema);
        } else {
            return null;
        }
    }

    @Deprecated
    public static DAO getDAO(String daoName, String dbURL, String schema) {
        if (daoName.equalsIgnoreCase("cassandra")) {
            return new CassandraDAO(dbURL, schema);
        } else if (daoName.equalsIgnoreCase("mongodb")) {
            return MongodbDAO.getDefaultInstance(dbURL, schema);
        } else {
            return null;
        }
    }

    public static DAO getDAO(String daoName, String dbURL, String port, String schema, String uname, String pwd) {
        if (daoName.equalsIgnoreCase("cassandra")) {
            return new CassandraDAO(dbURL, port, schema);
        } else if (daoName.equalsIgnoreCase("mongodb")) {
            return MongodbDAO.getInstance(dbURL, port, schema, uname, pwd);
        } else {
            return null;
        }
    }

    public static DAO getDAO(String daoName, String dbURL, String schema, String uname, String pwd) {
        if (daoName.equalsIgnoreCase("cassandra")) {
            return new CassandraDAO(dbURL, schema);
        } else if (daoName.equalsIgnoreCase("mongodb")) {
            return MongodbDAO.getDefaultInstance(dbURL, schema, uname, pwd);
        } else {
            return null;
        }
    }
}
