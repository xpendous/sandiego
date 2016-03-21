### author: Yufei Yi
###	usage: this script is to build a sentiment judgement model base on a sentiment API using Naive Bayes Classifier Model.

import couchdb
import unirest
from textblob.classifiers import NaiveBayesClassifier
from sklearn.externals import joblib
import cPickle as pickle
output = open('model.pkl', 'wb')

count = 1

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
	global count
	print(count)
	count+=1
	return response.body['result']['sentiment']

couch = couchdb.Server()
db = couch['sandiegotweets']

train_data = [(db1[id]['text'].encode('ascii','ignore'),get_response(db1[id]['text'].encode('ascii','ignore'))) for id in db]

joblib.dump(train_data,'train_data.pkl') #store the training data (including the results of Sentiment API)
#train_data = joblib.load('train_data.pkl')#load the training data (including the results of Sentiment API)

classifier = NaiveBayesClassifier(train_data) #classify the training data

print(classifier.classify("Their burgers are amazing."))
print(classifier.classify("you look so great today."))
print(classifier.classify("this place is so beautiful"))

pickle.dump(classifier, output) #store the model (for next time usage)





