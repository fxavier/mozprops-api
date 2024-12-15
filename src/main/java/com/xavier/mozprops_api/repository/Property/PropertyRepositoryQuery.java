package com.xavier.mozprops_api.repository.Property;

import java.util.List;

import com.xavier.mozprops_api.models.Property;
import com.xavier.mozprops_api.repository.filter.PropertyFilter;

public interface PropertyRepositoryQuery {
    public List<Property> filter(PropertyFilter propertyFilter);
}
