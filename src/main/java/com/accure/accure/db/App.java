package com.accure.accure.db;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.QueryResult;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        Cluster cluster = HFactory.getOrCreateCluster("TestCluster", new CassandraHostConfigurator("202.83.16.110:9160"));
        Keyspace keyspace = HFactory.createKeyspace("demo", cluster);
        StringSerializer stringSerializer = StringSerializer.get();
        Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
        mutator.insert("jsmith2", "users", HFactory.createStringColumn("first", "John"));
        mutator.insert("jsmith1", "users", HFactory.createStringColumn("last", "Smith1"));



        ColumnQuery<String, String, String> columnQuery =  HFactory.createStringColumnQuery(keyspace);
        columnQuery.setColumnFamily("users").setKey("accure").setName("website");
        //columnQuery.setColumnFamily("users").setKey("jsmith").setName("last");
        
        QueryResult<HColumn<String, String>> result = columnQuery.execute();
        HColumn<String, String> columns = result.get();
        System.out.println("name: " + columns.getName());
        System.out.println("value: " + columns.getValue());
        System.out.println("value bytes: " + columns.getValueBytes());
        System.out.println("serializer: " + columns.getValueSerializer());

    }
}
