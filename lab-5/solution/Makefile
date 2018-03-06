GRAMMAR = L5
ANTLR = $(HOME)/bin/antlr-4.7-complete.jar
JAVA = java -cp $(ANTLR):.
JAVAC = javac -cp $(ANTLR):.

all:
	java -jar $(ANTLR) $(GRAMMAR).g4
	$(JAVAC) *.java

test:
	$(JAVA) App test.txt

clean:
	rm -f *.tokens *.class $(GRAMMAR)*.java

