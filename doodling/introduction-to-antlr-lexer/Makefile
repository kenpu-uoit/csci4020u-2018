ANTLR=../antlr-4.7.1-complete.jar

all:
	java -jar $(ANTLR) Hello.g4
	javac -cp $(PWD):$(ANTLR) Main.java Hello.java

run:
	java -cp $(PWD):$(ANTLR) Main test.txt

clean:
	rm Hello.java *.class *.tokens *.interp

