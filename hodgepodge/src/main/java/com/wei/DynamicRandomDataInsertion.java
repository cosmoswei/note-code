package com.wei;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DynamicRandomDataInsertion {
    // JDBC连接参数
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/huangxuwei";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "huangxuwei";
    public static final String DATA_BASE_NAME = "huangxuwei";
    public static final String TABLE_NAME = "e_commerce_users";
    public static final int NUM_ROWS_TO_INSERT = 1_000;

    public static void main(String[] args) {
        dynamicRandomDataInsertion(TABLE_NAME, NUM_ROWS_TO_INSERT);
    }

    public static void dynamicRandomDataInsertion(String tableName, int numRowsToInsert) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSet pkResultSet; // 主键结果集
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            DatabaseMetaData metaData = conn.getMetaData();
            pstmt = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE 1=0");
            rs = pstmt.executeQuery();
            ResultSetMetaData rsMetaData = rs.getMetaData();
            // 获取主键信息
            pkResultSet = metaData.getPrimaryKeys(null, null, tableName);
            List<String> pkKeys = new ArrayList<>();
            List<String> uniqKeys = new ArrayList<>();
            // 输出主键字段名称
            keysSetHandler(pkResultSet, pkKeys, metaData, uniqKeys);
            StringBuilder insertSql = new StringBuilder("INSERT INTO " + tableName + " (");
            for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                insertSql.append(rsMetaData.getColumnName(i));
                if (i < rsMetaData.getColumnCount()) {
                    insertSql.append(", ");
                }
            }
            insertSql.append(") VALUES (");
            for (int i = 0; i < rsMetaData.getColumnCount(); i++) {
                insertSql.append("?");
                if (i < rsMetaData.getColumnCount() - 1) {
                    insertSql.append(", ");
                }
            }
            insertSql.append(")");
            pstmt.close();
            pstmt = conn.prepareStatement(insertSql.toString());
            Random random = new Random();
            for (int i = 0; i < numRowsToInsert; i++) {
                for (int j = 1; j <= rsMetaData.getColumnCount(); j++) {
                    int columnType = rsMetaData.getColumnType(j);
                    String columnName = rsMetaData.getColumnName(j);
                    if (pkKeys.contains(columnName) || uniqKeys.contains(columnName)) {
                        pstmt.setLong(j, System.nanoTime());
                        continue;
                    }
                    switch (columnType) {
                        case Types.INTEGER:
                            pstmt.setInt(j, random.nextInt(1000000));
                            break;
                        case Types.BIGINT:
                            pstmt.setLong(j, random.nextLong());
                            break;
                        case Types.FLOAT:
                            pstmt.setFloat(j, random.nextFloat());
                            break;
                        case Types.DOUBLE:
                            pstmt.setDouble(j, random.nextDouble());
                            break;
                        case Types.CHAR:
                        case Types.VARCHAR:
                            pstmt.setString(j, generateRandomString(random, 10));
                            break;
                        case Types.DATE:
                            pstmt.setDate(j, new java.sql.Date(System.currentTimeMillis()));
                            break;
                        case Types.TIME:
                            pstmt.setTime(j, new Time(System.currentTimeMillis()));
                            break;
                        case Types.TIMESTAMP:
                            pstmt.setTimestamp(j, new Timestamp(System.currentTimeMillis()));
                            break;
                        case Types.DECIMAL:
                            pstmt.setBigDecimal(j, BigDecimal.valueOf(random.nextDouble()));
                            break;
                        default:
                            pstmt.setNull(j, Types.NULL);
                            break;
                    }
                }
                pstmt.executeUpdate();
            }
            System.out.println("插入数据成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void keysSetHandler(ResultSet pkResultSet, List<String> pkKeys, DatabaseMetaData metaData, List<String> uniqKeys) throws SQLException {
        ResultSet indexResultSet;
        while (pkResultSet.next()) {
            String pkColumnName = pkResultSet.getString("COLUMN_NAME");
            pkKeys.add(pkColumnName);
        }

        // 获取索引信息，并且筛选出唯一索引
        indexResultSet = metaData.getIndexInfo(null, null, TABLE_NAME, false, true);

        // 输出唯一索引的字段名称
        StringBuilder uniqueIndexColumns = new StringBuilder();
        while (indexResultSet.next()) {
            String indexColumnName = indexResultSet.getString("COLUMN_NAME");
            // 非唯一索引或非唯一约束将是非唯一的，因此我们只关注非非唯一的
            if (!"FALSE".equals(indexResultSet.getString("NON_UNIQUE"))) {
                uniqueIndexColumns.append(indexColumnName).append(", ");
                uniqKeys.add(indexColumnName);
            }
        }
    }

    private static String generateRandomString(Random random, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) ('a' + random.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }
}
