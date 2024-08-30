package org.example;


import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.ipc.ArrowReader;
import org.duckdb.DuckDBResultSet;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.util.Properties;

public class Main {
    static {
        try {
            Class.forName("org.duckdb.DuckDBDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws SQLException, IOException {
        Properties connectionProperties = new Properties();
        String dir = Files.createTempDirectory("tmpCatalogs").toFile().getAbsolutePath();
        //connectionProperties.setProperty("temp_directory", res.getAbsolutePath());
        Connection conn = DriverManager.getConnection("jdbc:duckdb:/" + dir + "/db" , connectionProperties);
        // create a table
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE items (item VARCHAR, value DECIMAL(10, 2), count INTEGER)");
// insert two items into the table
        stmt.execute("INSERT INTO items VALUES ('jeans', 20.0, 1), ('hammer', 42.2, 2)");
        try (
                DuckDBResultSet resultSet = (DuckDBResultSet) stmt.executeQuery("select * from items");
        BufferAllocator allocator = new RootAllocator()) {
            try (ArrowReader reader = (ArrowReader) resultSet.arrowExportStream(allocator, 256)) {
                while (reader.loadNextBatch()) {
                    System.out.println(reader.getVectorSchemaRoot().contentToTSVString());
                }
            }
            stmt.close();
        }
        stmt.close();

    }
}