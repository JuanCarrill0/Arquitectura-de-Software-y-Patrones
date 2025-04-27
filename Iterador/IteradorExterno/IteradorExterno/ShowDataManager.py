# This example was elaborated from Partha Kuchana's example of his book "Software Architecture Design Patterns in Java" by Daniel Hernández and
# adapted by Henry Alberto Diosa
from tkinter import ttk
from tkinter import *
from AllCandidates import *

class SearchManager (Toplevel):
    
    GET_CANDIDATES = "Retrieve"
    EXIT = "Exit"

    def __init__(self,master):
        
        Toplevel.__init__(self,master)
        self.geometry("400x400")
        self.minsize(height=380, width=520)

        self.__cmbCertificationType = ttk.Combobox(self, state="readonly")
        self.__cmbCertificationType["values"] = ["Sun MicroSystems", "Microsoft", "IBM"]
        self.__cmbCertificationType.current(0)
        
        self.__lblCertificationType = Label(self,text="Certification Type:")
        self.__lblSelectedCandidates = Label(self,text="Results:")
        self.__txtSelectedCandidates = Text(self,height=15, width=40)

        self.__retrieveButton = Button(self,text=SearchManager.GET_CANDIDATES)
        self.__exitButton = Button(self,text=SearchManager.EXIT)

        self.__lblCertificationType.place(x=20, y=20)
        self.__lblSelectedCandidates.place(x=20, y=150)
        self.__cmbCertificationType.place(x=150, y=20)
        self.__txtSelectedCandidates.place(x=150, y=60)
        self.__retrieveButton.place(x=150,y=320)
        self.__exitButton.place(x=290,y=320)

    def getCertificationType(self):
        return self.__cmbCertificationType.get()

    def setSelectedCandidates(self, selectedCandidates):
        self.__txtSelectedCandidates.delete("1.0","end")
        return self.__txtSelectedCandidates.insert(INSERT, selectedCandidates)
    
    def getRetrieveButton(self):
        return self.__retrieveButton
        
    def getExitButton(self):
        return self.__exitButton
        
#End of class

class ButtonHandler():
    
    def __init__(self,root):
      self.__root=root
      self.__frame=SearchManager(root)
      self.__frame.getRetrieveButton().configure(command=self.eventRetrieve)
      self.__frame.getExitButton().configure(command=self.eventExit)
        
    def eventRetrieve(self):
      selection = self.__frame.getCertificationType()
      ac = AllCandidates()
      certCandidates = ac.getCertifiedCandidates(selection)
      selectedCandidates = "Name --- Cert Type --- Location" + "\n" + "--------------------------------------"
      while (certCandidates.hasNext()):
          c = certCandidates.next()
          selectedCandidates = selectedCandidates + "\n" + c.getName() + " - " + c.getCertificationType() + " - " + c.getLocation()

      self.__frame.setSelectedCandidates(selectedCandidates)
      
    def eventExit(self):
      self.__frame.destroy()
      self.__root.destroy()
#End of class

def main():
    root = Tk()
    root.withdraw()    
    root.title("Iterator Pattern - Example")
    app = ButtonHandler(root)    
    root.mainloop()

if __name__=="__main__":
    main()



      
    
    
   
