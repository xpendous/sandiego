__author__ = 'viva'
import simplejson as json
import couchdb
from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener
ckey = ''
csecret =''
atoken = ''
asecret = ''

class Listener(StreamListener):
    def __init__(self):
        self.lisData = []
        self.newTweetsNum = 0

    def on_data(self, data):

        data = json.loads(data)

        self.lisData.append(data)


        if len(self.lisData) == 100:
            # specify the main machine's server address
            couch = couchdb.Server('http://ccteam24:ccteam24@115.146.94.12:5984/')

            try:
                db = couch['sandiegotweets']
            except:
                db = couch.create('sandiegotweets')

            lis = []


            length = len(db)
            if 0 < length <= 1000:
                for dbid in db:
                    lis.append(db[dbid]['id'])
            else:
                for index, dbid in enumerate(db):
                    if index >= length - 1000:
                        lis.append(db[dbid]['id'])

            for data in self.lisData:

                if data['id'] not in lis:
                    db.save(data)
                    self.newTweetsNum += 1

            print 'New added Tweets: ' + str(self.newTweetsNum)
            self.lisData = []
            self.newTweetsNum = 0

    def on_error(self, status_code):
        print(status_code)


def main():

    auth = OAuthHandler(ckey, csecret)
    auth.set_access_token(atoken, asecret)
    stream = Stream(auth, Listener())
    stream.filter(locations=[-117.274378, 32.931812, -116.909082,  33.114890])

main()
