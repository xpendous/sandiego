
#!/usr/bin/python
import couchdb

couch = couchdb.Server('http://127.0.0.1:5984/')
db = couch['lang_sub_final']

retrieve_mapfn = """
  var id_array=[];
  function(doc) {
    var stats = { user: 0, tweet: 1, re: 0};
    if(id_array.indexOf(doc.user_id+doc.lang+doc.suburb) < 0){
        id_array[id_array.length]=doc.user_id+doc.lang+doc.suburb;
        stats.user=1;
    }
    emit([doc.suburb,doc.lang,doc.user_id], stats);
}

"""
reduce_fn ="""
function(keys, values, rereduce) {
  var stats = { user: 0, tweet: 0, re: 0};
  if(!rereduce){
    for(var i=0; i < values.length; i++) {
      stats.tweet+=1;
      stats.user+=values[i].user;
       
    }
    return stats;
  }else{
   
   for(var i=0;i<values.length; i++){
      if(stats.re=0){stats.user=1;}
      else{stats.user+=values[i].user;}
      stats.tweet+=values[i].tweet;
   }
   stats.re+=1;
   return stats;
   }
}

"""


design = { 'views': {
              'get_tweets': {
                'map': retrieve_mapfn,
                'reduce': reduce_fn
              }
            }
}
db["_design/sub_lang"] = design


suncliffs_list = db.view('sub_lang/get_tweets', group_level=2).rows;

for r in suncliffs_list :
    
    print str(r["key"][0].encode('utf-8'))+"  "+str(r["key"][1])+ ": " +str(r["value"])
