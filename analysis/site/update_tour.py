import couchdb
import unirest

couch = couchdb.Server('http://127.0.0.1:5984/')
db = couch['sandiegotweets']
tourdb = couch.create('tour_att') #couch['tour_att']

def get_response(string): 
  response = unirest.post("https://community-sentiment.p.mashape.com/text/",
    headers={
      "X-Mashape-Key": "zYLExHmQTGmshsADPuQ2pHJZxyd3p1La1XNjsnCr1Pialvj71e",
      "Content-Type": "application/x-www-form-urlencoded",
      "Accept": "application/json"
    },
    params={
      "txt": string
    }
  )
  return response.body['result']['sentiment']

retrieve_mapfn = """

  function(doc) {
    var str = doc.text.toLowerCase()
    if(str.search("balboa park")>0 || str.search("balboapark")>0){
      emit("balboa", str);
    }
    if(str.search("sunset cliffs")>0 || str.search("sunsetcliffs")>0){
      emit("suncliffs", str);
    }
    if(str.search("san diego zoo")>0 || str.search("sandiegozoo")>0){
      emit("zoo", str);
    }
    if(str.search("lajolla cove")>0 || str.search("lajollacove")>0){
      emit("lajollacove", str);
    }
    if(str.search("sea world")>0 || str.search("seaworld")>0){
      emit("seaworld", str);
    }
    if(str.search("midway museum")>0){
      emit("midwaymuseum", str);
    }
    if(str.search("shield island")>0 || str.search("shieldisland")>0){
      emit("shieldisland", str);
    }
    if(str.search("carlsbad flower")>0 || str.search("carlsbadflower")>0){
      emit("carlsbad", str);
    }
    if(str.search("state historic park")>0){
      emit("historicpark", str);
    }
 }
"""

#if(str.search("balboa park")>0 || str.search("balboapark")>0)
design = { 'views': {
           'get_tweets': {
              'map': retrieve_mapfn
            }
         } }
db["_design/tour"] = design


tour_list = db.view('tour/get_tweets');

for r in tour_list :
    attitude = get_response(r.value)
    print r.key + "  "+attitude
    doc = {'attitude':attitude, 'place': r.key}
    tourdb.save(doc)

#print len(suncliffs_list)

mapfun = """function(doc) {
  stat={'p':0,'neu':0,'neg':0};
  if(doc.attitude=='Positive'){
   stat.p=1;
  }else if(doc.attitude=='Neutral'){
   stat.neu=1;
  }else if(doc.attitude=='Negative'){
   stat.neg=1;
  }
   emit(doc.place, stat);
}"""

reducefun = """ function(keys, values, rereduce) {
  var stat={'p':0,'neu':0,'neg':0};
   for(var j=0;j<values.length; j++){
      stat.p+=values[j].p;
      stat.neu+=values[j].neu;
      stat.neg+=values[j].neg;
   }
   return stat;
}  """
design2 = { 'views': {
           'get_tweets': {
              'map': mapfun,
              'reduce':reducefun
            }
         } }
tourdb["_design/tour_att"] = design2