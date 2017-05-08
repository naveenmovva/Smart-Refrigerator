
import serial
import datetime
import struct
import thread
import weigh
import os
import picamera
from pymongo import MongoClient
s = 0
temp=0
previousReading=0
presentReading=0
#def vid() :
#       raspivid -o - -t 0 -vf -hf -fps 30 -b 6000000 | avconv -re -ar 44100 -ac 2 -acodec pcm_s16le -f s16le -ac 2 -i /dev/zero -f h264 -i - -vcodec copy -acodec aac -ab 128k -g 50 -stric$
camera=picamera.PiCamera()
#thread_vid=Thread(target=vid)
#thread_vid.start()
while True:
        #x=weigh.getweight();
        avg=0
        s=0
#        for i in range(20):
#               s=s+int(weigh.getweight())
#       avg=s/20
#       avg1=avg*0.217
#       print avg1
        presentReading=int(weigh.getweight())
        if abs(presentReading-previousReading)>50 :
                #print "if"
                print presentReading
                camera.capture('cam.jpg')
                os.system('raspistill -o cam.jpg')
                #os.system('mpack -s subject cam.jpg naveenmovva225@gmail.com')
                if presentReading<10 :
                        os.system('echo "weight is less" | mail -s "from ref" naveenmovva225@gmail.com')
                        #thread_img=Thread(target=img)
                        #thread_img.start()
                client = MongoClient('mongodb://naveenmovva:naveenmovva@ds119081.mlab.com:19081/deepdata')

                #data base name : 'test-database-1'
                mydb = client['deepdata']
                myrecord = {
                        "date" : datetime.datetime.utcnow(),

                        "weightValue":presentReading,
#       avg=s/20
#       avg1=avg*0.217
#       print avg1
        presentReading=int(weigh.getweight())
        if abs(presentReading-previousReading)>50 :
                #print "if"
                print presentReading
                camera.capture('cam.jpg')
                os.system('raspistill -o cam.jpg')
                #os.system('mpack -s subject cam.jpg naveenmovva225@gmail.com')
                if presentReading<10 :
                        os.system('echo "weight is less" | mail -s "from ref" naveenmovva225@gmail.com')
                        #thread_img=Thread(target=img)
                        #thread_img.start()
                client = MongoClient('mongodb://naveenmovva:naveenmovva@ds119081.mlab.com:19081/deepdata')

                #data base name : 'test-database-1'
                mydb = client['deepdata']
                myrecord = {
                        "date" : datetime.datetime.utcnow(),

                        "weightValue":presentReading,
                        "type":"vegetables"
                }
                record_id = mydb.deepdata.insert(myrecord)
        previousReading=presentReading

