package uk.co.rightmove.propertyanalyser.util;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Optional;

/**
 * Util class to help parse the raw JSON data and map it into a JSONLoader.
 */
public final class JSONLoader {

    private JSONLoader() {
    }

    /**
     * Utility method to loads raw JSON from resource provided into a JSONObject.
     *
     * @param resourceName String (i.e. the data-set)
     * @return a JSONobject object
     * @throws IOException exception
     */
    public static JSONObject loadJsonFromFile(String resourceName) throws IOException {

        return Optional.of(new JSONObject(Resources.toString(Resources.getResource(resourceName),
                Charsets.UTF_8))).orElse(null);
    }
}
