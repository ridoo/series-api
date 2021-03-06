/*
 * Copyright (C) 2013-2019 52°North Initiative for Geospatial Open Source
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
package org.n52.io.response;

import java.util.Map;
import java.util.Objects;

import org.n52.io.Utils;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class ServiceOutput extends ParameterOutput {

    public static final String COLLECTION_PATH = "services";

    public static final String SERVICE_URL = "serviceUrl";
    public static final String VERSION = "version";
    public static final String TYPE = "type";
    public static final String FEATURES = "features";
    public static final String QUANTITIES = "quantities";
    public static final String SUPPORTS_FIRST_LATEST = "supportsFirstLatest";
    public static final String SUPPORTED_MIME_TYPES = "supportedMimeTypes";

    private OptionalOutput<String> serviceUrl;

    private OptionalOutput<String> version;

    private OptionalOutput<String> type;

    private OptionalOutput<Map<String, Object>> features;

    private OptionalOutput<ParameterCount> quantities;

    /**
     * @deprecated since 2.0.0
     */
    @Deprecated
    private OptionalOutput<Boolean> supportsFirstLatest;

    @Override
    protected String getCollectionName() {
        return COLLECTION_PATH;
    }

    public String getServiceUrl() {
        return getIfSerialized(serviceUrl);
    }

    public void setServiceUrl(OptionalOutput<String> serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getVersion() {
        return getIfSerialized(version);
    }

    public void setVersion(OptionalOutput<String> version) {
        this.version = version;
    }

    public String getType() {
        return getIfSerialized(type);
    }

    public void setType(OptionalOutput<String> type) {
        this.type = type;
    }

    // public void setSupportedDatasets(Map<String, Set<String>> mimeTypesByDatasetTypes) {
    // for (Set<String> supportedMimeTypes : mimeTypesByDatasetTypes.values()) {
    // supportedMimeTypes.add(MimeType.APPLICATION_JSON.getMimeType());
    // }
    // addFeature("supportedMimeTypes", mimeTypesByDatasetTypes);
    // }

    @JsonAnyGetter
    public Map<String, Object> getFeatures() {
        return getIfSerializedMap(features);
    }

    public void setFeatures(OptionalOutput<Map<String, Object>> features) {
        this.features = features;
    }

    @Deprecated
    public Boolean isSupportsFirstLatest() {
        return getIfSerialized(supportsFirstLatest);
    }

    /**
     * @param supportsFirstLatest
     *        if first/latest data request are supported
     * @deprecated since 2.0.0, added to {@link #features} instead
     */
    @Deprecated
    public void setSupportsFirstLatest(OptionalOutput<Boolean> supportsFirstLatest) {
        // addFeature(SUPPORTS_FIRST_LATEST, supportsFirstLatest.getValue());
        this.supportsFirstLatest = supportsFirstLatest;
    }

    /**
     * @param quantities
     *        parameter count
     * @deprecated since 2.0.0, added to {@link #features} instead
     */
    @Deprecated
    public void setQuantities(OptionalOutput<ParameterCount> quantities) {
        this.quantities = quantities;
    }

    /**
     * @return the parameter count
     * @deprecated since 2.0.0, added to {@link #features} instead
     */
    @Deprecated
    public ParameterCount getQuantities() {
        return getIfSerialized(quantities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceUrl, version, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ServiceOutput)) {
            return false;
        } else {
            ServiceOutput other = (ServiceOutput) obj;
            return Objects.equals(serviceUrl, other.serviceUrl)
                    && Objects.equals(version, other.version)
                    && Objects.equals(type, other.type)
                    && super.equals(other);
        }
    }

    public static class ParameterCount {

        private String[] selectedPlatformTypes;

        private Integer amountOfferings;

        private Integer amountFeatures;

        private Integer amountProcedures;

        private Integer amountPhenomena;

        private Integer amountCategories;

        private Integer amountPlatforms;

        private DatasetCount amountDatasets;

        /**
         * @deprecated since 2.0.0
         */
        @Deprecated
        private Integer amountStations;

        private Integer amountTimeseries;

        private Integer amountSamplings;

        private Integer amountMeasuringProgram;

        public String[] getSelectedPlatformTypes() {
            return Utils.copy(selectedPlatformTypes);
        }

        public void setSelectedPlatformTypes(String... selectedPlatformTypes) {
            this.selectedPlatformTypes = selectedPlatformTypes;
        }

        public Integer getOfferings() {
            return amountOfferings;
        }

        public void setOfferingsSize(Integer size) {
            this.amountOfferings = size;
        }

        public Integer getFeatures() {
            return amountFeatures;
        }

        public void setFeaturesSize(Integer size) {
            this.amountFeatures = size;
        }

        public Integer getProcedures() {
            return amountProcedures;
        }

        public void setProceduresSize(Integer size) {
            this.amountProcedures = size;
        }

        public Integer getPhenomena() {
            return amountPhenomena;
        }

        public void setPhenomenaSize(Integer size) {
            this.amountPhenomena = size;
        }

        public Integer getCategories() {
            return amountCategories;
        }

        public void setCategoriesSize(Integer size) {
            this.amountCategories = size;
        }

        public Integer getPlatforms() {
            return amountPlatforms;
        }

        public void setPlatformsSize(Integer size) {
            this.amountPlatforms = size;
        }

        public DatasetCount getDatasets() {
            return amountDatasets;
        }

        public void setDatasets(DatasetCount datasets) {
            this.amountDatasets = datasets;
        }

        @Deprecated
        public Integer getStations() {
            return amountStations;
        }

        @Deprecated
        public void setStationsSize(int size) {
            this.amountStations = size;
        }

        public void setTimeseriesSize(Integer countTimeseries) {
            this.amountTimeseries = countTimeseries;
        }

        public Integer getTimeseries() {
            return this.amountTimeseries;
        }

        public void setSamplingsSize(Integer countSamplings) {
            this.amountSamplings = countSamplings;
        }

        public Integer getSamplings() {
            return this.amountSamplings;
        }

        public void setMeasuringProgramsSize(int countMeasuringPrograms) {
            this.amountMeasuringProgram = countMeasuringPrograms;
        }

        public Integer getMeasuringPrograms() {
            return this.amountMeasuringProgram;
        }
    }

    public static class DatasetCount {

        private Integer totalAmount;

        private Integer amountIndividualObservations;

        private Integer amountTimeseries;

        private Integer amountProfiles;

        private Integer amountTrajectories;

        public Integer getTotal() {
            return totalAmount;
        }

        public void setTotalAmount(Integer totalAmount) {
            this.totalAmount = totalAmount;
        }

        public Integer getIndividualObservations() {
            return amountIndividualObservations;
        }

        public void setAmountIndividualObservations(Integer amountIndividualObservations) {
            this.amountIndividualObservations = amountIndividualObservations;
        }

        public Integer getTimeseries() {
            return amountTimeseries;
        }

        public void setAmountTimeseries(Integer amountTimeseries) {
            this.amountTimeseries = amountTimeseries;
        }

        public Integer getProfiles() {
            return amountProfiles;
        }

        public void setAmountProfiles(Integer amountProfiles) {
            this.amountProfiles = amountProfiles;
        }

        public Integer getTrajectories() {
            return amountTrajectories;
        }

        public void setAmountTrajectories(Integer amountTrajectories) {
            this.amountTrajectories = amountTrajectories;
        }

    }

}
