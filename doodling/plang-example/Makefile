ANTLR = $(HOME)/bin/antlr-4.7-complete.jar
CP = -cp $(ANTLR):.
GRAMMAR = PL

all:
	java -jar $(ANTLR) -no-visitor -no-listener *.g4
	javac $(CP) *.java

run1:
	java $(CP) Main samples/1.calc
run2:
	java $(CP) Main samples/2.calc
run3:
	java $(CP) Main samples/3.calc
run4:
	java $(CP) Main samples/4.calc
run5:
	java $(CP) Main samples/5.calc
run6:
	java $(CP) Main samples/6.calc
run7:
	java $(CP) Main samples/7.calc

run: run1 run2 run3 run4 run5 run6 run7

clean:
	rm -f $(GRAMMAR)*.java *.tokens *.class

