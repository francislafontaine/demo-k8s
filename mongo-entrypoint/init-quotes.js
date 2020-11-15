print('===============JAVASCRIPT===============');
print('Count of rows in test collection: ' + db.quote.count());

db.quote.insert( { _id: "1", quoteLine: "Première pensée" } )
db.quote.insert( { _id: "2", quoteLine: "Deuxième pensée" } )
db.quote.insert( { _id: "3", quoteLine: "Troisème pensée" } )

print('Count of rows in test collection: ' + db.quote.count());