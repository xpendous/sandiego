import couchdb
import tweepy
import time
from tweepy import OAuthHandler

# twitter developer authorization needed
ckey = ''
csecret = ''
atoken = ''
asecret = ''


def main():

    auth = OAuthHandler(ckey, csecret)
    auth.set_access_token(atoken, asecret)
    api = tweepy.API(auth)

    # specify the main machine's server address
    couch = couchdb.Server('http://ccteam24:ccteam24@115.146.84.141:5984/')
    try:
        database = couch['train']
    except:
        database = couch.create('train')

    cursor = tweepy.Cursor(api.search, geocode="39.091919,-94.5757195,1000km", since="2015-05-03",
                           until="2015-05-10", lang="en").items()
    while True:
        try:
            tweet = cursor.next()

            database.save(tweet)
        except tweepy.TweepError:
            print 'waiting...'
            time.sleep(60*15)
        except StopIteration:
            break
main()