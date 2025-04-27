
class CertifiedCandidates():

    def __init__(self, inp_ac, certType):
        self.__ac = inp_ac
        self.__certificationType = certType
        self.__ec = inp_ac.getAllCandidates()
        
        self.__nextCandidate = None

    def hasNext(self):
        matchFound = False
        while (len(self.__ec) > 0):
            tempObj = self.__ec.pop(0)
            if (tempObj.getCertificationType() == self.__certificationType):
               matchFound = True
               self.__nextCandidate = tempObj
               break

        if (matchFound is False):
            self.__nextCandidate = None

        return matchFound

    def next(self):
        if (self.__nextCandidate is None):
             raise Exception("No Such Element Exception")
        else:
            return self.__nextCandidate

    def remove(self):
        pass
