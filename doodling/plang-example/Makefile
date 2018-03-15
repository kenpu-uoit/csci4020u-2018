ANTLR = $(HOME)/bin/antlr-4.7-complete.jar
CP = -cp $(ANTLR):.
GRAMMAR = PL

all:
	java -jar $(ANTLR) -no-visitor -no-listener *.g4
	javac $(CP) *.java

run:
	java $(CP) Main samples/1.calc
	java $(CP) Main samples/2.calc
	java $(CP) Main samples/3.calc

clean:
	rm -f $(GRAMMAR)*.java *.tokens *.class

