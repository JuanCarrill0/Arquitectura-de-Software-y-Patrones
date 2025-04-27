from FileUtil import *
from Candidate import *
from CertifiedCandidates import *

class AllCandidates():

    def __init__(self):
        self.__data = []
        self.initialize()        

    def initialize(self):
        dataLines = []
        util = FileUtil()
        dataLines = util.fileToVector("Candidates.txt")
        for i in dataLines:
            x = i.split(",")
            self.__data.append(Candidate(x[0], x[1], x[2]))
        
    def getAllCandidates(self):
        return self.__data

    def getCertifiedCandidates(self, strType):
        return CertifiedCandidates(self, strType)                    

