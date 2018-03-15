ANTLR = $(HOME)/bin/antlr-4.7-complete.jar
CP = -cp $(ANTLR):.

all:
	java -jar $(ANTLR) -no-visitor -no-listener *.g4
	javac $(CP) *.java

run:
	java $(CP) CalcParser samples/1.calc
	java $(CP) CalcParser samples/2.calc
	java $(CP) CalcParser samples/3.calc

clean:
	rm -f Calc*.java *.tokens *.class

