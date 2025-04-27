
class Candidate():

    def __init__(self, inp_name, inp_certType, loc):
        self.__name = inp_name
        self.__certificationType = inp_certType
        self.__location = loc

    def getCertificationType(self):
        return self.__certificationType

    def getName(self):
        return self.__name

    def getLocation(self):
        return self.__location
