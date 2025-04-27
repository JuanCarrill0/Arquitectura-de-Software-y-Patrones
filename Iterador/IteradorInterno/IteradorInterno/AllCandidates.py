from FileUtil import *
from Candidate import *

class AllCandidates():

    def __init__(self):
        self.__data = []
        self.initialize()
        self.__ec = self.__data
        self.__nextCandidate = None
        
    def initialize(self):
        dataLines = []
        util = FileUtil()
        dataLines = util.fileToVector("Candidates.txt")
        for i in dataLines:
            x = i.split(",")
            self.__data.append(Candidate(x[0], x[1], x[2]))

    def hasNext(self):
        matchFound = False
        while (len(self.__ec) > 0):
            tempObj = self.__ec.pop(0)
            self.__nextCandidate = tempObj
            matchFound = True
            break

        return matchFound

    def next(self):
        if (self.__nextCandidate is None):
             raise Exception("No Such Element Exception")
        else:
            return self.__nextCandidate

    def remove(self):
        pass
