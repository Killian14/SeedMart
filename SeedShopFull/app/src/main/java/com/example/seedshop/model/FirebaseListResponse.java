package com.example.seedshop.model;

import java.util.List;

public class FirebaseListResponse {
    public List<FirebaseDocument> documents;
}

// model/FirebaseDocument.java
package com.example.seedshop.model;

        import java.util.Map;

public class FirebaseDocument {
    public String name;    // full path, e.g. ".../seeds/{docId}"
    public Map<String, ValueWrapper> fields;

    public static class ValueWrapper {
        public String stringValue;
        public double doubleValue;
        // add other types if you need them
    }
}