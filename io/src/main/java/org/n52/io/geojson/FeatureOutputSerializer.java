/*
 * Copyright (C) 2013-2017 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public License
 * version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */
package org.n52.io.geojson;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vividsolutions.jts.geom.Geometry;

public class FeatureOutputSerializer extends JsonSerializer<GeoJSONFeature> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureOutputSerializer.class);

    @Override
    public void serialize(GeoJSONFeature value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if (value.isSetGeometry()) {
            writeFeature(value, gen);
        } else {
            writeGeometryLessFeature(value, gen);
        }
    }

    private void writeFeature(GeoJSONFeature value, JsonGenerator gen) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("type", "Feature");
        gen.writeStringField("id", value.getId());
        gen.writeObjectField("properties", value.getProperties());
        gen.writeObjectField("geometry", encodeGeometry(value));
        gen.writeEndObject();
    }

    private void writeGeometryLessFeature(GeoJSONFeature value, JsonGenerator gen) throws IOException {
        gen.writeStartObject();
        writeMap(value.getProperties(), gen);
        gen.writeEndObject();
    }

    private void writeMap(Map<String, Object> map, JsonGenerator gen) throws IOException {
        for (Entry<String, Object> entry : map.entrySet()) {
            gen.writeObjectField(entry.getKey(), entry.getValue());
        }
    }

    private Object encodeGeometry(GeoJSONFeature value) {
        try {
            final GeoJSONEncoder enc = new GeoJSONEncoder();
            final Geometry geometry = value.getGeometry();
            return enc.encodeGeometry(geometry);
        } catch (GeoJSONException e) {
            LOGGER.error("could not properly encode geometry.", e);
            return null;
        }
    }

}