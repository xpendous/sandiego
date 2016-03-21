
#!/usr/bin/python
import couchdb
from geopy.geocoders import Nominatim
from geopy.exc import GeocoderTimedOut
from geopy.exc import GeocoderServiceError

couch = couchdb.Server('http://127.0.0.1:5984/')
db = couch['sandiegotweets']
langdb = couch.create('lang_sub_final')
geolocator = Nominatim()

sublang = dict()

def getSub(coordinates, lang, sublang, user_id):
    try:
        location = geolocator.reverse(coordinates, timeout=100);#get address according to the coordinates by Geopy
        if location.raw['address'].has_key('suburb'):
              sub=location.raw['address']['suburb'];

              doc = {'state':location.raw['address']['state'], 'suburb': sub, 'lang': lang, 'user_id':user_id}
              langdb.save(doc)


    except GeocoderTimedOut as e:
        print "Error: geocode failed"
    except GeocoderServiceError as e:
        print "Error: geocode failed"

i=0;
for id in db:
    i+=1;
    if(i>-1):
       # print "processed: " + str(i);
        if db[id]['lang']:
            if db[id]['geo']:
                getSub(db[id]['geo']['coordinates'],db[id]['lang'],sublang,db[id]['user']['id']);




