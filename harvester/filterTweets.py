__author__ = 'viva'
import couchdb   

# specify the main machine's server address
server = couchdb.Server('http://ccteam24:ccteam24@115.146.84.141:5984/')
db = server['sandiegotweets']

lis = []
num = 0

for dbid in db:

    num += 1
    print num

    if db[dbid]['id'] not in lis:
        lis.append(db[dbid]['id'])
    else:
        db.delete(db[dbid])