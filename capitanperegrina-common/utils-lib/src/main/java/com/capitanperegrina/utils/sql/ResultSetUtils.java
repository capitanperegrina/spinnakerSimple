package com.capitanperegrina.utils.sql;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class ResultSetUtils {

    private ResultSetUtils() {
        super();
    }

    public static <T> T wasNull(final T result, final ResultSet rs) throws SQLException {
        if (rs.wasNull()) {
            return null;
        }
        return result;
    }

    public static Integer getInteger(final String name, final ResultSet rs) throws SQLException {
        return wasNull(rs.getInt(name), rs);
    }

    public static Integer getInteger(final int indice, final ResultSet rs) throws SQLException {
        return wasNull(rs.getInt(indice), rs);
    }

    public static Double getDouble(final String name, final ResultSet rs) throws SQLException {
        return wasNull(rs.getDouble(name), rs);
    }

    public static Double getDouble(final int indice, final ResultSet rs) throws SQLException {
        return wasNull(rs.getDouble(indice), rs);
    }

    public static String getString(final int indice, final ResultSet rs) throws SQLException {
        return rs.getString(indice);
    }

    public static Boolean getBoolean(final int indice, final ResultSet rs) throws SQLException {
        return wasNull(rs.getBoolean(indice), rs);
    }

    public static Boolean getBoolean(final String name, final ResultSet rs) throws SQLException {
        return wasNull(rs.getBoolean(name), rs);
    }

    public static Byte getByte(final int indice, final ResultSet rs) throws SQLException {

        return wasNull(rs.getByte(indice), rs);
    }

    public static Short getShort(final int indice, final ResultSet rs) throws SQLException {
        return wasNull(rs.getShort(indice), rs);
    }

    public static Long getLong(final int indice, final ResultSet rs) throws SQLException {
        return wasNull(rs.getLong(indice), rs);
    }

    public static Date getDate(final int indice, final ResultSet rs) throws SQLException {
        return rs.getDate(indice);
    }

    public static Time getTime(final int indice, final ResultSet rs) throws SQLException {
        return rs.getTime(indice);
    }

    public static Timestamp getTimestamp(final int indice, final ResultSet rs) throws SQLException {
        return rs.getTimestamp(indice);
    }

    public static InputStream getAsciiStream(final int indice, final ResultSet rs) throws SQLException {
        return rs.getAsciiStream(indice);
    }

    public static InputStream getBinaryStream(final int indice, final ResultSet rs) throws SQLException {
        return rs.getBinaryStream(indice);
    }

    public static String getString(final String name, final ResultSet rs) throws SQLException {
        return rs.getString(name);
    }

    public static Byte getByte(final String name, final ResultSet rs) throws SQLException {
        return rs.getByte(name);
    }

    public static Short getShort(final String name, final ResultSet rs) throws SQLException {
        return wasNull(rs.getShort(name), rs);
    }

    public static Long getLong(final String name, final ResultSet rs) throws SQLException {
        return wasNull(rs.getLong(name), rs);
    }

    public static Date getDate(final String name, final ResultSet rs) throws SQLException {
        return rs.getDate(name);
    }

    public static Time getTime(final String name, final ResultSet rs) throws SQLException {
        return rs.getTime(name);
    }

    public static Timestamp getTimestamp(final String name, final ResultSet rs) throws SQLException {
        return rs.getTimestamp(name);
    }

    public static InputStream getAsciiStream(final String name, final ResultSet rs) throws SQLException {
        return rs.getAsciiStream(name);
    }

    public static InputStream getBinaryStream(final String name, final ResultSet rs) throws SQLException {
        return rs.getBinaryStream(name);
    }

    public static Object getObject(final int indice, final ResultSet rs) throws SQLException {
        return rs.getObject(indice);
    }

    public static Object getObject(final String name, final ResultSet rs) throws SQLException {
        return rs.getObject(name);
    }

    public static BigDecimal getBigDecimal(final int indice, final ResultSet rs) throws SQLException {
        return rs.getBigDecimal(indice);
    }

    public static BigDecimal getBigDecimal(final String name, final ResultSet rs) throws SQLException {
        return rs.getBigDecimal(name);
    }

    public static BigDecimal getBigDecimal(final int indice, final int scale, final ResultSet rs) throws SQLException {
        return setBigDecimalScale(rs.getBigDecimal(indice), scale);
    }

    public static BigDecimal getBigDecimal(final String name, final int scale, final ResultSet rs) throws SQLException {
        return setBigDecimalScale(rs.getBigDecimal(name), scale);
    }

    private static BigDecimal setBigDecimalScale(final BigDecimal result, final int scale) {
        if ((result != null) && (result.scale() < scale)) {
            return result.setScale(scale);
        }
        return result;
    }

    public static Blob getBlob(final String name, final ResultSet rs) throws SQLException {
        return rs.getBlob(name);
    }

    public static Clob getClob(final String name, final ResultSet rs) throws SQLException {
        return rs.getClob(name);
    }

    public static Array getArray(final String name, final ResultSet rs) throws SQLException {
        return rs.getArray(name);
    }

    public static Date getDate(final int indice, final ResultSet rs, final Calendar cal) throws SQLException {
        return rs.getDate(indice, cal);
    }

    public static Date getDate(final String name, final ResultSet rs, final Calendar cal) throws SQLException {
        return rs.getDate(name, cal);
    }

    public static Time getTime(final int indice, final ResultSet rs, final Calendar cal) throws SQLException {
        return rs.getTime(indice, cal);
    }

    public static Time getTime(final String name, final ResultSet rs, final Calendar cal) throws SQLException {
        return rs.getTime(name, cal);
    }

    public static Timestamp getTimestamp(final int indice, final ResultSet rs, final Calendar cal) throws SQLException {
        return rs.getTimestamp(indice, cal);
    }

    public static Timestamp getTimestamp(final String name, final ResultSet rs, final Calendar c) throws SQLException {
        return rs.getTimestamp(name, c);
    }
}
