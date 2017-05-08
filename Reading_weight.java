
import struct
def getweight():
        ser = serial.Serial('/dev/ttyACM1',9600)



        while True:

                read_serial=ser.readline()
                head=read_serial[0:2]
               # print(read_serial[0:2])
                data=read_serial[2:]
                if(head=="FF"):
                        #print(str(int(str(data),16)))
                        return str(int(str(data),16))
#print(getweight())

