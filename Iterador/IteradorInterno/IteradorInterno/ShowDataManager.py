# "Builder" example.
# This example was elaborated from Partha Kuchana's example of his book "Software Architecture Design Patterns in Java" by Daniel Hernández and
# adapted by Henry Alberto Diosa
from tkinter import ttk
from tkinter import *
from AllCandidates import *

class SearchManager (Toplevel):
    
    SHOW_ALL = "Show All"
    EXIT = "Exit"

    def __init__(self,master):
        
        Toplevel.__init__(self,master)
        self.geometry("300x300")
        self.minsize(height=360, width=360)

        self.__lblSelectedCandidates = Label(self,text="List:")
        self.__taSelectedCandidates = Text(self,height=15, width=40)

        self.__showButton = Button(self,text=SearchManager.SHOW_ALL)
        self.__exitButton = Button(self,text=SearchManager.EXIT)

        self.__lblSelectedCandidates.place(x=30, y=20)
        self.__taSelectedCandidates.place(x=20, y=60)
        self.__showButton.place(x=40,y=320)
        self.__exitButton.place(x=150,y=320)


    def setSelectedCandidates(self, selectedCandidates):
        self.__taSelectedCandidates.delete("1.0","end")
        return self.__taSelectedCandidates.insert(INSERT, selectedCandidates)
    
    def getShowButton(self):
        return self.__showButton
        
    def getExitButton(self):
        return self.__exitButton

class ButtonHandler():
    
    def __init__(self,root):
      self.__root=root
      self.__frame=SearchManager(root)
      self.__frame.getShowButton().configure(command=self.eventRetrieve)
      self.__frame.getExitButton().configure(command=self.eventExit)
        
    def eventRetrieve(self):
      ac = AllCandidates()
      selectedCandidates = "Name --- Cert Type --- Location" + "\n" + "--------------------------------------"
      
      while (ac.hasNext()):
          c = ac.next()
          selectedCandidates = selectedCandidates + "\n" + c.getName() + " - " + c.getCertificationType() + " - " + c.getLocation()

      self.__frame.setSelectedCandidates(selectedCandidates)
      
    def eventExit(self):
      self.__frame.destroy()
      self.__root.destroy()

                

if __name__=="__main__":
    root = Tk()
    root.withdraw()    
    root.title("Iterator Pattern - Example")
    app = ButtonHandler(root)    
    root.mainloop()                        


      
    
    
   
