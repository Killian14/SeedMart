List<Seed> seeds = new ArrayList<>();
        for (FirebaseDocument doc : response.body().documents) {
        String docId = doc.name.substring(doc.name.lastIndexOf('/') + 1);
        Seed s = new Seed();
        s.id = Integer.parseInt(docId);      // or keep docId as String
        s.name = doc.fields.get("name").stringValue;
        s.description = doc.fields.get("description").stringValue;
        s.price = doc.fields.get("price").doubleValue;
        seeds.add(s);
        }