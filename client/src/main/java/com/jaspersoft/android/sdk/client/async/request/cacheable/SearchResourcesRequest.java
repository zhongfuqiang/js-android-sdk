/*
 * Copyright (C) 2012-2013 Jaspersoft Corporation. All rights reserved.
 * http://community.jaspersoft.com/project/mobile-sdk-android
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Mobile SDK for Android.
 *
 * Jaspersoft Mobile SDK is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Mobile SDK is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Jaspersoft Mobile SDK for Android. If not, see
 * <http://www.gnu.org/licenses/lgpl>.
 */

package com.jaspersoft.android.sdk.client.async.request.cacheable;

import com.jaspersoft.android.sdk.client.JsRestClient;
import com.jaspersoft.android.sdk.client.oxm.ResourcesList;

import java.util.Arrays;
import java.util.List;

/**
 * Gets the list of resource descriptors for the resources available
 * in the folder specified in the URI and matching the specified parameters.
 *
 * @author Ivan Gadzhega
 * @since 1.6
 */
public class SearchResourcesRequest extends CacheableRequest<ResourcesList> {

    private String uri;
    private String query;
    private List<String> types;
    private Boolean recursive;
    private Integer limit;

    /**
     * Creates a new instance of {@link SearchResourcesRequest}.
     *
     * @param uri folder URI (e.g. /reports/samples/)
     * @param query     Match only resources having the specified text in the name or description (can be <code>null</code>)
     * @param type     Match only resources of the given type
     * @param recursive Get resources recursively and not only in the specified URI. Used only when a search criteria
     *                  is specified, either query or type. (can be <code>null</code>)
     * @param limit     Maximum number of items returned to the client. The default is 0 (can be <code>null</code>),
     *                  meaning no limit.
     */
    public SearchResourcesRequest(JsRestClient jsRestClient, String uri, String query, String type,
                                  Boolean recursive, Integer limit) {
        this(jsRestClient, uri, query, Arrays.asList(type), recursive, limit);
    }

    /**
     * Creates a new instance of {@link SearchResourcesRequest}.
     *
     * @param uri folder URI (e.g. /reports/samples/)
     * @param query     Match only resources having the specified text in the name or description (can be <code>null</code>)
     * @param types     Match only resources of the given types
     * @param recursive Get resources recursively and not only in the specified URI. Used only when a search criteria
     *                  is specified, either query or type. (can be <code>null</code>)
     * @param limit     Maximum number of items returned to the client. The default is 0 (can be <code>null</code>),
     *                  meaning no limit.
     */
    public SearchResourcesRequest(JsRestClient jsRestClient, String uri, String query, List<String> types,
                                  Boolean recursive, Integer limit) {
        super(jsRestClient, ResourcesList.class);
        this.uri = uri;
        this.query = query;
        this.types = types;
        this.recursive = recursive;
        this.limit = limit;
    }

    @Override
    public ResourcesList loadDataFromNetwork() throws Exception {
        return getJsRestClient().getResources(uri, query, types, recursive, limit);
    }

    @Override
    protected String createCacheKeyString() {
        return super.createCacheKeyString() + uri + query + types + recursive + limit;
    }

    //---------------------------------------------------------------------
    // Getters & Setters
    //---------------------------------------------------------------------

    public String getUri() {
        return uri;
    }

    public String getQuery() {
        return query;
    }

    public List<String> getTypes() {
        return types;
    }

    public Boolean getRecursive() {
        return recursive;
    }

    public Integer getLimit() {
        return limit;
    }

}
