package io.datadynamics.hive.geoserver;

import org.geotools.api.feature.type.GeometryDescriptor;
import org.geotools.jdbc.JDBCDataStore;
import org.geotools.jdbc.SQLDialect;
import org.geotools.util.factory.Hints;
import org.locationtech.jts.geom.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import static java.util.Map.entry;

public class ImpalaDialect extends SQLDialect {

    static final Map<String, Class> TYPE_TO_CLASS_MAP = Map.ofEntries(
            entry("GEOMETRY", Geometry.class),
            entry("GEOGRAPHY", Geometry.class),
            entry("POINT", Point.class),
            entry("POINTM", Point.class),
            entry("LINESTRING", LineString.class),
            entry("LINESTRINGM", LineString.class),
            entry("POLYGON", Polygon.class),
            entry("POLYGONM", Polygon.class),
            entry("MULTIPOINT", MultiPoint.class),
            entry("MULTIPOINTM", MultiPoint.class),
            entry("MULTILINESTRING", MultiLineString.class),
            entry("MULTILINESTRINGM", MultiLineString.class),
            entry("MULTIPOLYGON", MultiPolygon.class),
            entry("MULTIPOLYGONM", MultiPolygon.class),
            entry("GEOMETRYCOLLECTION", GeometryCollection.class),
            entry("GEOMETRYCOLLECTIONM", GeometryCollection.class));

    static final Map<Class, String> CLASS_TO_TYPE_MAP = Map.of(
            Geometry.class, "GEOMETRY",
            Point.class, "POINT",
            LineString.class, "LINESTRING",
            Polygon.class, "POLYGON",
            MultiPoint.class, "MULTIPOINT",
            MultiLineString.class, "MULTILINESTRING",
            MultiPolygon.class, "MULTIPOLYGON",
            GeometryCollection.class, "GEOMETRYCOLLECTION");

    public ImpalaDialect(JDBCDataStore jdbcDataStore) {
        super(jdbcDataStore);
    }

    @Override
    public void registerSqlTypeToSqlTypeNameOverrides(Map<Integer, String> overrides) {
        super.registerSqlTypeToSqlTypeNameOverrides(overrides);

        overrides.put(Types.VARCHAR, "STRING");
        overrides.put(Types.BLOB, "BYTES");
        overrides.put(Types.CLOB, "STRING");
    }

    @Override
    public void encodeGeometryEnvelope(String s, String s1, StringBuffer stringBuffer) {
        
    }

    @Override
    public Envelope decodeGeometryEnvelope(ResultSet resultSet, int i, Connection connection) throws SQLException, IOException {
        return null;
    }

    @Override
    public Geometry decodeGeometryValue(GeometryDescriptor geometryDescriptor, ResultSet resultSet, String s, GeometryFactory geometryFactory, Connection connection, Hints hints) throws IOException, SQLException {
        return null;
    }
}
